package com.tencent.bugly.legu.proguard;

import android.content.Context;
import android.os.Process;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/* compiled from: BUGLY */
public final class x {
    public static boolean a = true;
    private static SimpleDateFormat b;
    /* access modifiers changed from: private */
    public static int c = 5120;
    private static StringBuilder d;
    /* access modifiers changed from: private */
    public static StringBuilder e;
    /* access modifiers changed from: private */
    public static a f;
    private static String g;
    private static String h;
    private static Context i;
    /* access modifiers changed from: private */
    public static String j;
    private static boolean k;
    private static int l;
    /* access modifiers changed from: private */
    public static Object m = new Object();
    private static Object n = null;
    private static Method o;

    /* compiled from: BUGLY */
    public static class a {
        /* access modifiers changed from: private */
        public boolean a;
        /* access modifiers changed from: private */
        public File b;
        private String c;
        private long d;
        /* access modifiers changed from: private */
        public long e = 30720;

        public a(String str) {
            if (str != null && !str.equals("")) {
                this.c = str;
                this.a = a();
            }
        }

        /* access modifiers changed from: private */
        public synchronized boolean a() {
            boolean z = false;
            synchronized (this) {
                try {
                    this.b = new File(this.c);
                    if (!this.b.exists() || this.b.delete()) {
                        if (!this.b.createNewFile()) {
                            this.a = false;
                        }
                        z = true;
                    } else {
                        this.a = false;
                    }
                } catch (Throwable th) {
                    this.a = false;
                }
            }
            return z;
        }

        public final synchronized boolean a(String str) {
            boolean z = false;
            synchronized (this) {
                if (this.a) {
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(this.b, true);
                        byte[] bytes = str.getBytes("UTF-8");
                        fileOutputStream.write(bytes);
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        this.d = ((long) bytes.length) + this.d;
                        z = true;
                    } catch (Throwable th) {
                        this.a = false;
                    }
                }
            }
            return z;
        }
    }

    static {
        b = null;
        o = null;
        try {
            b = new SimpleDateFormat("MM-dd HH:mm:ss");
            o = Class.forName("com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler").getDeclaredMethod("appendLogToNative", new Class[]{String.class, String.class, String.class});
        } catch (Throwable th) {
        }
    }

    private static boolean b(String str, String str2, String str3) {
        if (o == null) {
            return false;
        }
        if (n == null) {
            Object a2 = a.a("com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler", "getInstance", null, null, null);
            n = a2;
            if (a2 == null) {
                return false;
            }
        }
        try {
            return ((Boolean) o.invoke(n, new Object[]{str, str2, str3})).booleanValue();
        } catch (Throwable th) {
            Log.w(w.a, th.getMessage());
            return false;
        }
    }

    public static synchronized void a(Context context) {
        synchronized (x.class) {
            if (!k && context != null && a) {
                try {
                    e = new StringBuilder(0);
                    d = new StringBuilder(0);
                    i = context;
                    com.tencent.bugly.legu.crashreport.common.info.a a2 = com.tencent.bugly.legu.crashreport.common.info.a.a(context);
                    g = a2.d;
                    a2.getClass();
                    h = "legu";
                    j = i.getFilesDir().getPath() + "/buglylog_" + g + "_" + h + ".txt";
                    l = Process.myPid();
                } catch (Throwable th) {
                }
                k = true;
            }
        }
    }

    public static void a(int i2) {
        synchronized (m) {
            c = i2;
            if (i2 < 0) {
                c = 0;
            } else if (i2 > 10240) {
                c = 10240;
            }
        }
    }

    public static void a(String str, String str2, Throwable th) {
        String stringWriter;
        if (th != null) {
            String message = th.getMessage();
            if (message == null) {
                message = "";
            }
            StringBuilder append = new StringBuilder().append(message).append(10);
            if (th == null) {
                stringWriter = "";
            } else {
                StringWriter stringWriter2 = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter2);
                th.printStackTrace(printWriter);
                printWriter.flush();
                stringWriter = stringWriter2.toString();
            }
            a(str, str2, append.append(stringWriter).toString());
        }
    }

    public static synchronized void a(String str, String str2, String str3) {
        synchronized (x.class) {
            if (k && a) {
                b(str, str2, str3);
                int myTid = Process.myTid();
                d.setLength(0);
                if (str3.length() > 30720) {
                    str3 = str3.substring(str3.length() - 30720, str3.length() - 1);
                }
                Date date = new Date();
                d.append(b != null ? b.format(date) : date.toString()).append(" ").append(l).append(" ").append(myTid).append(" ").append(str).append(" ").append(str2).append(": ").append(str3).append("\u0001\r\n");
                final String sb = d.toString();
                synchronized (m) {
                    e.append(sb);
                }
                if (e.length() > c) {
                    v.a().a(new Runnable() {
                        public final void run() {
                            synchronized (x.m) {
                                try {
                                    if (x.e.length() > x.c) {
                                        if (x.f == null) {
                                            x.f = new a(x.j);
                                        } else if (x.f.b.length() + ((long) x.e.length()) > x.f.e) {
                                            x.f.a();
                                        }
                                        if (x.f.a) {
                                            x.f.a(x.e.toString());
                                            x.e.setLength(0);
                                        } else {
                                            x.e.setLength(0);
                                            x.e.append(sb);
                                        }
                                    }
                                } catch (Throwable th) {
                                }
                            }
                        }
                    });
                }
            }
        }
    }

    public static byte[] a(boolean z) {
        File file;
        byte[] bArr = 0;
        if (a) {
            synchronized (m) {
                if (z) {
                    try {
                        if (f != null && f.a) {
                            file = f.b;
                            if (e.length() == 0 || file != 0) {
                                bArr = a.a(file, e.toString());
                            }
                        }
                    } catch (Throwable th) {
                    }
                }
                file = bArr;
                if (e.length() == 0) {
                }
                bArr = a.a(file, e.toString());
            }
        }
        return bArr;
    }
}
