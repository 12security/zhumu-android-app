package com.tencent.bugly.legu.crashreport;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.tencent.bugly.legu.BuglyStrategy;
import com.tencent.bugly.legu.BuglyStrategy.a;
import com.tencent.bugly.legu.CrashModule;
import com.tencent.bugly.legu.b;
import com.tencent.bugly.legu.crashreport.crash.BuglyBroadcastRecevier;
import com.tencent.bugly.legu.crashreport.crash.c;
import com.tencent.bugly.legu.crashreport.crash.h5.H5JavaScriptInterface;
import com.tencent.bugly.legu.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.legu.proguard.v;
import com.tencent.bugly.legu.proguard.w;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/* compiled from: BUGLY */
public class CrashReport {
    private static Context a;

    /* compiled from: BUGLY */
    public static class CrashHandleCallback extends a {
    }

    /* compiled from: BUGLY */
    public static class UserStrategy extends BuglyStrategy {
        private CrashHandleCallback a;

        public UserStrategy(Context context) {
        }

        public synchronized CrashHandleCallback getCrashHandleCallback() {
            return this.a;
        }

        public synchronized void setCrashHandleCallback(CrashHandleCallback crashHandleCallback) {
            this.a = crashHandleCallback;
        }
    }

    public static void enableBugly(boolean z) {
        b.a = z;
    }

    public static synchronized void initCrashReport(Context context) {
        synchronized (CrashReport.class) {
            a = context;
            b.a((com.tencent.bugly.legu.a) CrashModule.getInstance());
            b.a(context);
        }
    }

    public static synchronized void initCrashReport(Context context, UserStrategy userStrategy) {
        synchronized (CrashReport.class) {
            a = context;
            b.a((com.tencent.bugly.legu.a) CrashModule.getInstance());
            b.a(context, userStrategy);
        }
    }

    public static synchronized void initCrashReport(Context context, String str, boolean z) {
        synchronized (CrashReport.class) {
            initCrashReport(context, str, z, null);
        }
    }

    public static synchronized void initCrashReport(Context context, String str, boolean z, UserStrategy userStrategy) {
        synchronized (CrashReport.class) {
            if (context != null) {
                a = context;
                b.a((com.tencent.bugly.legu.a) CrashModule.getInstance());
                b.a(context, str, z, userStrategy);
            }
        }
    }

    public static String getBuglyVersion(Context context) {
        if (context == null) {
            w.d("Please call with context.", new Object[0]);
            return "unknown";
        }
        com.tencent.bugly.legu.crashreport.common.info.a.a(context);
        return com.tencent.bugly.legu.crashreport.common.info.a.b();
    }

