package com.tencent.bugly.legu.crashreport.crash;

import android.content.Context;
import com.tencent.bugly.legu.BuglyStrategy.a;
import com.tencent.bugly.legu.CrashModule;
import com.tencent.bugly.legu.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.legu.crashreport.crash.anr.b;
import com.tencent.bugly.legu.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.legu.proguard.n;
import com.tencent.bugly.legu.proguard.o;
import com.tencent.bugly.legu.proguard.q;
import com.tencent.bugly.legu.proguard.t;
import com.tencent.bugly.legu.proguard.v;
import com.tencent.bugly.legu.proguard.w;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: BUGLY */
public final class c {
    public static int a = 0;
    public static boolean b = false;
    public static boolean c = true;
    public static int d = 20000;
    public static int e = 20000;
    public static long f = 604800000;
    public static String g = null;
    public static boolean h = false;
    public static String i = null;
    public static int j = 5000;
    private static c n;
    public final b k;
    public a l;
    /* access modifiers changed from: private */
    public final Context m;
    /* access modifiers changed from: private */
    public final e o;
    private final NativeCrashHandler p;
    private com.tencent.bugly.legu.crashreport.common.strategy.a q;
    private v r;
    private final b s;
    private Boolean t;

    private c(int i2, Context context, v vVar, boolean z, a aVar, n nVar, String str) {
        Context applicationContext;
        a = i2;
        if (context == null) {
            applicationContext = context;
        } else {
            applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                applicationContext = context;
            }
        }
        this.m = applicationContext;
        this.q = com.tencent.bugly.legu.crashreport.common.strategy.a.a();
        t a2 = t.a();
        o a3 = o.a();
        com.tencent.bugly.legu.crashreport.common.info.a a4 = com.tencent.bugly.legu.crashreport.common.info.a.a(applicationContext);
        this.r = vVar;
        this.l = aVar;
        this.k = new b(i2, applicationContext, a2, a3, this.q, aVar, nVar);
        this.o = new e(applicationContext, this.k, this.q, a4);
        this.p = NativeCrashHandler.getInstance(applicationContext, a4, this.k, this.q, vVar, z, str);
        this.s = new b(applicationContext, this.q, a4, vVar, this.k);
        BuglyBroadcastRecevier instance = BuglyBroadcastRecevier.getInstance();
        instance.addFilter("android.net.conn.CONNECTIVITY_CHANGE");
        instance.regist(applicationContext, this.k);
    }

    public static synchronized void a(int i2, Context context, boolean z, a aVar, n nVar, String str) {
        synchronized (c.class) {
            if (n == null) {
                n = new c(CrashModule.MODULE_ID, context, v.a(), z, aVar, null, null);
            }
        }
    }

    public static c a() {
        return n;
    }

    public final void a(StrategyBean strategyBean) {
        this.o.a(strategyBean);
        this.p.onStrategyChanged(strategyBean);
        this.s.a(strategyBean);
    }

    public final boolean b() {
        Boolean bool = this.t;
        if (bool != null) {
            return bool.booleanValue();
        }
        String str = com.tencent.bugly.legu.crashreport.common.info.a.a().d;
        List<q> a2 = o.a().a(1);
        ArrayList arrayList = new ArrayList();
        if (a2 == null || a2.size() <= 0) {
            this.t = Boolean.valueOf(false);
            return false;
        }
        for (q qVar : a2) {
            if (str.equals(qVar.c)) {
                this.t = Boolean.valueOf(true);
                arrayList.add(qVar);
            }
        }
        if (arrayList.size() > 0) {
            o.a();
            o.a((List<q>) arrayList);
        }
        return true;
    }

    public final synchronized void c() {
        this.o.a();
        this.p.setUserOpened(true);
        this.s.a(true);
    }

    public final synchronized void d() {
        this.o.b();
        this.p.setUserOpened(false);
        this.s.a(false);
    }

    public final void e() {
        this.o.a();
    }

    public final void f() {
        this.p.setUserOpened(false);
    }

    public final void g() {
        this.p.setUserOpened(true);
    }

    public final void h() {
        this.s.a(true);
    }

    public final void i() {
        this.s.a(false);
    }

    public final synchronized void j() {
        this.p.testNativeCrash();
    }

    public final synchronized void k() {
        int i2 = 0;
        synchronized (this) {
            b bVar = this.s;
            while (true) {
                int i3 = i2 + 1;
                if (i2 >= 30) {
                    break;
                }
                try {
                    w.a("try main sleep for make a test anr! try:%d/30 , kill it if you don't want to wait!", Integer.valueOf(i3));
                    Thread.sleep(5000);
                    i2 = i3;
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                    i2 = i3;
                } catch (Throwable th) {
                    if (!w.a(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
    }

    public final boolean l() {
        return this.s.a();
    }

    public final void a(Thread thread, Throwable th, boolean z, String str, byte[] bArr) {
        final Thread thread2 = thread;
        final Throwable th2 = th;
        this.r.b(new Runnable(false, null, null) {
            public final void run() {
                try {
                    w.c("post a throwable %b", Boolean.valueOf(false));
                    c.this.o.a(thread2, th2, false, null, null);
                } catch (Throwable th) {
                    if (!w.b(th)) {
                        th.printStackTrace();
                    }
                    w.e("java catch error: %s", th2.toString());
                }
            }
        });
    }

    public final void a(CrashDetailBean crashDetailBean) {
        this.k.c(crashDetailBean);
    }

    public final void a(long j2) {
        v.a().a(new Thread() {
            public final void run() {
                List list;
                if (com.tencent.bugly.legu.proguard.a.a(c.this.m, "local_crash_lock", 10000)) {
                    List a2 = c.this.k.a();
                    if (a2 != null && a2.size() > 0) {
                        int size = a2.size();
                        if (((long) size) > 100) {
                            list = new ArrayList();
                            Collections.sort(a2);
                            for (int i = 0; ((long) i) < 100; i++) {
                                list.add(a2.get((size - 1) - i));
                            }
                        } else {
                            list = a2;
                        }
                        c.this.k.a(list, 0, false);
                    }
                    com.tencent.bugly.legu.proguard.a.b(c.this.m, "local_crash_lock");
                }
            }
        }, j2);
    }
}
