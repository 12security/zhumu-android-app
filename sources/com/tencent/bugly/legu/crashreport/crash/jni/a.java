package com.tencent.bugly.legu.crashreport.crash.jni;

import android.content.Context;
import com.tencent.bugly.legu.crashreport.common.info.AppInfo;
import com.tencent.bugly.legu.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.legu.crashreport.crash.b;
import com.tencent.bugly.legu.crashreport.crash.c;
import com.tencent.bugly.legu.proguard.w;
import com.tencent.bugly.legu.proguard.x;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: BUGLY */
public final class a implements NativeExceptionHandler {
    private final Context a;
    private final b b;
    private final com.tencent.bugly.legu.crashreport.common.info.a c;
    private final com.tencent.bugly.legu.crashreport.common.strategy.a d;
    private final String e;

    public a(Context context, com.tencent.bugly.legu.crashreport.common.info.a aVar, b bVar, com.tencent.bugly.legu.crashreport.common.strategy.a aVar2, String str) {
        this.a = context;
        this.b = bVar;
        this.c = aVar;
        this.d = aVar2;
        this.e = str;
    }

    public final CrashDetailBean packageCrashDatas(String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, String str8, String str9, byte[] bArr, Map<String, String> map, boolean z) {
        boolean l = c.a().l();
        String str10 = l ? " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]" : "";
        if (l) {
            w.e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
        }
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.b = 1;
        crashDetailBean.e = this.c.g();
        crashDetailBean.f = this.c.i;
        crashDetailBean.g = this.c.t();
        crashDetailBean.m = this.c.f();
        crashDetailBean.n = str3;
        crashDetailBean.o = str10;
        crashDetailBean.p = str4;
        crashDetailBean.q = str5;
        crashDetailBean.r = j;
        crashDetailBean.u = com.tencent.bugly.legu.proguard.a.b(crashDetailBean.q.getBytes());
        crashDetailBean.z = str;
        crashDetailBean.A = str2;
        crashDetailBean.H = this.c.v();
        crashDetailBean.h = this.c.s();
        crashDetailBean.i = this.c.E();
        crashDetailBean.v = str8;
        crashDetailBean.I = str7;
        crashDetailBean.J = str6;
        crashDetailBean.K = str9;
        crashDetailBean.E = this.c.o();
        crashDetailBean.F = this.c.n();
        crashDetailBean.G = this.c.p();
        if (z) {
            crashDetailBean.B = com.tencent.bugly.legu.proguard.a.i();
            crashDetailBean.C = com.tencent.bugly.legu.proguard.a.g();
            crashDetailBean.D = com.tencent.bugly.legu.proguard.a.k();
            crashDetailBean.w = com.tencent.bugly.legu.proguard.a.a(this.a, c.d, (String) null);
            crashDetailBean.x = x.a(true);
            crashDetailBean.L = this.c.a;
            crashDetailBean.M = this.c.n;
            crashDetailBean.O = this.c.B();
            crashDetailBean.P = this.c.C();
            crashDetailBean.Q = this.c.w();
            crashDetailBean.R = this.c.A();
            crashDetailBean.y = com.tencent.bugly.legu.proguard.a.a(c.e, false);
            String str11 = "java:\n";
            if (crashDetailBean.q != null) {
                int indexOf = crashDetailBean.q.indexOf(str11);
                if (indexOf > 0) {
                    int length = indexOf + str11.length();
                    String substring = crashDetailBean.q.substring(length, crashDetailBean.q.length() - 1);
                    if (substring.length() > 0 && crashDetailBean.y.containsKey(crashDetailBean.A)) {
                        String str12 = (String) crashDetailBean.y.get(crashDetailBean.A);
                        int indexOf2 = str12.indexOf(substring);
                        if (indexOf2 > 0) {
                            String substring2 = str12.substring(indexOf2);
                            crashDetailBean.y.put(crashDetailBean.A, substring2);
                            crashDetailBean.q = crashDetailBean.q.substring(0, length);
                            crashDetailBean.q += substring2;
                        }
                    }
                }
            }
            if (str == null) {
                crashDetailBean.z = this.c.d;
            }
            this.b.b(crashDetailBean);
        } else {
            crashDetailBean.B = -1;
            crashDetailBean.C = -1;
            crashDetailBean.D = -1;
            crashDetailBean.w = "this crash is occurred at last process! Log is miss, when get an terrible ABRT Native Exception etc.";
            crashDetailBean.L = -1;
            crashDetailBean.O = -1;
            crashDetailBean.P = -1;
            crashDetailBean.Q = map;
            crashDetailBean.R = null;
            crashDetailBean.y = null;
            if (str == null) {
                crashDetailBean.z = "unknown(record)";
            }
            if (bArr == null) {
                crashDetailBean.x = "this crash is occurred at last process! Log is miss, when get an terrible ABRT Native Exception etc.".getBytes();
            } else {
                crashDetailBean.x = bArr;
            }
        }
        return crashDetailBean;
    }

