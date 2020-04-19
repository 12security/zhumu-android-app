package com.tencent.bugly.legu.crashreport.crash;

import android.content.Context;
import com.tencent.bugly.legu.crashreport.common.info.a;
import com.tencent.bugly.legu.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.legu.crashreport.crash.h5.b;
import com.tencent.bugly.legu.proguard.v;
import com.tencent.bugly.legu.proguard.w;
import com.tencent.bugly.legu.proguard.y;
import com.tencent.bugly.legu.proguard.z;
import java.lang.reflect.Field;
import java.util.Map;

/* compiled from: BUGLY */
public final class d {
    /* access modifiers changed from: private */
    public static z a;
    /* access modifiers changed from: private */
    public static y b;
    /* access modifiers changed from: private */
    public static b c;

    static /* synthetic */ void a() {
        a a2 = a.a();
        try {
            Class cls = Class.forName("com.tencent.bugly.unity.UnityAgent");
            String str = "com.tencent.bugly";
            a2.getClass();
            String str2 = "legu";
            if (!"".equals(str2)) {
                str = str + "." + str2;
            }
            try {
                Field declaredField = cls.getDeclaredField("sdkPackageName");
                declaredField.setAccessible(true);
                declaredField.set(null, str);
            } catch (Exception e) {
            }
        } catch (Throwable th) {
            w.a("no unity agent", new Object[0]);
        }
        try {
            Class cls2 = Class.forName("com.tencent.bugly.cocos.Cocos2dxAgent");
            String str3 = "com.tencent.bugly";
            a2.getClass();
            String str4 = "legu";
            if (!"".equals(str4)) {
                str3 = str3 + "." + str4;
            }
            try {
                Field declaredField2 = cls2.getDeclaredField("sdkPackageName");
                declaredField2.setAccessible(true);
                declaredField2.set(null, str3);
            } catch (Exception e2) {
            }
        } catch (Throwable th2) {
            w.a("no cocos agent", new Object[0]);
        }
    }

    public static void a(Context context) {
        c a2 = c.a();
        if (a2 != null) {
            a = new z(context, a2.k, com.tencent.bugly.legu.crashreport.common.strategy.a.a(), a.a(), a2.l);
            b = new y(context, a2.k, com.tencent.bugly.legu.crashreport.common.strategy.a.a(), a.a(), a2.l);
            c = new b(context, a2.k, com.tencent.bugly.legu.crashreport.common.strategy.a.a(), a.a(), a2.l);
            v.a().b(new Runnable() {
                public final void run() {
                    d.a();
                }
            });
        }
    }

    public static void a(StrategyBean strategyBean) {
        if (b != null) {
            y yVar = b;
            boolean z = strategyBean.h;
        }
    }

    public static void a(final Thread thread, final String str, final String str2, final String str3) {
        if (a != null) {
            v.a().b(new Runnable() {
                public final void run() {
                    try {
                        d.a.a(thread, str, str2, str3);
                    } catch (Throwable th) {
                        if (!w.b(th)) {
                            th.printStackTrace();
                        }
                        w.e("u3d crash error %s %s %s", str, str2, str3);
                    }
                }
            });
        }
    }

    public static void a(Thread thread, int i, String str, String str2, String str3) {
        if (b != null) {
            final Thread thread2 = thread;
            final int i2 = i;
            final String str4 = str;
            final String str5 = str2;
            final String str6 = str3;
            v.a().b(new Runnable() {
                public final void run() {
                    try {
                        d.b.a(thread2, i2, str4, str5, str6);
                    } catch (Throwable th) {
                        if (!w.b(th)) {
                            th.printStackTrace();
                        }
                        w.e("cocos2d-x crash error %s %s %s", str4, str5, str6);
                    }
                }
            });
        }
    }

    public static void a(Thread thread, String str, String str2, String str3, Map<String, String> map) {
        if (c != null) {
            final Thread thread2 = thread;
            final String str4 = str;
            final String str5 = str2;
            final String str6 = str3;
            final Map<String, String> map2 = map;
            v.a().b(new Runnable() {
                public final void run() {
                    try {
                        d.c.a(thread2, str4, str5, str6, map2);
                    } catch (Throwable th) {
                        if (!w.b(th)) {
                            th.printStackTrace();
                        }
                        w.e("H5 crash error %s %s %s", str4, str5, str6);
                    }
                }
            });
        }
    }
}