    public static synchronized void testJavaCrash() {
        synchronized (CrashReport.class) {
            if (!b.a) {
                Log.w(w.a, "Can not test Java crash because bugly is disable.");
            } else if (!CrashModule.hasInitialized()) {
                Log.e(w.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            } else {
                throw new RuntimeException("This Crash create for Test! You can go to Bugly see more detail!");
            }
        }
    }

    public static synchronized void testNativeCrash() {
        synchronized (CrashReport.class) {
            if (!b.a) {
                Log.w(w.a, "Can not test native crash because bugly is disable.");
            } else if (!CrashModule.hasInitialized()) {
                Log.e(w.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            } else {
                w.a("start to create a native crash for test!", new Object[0]);
                c.a().j();
            }
        }
    }

    public static synchronized void testANRCrash() {
        synchronized (CrashReport.class) {
            if (!b.a) {
                Log.w(w.a, "Can not test ANR crash because bugly is disable.");
            } else if (!CrashModule.hasInitialized()) {
                Log.e(w.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            } else {
                w.a("start to create a anr crash for test!", new Object[0]);
                c.a().k();
            }
        }
    }

    public static synchronized void postCatchedException(Throwable th) {
        synchronized (CrashReport.class) {
            postCatchedException(th, false);
        }
    }

    public static synchronized void postCatchedException(Throwable th, boolean z) {
        synchronized (CrashReport.class) {
            if (!b.a) {
                Log.w(w.a, "Can not post crash caught because bugly is disable.");
            } else if (!CrashModule.hasInitialized()) {
                Log.e(w.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            } else if (th == null) {
                w.d("throwable is null, just return", new Object[0]);
            } else {
                c.a().a(Thread.currentThread(), th, false, null, null);
                if (z) {
                    w.a("clear user datas", new Object[0]);
                    com.tencent.bugly.legu.crashreport.common.info.a.a(a).x();
                }
            }
        }
    }

    public static synchronized void closeNativeReport() {
        synchronized (CrashReport.class) {
            if (!b.a) {
                Log.w(w.a, "Can not close native report because bugly is disable.");
            } else if (!CrashModule.hasInitialized()) {
                Log.e(w.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            } else {
                c.a().f();
            }
        }
    }

    public static synchronized void startCrashReport() {
        synchronized (CrashReport.class) {
            if (!b.a) {
                Log.w(w.a, "Can not start crash report because bugly is disable.");
            } else if (!CrashModule.hasInitialized()) {
                Log.w(w.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            } else {
                c.a().c();
            }
        }
    }

    public static synchronized void closeCrashReport() {
        synchronized (CrashReport.class) {
            if (!b.a) {
                Log.w(w.a, "Can not close crash report because bugly is disable.");
            } else if (!CrashModule.hasInitialized()) {
                Log.w(w.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            } else {
                c.a().d();
            }
        }
    }

    public static void closeBugly() {
        if (!b.a) {
            Log.w(w.a, "Can not close bugly because bugly is disable.");
        } else if (!CrashModule.hasInitialized()) {
            Log.w(w.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else if (a != null) {
            BuglyBroadcastRecevier instance = BuglyBroadcastRecevier.getInstance();
            if (instance != null) {
                instance.unregist(a);
            }
            closeCrashReport();
            com.tencent.bugly.legu.crashreport.biz.b.a(a);
            v a2 = v.a();
            if (a2 != null) {
                a2.b();
            }
        }
    }

    public static void setUserSceneTag(Context context, int i) {
        if (!b.a) {
            Log.w(w.a, "Can not set tag caught because bugly is disable.");
        } else if (context == null) {
            Log.e(w.a, "setTag args context should not be null");
        } else {
            if (i <= 0) {
                w.d("setTag args tagId should > 0", new Object[0]);
            }
            com.tencent.bugly.legu.crashreport.common.info.a.a(context).a(i);
            w.b("[param] set user scene tag: %d", Integer.valueOf(i));
        }
    }

    public static int getUserSceneTagId(Context context) {
        if (!b.a) {
            Log.w(w.a, "Can not get user scene tag because bugly is disable.");
            return -1;
        } else if (context != null) {
            return com.tencent.bugly.legu.crashreport.common.info.a.a(context).B();
        } else {
            Log.e(w.a, "getUserSceneTagId args context should not be null");
            return -1;
        }
    }

    public static String getUserData(Context context, String str) {
        if (!b.a) {
            Log.w(w.a, "Can not get user data because bugly is disable.");
            return "unknown";
        } else if (context == null) {
            Log.e(w.a, "getUserDataValue args context should not be null");
            return "unknown";
        } else {
            if (str == null || str.trim().length() <= 0) {
                return null;
            }
            return com.tencent.bugly.legu.crashreport.common.info.a.a(context).g(str);
        }
    }

    public static void putUserData(Context context, String str, String str2) {
        if (!b.a) {
            Log.w(w.a, "Can not put user data because bugly is disable.");
        } else if (context == null) {
            Log.w(w.a, "putUserData args context should not be null");
        } else if (str == null) {
            str;
            w.d("putUserData args key should not be null or empty", new Object[0]);
        } else if (str2 == null) {
            str2;
            w.d("putUserData args value should not be null", new Object[0]);
        } else if (!str.matches("[a-zA-Z[0-9]]+")) {
            w.d("putUserData args key should match [a-zA-Z[0-9]]+  {" + str + "}", new Object[0]);
        } else {
            if (str2.length() > 200) {
                w.d("user data value length over limit %d, it will be cutted!", Integer.valueOf(200));
                str2 = str2.substring(0, 200);
            }
            com.tencent.bugly.legu.crashreport.common.info.a a2 = com.tencent.bugly.legu.crashreport.common.info.a.a(context);
            if (a2.z().contains(str)) {
                NativeCrashHandler instance = NativeCrashHandler.getInstance();
                if (instance != null) {
                    instance.putKeyValueToNative(str, str2);
                }
                com.tencent.bugly.legu.crashreport.common.info.a.a(context).a(str, str2);
                w.c("replace KV %s %s", str, str2);
            } else if (a2.y() >= 10) {
                w.d("user data size is over limit %d, it will be cutted!", Integer.valueOf(10));
            } else {
                if (str.length() > 50) {
                    w.d("user data key length over limit %d , will drop this new key %s", Integer.valueOf(50), str);
                    str = str.substring(0, 50);
                }
                NativeCrashHandler instance2 = NativeCrashHandler.getInstance();
                if (instance2 != null) {
                    instance2.putKeyValueToNative(str, str2);
                }
                com.tencent.bugly.legu.crashreport.common.info.a.a(context).a(str, str2);
                w.b("[param] set user data: %s - %s", str, str2);
            }
        }
    }

    public static String removeUserData(Context context, String str) {
        boolean z;
        if (!b.a) {
            Log.w(w.a, "Can not remove user data because bugly is disable.");
            return "unknown";
        } else if (context == null) {
            Log.e(w.a, "removeUserData args context should not be null");
            return "unknown";
        } else {
            if (str == null || str.trim().length() <= 0) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                return null;
            }
            w.b("[param] remove user data: %s", str);
            return com.tencent.bugly.legu.crashreport.common.info.a.a(context).f(str);
        }
    }

    public static Set<String> getAllUserDataKeys(Context context) {
        if (!b.a) {
            Log.w(w.a, "Can not get all keys of user data because bugly is disable.");
            return new HashSet();
        } else if (context != null) {
            return com.tencent.bugly.legu.crashreport.common.info.a.a(context).z();
        } else {
            Log.e(w.a, "getAllUserDataKeys args context should not be null");
            return new HashSet();
        }
    }

    public static int getUserDatasSize(Context context) {
        if (!b.a) {
            Log.w(w.a, "Can not get size of user data because bugly is disable.");
            return -1;
        } else if (context != null) {
            return com.tencent.bugly.legu.crashreport.common.info.a.a(context).y();
        } else {
            Log.e(w.a, "getUserDatasSize args context should not be null");
            return -1;
        }
    }

    public static synchronized String getAppID() {
        String e;
        synchronized (CrashReport.class) {
            if (!b.a) {
                Log.w(w.a, "Can not get App ID because bugly is disable.");
                e = "unknown";
            } else if (!CrashModule.hasInitialized()) {
                Log.e(w.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
                e = "unknown";
            } else {
                e = com.tencent.bugly.legu.crashreport.common.info.a.a(a).e();
            }
        }
        return e;
    }

    public static void setUserId(String str) {
        if (!b.a) {
            Log.w(w.a, "Can not set user ID because bugly is disable.");
        } else if (!CrashModule.hasInitialized()) {
            Log.e(w.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            setUserId(a, str);
        }
    }

    public static void setUserId(Context context, String str) {
        if (!b.a) {
            Log.w(w.a, "Can not set user ID because bugly is disable.");
        } else if (context == null) {
            Log.e(w.a, "Context should not be null when bugly has not been initialed!");
        } else if (str == null) {
            w.d("userId should not be null", new Object[0]);
        } else {
            if (str.length() > 100) {
                String substring = str.substring(0, 100);
                w.d("userId %s length is over limit %d substring to %s", str, Integer.valueOf(100), substring);
                str = substring;
            }
            if (!str.equals(com.tencent.bugly.legu.crashreport.common.info.a.a(context).f())) {
                com.tencent.bugly.legu.crashreport.common.info.a.a(context).b(str);
                w.b("[user] set userId : %s", str);
                if (CrashModule.hasInitialized()) {
                    com.tencent.bugly.legu.crashreport.biz.b.a();
                }
            }
        }
    }

    public static synchronized String getUserId() {
        String f;
        synchronized (CrashReport.class) {
            if (!b.a) {
                Log.w(w.a, "Can not get user ID because bugly is disable.");
                f = "unknown";
            } else if (!CrashModule.hasInitialized()) {
                Log.e(w.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
                f = "unknown";
            } else {
                f = com.tencent.bugly.legu.crashreport.common.info.a.a(a).f();
            }
        }
        return f;
    }

    public static synchronized String getAppVer() {
        String str;
        synchronized (CrashReport.class) {
            if (!b.a) {
                Log.w(w.a, "Can not get app version because bugly is disable.");
                str = "unknown";
            } else if (!CrashModule.hasInitialized()) {
                Log.e(w.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
                str = "unknown";
            } else {
                str = com.tencent.bugly.legu.crashreport.common.info.a.a(a).i;
            }
        }
        return str;
    }

    public static synchronized String getAppChannel() {
        String str;
        synchronized (CrashReport.class) {
            if (!b.a) {
                Log.w(w.a, "Can not get App channel because bugly is disable.");
                str = "unknown";
            } else if (!CrashModule.hasInitialized()) {
                Log.e(w.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
                str = "unknown";
            } else {
                str = com.tencent.bugly.legu.crashreport.common.info.a.a(a).j;
            }
        }
        return str;
    }

    public static synchronized void setContext(Context context) {
        synchronized (CrashReport.class) {
            a = context;
        }
    }

    public static synchronized boolean isLastSessionCrash() {
        boolean z = false;
        synchronized (CrashReport.class) {
            if (!b.a) {
                Log.w(w.a, "The info 'isLastSessionCrash' is not accurate because bugly is disable.");
            } else if (!CrashModule.hasInitialized()) {
                Log.e(w.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            } else {
                z = c.a().b();
            }
        }
        return z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0023 A[ADDED_TO_REGION] */
    public static synchronized void setSdkExtraData(Context context, String str, String str2) {
        boolean z;
        Map<String, String> map;
        boolean z2 = false;
        synchronized (CrashReport.class) {
            if (!b.a) {
                Log.w(w.a, "Can not put SDK extra data because bugly is disable.");
            } else if (context != null) {
                if (str != null) {
                    if (str.trim().length() > 0) {
                        z = false;
                        if (!z) {
                            if (str2 == null || str2.trim().length() <= 0) {
                                z2 = true;
                            }
                            if (!z2) {
                                Map<String, String> map2 = b.c;
                                if (map2 == null) {
                                    map = new HashMap<>();
                                } else {
                                    map = map2;
                                }
                                map.put(str, str2);
                                if (map.size() > 0) {
                                    String str3 = "SDK_INFO";
                                    String str4 = "";
                                    for (Entry entry : map.entrySet()) {
                                        str4 = str4 + "[" + ((String) entry.getKey()) + "," + ((String) entry.getValue()) + "] ";
                                    }
                                    putSdkData(context, str3, str4);
                                }
                                b.c = map;
                            }
                        }
                    }
                }
                z = true;
                if (!z) {
                }
            }
        }
    }

    public static Map<String, String> getSdkExtraData() {
        if (b.a) {
            return b.c;
        }
        Log.w(w.a, "Can not get SDK extra data because bugly is disable.");
        return new HashMap();
    }

    private static void putSdkData(Context context, String str, String str2) {
        boolean z;
        boolean z2;
        if (context != null) {
            if (str == null || str.trim().length() <= 0) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                if (str2 == null || str2.trim().length() <= 0) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (!z2) {
                    String replace = str.replace("[a-zA-Z[0-9]]+", "");
                    if (replace.length() > 100) {
                        Log.w(w.a, String.format("putSdkData key length over limit %d, will be cutted.", new Object[]{Integer.valueOf(50)}));
                        replace = replace.substring(0, 50);
                    }
                    if (str2.length() > 500) {
                        Log.w(w.a, String.format("putSdkData value length over limit %d, will be cutted!", new Object[]{Integer.valueOf(200)}));
                        str2 = str2.substring(0, 200);
                    }
                    com.tencent.bugly.legu.crashreport.common.info.a.a(context).b(replace, str2);
                    w.b(String.format("[param] putSdkData data: %s - %s", new Object[]{replace, str2}), new Object[0]);
                }
            }
        }
    }

    public static void setIsAppForeground(Context context, boolean z) {
        if (!b.a) {
            Log.w(w.a, "Can not set 'isAppForeground' because bugly is disable.");
        } else if (context == null) {
            w.d("Context should not be null.", new Object[0]);
        } else {
            if (z) {
                w.c("App is in foreground.", new Object[0]);
            } else {
                w.c("App is in background.", new Object[0]);
            }
            com.tencent.bugly.legu.crashreport.common.info.a.a(context).n = z;
        }
    }

    public static void setIsDevelopmentDevice(Context context, boolean z) {
        if (!b.a) {
            Log.w(w.a, "Can not set 'isDevelopmentDevice' because bugly is disable.");
        } else if (context == null) {
            w.d("Context should not be null.", new Object[0]);
        } else {
            if (z) {
                w.c("This is a development device.", new Object[0]);
            } else {
                w.c("This is not a development device.", new Object[0]);
            }
            com.tencent.bugly.legu.crashreport.common.info.a.a(context).x = z;
        }
    }

    public static void setSessionIntervalMills(long j) {
        if (!b.a) {
            Log.w(w.a, "Can not set 'SessionIntervalMills' because bugly is disable.");
        } else {
            com.tencent.bugly.legu.crashreport.biz.b.a(j);
        }
    }

    public static void setAppVersion(Context context, String str) {
        if (!b.a) {
            Log.w(w.a, "Can not set APP version because bugly is disable.");
        } else if (context == null) {
            Log.w(w.a, "setAppVersion args context should not be null");
        } else if (str == null) {
            Log.w(w.a, "Version is null, will not set");
        } else {
            com.tencent.bugly.legu.crashreport.common.info.a.a(context).i = str;
        }
    }

    public static boolean setJavascriptMonitor(WebView webView, boolean z) {
        return setJavascriptMonitor(webView, z, false);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public static boolean setJavascriptMonitor(WebView webView, boolean z, boolean z2) {
        if (webView == null) {
            Log.w(w.a, "Webview is null.");
            return false;
        } else if (!CrashModule.hasInitialized()) {
            w.e("CrashReport has not been initialed! please to call method 'initCrashReport' first!", new Object[0]);
            return false;
        } else {
            w.a("Set Javascript exception monitor of webview.", new Object[0]);
            if (!b.a) {
                Log.w(w.a, "Can not set JavaScript monitor because bugly is disable.");
                return false;
            }
            w.c("URL of webview is %s", webView.getUrl());
            if (webView.getUrl() == null) {
                return false;
            }
            if (z2 || VERSION.SDK_INT >= 19) {
                WebSettings settings = webView.getSettings();
                if (!settings.getJavaScriptEnabled()) {
                    w.a("Enable the javascript needed by webview monitor.", new Object[0]);
                    settings.setJavaScriptEnabled(true);
                }
                H5JavaScriptInterface instance = H5JavaScriptInterface.getInstance(webView);
                if (instance != null) {
                    w.a("Add a secure javascript interface to the webview.", new Object[0]);
                    webView.addJavascriptInterface(instance, "exceptionUploader");
                }
                if (z) {
                    w.a("Inject bugly.js(v%s) to the webview.", com.tencent.bugly.legu.crashreport.crash.h5.c.b());
                    String a2 = com.tencent.bugly.legu.crashreport.crash.h5.c.a();
                    if (a2 == null) {
                        w.e("Failed to inject Bugly.js.", com.tencent.bugly.legu.crashreport.crash.h5.c.b());
                        return false;
                    }
                    webView.loadUrl("javascript:" + a2);
                }
                return true;
            }
            w.e("This interface is only available for Android 4.4 or later.", new Object[0]);
            return false;
        }
    }
}
