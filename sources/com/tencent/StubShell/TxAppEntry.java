package com.tencent.StubShell;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Debug;
import android.os.Handler;
import android.os.Process;
import android.util.Log;
import com.tencent.bugly.legu.crashreport.CrashReport;
import com.tencent.bugly.legu.crashreport.CrashReport.UserStrategy;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;

public class TxAppEntry extends Application {
    public static String a = "";
    public static String b = "";
    public static String c = "___XSSTUB___";
    static int d = 0;
    static boolean e = false;
    private static Context f = null;
    private static String g = null;
    private static String h = null;
    /* access modifiers changed from: private */
    public static String i = "";
    private static boolean j = false;
    private static String mOldAPPName = null;
    public static Object mPClassLoader = null;
    private static String mPKName = null;
    private static String mSocPath = null;
    /* access modifiers changed from: private */
    public static String mSrcPath = null;
    private static String mVerFilePath = null;
    private static boolean mbVerCheck = false;
    private static final String version = "154172cd4b2dd508af3a487b01c79939313ec15299fb2b582177513e3cda5a7b";
    private Handler k = null;

    static {
        try {
            j = "__TRUE__".equals(c);
        } catch (Throwable th) {
            Log.w("SecShell", th);
        }
    }

    public static void a(Intent intent) {
        reciver(intent);
    }

