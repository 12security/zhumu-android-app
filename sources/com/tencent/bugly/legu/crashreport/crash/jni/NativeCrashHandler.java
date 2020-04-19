package com.tencent.bugly.legu.crashreport.crash.jni;

import android.annotation.SuppressLint;
import android.content.Context;
import com.tencent.bugly.legu.crashreport.common.info.a;
import com.tencent.bugly.legu.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.legu.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.legu.crashreport.crash.b;
import com.tencent.bugly.legu.crashreport.crash.c;
import com.tencent.bugly.legu.proguard.v;
import com.tencent.bugly.legu.proguard.w;
import java.io.File;

/* compiled from: BUGLY */
public class NativeCrashHandler {
    private static NativeCrashHandler a;
    private static boolean l = false;
    /* access modifiers changed from: private */
    public final Context b;
    private final a c;
    private final v d;
    /* access modifiers changed from: private */
    public NativeExceptionHandler e;
    /* access modifiers changed from: private */
    public String f;
    private final boolean g;
    private boolean h = false;
    private boolean i = false;
    private boolean j = false;
    private boolean k = false;
    /* access modifiers changed from: private */
    public b m;

    /* access modifiers changed from: protected */
    public native boolean appendNativeLog(String str, String str2, String str3);

    /* access modifiers changed from: protected */
    public native boolean appendWholeNativeLog(String str);

    /* access modifiers changed from: protected */
    public native String getNativeKeyValueList();

    /* access modifiers changed from: protected */
    public native String getNativeLog();

    /* access modifiers changed from: protected */
    public native boolean putNativeKeyValue(String str, String str2);

    /* access modifiers changed from: protected */
    public native String regist(String str, boolean z, int i2);

    /* access modifiers changed from: protected */
    public native String removeNativeKeyValue(String str);

    /* access modifiers changed from: protected */
    public native void testCrash();

