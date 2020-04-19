package com.tencent.bugly.legu;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.bugly.legu.crashreport.common.info.a;
import com.tencent.bugly.legu.proguard.m;
import com.tencent.bugly.legu.proguard.n;
import com.tencent.bugly.legu.proguard.o;
import com.tencent.bugly.legu.proguard.t;
import com.tencent.bugly.legu.proguard.w;
import com.tencent.bugly.legu.proguard.x;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: BUGLY */
public final class b {
    public static boolean a = true;
    public static boolean b;
    public static Map<String, String> c = null;
    private static List<a> d = new ArrayList();
    private static o e;
    private static m f;
    private static boolean g;

    private static boolean a(a aVar) {
        String str;
        List<String> list = aVar.m;
        aVar.getClass();
        if ("legu".equals("")) {
            str = "bugly";
        } else {
            aVar.getClass();
            str = "legu";
        }
        if (list == null || !list.contains(str)) {
            return false;
        }
        return true;
    }

    public static synchronized void a(Context context) {
        synchronized (b.class) {
            a(context, null);
        }
    }

    public static synchronized void a(Context context, BuglyStrategy buglyStrategy) {
        synchronized (b.class) {
            if (g) {
                w.d("[init] initial Multi-times, ignore this.", new Object[0]);
            } else if (context == null) {
                Log.w(w.a, "[init] context of init() is null, check it.");
            } else {
                a a2 = a.a(context);
                if (a(a2)) {
                    a = false;
                } else {
                    String e2 = a2.e();
                    if (e2 == null) {
                        Log.e(w.a, "[init] meta data of BUGLY_APPID in AndroidManifest.xml should be set.");
                    } else {
                        a(context, e2, a2.t, buglyStrategy);
                    }
                }
            }
        }
    }

    public static synchronized void a(Context context, String str, boolean z, BuglyStrategy buglyStrategy) {
        String str2;
        String str3;
        String str4;
        String str5;
        synchronized (b.class) {
            if (g) {
                w.d("[init] initial Multi-times, ignore this.", new Object[0]);
            } else if (context == null) {
                Log.w(w.a, "[init] context of init() is null, check it.");
            } else if (str == null) {
                Log.e(w.a, "init arg 'crashReportAppID' should not be null!");
            } else {
                g = true;
                if (z) {
                    b = true;
                    w.b = true;
                    w.d("Bugly debug模式开启，请在发布时把isDebug关闭。 -- Running in debug model for 'isDebug' is enabled. Please disable it when you release.", new Object[0]);
                    w.e("--------------------------------------------------------------------------------------------", new Object[0]);
                    w.d("Bugly debug模式将有以下行为特性 -- The following list shows the behaviour of debug model: ", new Object[0]);
                    w.d("[1] 输出详细的Bugly SDK的Log -- More detailed log of Bugly SDK will be output to logcat;", new Object[0]);
                    w.d("[2] 每一条Crash都会被立即上报 -- Every crash caught by Bugly will be uploaded immediately.", new Object[0]);
                    w.d("[3] 自定义日志将会在Logcat中输出 -- Custom log will be output to logcat.", new Object[0]);
                    w.e("--------------------------------------------------------------------------------------------", new Object[0]);
                    w.b("[init] bugly in debug mode.", new Object[0]);
                }
                if (context != null) {
                    Context applicationContext = context.getApplicationContext();
                    if (applicationContext != null) {
                        context = applicationContext;
                    }
                }
                x.a(context);
                a a2 = a.a(context);
                e = o.a(context, d);
                t.a(context);
                com.tencent.bugly.legu.crashreport.common.strategy.a.a(context, d);
                f = m.a(context);
                if (a(a2)) {
                    a = false;
                } else {
                    StringBuilder sb = new StringBuilder();
                    a2.getClass();
                    w.a(sb.append("legu crash report start init!").toString(), new Object[0]);
                    w.b("[init] bugly start init...", new Object[0]);
                    a2.a(str);
                    w.a("[param] setted APPID:%s", str);
                    if (buglyStrategy != null) {
                        String appVersion = buglyStrategy.getAppVersion();
                        if (!TextUtils.isEmpty(appVersion)) {
                            if (appVersion.length() > 100) {
                                str5 = appVersion.substring(0, 100);
                                w.d("appVersion %s length is over limit %d substring to %s", appVersion, Integer.valueOf(100), str5);
                            } else {
                                str5 = appVersion;
                            }
                            a2.i = str5;
                            w.a("setted APPVERSION:%s", buglyStrategy.getAppVersion());
                        }
                        try {
                            if (buglyStrategy.isReplaceOldChannel()) {
                                String appChannel = buglyStrategy.getAppChannel();
                                if (!TextUtils.isEmpty(appChannel)) {
                                    if (appChannel.length() > 100) {
                                        String substring = appChannel.substring(0, 100);
                                        w.d("appChannel %s length is over limit %d substring to %s", appChannel, Integer.valueOf(100), substring);
                                        str4 = substring;
                                    } else {
                                        str4 = appChannel;
                                    }
                                    e.a(556, "app_channel", str4.getBytes(), (n) null, false);
                                    a2.j = str4;
                                }
                            } else {
                                Map a3 = e.a(556, (n) null, true);
                                if (a3 != null) {
                                    byte[] bArr = (byte[]) a3.get("app_channel");
                                    if (bArr != null) {
                                        a2.j = new String(bArr);
                                    }
                                }
                            }
                            w.a("setted APPCHANNEL:%s", a2.j);
                        } catch (Exception e2) {
                            if (b) {
                                e2.printStackTrace();
                            }
                        }
                        String appPackageName = buglyStrategy.getAppPackageName();
                        if (!TextUtils.isEmpty(appPackageName)) {
                            if (appPackageName.length() > 100) {
                                str3 = appPackageName.substring(0, 100);
                                w.d("appPackageName %s length is over limit %d substring to %s", appPackageName, Integer.valueOf(100), str3);
                            } else {
                                str3 = appPackageName;
                            }
                            a2.c = str3;
                            w.a("setted PACKAGENAME:%s", buglyStrategy.getAppPackageName());
                        }
                        String deviceID = buglyStrategy.getDeviceID();
                        if (deviceID != null) {
                            if (deviceID.length() > 100) {
                                str2 = deviceID.substring(0, 100);
                                w.d("deviceId %s length is over limit %d substring to %s", deviceID, Integer.valueOf(100), str2);
                            } else {
                                str2 = deviceID;
                            }
                            a2.c(str2);
                            w.a("setted deviceId :%s", str2);
                        }
                        x.a = buglyStrategy.isBuglyLogUpload();
                    }
                    com.tencent.bugly.legu.crashreport.biz.b.a(context, buglyStrategy);
                    f.b();
                    for (int i = 0; i < d.size(); i++) {
                        try {
                            if (f.a(((a) d.get(i)).id)) {
                                ((a) d.get(i)).init(context, z, buglyStrategy);
                            }
                        } catch (Throwable th) {
                            if (!w.a(th)) {
                                th.printStackTrace();
                            }
                        }
                    }
                    w.a("crash report inited!", new Object[0]);
                    w.b("[init] bugly init finished.", new Object[0]);
                }
            }
        }
    }

    public static void a(a aVar) {
        if (!d.contains(aVar)) {
            d.add(aVar);
        }
    }
}