    public final void handleNativeException(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7) {
        w.a("Native Crash Happen v1", new Object[0]);
        handleNativeException2(i, i2, j, j2, str, str2, str3, str4, i3, str5, i4, i5, i6, str6, str7, null);
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    public final void handleNativeException2(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7, String[] strArr) {
        String str8;
        String str9;
        w.a("Native Crash Happen v2", new Object[0]);
        try {
            this.d.c();
            if (!this.d.b()) {
                w.e("waiting for remote sync", new Object[0]);
                int i7 = 0;
                while (!this.d.b()) {
                    Thread.sleep(500);
                    i7 += 500;
                    if (i7 >= 5000) {
                        break;
                    }
                }
            }
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
                return;
            }
            return;
        }
        long j3 = (1000 * j) + (j2 / 1000);
        String a2 = b.a(str3);
        String str10 = "UNKNOWN(" + i4 + ")";
        if (i3 > 0) {
            str8 = "KERNEL";
            str9 = str + "(" + str5 + ")";
        } else if (i4 > 0) {
            Context context = this.a;
            str10 = AppInfo.a(i4);
            str8 = str5;
            str9 = str;
        } else {
            str8 = str5;
            str9 = str;
        }
        if (!this.d.b()) {
            w.d("no remote but still store!", new Object[0]);
        }
        if (this.d.c().d || !this.d.b()) {
            String str11 = null;
            String str12 = null;
            if (strArr != null) {
                HashMap hashMap = new HashMap();
                for (String str13 : strArr) {
                    String[] split = str13.split("=");
                    if (split.length == 2) {
                        hashMap.put(split[0], split[1]);
                    } else {
                        w.d("bad extraMsg %s", str13);
                    }
                }
                str12 = (String) hashMap.get("ExceptionThreadName");
                str11 = (String) hashMap.get("ExceptionProcessName");
            } else {
                w.c("not found extraMsg", new Object[0]);
            }
            if (str11 == null || str11.length() == 0) {
                str11 = this.c.d;
            } else {
                w.c("crash process name change to %s", str11);
            }
            if (str12 != null && str12.length() != 0) {
                w.c("crash thread name change to %s", str12);
                Iterator it = Thread.getAllStackTraces().keySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Thread thread = (Thread) it.next();
                    if (thread.getName().equals(str12)) {
                        str12 = str12 + "(" + thread.getId() + ")";
                        break;
                    }
                }
            } else {
                Thread currentThread = Thread.currentThread();
                str12 = currentThread.getName() + "(" + currentThread.getId() + ")";
            }
            CrashDetailBean packageCrashDatas = packageCrashDatas(str11, str12, j3, str9, str2, a2, str8, str10, str4, str7, null, null, true);
            if (packageCrashDatas == null) {
                w.e("pkg crash datas fail!", new Object[0]);
                return;
            }
            b.a("NATIVE_CRASH", com.tencent.bugly.legu.proguard.a.n(), this.c.d, Thread.currentThread(), str9 + "\n" + str2 + "\n" + a2, packageCrashDatas);
            if (!this.b.a(packageCrashDatas, i3)) {
                this.b.a(packageCrashDatas, 5000, true);
            }
            b.b(this.e);
            return;
        }
        w.e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
        b.a("NATIVE_CRASH", com.tencent.bugly.legu.proguard.a.n(), this.c.d, Thread.currentThread(), str9 + "\n" + str2 + "\n" + a2, null);
        if (str4 != null) {
            File file = new File(str4);
            if (file.isFile() && file.exists() && file.canWrite()) {
                file.delete();
            }
        }
    }
}
