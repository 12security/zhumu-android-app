package com.tencent.bugly.legu;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.bugly.legu.crashreport.common.info.a;
import com.tencent.bugly.legu.proguard.n;
import com.tencent.bugly.legu.proguard.o;
import com.tencent.bugly.legu.proguard.w;
import java.util.Map;

/* compiled from: BUGLY */
public class Bugly {
    public static final String SDK_IS_DEV = "false";
    private static boolean a;
    public static Context applicationContext = null;
    private static String[] b = {"BuglyCrashModule", "BuglyRqdModule", "BuglyBetaModule"};
    private static String[] c = {"BuglyRqdModule", "BuglyCrashModule", "BuglyBetaModule"};
    public static boolean enable = true;
    public static Boolean isDev;

    public static void init(Context context, String str, boolean z) {
        init(context, str, z, null);
    }

    public static synchronized void init(Context context, String str, boolean z, BuglyStrategy buglyStrategy) {
        String[] strArr;
        synchronized (Bugly.class) {
            if (!a) {
                a = true;
                if (context != null) {
                    Context applicationContext2 = context.getApplicationContext();
                    if (applicationContext2 != null) {
                        context = applicationContext2;
                    }
                }
                applicationContext = context;
                if (context == null) {
                    Log.e(w.a, "init arg 'context' should not be null!");
                } else {
                    if (isDev()) {
                        b = c;
                    }
                    for (String str2 : b) {
                        try {
                            if (str2.equals("BuglyCrashModule")) {
                                b.a((a) CrashModule.getInstance());
                            } else if (!str2.equals("BuglyBetaModule") && !str2.equals("BuglyRqdModule")) {
                                str2.equals("BuglyFeedbackModule");
                            }
                        } catch (Throwable th) {
                            w.b(th);
                        }
                    }
                    b.a = enable;
                    b.a(applicationContext, str, z, buglyStrategy);
                }
            }
        }
    }

    public static synchronized String getAppChannel() {
        String str = null;
        synchronized (Bugly.class) {
            a a2 = a.a();
            if (a2 != null) {
                if (TextUtils.isEmpty(a2.j)) {
                    o a3 = o.a();
                    if (a3 == null) {
                        str = a2.j;
                    } else {
                        Map a4 = a3.a(556, (n) null, true);
                        if (a4 != null) {
                            byte[] bArr = (byte[]) a4.get("app_channel");
                            if (bArr != null) {
                                str = new String(bArr);
                            }
                        }
                    }
                }
                str = a2.j;
            }
        }
        return str;
    }

    public static boolean isDev() {
        if (isDev == null) {
            isDev = Boolean.valueOf(Boolean.parseBoolean(SDK_IS_DEV.replace("@", "")));
        }
        return isDev.booleanValue();
    }
}
