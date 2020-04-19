package com.tencent.bugly.legu.crashreport.biz;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.tencent.bugly.legu.BuglyStrategy;
import com.tencent.bugly.legu.crashreport.common.info.a;
import com.tencent.bugly.legu.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.legu.proguard.t;
import com.tencent.bugly.legu.proguard.v;
import com.tencent.bugly.legu.proguard.w;
import java.util.List;

/* compiled from: BUGLY */
public final class b {
    public static a a;
    private static boolean b;
    /* access modifiers changed from: private */
    public static int c = 10;
    /* access modifiers changed from: private */
    public static long d = 300000;
    /* access modifiers changed from: private */
    public static long e = 30000;
    /* access modifiers changed from: private */
    public static long f = 0;
    /* access modifiers changed from: private */
    public static int g;
    /* access modifiers changed from: private */
    public static long h;
    /* access modifiers changed from: private */
    public static long i;
    /* access modifiers changed from: private */
    public static long j = 0;
    private static ActivityLifecycleCallbacks k = null;
    /* access modifiers changed from: private */
    public static Class<?> l = null;

    static /* synthetic */ int g() {
        int i2 = g;
        g = i2 + 1;
        return i2;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:66:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    public static void c(Context context, BuglyStrategy buglyStrategy) {
        boolean z;
        boolean z2;
        StackTraceElement[] stackTrace;
        boolean z3;
        if (buglyStrategy != null) {
            boolean recordUserInfoOnceADay = buglyStrategy.recordUserInfoOnceADay();
            z = buglyStrategy.isEnableUserInfo();
            z2 = recordUserInfoOnceADay;
        } else {
            z = true;
            z2 = false;
        }
        if (z2) {
            a a2 = a.a(context);
            List a3 = a.a(a2.d);
            if (a3 != null) {
                int i2 = 0;
                while (true) {
                    int i3 = i2;
                    if (i3 >= a3.size()) {
                        break;
                    }
                    UserInfoBean userInfoBean = (UserInfoBean) a3.get(i3);
                    if (userInfoBean.n.equals(a2.i) && userInfoBean.b == 1) {
                        long o = com.tencent.bugly.legu.proguard.a.o();
                        if (o <= 0) {
                            break;
                        } else if (userInfoBean.e >= o) {
                            if (userInfoBean.f <= 0) {
                                a.b();
                            }
                            z3 = false;
                        }
                    }
                    i2 = i3 + 1;
                }
                if (!z3) {
                    z = false;
                } else {
                    return;
                }
            }
            z3 = true;
            if (!z3) {
            }
        }
        a a4 = a.a();
        if (a4 != null) {
            boolean z4 = false;
            String str = null;
            for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
                if (stackTraceElement.getMethodName().equals("onCreate")) {
                    str = stackTraceElement.getClassName();
                }
                if (stackTraceElement.getClassName().equals("android.app.Activity")) {
                    z4 = true;
                }
            }
            if (str == null) {
                str = "unknown";
            } else if (z4) {
                a4.n = true;
            } else {
                str = "background";
            }
            a4.o = str;
        }
        if (z) {
            Application application = null;
            if (VERSION.SDK_INT >= 14) {
                if (context.getApplicationContext() instanceof Application) {
                    application = (Application) context.getApplicationContext();
                }
                if (application != null) {
                    try {
                        if (k == null) {
                            k = new ActivityLifecycleCallbacks() {
                                public final void onActivityStopped(Activity activity) {
                                }

                                public final void onActivityStarted(Activity activity) {
                                }

                                public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                                }

                                public final void onActivityResumed(Activity activity) {
                                    String str = "unknown";
                                    if (activity != null) {
                                        str = activity.getClass().getName();
                                    }
                                    if (b.l == null || b.l.getName().equals(str)) {
                                        w.c(">>> %s onResumed <<<", str);
                                        a a = a.a();
                                        if (a != null) {
                                            a.n = true;
                                            a.o = str;
                                            a.p = System.currentTimeMillis();
                                            a.s = a.p - b.i;
                                            long d = a.p - b.h;
                                            if (d > (b.f > 0 ? b.f : b.e)) {
                                                a.c();
                                                b.g();
                                                w.a("[session] launch app one times (app in background %d seconds and over %d seconds)", Long.valueOf(d / 1000), Long.valueOf(b.e / 1000));
                                                if (b.g % b.c == 0) {
                                                    b.a.a(4, true, 0);
                                                    return;
                                                }
                                                b.a.a(4, false, 0);
                                                long currentTimeMillis = System.currentTimeMillis();
                                                if (currentTimeMillis - b.j > b.d) {
                                                    b.j = currentTimeMillis;
                                                    w.a("add a timer to upload hot start user info", new Object[0]);
                                                    v.a().a(new C0000a(null, true), b.d);
                                                }
                                            }
                                        }
                                    }
                                }

                                public final void onActivityPaused(Activity activity) {
                                    String str = "unknown";
                                    if (activity != null) {
                                        str = activity.getClass().getName();
                                    }
                                    if (b.l == null || b.l.getName().equals(str)) {
                                        w.c(">>> %s onPaused <<<", str);
                                        a a = a.a();
                                        if (a != null) {
                                            a.n = false;
                                            a.q = System.currentTimeMillis();
                                            a.r = a.q - a.p;
                                            b.h = a.q;
                                            if (a.r < 0) {
                                                a.r = 0;
                                            }
                                            if (activity != null) {
                                                a.o = "background";
                                            } else {
                                                a.o = "unknown";
                                            }
                                        }
                                    }
                                }

                                public final void onActivityDestroyed(Activity activity) {
                                }

                                public final void onActivityCreated(Activity activity, Bundle bundle) {
                                }
                            };
                        }
                        application.registerActivityLifecycleCallbacks(k);
                    } catch (Exception e2) {
                    }
                }
            }
        }
        i = System.currentTimeMillis();
        a.a(1, true, 0);
        t.a().a(1001, System.currentTimeMillis());
        w.a("[session] launch app, new start", new Object[0]);
        a.a();
        v.a().a(new C0000a(null, true), 21600000);
    }

    public static void a(final Context context, final BuglyStrategy buglyStrategy) {
        long j2;
        if (!b) {
            e = com.tencent.bugly.legu.crashreport.common.strategy.a.a().c().m;
            c = com.tencent.bugly.legu.crashreport.common.strategy.a.a().c().s;
            a = new a(context);
            b = true;
            if (buglyStrategy != null) {
                l = buglyStrategy.getUserInfoActivity();
                j2 = buglyStrategy.getAppReportDelay();
            } else {
                j2 = 0;
            }
            if (j2 <= 0) {
                c(context, buglyStrategy);
            } else {
                v.a().a(new Runnable() {
                    public final void run() {
                        b.c(context, buglyStrategy);
                    }
                }, j2);
            }
        }
    }

    public static void a(long j2) {
        if (j2 < 0) {
            j2 = com.tencent.bugly.legu.crashreport.common.strategy.a.a().c().m;
        }
        f = j2;
    }

    public static void a(StrategyBean strategyBean) {
        if (strategyBean != null) {
            if (strategyBean.m > 0) {
                e = strategyBean.m;
            }
            if (strategyBean.s > 0) {
                c = strategyBean.s;
            }
            if (strategyBean.t > 0) {
                d = strategyBean.t;
            }
        }
    }

    public static void a() {
        if (a != null) {
            a.a(2, false, 0);
        }
    }

    public static void a(Context context) {
        if (b && context != null) {
            Application application = null;
            if (VERSION.SDK_INT >= 14) {
                if (context.getApplicationContext() instanceof Application) {
                    application = (Application) context.getApplicationContext();
                }
                if (application != null) {
                    try {
                        if (k != null) {
                            application.unregisterActivityLifecycleCallbacks(k);
                        }
                    } catch (Exception e2) {
                    }
                }
            }
            b = false;
        }
    }
}