    private void a(String str) {
        String str2;
        String str3;
        boolean z;
        ApplicationInfo applicationInfo = getApplicationInfo();
        String str4 = applicationInfo.dataDir + "/tx_shell";
        b = applicationInfo.sourceDir;
        int i2 = VERSION.SDK_INT;
        boolean z2 = false;
        if (i2 >= 19) {
            if (i2 > 19) {
                String[] strArr = Build.SUPPORTED_64_BIT_ABIS;
                if (strArr != null && strArr.length > 1) {
                    z2 = true;
                }
            } else if (new File("/system/lib64").exists()) {
                z2 = true;
            }
        }
        String str5 = null;
        if (VERSION.SDK_INT < 21) {
            str5 = Build.CPU_ABI;
        }
        if ((str5 == null || str5.length() < 2) && VERSION.SDK_INT >= 21) {
            str5 = Build.SUPPORTED_ABIS[0];
        }
        boolean z3 = true;
        if (str5.toLowerCase(Locale.US).contains("x86")) {
            z3 = false;
        } else if (VERSION.SDK_INT >= 21) {
            String[] strArr2 = Build.SUPPORTED_ABIS;
            if (strArr2 != null) {
                for (String lowerCase : strArr2) {
                    if (lowerCase.toLowerCase(Locale.US).contains("x86")) {
                        z3 = false;
                    }
                }
            }
        }
        boolean z4 = false;
        if (str5.toLowerCase(Locale.US).contains("mips")) {
            z4 = true;
        }
        String str6 = VERSION.SDK_INT > 8 ? applicationInfo.nativeLibraryDir : "/data/data/" + mPKName + "/lib";
        String str7 = "";
        String str8 = "";
        String str9 = "";
        String str10 = "";
        if (z3) {
            str2 = str + d();
        } else {
            str2 = str + d();
            str10 = str + d();
        }
        String str11 = str7 + "lib" + str2 + ".so";
        String str12 = str8 + "lib" + str10 + ".so";
        File file = new File(str6 + "/" + str11);
        File file2 = new File(str4 + "/" + str12);
        if (!z3 && VERSION.SDK_INT < 19) {
            if (!file2.exists()) {
                if (ZipUtil.exist(b, "lib/armeabi/" + str12) == 0) {
                }
            }
            try {
                Runtime.getRuntime().exec("chmod 700 " + str4 + "/" + str12);
                System.load(str4 + "/" + str12);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } else if (file.exists()) {
            System.loadLibrary(str2);
        } else {
            String str13 = "";
            String str14 = str4 + "/" + str11;
            if (ZipUtil.exist(b, "lib/" + str5 + "/" + str11) == 0) {
                str3 = "lib/" + str5 + "/" + str11;
            } else {
                if (z2) {
                    if (z3) {
                        if (ZipUtil.exist(b, "lib/arm64-v8a/" + str11) == 0) {
                            str3 = "lib/arm64-v8a/" + str11;
                        } else if (ZipUtil.exist(b, "lib/armeabi/" + str11) == 0) {
                            str3 = "lib/armeabi/" + str11;
                        } else if (ZipUtil.exist(b, "lib/armeabi-v7a/" + str11) == 0) {
                            str3 = "lib/armeabi-v7a/" + str11;
                        }
                    } else if (ZipUtil.exist(b, "lib/x86_64/" + str11) == 0) {
                        str3 = "lib/x86_64/" + str11;
                    } else if (ZipUtil.exist(b, "lib/arm64-v8a/" + str11) == 0) {
                        str3 = "lib/arm64-v8a/" + str11;
                    } else if (ZipUtil.exist(b, "lib/x86/" + str11) == 0) {
                        str3 = "lib/x86/" + str11;
                    } else if (ZipUtil.exist(b, "lib/armeabi/" + str11) == 0) {
                        str3 = "lib/armeabi/" + str11;
                    } else if (ZipUtil.exist(b, "lib/armeabi-v7a/" + str11) == 0) {
                        str3 = "lib/armeabi-v7a/" + str11;
                    }
                } else if (z3) {
                    if (!z4 || ZipUtil.exist(b, "lib/mips/" + str11) != 0) {
                        z = false;
                        str3 = str13;
                    } else {
                        z = true;
                        str3 = "lib/mips/" + str11;
                    }
                    if (!z) {
                        if (ZipUtil.exist(b, "lib/armeabi/" + str11) == 0) {
                            str3 = "lib/armeabi/" + str11;
                        } else if (ZipUtil.exist(b, "lib/armeabi-v7a/" + str11) == 0) {
                            str3 = "lib/armeabi-v7a/" + str11;
                        }
                    }
                } else if (ZipUtil.exist(b, "lib/x86/" + str11) == 0) {
                    str3 = "lib/x86/" + str11;
                } else if (ZipUtil.exist(b, "lib/armeabi/" + str11) == 0) {
                    str3 = "lib/armeabi/" + str11;
                } else if (ZipUtil.exist(b, "lib/armeabi-v7a/" + str11) == 0) {
                    str3 = "lib/armeabi-v7a/" + str11;
                }
                str3 = str13;
            }
            if (ZipUtil.extract(b, str3, str14) == 0) {
                try {
                    Runtime.getRuntime().exec("chmod 700 " + str14);
                    System.load(str14);
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    private boolean b(Context context) {
        if (context == null) {
            return false;
        }
        f = context;
        mPKName = getBaseContext().getPackageName();
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(mPKName, 128);
        } catch (NameNotFoundException e2) {
        }
        if (applicationInfo == null) {
            return false;
        }
        mOldAPPName = applicationInfo.metaData.getString("TxAppEntry");
        mSrcPath = f.getApplicationInfo().sourceDir;
        try {
            i = f.getPackageManager().getPackageInfo(mPKName, 0).versionName;
        } catch (Exception e3) {
        }
        mVerFilePath = "/data/data/";
        mVerFilePath += mPKName;
        mVerFilePath += "/tx_shell/";
        a = mVerFilePath;
        File file = new File(mVerFilePath);
        if (!file.exists()) {
            file.mkdir();
        }
        g = mVerFilePath + "libshella.so";
        h = mVerFilePath + "libshellb.so";
        mSocPath = mVerFilePath + "libshellc.so";
        mVerFilePath += g();
        return true;
    }

    private String c() {
        return "3.0.0.0";
    }

    private void c(Context context) {
        String str = i;
        String str2 = "10000";
        if (!str2.equals("user_bugly_appid")) {
            UserStrategy userStrategy = new UserStrategy(context);
            userStrategy.setAppVersion(str);
            userStrategy.setEnableUserInfo(false);
            userStrategy.setRecordUserInfoOnceADay(true);
            CrashReport.setSdkExtraData(context, str2, str);
            CrashReport.initCrashReport(context, str2, false, userStrategy);
        }
    }

    private static native void changeEnv(Context context);

    private String d() {
        return "";
    }

    private void d(Context context) {
        AssetManager assets = context.getAssets();
        String str = context.getApplicationInfo().sourceDir;
        try {
            System.loadLibrary("nfix");
            fixNativeResource(assets, str);
        } catch (Throwable th) {
        }
        try {
            System.loadLibrary("ufix");
            fixUnityResource(assets, str);
        } catch (Throwable th2) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:104:0x045b  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x0584  */
    private void e() {
        String str;
        boolean z;
        String str2;
        boolean z2;
        String str3;
        ApplicationInfo applicationInfo = getApplicationInfo();
        String str4 = applicationInfo.dataDir + "/tx_shell";
        b = applicationInfo.sourceDir;
        int i2 = VERSION.SDK_INT;
        boolean z3 = false;
        if (i2 >= 19) {
            if (i2 > 19) {
                String[] strArr = Build.SUPPORTED_64_BIT_ABIS;
                if (strArr != null && strArr.length > 1) {
                    z3 = true;
                }
            } else if (new File("/system/lib64").exists()) {
                z3 = true;
            }
        }
        String str5 = null;
        if (VERSION.SDK_INT < 21) {
            str5 = Build.CPU_ABI;
        }
        if ((str5 == null || str5.length() < 2) && VERSION.SDK_INT >= 21) {
            str5 = Build.SUPPORTED_ABIS[0];
        }
        boolean z4 = true;
        if (str5.toLowerCase(Locale.US).contains("x86")) {
            z4 = false;
        } else if (VERSION.SDK_INT >= 21) {
            String[] strArr2 = Build.SUPPORTED_ABIS;
            if (strArr2 != null) {
                for (String lowerCase : strArr2) {
                    if (lowerCase.toLowerCase(Locale.US).contains("x86")) {
                        z4 = false;
                    }
                }
            }
        }
        boolean z5 = false;
        if (str5.toLowerCase(Locale.US).contains("mips")) {
            z5 = true;
        }
        String str6 = VERSION.SDK_INT > 8 ? applicationInfo.nativeLibraryDir : "/data/data/" + mPKName + "/lib";
        String str7 = "";
        String str8 = "";
        String str9 = "";
        String str10 = "";
        if (z4) {
            str = f() + "-" + c();
        } else {
            str = "shellx-" + c();
            str10 = f() + "-" + c();
        }
        String str11 = str7 + "lib" + str + ".so";
        String str12 = str8 + "lib" + str10 + ".so";
        File file = new File(str6 + "/" + str11);
        File file2 = new File(str4 + "/" + str12);
        if (!z4 && VERSION.SDK_INT < 19) {
            if (!file2.exists()) {
                if (ZipUtil.exist(b, "lib/armeabi/" + str12) == 0) {
                }
            }
            try {
                Runtime.getRuntime().exec("chmod 700 " + str4 + "/" + str12);
                System.load(str4 + "/" + str12);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } else if (file.exists()) {
            System.loadLibrary(str);
        } else {
            String str13 = "";
            String str14 = str4 + "/" + str11;
            if (ZipUtil.exist(b, "lib/" + str5 + "/" + str11) == 0) {
                str13 = "lib/" + str5 + "/" + str11;
            } else if (z3) {
                if (!z4) {
                    if (VERSION.SDK_INT >= 21) {
                        String[] strArr3 = Build.SUPPORTED_ABIS;
                        String str15 = "";
                        if (strArr3 != null) {
                            int i3 = 0;
                            while (true) {
                                if (i3 >= strArr3.length) {
                                    break;
                                } else if (ZipUtil.exist(b, "lib/" + strArr3[i3].toLowerCase(Locale.US) + "/" + str12) == 0 || ZipUtil.exist(b, "lib/" + strArr3[i3].toLowerCase(Locale.US) + "/" + str11) == 0) {
                                    String lowerCase2 = strArr3[i3].toLowerCase(Locale.US);
                                    z2 = true;
                                } else {
                                    i3++;
                                }
                            }
                            String lowerCase22 = strArr3[i3].toLowerCase(Locale.US);
                            z2 = true;
                            if (ZipUtil.exist(b, "lib/x86_64/" + str11) == 0) {
                                str3 = "lib/x86_64/" + str11;
                            } else {
                                if (lowerCase22.compareTo("arm64-v8a") == 0) {
                                    if (ZipUtil.exist(b, "lib/arm64-v8a/" + str11) == 0) {
                                        str3 = "lib/arm64-v8a/" + str11;
                                    }
                                } else if (lowerCase22.compareTo("x86") == 0) {
                                    if (ZipUtil.exist(b, "lib/x86/" + str11) == 0) {
                                        str3 = "lib/x86/" + str11;
                                    }
                                } else if (lowerCase22.compareTo("armeabi-v7a") == 0 || lowerCase22.compareTo("armeabi") == 0) {
                                    if (ZipUtil.exist(b, "lib/x86/" + str11) == 0) {
                                        str3 = "lib/x86/" + str11;
                                    } else if (ZipUtil.exist(b, "lib/armeabi/" + str11) == 0) {
                                        str3 = "lib/armeabi/" + str11;
                                    } else if (ZipUtil.exist(b, "lib/armeabi-v7a/" + str11) == 0) {
                                        str3 = "lib/armeabi-v7a/" + str11;
                                    }
                                }
                                str3 = str13;
                            }
                            if (VERSION.SDK_INT < 21 || !z2) {
                                if (ZipUtil.exist(b, "lib/x86_64/" + str11) != 0) {
                                    str3 = "lib/x86_64/" + str11;
                                } else if (ZipUtil.exist(b, "lib/arm64-v8a/" + str11) == 0) {
                                    str3 = "lib/arm64-v8a/" + str11;
                                } else if (ZipUtil.exist(b, "lib/x86/" + str11) == 0) {
                                    str3 = "lib/x86/" + str11;
                                } else if (ZipUtil.exist(b, "lib/armeabi/" + str11) == 0) {
                                    str3 = "lib/armeabi/" + str11;
                                } else if (ZipUtil.exist(b, "lib/armeabi-v7a/" + str11) == 0) {
                                    str3 = "lib/armeabi-v7a/" + str11;
                                }
                            }
                            str13 = str3;
                        }
                    }
                    z2 = false;
                    str3 = str13;
                    if (ZipUtil.exist(b, "lib/x86_64/" + str11) != 0) {
                    }
                    str13 = str3;
                } else if (ZipUtil.exist(b, "lib/arm64-v8a/" + str11) == 0) {
                    str13 = "lib/arm64-v8a/" + str11;
                } else if (ZipUtil.exist(b, "lib/armeabi/" + str11) == 0) {
                    str13 = "lib/armeabi/" + str11;
                } else if (ZipUtil.exist(b, "lib/armeabi-v7a/" + str11) == 0) {
                    str13 = "lib/armeabi-v7a/" + str11;
                }
            } else if (z4) {
                if (!z5 || ZipUtil.exist(b, "lib/mips/" + str11) != 0) {
                    z = false;
                    str2 = str13;
                } else {
                    z = true;
                    str2 = "lib/mips/" + str11;
                }
                if (!z) {
                    if (ZipUtil.exist(b, "lib/armeabi/" + str11) == 0) {
                        str2 = "lib/armeabi/" + str11;
                    } else if (ZipUtil.exist(b, "lib/armeabi-v7a/" + str11) == 0) {
                        str2 = "lib/armeabi-v7a/" + str11;
                    }
                }
                str13 = str2;
            } else if (ZipUtil.exist(b, "lib/x86/" + str11) == 0) {
                str13 = "lib/x86/" + str11;
            } else if (ZipUtil.exist(b, "lib/armeabi/" + str11) == 0) {
                str13 = "lib/armeabi/" + str11;
            } else if (ZipUtil.exist(b, "lib/armeabi-v7a/" + str11) == 0) {
                str13 = "lib/armeabi-v7a/" + str11;
            }
            if (ZipUtil.extract(b, str13, str14) == 0) {
                try {
                    Runtime.getRuntime().exec("chmod 700 " + str14);
                    System.load(str14);
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    private static boolean e(Context context) {
        if (!Debug.isDebuggerConnected()) {
            return false;
        }
        Process.killProcess(Process.myPid());
        return true;
    }

    private static String f() {
        StringBuilder sb = new StringBuilder();
        sb.append("sh");
        sb.append("el");
        sb.append("la");
        return sb.toString();
    }

    private static native void fixNativeResource(AssetManager assetManager, String str);

    private static native void fixUnityResource(AssetManager assetManager, String str);

    private static String g() {
        StringBuilder sb = new StringBuilder();
        sb.append("sh");
        sb.append("el");
        sb.append("la");
        sb.append("_ve");
        sb.append("rs");
        sb.append("i");
        sb.append("on");
        return sb.toString();
    }

    private static native void load(Context context);

    private static native void reciver(Intent intent);

    private static native void runCreate(Context context);

    public static native Enumeration txEntries(DexFile dexFile);

    public void a(Context context) {
        e();
        load(f);
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        e(context);
        SystemClassLoaderInjector.fixAndroid(context, this);
        if (b(this)) {
            d(context);
            this.k = new Handler(getMainLooper());
            String str = "3.0.0.0";
            String str2 = "900015015";
            UserStrategy userStrategy = new UserStrategy(this);
            userStrategy.setAppVersion(str);
            CrashReport.setSdkExtraData(this, str2, str);
            CrashReport.initCrashReport(this, str2, false, userStrategy);
            new Thread(new d(this)).start();
            a((Context) this);
        }
    }

    public String getPackageName() {
        StackTraceElement[] stackTrace;
        try {
            for (StackTraceElement stackTraceElement : new Exception().getStackTrace()) {
                if (stackTraceElement.getClassName().compareTo("android.app.ActivityThread") == 0 && stackTraceElement.getMethodName().compareTo("installProvider") == 0) {
                    d++;
                    Log.d("SecShell", "getPackageName g_trickCount:" + d + " mPKName:" + mPKName);
                    if (d == 1) {
                        return mPKName == null ? "" : mPKName;
                    }
                    if (d > 1) {
                        Log.d("SecShell", "getPackageName change_flag:" + e + " ret:" + mPKName + " sp:" + j);
                        if (!e && !j) {
                            Log.d("SecShell", "getPackageName changeEnv call");
                            changeEnv(this);
                            Log.d("SecShell", "getPackageName changeEnv end");
                            e = true;
                        }
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return mPKName;
    }

    public void onCreate() {
        e(this);
        runCreate(this);
        c(f);
    }
}
