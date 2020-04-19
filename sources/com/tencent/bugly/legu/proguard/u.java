package com.tencent.bugly.legu.proguard;

import android.content.Context;
import android.os.Process;
import com.tencent.bugly.legu.BuglyStrategy;
import com.tencent.bugly.legu.crashreport.common.info.a;
import com.tencent.bugly.legu.crashreport.common.strategy.StrategyBean;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BUGLY */
public final class u implements Runnable {
    private int a;
    private int b;
    private final Context c;
    private final int d;
    private final byte[] e;
    private final a f;
    private final com.tencent.bugly.legu.crashreport.common.strategy.a g;
    private final r h;
    private final t i;
    private final int j;
    private final s k;
    private final s l;
    private String m;
    private int n;
    private long o;
    private long p;
    private boolean q;

    public u(Context context, int i2, am amVar, String str, s sVar, boolean z) {
        this(context, i2, amVar.g, a.a(amVar), str, sVar, z);
    }

    public u(Context context, int i2, int i3, byte[] bArr, String str, s sVar, boolean z) {
        this(context, i2, i3, bArr, str, sVar, z, 5, 60000);
    }

    private u(Context context, int i2, int i3, byte[] bArr, String str, s sVar, boolean z, int i4, int i5) {
        this.a = 3;
        this.b = BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH;
        this.m = "";
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = true;
        this.c = context;
        this.f = a.a(context);
        this.e = bArr;
        this.g = com.tencent.bugly.legu.crashreport.common.strategy.a.a();
        this.h = r.a(context);
        this.i = t.a();
        this.j = i2;
        this.m = str;
        this.k = sVar;
        t tVar = this.i;
        this.l = null;
        this.q = z;
        if (z) {
            switch (i3) {
                case 510:
                case 640:
                    i3 = 840;
                    break;
                case 630:
                    i3 = 830;
                    break;
            }
        }
        this.d = i3;
        this.a = 5;
        this.b = 60000;
    }

    private void a(an anVar, boolean z, int i2, String str, int i3) {
        String str2;
        switch (this.d) {
            case 630:
            case 830:
                str2 = "crash";
                break;
            case 640:
            case 840:
                str2 = "userinfo";
                break;
            default:
                str2 = String.valueOf(this.d);
                break;
        }
        if (z) {
            w.a("[upload] success: %s", str2);
        } else {
            w.e("[upload] fail! %s %d %s", str2, Integer.valueOf(i2), str);
            if (this.q) {
                this.i.a(i3, (an) null);
            }
        }
        if (this.o + this.p > 0) {
            this.i.a(this.i.b() + this.o + this.p);
        }
        if (this.k != null) {
            s sVar = this.k;
            int i4 = this.d;
            long j2 = this.o;
            long j3 = this.p;
            sVar.a(z);
        }
        if (this.l != null) {
            s sVar2 = this.l;
            int i5 = this.d;
            long j4 = this.o;
            long j5 = this.p;
            sVar2.a(z);
        }
    }

