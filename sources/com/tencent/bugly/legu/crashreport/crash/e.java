package com.tencent.bugly.legu.crashreport.crash;

import android.content.Context;
import android.os.Process;
import com.tencent.bugly.legu.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.legu.crashreport.common.strategy.a;
import com.tencent.bugly.legu.proguard.w;
import com.tencent.bugly.legu.proguard.x;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashMap;

/* compiled from: BUGLY */
public final class e implements UncaughtExceptionHandler {
    private static UncaughtExceptionHandler f;
    private static boolean h;
    private static final Object i = new Object();
    private static int j;
    private Context a;
    private b b;
    private a c;
    private com.tencent.bugly.legu.crashreport.common.info.a d;
    private UncaughtExceptionHandler e;
    private boolean g = false;

    public e(Context context, b bVar, a aVar, com.tencent.bugly.legu.crashreport.common.info.a aVar2) {
        this.a = context;
        this.b = bVar;
        this.c = aVar;
        this.d = aVar2;
    }

    public final synchronized void a() {
        if (j >= 10) {
            w.a("java crash handler over %d, no need set.", Integer.valueOf(10));
        } else {
            UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            if (defaultUncaughtExceptionHandler != null && !getClass().getName().equals(defaultUncaughtExceptionHandler.getClass().getName())) {
                if ("com.android.internal.os.RuntimeInit$UncaughtHandler".equals(defaultUncaughtExceptionHandler.getClass().getName())) {
                    w.a("backup system java handler: %s", defaultUncaughtExceptionHandler.toString());
                    f = defaultUncaughtExceptionHandler;
                    this.e = defaultUncaughtExceptionHandler;
                } else {
                    w.a("backup java handler: %s", defaultUncaughtExceptionHandler.toString());
                    this.e = defaultUncaughtExceptionHandler;
                }
                a(defaultUncaughtExceptionHandler);
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.g = true;
                j++;
                w.a("registered java monitor: %s", toString());
            }
        }
    }

    public final synchronized void b() {
        this.g = false;
        w.a("close java monitor!", new Object[0]);
        if (Thread.getDefaultUncaughtExceptionHandler().getClass().getName().contains("bugly")) {
            w.a("Java monitor to unregister: %s", toString());
            Thread.setDefaultUncaughtExceptionHandler(this.e);
            j--;
        }
    }

    private synchronized void a(UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.e = uncaughtExceptionHandler;
    }