    /* access modifiers changed from: protected */
    public native String unregist();

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0035  */
    @SuppressLint({"SdCardPath"})
    private NativeCrashHandler(Context context, a aVar, b bVar, v vVar, boolean z, String str) {
        Context applicationContext;
        boolean z2;
        if (context == null) {
            applicationContext = context;
        } else {
            applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                applicationContext = context;
            }
        }
        this.b = applicationContext;
        if (str != null) {
            try {
                if (str.trim().length() > 0) {
                    z2 = false;
                    if (z2) {
                        str = context.getDir("bugly", 0).getAbsolutePath();
                    }
                    this.m = bVar;
                    this.f = str;
                    this.c = aVar;
                    this.d = vVar;
                    this.g = z;
                }
            } catch (Throwable th) {
                str = "/data/data/" + a.a(context).c + "/app_bugly";
            }
        }
        z2 = true;
        if (z2) {
        }
        this.m = bVar;
        this.f = str;
        this.c = aVar;
        this.d = vVar;
        this.g = z;
    }

    public static synchronized NativeCrashHandler getInstance(Context context, a aVar, b bVar, com.tencent.bugly.legu.crashreport.common.strategy.a aVar2, v vVar, boolean z, String str) {
        NativeCrashHandler nativeCrashHandler;
        synchronized (NativeCrashHandler.class) {
            if (a == null) {
                a = new NativeCrashHandler(context, aVar, bVar, vVar, z, str);
            }
            nativeCrashHandler = a;
        }
        return nativeCrashHandler;
    }

    public static synchronized NativeCrashHandler getInstance() {
        NativeCrashHandler nativeCrashHandler;
        synchronized (NativeCrashHandler.class) {
            nativeCrashHandler = a;
        }
        return nativeCrashHandler;
    }

    public synchronized String getDumpFilePath() {
        return this.f;
    }

    public synchronized void setDumpFilePath(String str) {
        this.f = str;
    }

    private synchronized void a(boolean z) {
        if (this.j) {
            w.d("native already registed!", new Object[0]);
        } else {
            this.e = new a(this.b, this.c, this.m, com.tencent.bugly.legu.crashreport.common.strategy.a.a(), this.f);
            if (this.i) {
                try {
                    String regist = regist(this.f, z, 1);
                    if (regist != null) {
                        w.a("Native Crash Report enable!", new Object[0]);
                        w.c("Check extra jni for Bugly NDK v%s", regist);
                        String replace = "2.1.1".replace(".", "");
                        String replace2 = regist.replace(".", "");
                        if (replace2.length() == 2) {
                            replace2 = replace2 + "0";
                        } else if (replace2.length() == 1) {
                            replace2 = replace2 + "00";
                        }
                        try {
                            if (Integer.parseInt(replace2) >= Integer.parseInt(replace)) {
                                l = true;
                            }
                        } catch (Throwable th) {
                        }
                        if (l) {
                            w.a("Extra bugly jni can be accessed.", new Object[0]);
                        } else {
                            w.d("Extra bugly jni can not be accessed.", new Object[0]);
                        }
                        this.c.l = regist;
                        this.j = true;
                    }
                } catch (Throwable th2) {
                    w.c("load bugly so fail", new Object[0]);
                }
            } else if (this.h) {
                try {
                    String str = (String) com.tencent.bugly.legu.proguard.a.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "registNativeExceptionHandler2", null, new Class[]{String.class, String.class, Integer.TYPE, Integer.TYPE}, new Object[]{this.f, a.a().q(), Integer.valueOf(a.a().F()), Integer.valueOf(1)});
                    if (str == null) {
                        str = (String) com.tencent.bugly.legu.proguard.a.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "registNativeExceptionHandler", null, new Class[]{String.class, String.class, Integer.TYPE}, new Object[]{this.f, a.a().q(), Integer.valueOf(a.a().F())});
                    }
                    if (str != null) {
                        this.j = true;
                        a.a().l = str;
                        com.tencent.bugly.legu.proguard.a.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "enableHandler", null, new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(true)});
                        com.tencent.bugly.legu.proguard.a.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "setLogMode", null, new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(com.tencent.bugly.legu.b.b ? 3 : 5)});
                    }
                } catch (Throwable th3) {
                }
            }
            this.i = false;
            this.h = false;
        }
    }

    public synchronized void startNativeMonitor() {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = true;
        synchronized (this) {
            if (this.i || this.h) {
                a(this.g);
            } else {
                String str = this.c.k;
                if (str == null || str.trim().length() <= 0) {
                    z = true;
                } else {
                    z = false;
                }
                if (!z) {
                    String str2 = this.c.k;
                }
                String str3 = this.c.k;
                if (str3 == null || str3.trim().length() <= 0) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                String str4 = z2 ? "Bugly" : this.c.k;
                String str5 = this.c.k;
                if (str5 == null || str5.trim().length() <= 0) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z3) {
                    z4 = false;
                }
                this.i = a(str4, z4);
                if (this.i || this.h) {
                    a(this.g);
                    this.d.b(new Runnable() {
                        public final void run() {
                            if (!com.tencent.bugly.legu.proguard.a.a(NativeCrashHandler.this.b, "native_record_lock", 10000)) {
                                w.a("Failed to lock file for handling native crash record.", new Object[0]);
                                return;
                            }
                            CrashDetailBean a2 = b.a(NativeCrashHandler.this.b, NativeCrashHandler.this.f, NativeCrashHandler.this.e);
                            if (a2 != null) {
                                if (!NativeCrashHandler.this.m.a(a2)) {
                                    NativeCrashHandler.this.m.a(a2, 5000, false);
                                }
                                b.b(NativeCrashHandler.this.f);
                                w.a("get crash from native record!", new Object[0]);
                            }
                            NativeCrashHandler.this.a();
                            com.tencent.bugly.legu.proguard.a.b(NativeCrashHandler.this.b, "native_record_lock");
                        }
                    });
                }
            }
        }
    }

    private static boolean a(String str, boolean z) {
        Throwable th;
        boolean z2;
        try {
            w.a("[native] trying to load so: %s", str);
            if (z) {
                System.load(str);
            } else {
                System.loadLibrary(str);
            }
            try {
                w.a("[native] load so success: %s", str);
                return true;
            } catch (Throwable th2) {
                th = th2;
                z2 = true;
            }
        } catch (Throwable th3) {
            th = th3;
            z2 = false;
        }
        w.d(th.getMessage(), new Object[0]);
        w.d("[native] Failed to load so, please check.", str);
        return z2;
    }

    private synchronized void b() {
        if (!this.j) {
            w.d("native already unregisted!", new Object[0]);
        } else {
            try {
                if (unregist() != null) {
                    w.a("Native Crash Report close!", new Object[0]);
                    this.j = false;
                } else {
                    w.c("unregist bugly so success", new Object[0]);
                    try {
                        com.tencent.bugly.legu.proguard.a.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "enableHandler", null, new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(false)});
                        this.j = false;
                        w.c("unregist rqd so success", new Object[0]);
                    } catch (Throwable th) {
                        w.c("unregist rqd so fail", new Object[0]);
                        this.i = false;
                        this.h = false;
                    }
                }
            } catch (Throwable th2) {
                w.c("unregist bugly so fail", new Object[0]);
            }
        }
        return;
    }

    public void testNativeCrash() {
        if (!this.i) {
            w.d("libBugly.so has not been load! so fail!", new Object[0]);
        } else {
            testCrash();
        }
    }

    public NativeExceptionHandler getNativeExceptionHandler() {
        return this.e;
    }

    /* access modifiers changed from: protected */
    public final void a() {
        long o = com.tencent.bugly.legu.proguard.a.o() - c.f;
        File file = new File(this.f);
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                String str = "tomb_";
                String str2 = ".txt";
                int length = str.length();
                int i2 = 0;
                for (File file2 : listFiles) {
                    String name = file2.getName();
                    if (name.startsWith(str)) {
                        try {
                            int indexOf = name.indexOf(str2);
                            if (indexOf > 0 && Long.parseLong(name.substring(length, indexOf)) >= o) {
                            }
                        } catch (Throwable th) {
                            w.e("tomb format error delete %s", name);
                        }
                        if (file2.delete()) {
                            i2++;
                        }
                    }
                }
                w.c("clean tombs %d", Integer.valueOf(i2));
            }
        }
    }

    private synchronized void b(boolean z) {
        if (z) {
            startNativeMonitor();
        } else {
            b();
        }
    }

    public synchronized boolean isUserOpened() {
        return this.k;
    }

    private synchronized void c(boolean z) {
        if (this.k != z) {
            w.a("user change native %b", Boolean.valueOf(z));
            this.k = z;
        }
    }

    public void setUserOpened(boolean z) {
        c(z);
        boolean z2 = com.tencent.bugly.legu.crashreport.common.strategy.a.a().c().d && isUserOpened();
        if (z2 != this.j) {
            w.a("native changed to %b", Boolean.valueOf(z2));
            b(z2);
        }
    }

    public synchronized void onStrategyChanged(StrategyBean strategyBean) {
        boolean z = true;
        synchronized (this) {
            if (strategyBean != null) {
                if (strategyBean.d != this.j) {
                    w.d("server native changed to %b", Boolean.valueOf(strategyBean.d));
                }
            }
            if (!com.tencent.bugly.legu.crashreport.common.strategy.a.a().c().d || !this.k) {
                z = false;
            }
            if (z != this.j) {
                w.a("native changed to %b", Boolean.valueOf(z));
                b(z);
            }
        }
    }

    public boolean appendLogToNative(String str, String str2, String str3) {
        boolean z = false;
        if (!this.i || !l || str == null || str2 == null || str3 == null) {
            return z;
        }
        try {
            return appendNativeLog(str, str2, str3);
        } catch (UnsatisfiedLinkError e2) {
            l = z;
            return z;
        } catch (Throwable th) {
            if (w.a(th)) {
                return z;
            }
            th.printStackTrace();
            return z;
        }
    }

    public boolean putKeyValueToNative(String str, String str2) {
        boolean z = false;
        if (!this.i || !l || str == null || str2 == null) {
            return z;
        }
        try {
            return putNativeKeyValue(str, str2);
        } catch (UnsatisfiedLinkError e2) {
            l = z;
            return z;
        } catch (Throwable th) {
            if (w.a(th)) {
                return z;
            }
            th.printStackTrace();
            return z;
        }
    }
}