    private static boolean a(an anVar, a aVar, com.tencent.bugly.legu.crashreport.common.strategy.a aVar2) {
        boolean z;
        if (anVar == null) {
            w.d("resp == null!", new Object[0]);
            return false;
        } else if (anVar.a != 0) {
            w.e("resp result error %d", Byte.valueOf(anVar.a));
            return false;
        } else {
            try {
                String str = anVar.d;
                if (!(str == null || str.trim().length() <= 0) && a.a().h() != anVar.d) {
                    o.a().a(com.tencent.bugly.legu.crashreport.common.strategy.a.a, "key_ip", anVar.d.getBytes("UTF-8"), (n) null, true);
                    aVar.d(anVar.d);
                }
                String str2 = anVar.g;
                if (str2 == null || str2.trim().length() <= 0) {
                    z = true;
                } else {
                    z = false;
                }
                if (!z && a.a().i() != anVar.g) {
                    o.a().a(com.tencent.bugly.legu.crashreport.common.strategy.a.a, "key_imei", anVar.g.getBytes("UTF-8"), (n) null, true);
                    aVar.e(anVar.g);
                }
            } catch (Throwable th) {
            }
            aVar.h = anVar.e;
            if (anVar.b == 510) {
                if (anVar.c == null) {
                    w.e("remote data is miss! %d", Integer.valueOf(anVar.b));
                    return false;
                }
                ap apVar = (ap) a.a(anVar.c, ap.class);
                if (apVar == null) {
                    w.e("remote data is error! %d", Integer.valueOf(anVar.b));
                    return false;
                }
                String str3 = "en:%b qu:%b uin:%b vm:%d";
                Object[] objArr = new Object[4];
                objArr[0] = Boolean.valueOf(apVar.a);
                objArr[1] = Boolean.valueOf(apVar.c);
                objArr[2] = Boolean.valueOf(apVar.b);
                objArr[3] = Integer.valueOf(apVar.g == null ? -1 : apVar.g.size());
                w.c(str3, objArr);
                aVar2.a(apVar);
            }
            return true;
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    public final void run() {
        boolean z;
        byte[] bArr;
        try {
            byte[] bArr2 = this.e;
            this.n = 0;
            this.o = 0;
            this.p = 0;
            this.i.a(this.j, System.currentTimeMillis());
            if (this.k != null) {
                s sVar = this.k;
                int i2 = this.d;
            }
            if (this.l != null) {
                s sVar2 = this.l;
                int i3 = this.d;
            }
            if (a.e(this.c) == null) {
                a(null, false, 0, "network is not availiable!", 0);
            } else if (bArr2 == null || bArr2.length == 0) {
                a(null, false, 0, "[upload] fail, request package is empty!", 0);
            } else {
                long b2 = this.i.b();
                if (((long) bArr2.length) + b2 >= 2097152) {
                    w.e("up too much wait to next time ! %d %d ", Long.valueOf(b2), Long.valueOf(2097152));
                    a(null, false, 0, "[upload] fail, over net consume: " + 2048 + "K", 0);
                    return;
                }
                w.c("do upload task %d", Integer.valueOf(this.d));
                if (this.c == null || bArr2 == null || this.f == null || this.g == null || this.h == null) {
                    a(null, false, 0, "[upload] fail, illegal access error!", 0);
                    return;
                }
                StrategyBean c2 = this.g.c();
                if (c2 == null) {
                    a(null, false, 0, "[upload] fail, illegal local strategy!", 0);
                    return;
                }
                HashMap hashMap = new HashMap();
                hashMap.put("prodId", this.f.e());
                hashMap.put("bundleId", this.f.c);
                hashMap.put("appVer", this.f.i);
                if (this.q) {
                    hashMap.put("cmd", Integer.toString(this.d));
                    hashMap.put("platformId", Byte.toString(1));
                    this.f.getClass();
                    hashMap.put("sdkVer", "2.1.9");
                    hashMap.put("strategylastUpdateTime", Long.toString(c2.l));
                    if (!this.i.a((Map<String, String>) hashMap)) {
                        a(null, false, 0, "[upload] fail, failed to add security info to HTTP headers", 0);
                        return;
                    }
                    byte[] a2 = a.a(bArr2, 2);
                    if (a2 == null) {
                        a(null, false, 0, "[upload] fail, failed to zip request body", 0);
                        return;
                    }
                    bArr2 = this.i.a(a2);
                    if (bArr2 == null) {
                        a(null, false, 0, "[upload] fail, failed to encrypt request body", 0);
                        return;
                    }
                }
                byte[] bArr3 = bArr2;
                int i4 = -1;
                int i5 = 0;
                int i6 = 0;
                while (i5 < this.a) {
                    int i7 = i5 + 1;
                    if (i5 != 0) {
                        w.d("failed to upload last time, wait and try(%d) again", Integer.valueOf(i7));
                        Thread.sleep((long) this.b);
                    }
                    w.c("send %d", Integer.valueOf(bArr3.length));
                    String str = this.m;
                    if (str == null || str.trim().length() <= 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        if (!this.q) {
                            this.m = c2.p;
                        } else if (this.d == 830) {
                            this.m = c2.o;
                        } else {
                            this.m = c2.n;
                        }
                    }
                    w.c("do upload to %s with cmd %d (pid=%d | tid=%d)", this.m, Integer.valueOf(this.d), Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                    byte[] a3 = this.h.a(this.m, bArr3, this, (Map<String, String>) hashMap);
                    if (a3 == null) {
                        w.e("try upload fail! %d %s", Integer.valueOf(this.d), "upload fail, no response!");
                        i5 = i7;
                        i6 = 1;
                    } else {
                        Map<String, String> map = this.h.a;
                        if (this.q) {
                            if (map == null || map.size() == 0 || !map.containsKey("status")) {
                                w.c("no headers from server, just try again (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                                w.e("try upload fail! %d %s", Integer.valueOf(this.d), "upload fail, no status header");
                                i5 = i7;
                                i6 = 1;
                            } else {
                                try {
                                    i4 = Integer.parseInt((String) map.get("status"));
                                    w.c("status from server is %d (pid=%d | tid=%d)", Integer.valueOf(i4), Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                                    if (i4 != 0) {
                                        if (i4 == 2) {
                                            if (this.o + this.p > 0) {
                                                this.i.a(this.i.b() + this.o + this.p);
                                            }
                                            this.i.a(i4, (an) null);
                                            w.a("Session ID is invalid, will try again immediately (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                                            this.i.a(this.j, this.d, this.e, null, this.l);
                                            return;
                                        }
                                        a(null, false, 1, "upload fail, status: " + Integer.toString(i4), i4);
                                        return;
                                    }
                                } catch (Throwable th) {
                                    w.e("try upload fail! %d %s", Integer.valueOf(this.d), "upload fail, format of status header is invalid: " + Integer.toString(i4));
                                    i5 = i7;
                                    i6 = 1;
                                }
                            }
                        }
                        w.c("recv %d", Integer.valueOf(a3.length));
                        if (this.q) {
                            byte[] b3 = this.i.b(a3);
                            if (b3 == null) {
                                a(null, false, 1, "upload fail, failed to decrypt response from server", 0);
                                return;
                            }
                            bArr = a.b(b3, 2);
                            if (bArr == null) {
                                a(null, false, 1, "upload fail, failed to unzip(gzip) response from server", 0);
                                return;
                            }
                        } else {
                            bArr = a3;
                        }
                        an a4 = a.a(bArr, this.q);
                        if (a4 == null) {
                            a(null, false, 1, "upload fail, resp null!", 0);
                            return;
                        }
                        if (this.q) {
                            this.i.a(i4, a4);
                        }
                        String str2 = "response %d %d";
                        Object[] objArr = new Object[2];
                        objArr[0] = Integer.valueOf(a4.b);
                        objArr[1] = Integer.valueOf(a4.c == null ? 0 : a4.c.length);
                        w.c(str2, objArr);
                        if (!a(a4, this.f, this.g)) {
                            a(a4, false, 2, a4.f, 0);
                            return;
                        } else {
                            a(a4, true, 2, null, 0);
                            return;
                        }
                    }
                }
                a(null, false, i6, "try OT Fail!", 0);
            }
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        } catch (Throwable th2) {
            if (!w.a(th2)) {
                th2.printStackTrace();
            }
        }
    }

    public final void a(long j2) {
        this.n++;
        this.o += j2;
    }

    public final void b(long j2) {
        this.p += j2;
    }
}