    private CrashDetailBean b(Thread thread, Throwable th, boolean z, String str, byte[] bArr) {
        String a2;
        boolean l = c.a().l();
        String str2 = (!l || !z) ? "" : " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]";
        if (l && z) {
            w.e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
        }
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        w.e("3", new Object[0]);
        crashDetailBean.B = com.tencent.bugly.legu.proguard.a.i();
        crashDetailBean.C = com.tencent.bugly.legu.proguard.a.g();
        crashDetailBean.D = com.tencent.bugly.legu.proguard.a.k();
        crashDetailBean.E = this.d.o();
        crashDetailBean.F = this.d.n();
        crashDetailBean.G = this.d.p();
        crashDetailBean.w = com.tencent.bugly.legu.proguard.a.a(this.a, c.d, (String) null);
        crashDetailBean.x = x.a(z);
        String str3 = "user log size:%d";
        Object[] objArr = new Object[1];
        objArr[0] = Integer.valueOf(crashDetailBean.x == null ? 0 : crashDetailBean.x.length);
        w.a(str3, objArr);
        crashDetailBean.b = z ? 0 : 2;
        crashDetailBean.e = this.d.g();
        crashDetailBean.f = this.d.i;
        crashDetailBean.g = this.d.t();
        crashDetailBean.m = this.d.f();
        if (th == null) {
            return null;
        }
        Throwable th2 = th;
        while (th2.getCause() != null) {
            th2 = th2.getCause();
        }
        String name = th.getClass().getName();
        String b2 = b(th, 1000);
        if (b2 == null) {
            b2 = "";
        }
        String str4 = "stack frame :%d, has cause %b";
        Object[] objArr2 = new Object[2];
        objArr2[0] = Integer.valueOf(th.getStackTrace().length);
        objArr2[1] = Boolean.valueOf(th.getCause() != null);
        w.e(str4, objArr2);
        String str5 = "";
        if (th.getStackTrace().length > 0) {
            str5 = th.getStackTrace()[0].toString();
        }
        if (th2 != th) {
            crashDetailBean.n = th2.getClass().getName();
            crashDetailBean.o = b(th2, 1000);
            if (crashDetailBean.o == null) {
                crashDetailBean.o = "";
            }
            crashDetailBean.p = th2.getStackTrace()[0].toString();
            StringBuilder sb = new StringBuilder();
            sb.append(name).append(":").append(b2).append("\n");
            sb.append(str5);
            sb.append("\n......");
            sb.append("\nCaused by:\n");
            sb.append(crashDetailBean.n).append(":").append(crashDetailBean.o).append("\n");
            a2 = a(th2, c.e);
            sb.append(a2);
            crashDetailBean.q = sb.toString();
        } else {
            crashDetailBean.n = name;
            crashDetailBean.o = b2 + str2;
            if (crashDetailBean.o == null) {
                crashDetailBean.o = "";
            }
            crashDetailBean.p = str5;
            a2 = a(th, c.e);
            crashDetailBean.q = a2;
        }
        crashDetailBean.r = System.currentTimeMillis();
        crashDetailBean.u = com.tencent.bugly.legu.proguard.a.b(crashDetailBean.q.getBytes());
        try {
            crashDetailBean.y = com.tencent.bugly.legu.proguard.a.a(c.e, false);
            crashDetailBean.z = this.d.d;
            crashDetailBean.A = thread.getName() + "(" + thread.getId() + ")";
            crashDetailBean.y.put(crashDetailBean.A, a2);
            crashDetailBean.H = this.d.v();
            crashDetailBean.h = this.d.s();
            crashDetailBean.i = this.d.E();
            crashDetailBean.L = this.d.a;
            crashDetailBean.M = this.d.n;
            crashDetailBean.O = this.d.B();
            crashDetailBean.P = this.d.C();
            crashDetailBean.Q = this.d.w();
            crashDetailBean.R = this.d.A();
        } catch (Throwable th3) {
            w.e("handle crash error %s", th3.toString());
        }
        if (z) {
            this.b.b(crashDetailBean);
        } else {
            boolean z2 = str != null && str.length() > 0;
            boolean z3 = bArr != null && bArr.length > 0;
            if (z2) {
                crashDetailBean.N = new HashMap(1);
                crashDetailBean.N.put("UserData", str);
            }
            if (z3) {
                crashDetailBean.S = bArr;
            }
        }
        return crashDetailBean;
    }

