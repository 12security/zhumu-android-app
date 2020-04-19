package com.tencent.bugly.legu.proguard;

import android.content.Context;
import com.tencent.bugly.legu.BuglyStrategy;
import com.tencent.bugly.legu.crashreport.common.info.a;
import java.util.List;

/* compiled from: BUGLY */
public final class m {
    private static m a = null;
    private static long b = System.currentTimeMillis();

    private m(Context context) {
    }

    public static synchronized m a(Context context) {
        m mVar;
        synchronized (m.class) {
            if (a == null) {
                a = new m(context);
            }
            mVar = a;
        }
        return mVar;
    }

    public static synchronized m a() {
        m mVar;
        synchronized (m.class) {
            mVar = a;
        }
        return mVar;
    }

    private synchronized boolean b(int i) {
        boolean z;
        try {
            String str = a.a().d;
            if (str != null) {
                long d = d(0);
                o a2 = o.a();
                a.a().getClass();
                List a3 = a2.a(i, str, d, "2.1.9");
                if (a3 != null) {
                    if (a3.size() >= 2) {
                        int i2 = 0;
                        while (true) {
                            if (i2 + 1 >= a3.size()) {
                                z = false;
                                break;
                            } else if (((l) a3.get(i2 + 1)).c - ((l) a3.get(i2)).c < 86400000) {
                                z = true;
                                break;
                            } else {
                                i2++;
                            }
                        }
                    } else {
                        z = false;
                    }
                } else {
                    z = false;
                }
            } else {
                w.a("process name is null", new Object[0]);
                z = false;
            }
        } catch (Exception e) {
            w.a("FrenquencyCrash failed", new Object[0]);
            z = false;
        }
        return z;
    }

    private synchronized l c(int i) {
        l lVar = null;
        synchronized (this) {
            try {
                String str = a.a().d;
                if (str == null) {
                    w.a("process name is null", new Object[0]);
                } else {
                    lVar = o.a().a(i, str);
                }
            } catch (Exception e) {
                w.a("getLatestCrashRecord failed", new Object[0]);
            }
        }
        return lVar;
    }

    public final synchronized boolean a(int i, int i2) {
        boolean z = false;
        synchronized (this) {
            try {
                l lVar = new l();
                lVar.a = 1004;
                lVar.b = a.a().d;
                lVar.f = a.a().i;
                a.a().getClass();
                lVar.e = "2.1.9";
                lVar.d = i2;
                lVar.c = System.currentTimeMillis();
                lVar.g = b;
                z = o.a().a(lVar);
            } catch (Exception e) {
                w.a("saveCrashRecord failed", new Object[0]);
            }
        }
        return z;
    }

    private synchronized int b(int i, int i2) {
        int i3 = 0;
        synchronized (this) {
            try {
                i3 = o.a().a(i, a.a().d, d(1));
            } catch (Exception e) {
                w.a("clearHistoryCrashRecord failed", new Object[0]);
            }
        }
        return i3;
    }

    private synchronized long d(int i) {
        long j = 0;
        synchronized (this) {
            switch (i) {
                case BuglyStrategy.a.CRASHTYPE_JAVA_CATCH /*1*/:
                    j = System.currentTimeMillis() - 86400000;
                    break;
            }
        }
        return j;
    }

    public final synchronized int b() {
        int i = 0;
        synchronized (this) {
            try {
                o a2 = o.a();
                a.a().getClass();
                i = a2.b();
            } catch (Exception e) {
                w.a("clearInvalidCrashRecord failed", new Object[0]);
            }
        }
        return i;
    }

    public final synchronized boolean a(int i) {
        boolean z = true;
        synchronized (this) {
            try {
                l c = c(i);
                if (c != null && System.currentTimeMillis() - c.c <= 86400000 && b(i)) {
                    z = false;
                }
                if (z) {
                    b(i, 1);
                }
            } catch (Exception e) {
                w.a("canInit failed", new Object[0]);
            }
        }
        return z;
    }
}