    private static boolean c() {
        boolean z;
        synchronized (i) {
            z = h;
            h = true;
        }
        return z;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00e3, code lost:
        if (r0 >= 5000) goto L_0x00e5;
     */
    public final void a(Thread thread, Throwable th, boolean z, String str, byte[] bArr) {
        String str2;
        if (z) {
            w.e("Java Crash Happen cause by %s(%d)", thread.getName(), Long.valueOf(thread.getId()));
            if (c()) {
                w.a("this class has handled this exception", new Object[0]);
                if (f != null) {
                    w.a("call system handler", new Object[0]);
                    f.uncaughtException(thread, th);
                } else {
                    w.e("current process die", new Object[0]);
                    Process.killProcess(Process.myPid());
                    System.exit(1);
                }
            }
        } else {
            w.e("Java Catch Happen", new Object[0]);
        }
        try {
            if (!this.g) {
                w.c("Java crash handler is disable. Just return.", new Object[0]);
                if (!z) {
                    w.e("not to shut down return", new Object[0]);
                } else if (this.e != null && b(this.e)) {
                    w.e("sys default last handle start!", new Object[0]);
                    this.e.uncaughtException(thread, th);
                    w.e("sys default last handle end!", new Object[0]);
                } else if (f != null) {
                    w.e("system handle start!", new Object[0]);
                    f.uncaughtException(thread, th);
                    w.e("system handle end!", new Object[0]);
                } else {
                    w.e("crashreport last handle start!", new Object[0]);
                    w.e("current process die", new Object[0]);
                    Process.killProcess(Process.myPid());
                    System.exit(1);
                    w.e("crashreport last handle end!", new Object[0]);
                }
            } else {
                if (!this.c.b()) {
                    w.e("waiting for remote sync", new Object[0]);
                    int i2 = 0;
                    while (!this.c.b()) {
                        Thread.sleep(500);
                        i2 += 500;
                    }
                }
                if (!this.c.b()) {
                    w.d("no remote but still store!", new Object[0]);
                }
                if (this.c.c().d || !this.c.b()) {
                    CrashDetailBean b2 = b(thread, th, z, str, bArr);
                    if (b2 == null) {
                        w.e("pkg crash datas fail!", new Object[0]);
                        if (!z) {
                            w.e("not to shut down return", new Object[0]);
                        } else if (this.e != null && b(this.e)) {
                            w.e("sys default last handle start!", new Object[0]);
                            this.e.uncaughtException(thread, th);
                            w.e("sys default last handle end!", new Object[0]);
                        } else if (f != null) {
                            w.e("system handle start!", new Object[0]);
                            f.uncaughtException(thread, th);
                            w.e("system handle end!", new Object[0]);
                        } else {
                            w.e("crashreport last handle start!", new Object[0]);
                            w.e("current process die", new Object[0]);
                            Process.killProcess(Process.myPid());
                            System.exit(1);
                            w.e("crashreport last handle end!", new Object[0]);
                        }
                    } else {
                        b.a(z ? "JAVA_CRASH" : "JAVA_CATCH", com.tencent.bugly.legu.proguard.a.n(), this.d.d, thread, com.tencent.bugly.legu.proguard.a.a(th), b2);
                        if (!this.b.a(b2)) {
                            this.b.a(b2, 5000, z);
                        }
                        if (!z) {
                            w.e("not to shut down return", new Object[0]);
                        } else if (this.e != null && b(this.e)) {
                            w.e("sys default last handle start!", new Object[0]);
                            this.e.uncaughtException(thread, th);
                            w.e("sys default last handle end!", new Object[0]);
                        } else if (f != null) {
                            w.e("system handle start!", new Object[0]);
                            f.uncaughtException(thread, th);
                            w.e("system handle end!", new Object[0]);
                        } else {
                            w.e("crashreport last handle start!", new Object[0]);
                            w.e("current process die", new Object[0]);
                            Process.killProcess(Process.myPid());
                            System.exit(1);
                            w.e("crashreport last handle end!", new Object[0]);
                        }
                    }
                } else {
                    w.e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
                    if (z) {
                        str2 = "JAVA_CRASH";
                    } else {
                        str2 = "JAVA_CATCH";
                    }
                    b.a(str2, com.tencent.bugly.legu.proguard.a.n(), this.d.d, thread, com.tencent.bugly.legu.proguard.a.a(th), null);
                    if (!z) {
                        w.e("not to shut down return", new Object[0]);
                    } else if (this.e != null && b(this.e)) {
                        w.e("sys default last handle start!", new Object[0]);
                        this.e.uncaughtException(thread, th);
                        w.e("sys default last handle end!", new Object[0]);
                    } else if (f != null) {
                        w.e("system handle start!", new Object[0]);
                        f.uncaughtException(thread, th);
                        w.e("system handle end!", new Object[0]);
                    } else {
                        w.e("crashreport last handle start!", new Object[0]);
                        w.e("current process die", new Object[0]);
                        Process.killProcess(Process.myPid());
                        System.exit(1);
                        w.e("crashreport last handle end!", new Object[0]);
                    }
                }
            }
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        } catch (Throwable th2) {
            try {
                if (!w.a(th2)) {
                    th2.printStackTrace();
                }
                if (!z) {
                    w.e("not to shut down return", new Object[0]);
                } else if (this.e != null && b(this.e)) {
                    w.e("sys default last handle start!", new Object[0]);
                    this.e.uncaughtException(thread, th);
                    w.e("sys default last handle end!", new Object[0]);
                } else if (f != null) {
                    w.e("system handle start!", new Object[0]);
                    f.uncaughtException(thread, th);
                    w.e("system handle end!", new Object[0]);
                } else {
                    w.e("crashreport last handle start!", new Object[0]);
                    w.e("current process die", new Object[0]);
                    Process.killProcess(Process.myPid());
                    System.exit(1);
                    w.e("crashreport last handle end!", new Object[0]);
                }
            } catch (Throwable th3) {
                if (!z) {
                    w.e("not to shut down return", new Object[0]);
                    return;
                }
                if (this.e != null && b(this.e)) {
                    w.e("sys default last handle start!", new Object[0]);
                    this.e.uncaughtException(thread, th);
                    w.e("sys default last handle end!", new Object[0]);
                } else if (f != null) {
                    w.e("system handle start!", new Object[0]);
                    f.uncaughtException(thread, th);
                    w.e("system handle end!", new Object[0]);
                } else {
                    w.e("crashreport last handle start!", new Object[0]);
                    w.e("current process die", new Object[0]);
                    Process.killProcess(Process.myPid());
                    System.exit(1);
                    w.e("crashreport last handle end!", new Object[0]);
                }
                throw th3;
            }
        }
    }

    private static boolean b(UncaughtExceptionHandler uncaughtExceptionHandler) {
        StackTraceElement[] stackTrace;
        if (uncaughtExceptionHandler == null) {
            return true;
        }
        String name = uncaughtExceptionHandler.getClass().getName();
        String str = "uncaughtException";
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            String className = stackTraceElement.getClassName();
            String methodName = stackTraceElement.getMethodName();
            if (name.equals(className) && str.equals(methodName)) {
                return false;
            }
        }
        return true;
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        a(thread, th, true, null, null);
    }

    public final synchronized void a(StrategyBean strategyBean) {
        if (strategyBean != null) {
            if (strategyBean.d != this.g) {
                w.a("java changed to %b", Boolean.valueOf(strategyBean.d));
                if (strategyBean.d) {
                    a();
                } else {
                    b();
                }
            }
        }
    }

    private static String a(Throwable th, int i2) {
        if (th == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
            if (th.getStackTrace() != null) {
                StackTraceElement[] stackTrace = th.getStackTrace();
                int length = stackTrace.length;
                int i3 = 0;
                while (i3 < length) {
                    StackTraceElement stackTraceElement = stackTrace[i3];
                    if (i2 <= 0 || sb.length() < i2) {
                        sb.append(stackTraceElement.toString()).append("\n");
                        i3++;
                    } else {
                        sb.append("\n[Stack over limit size :" + i2 + " , has been cutted !]");
                        return sb.toString();
                    }
                }
            }
        } catch (Throwable th2) {
            w.e("gen stack error %s", th2.toString());
        }
        return sb.toString();
    }

    private static String b(Throwable th, int i2) {
        if (th.getMessage() == null) {
            return "";
        }
        if (th.getMessage().length() <= 1000) {
            return th.getMessage();
        }
        return th.getMessage().substring(0, 1000) + "\n[Message over limit size:1000" + ", has been cutted!]";
    }
}
