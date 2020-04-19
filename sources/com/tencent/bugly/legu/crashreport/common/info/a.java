package com.tencent.bugly.legu.crashreport.common.info;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.tencent.bugly.legu.b;
import com.tencent.bugly.legu.proguard.w;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/* compiled from: BUGLY */
public final class a {
    private static a V = null;
    private String A;
    private String B;
    private String C = "unknown";
    private String D = "unknown";
    private String E = "";
    private String F = null;
    private String G = null;
    private String H = null;
    private String I = null;
    private long J = -1;
    private long K = -1;
    private long L = -1;
    private String M = null;
    private String N = null;
    private Map<String, PlugInBean> O = null;
    private boolean P = true;
    private String Q = null;
    private String R = null;
    private Boolean S = null;
    private String T = null;
    private Map<String, PlugInBean> U = null;
    private int W = -1;
    private int X = -1;
    private Map<String, String> Y = new HashMap();
    private Map<String, String> Z = new HashMap();
    public final long a = System.currentTimeMillis();
    private final Object aa = new Object();
    private final Object ab = new Object();
    public final byte b;
    public String c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;
    public long h;
    public String i = null;
    public String j = null;
    public String k = null;
    public String l = null;
    public List<String> m = null;
    public boolean n;
    public String o = "unknown";
    public long p = 0;
    public long q = 0;
    public long r = 0;
    public long s = 0;
    public boolean t = false;
    public String u = null;
    public String v = null;
    public String w = null;
    public boolean x = false;
    public boolean y = false;
    private final Context z;

    private a(Context context) {
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
        this.z = applicationContext;
        this.b = 1;
        PackageInfo b2 = AppInfo.b(context);
        if (b2 != null) {
            this.i = AppInfo.a(b2);
            this.u = this.i;
            this.v = AppInfo.b(b2);
        }
        this.c = AppInfo.a(context);
        this.d = AppInfo.c(context);
        this.e = com.tencent.bugly.legu.proguard.a.m();
        this.f = com.tencent.bugly.legu.proguard.a.b();
        this.g = "Android " + com.tencent.bugly.legu.proguard.a.c() + ",level " + com.tencent.bugly.legu.proguard.a.d();
        this.f + ";" + this.g;
        Map d2 = AppInfo.d(context);
        if (d2 != null) {
            try {
                this.m = AppInfo.a(d2);
                String str = (String) d2.get("BUGLY_APPID");
                if (str != null) {
                    this.R = str;
                }
                String str2 = (String) d2.get("BUGLY_APP_VERSION");
                if (str2 != null) {
                    this.i = str2;
                }
                String str3 = (String) d2.get("BUGLY_APP_CHANNEL");
                if (str3 != null) {
                    this.j = str3;
                }
                String str4 = (String) d2.get("BUGLY_ENABLE_DEBUG");
                if (str4 != null) {
                    if (str4.toLowerCase().equals("true")) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    this.t = z2;
                }
                String str5 = (String) d2.get("com.tencent.rdm.uuid");
                if (str5 != null) {
                    this.w = str5;
                }
            } catch (Throwable th) {
                if (!w.a(th)) {
                    th.printStackTrace();
                }
            }
        }
        try {
            if (!context.getDatabasePath("bugly_db_legu").exists()) {
                this.y = true;
                w.c("App is first time to be installed on the device.", new Object[0]);
            }
        } catch (Throwable th2) {
            if (b.b) {
                th2.printStackTrace();
            }
        }
        w.c("com info create end", new Object[0]);
    }

    public static synchronized a a(Context context) {
        a aVar;
        synchronized (a.class) {
            if (V == null) {
                V = new a(context);
            }
            aVar = V;
        }
        return aVar;
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            aVar = V;
        }
        return aVar;
    }

    public static String b() {
        return "2.1.9";
    }

    public final void c() {
        synchronized (this.aa) {
            this.A = UUID.randomUUID().toString();
        }
    }

    public final String d() {
        if (this.A == null) {
            synchronized (this.aa) {
                if (this.A == null) {
                    this.A = UUID.randomUUID().toString();
                }
            }
        }
        return this.A;
    }

    public final String e() {
        String str = null;
        return !(str == null || str.trim().length() <= 0) ? str : this.R;
    }

    public final void a(String str) {
        this.R = str;
    }

    public final synchronized String f() {
        return this.C;
    }

    public final synchronized void b(String str) {
        if (str == null) {
            str = "10000";
        }
        this.C = str;
    }

    public final synchronized String g() {
        String str;
        if (this.B != null) {
            str = this.B;
        } else {
            this.B = j() + "|" + l() + "|" + m();
            str = this.B;
        }
        return str;
    }

    public final synchronized void c(String str) {
        this.B = str;
    }

    public final synchronized String h() {
        return this.D;
    }

    public final synchronized void d(String str) {
        this.D = str;
    }

    public final synchronized String i() {
        return this.E;
    }

    public final synchronized void e(String str) {
        this.E = str;
    }

    public final synchronized String j() {
        String str;
        if (!this.P) {
            str = "";
        } else {
            if (this.F == null) {
                this.F = com.tencent.bugly.legu.proguard.a.a(this.z);
            }
            str = this.F;
        }
        return str;
    }

    public final synchronized String k() {
        String str;
        if (!this.P) {
            str = "";
        } else {
            if (this.G == null) {
                this.G = com.tencent.bugly.legu.proguard.a.d(this.z);
            }
            str = this.G;
        }
        return str;
    }

    public final synchronized String l() {
        String str;
        if (!this.P) {
            str = "";
        } else {
            if (this.H == null) {
                this.H = com.tencent.bugly.legu.proguard.a.b(this.z);
            }
            str = this.H;
        }
        return str;
    }

    public final synchronized String m() {
        String str;
        if (!this.P) {
            str = "";
        } else {
            if (this.I == null) {
                this.I = com.tencent.bugly.legu.proguard.a.c(this.z);
            }
            str = this.I;
        }
        return str;
    }

    public final synchronized long n() {
        if (this.J <= 0) {
            this.J = com.tencent.bugly.legu.proguard.a.f();
        }
        return this.J;
    }

    public final synchronized long o() {
        if (this.K <= 0) {
            this.K = com.tencent.bugly.legu.proguard.a.h();
        }
        return this.K;
    }

    public final synchronized long p() {
        if (this.L <= 0) {
            this.L = com.tencent.bugly.legu.proguard.a.j();
        }
        return this.L;
    }

    public final synchronized String q() {
        if (this.M == null) {
            this.M = com.tencent.bugly.legu.proguard.a.e();
        }
        return this.M;
    }

    public final String r() {
        if (this.N == null) {
            synchronized (this.ab) {
                if (this.N == null) {
                    this.N = com.tencent.bugly.legu.proguard.a.a(this.z, "ro.board.platform");
                }
            }
        }
        return this.N;
    }

    public final synchronized Map<String, PlugInBean> s() {
        return null;
    }

    public final String t() {
        if (this.Q == null) {
            this.Q = com.tencent.bugly.legu.proguard.a.l();
        }
        return this.Q;
    }

    public final synchronized Boolean u() {
        if (this.S == null) {
            this.S = Boolean.valueOf(com.tencent.bugly.legu.proguard.a.f(this.z));
        }
        return this.S;
    }

    public final synchronized String v() {
        String str;
        boolean z2;
        String str2;
        boolean z3 = false;
        synchronized (this) {
            if (this.T == null) {
                StringBuilder sb = new StringBuilder();
                Context context = this.z;
                String a2 = com.tencent.bugly.legu.proguard.a.a(context, "ro.miui.ui.version.name");
                if (a2 == null || a2.trim().length() <= 0) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (z2 || a2.equals("fail")) {
                    String a3 = com.tencent.bugly.legu.proguard.a.a(context, "ro.build.version.emui");
                    if ((a3 == null || a3.trim().length() <= 0) || a3.equals("fail")) {
                        String a4 = com.tencent.bugly.legu.proguard.a.a(context, "ro.lenovo.series");
                        if ((a4 == null || a4.trim().length() <= 0) || a4.equals("fail")) {
                            String a5 = com.tencent.bugly.legu.proguard.a.a(context, "ro.build.nubia.rom.name");
                            if ((a5 == null || a5.trim().length() <= 0) || a5.equals("fail")) {
                                String a6 = com.tencent.bugly.legu.proguard.a.a(context, "ro.meizu.product.model");
                                if ((a6 == null || a6.trim().length() <= 0) || a6.equals("fail")) {
                                    String a7 = com.tencent.bugly.legu.proguard.a.a(context, "ro.build.version.opporom");
                                    if ((a7 == null || a7.trim().length() <= 0) || a7.equals("fail")) {
                                        String a8 = com.tencent.bugly.legu.proguard.a.a(context, "ro.vivo.os.build.display.id");
                                        if ((a8 == null || a8.trim().length() <= 0) || a8.equals("fail")) {
                                            String a9 = com.tencent.bugly.legu.proguard.a.a(context, "ro.aa.romver");
                                            if ((a9 == null || a9.trim().length() <= 0) || a9.equals("fail")) {
                                                String a10 = com.tencent.bugly.legu.proguard.a.a(context, "ro.lewa.version");
                                                if ((a10 == null || a10.trim().length() <= 0) || a10.equals("fail")) {
                                                    String a11 = com.tencent.bugly.legu.proguard.a.a(context, "ro.gn.gnromvernumber");
                                                    if ((a11 == null || a11.trim().length() <= 0) || a11.equals("fail")) {
                                                        String a12 = com.tencent.bugly.legu.proguard.a.a(context, "ro.build.tyd.kbstyle_version");
                                                        if (a12 == null || a12.trim().length() <= 0) {
                                                            z3 = true;
                                                        }
                                                        str2 = (z3 || a12.equals("fail")) ? com.tencent.bugly.legu.proguard.a.a(context, "ro.build.fingerprint") + "/" + com.tencent.bugly.legu.proguard.a.a(context, "ro.build.rom.id") : "dido/" + a12;
                                                    } else {
                                                        str2 = "amigo/" + a11 + "/" + com.tencent.bugly.legu.proguard.a.a(context, "ro.build.display.id");
                                                    }
                                                } else {
                                                    str2 = "tcl/" + a10 + "/" + com.tencent.bugly.legu.proguard.a.a(context, "ro.build.display.id");
                                                }
                                            } else {
                                                str2 = "htc/" + a9 + "/" + com.tencent.bugly.legu.proguard.a.a(context, "ro.build.description");
                                            }
                                        } else {
                                            str2 = "vivo/FUNTOUCH/" + a8;
                                        }
                                    } else {
                                        str2 = "Oppo/COLOROS/" + a7;
                                    }
                                } else {
                                    str2 = "Meizu/FLYME/" + com.tencent.bugly.legu.proguard.a.a(context, "ro.build.display.id");
                                }
                            } else {
                                str2 = "Zte/NUBIA/" + a5 + "_" + com.tencent.bugly.legu.proguard.a.a(context, "ro.build.nubia.rom.code");
                            }
                        } else {
                            str2 = "Lenovo/VIBE/" + com.tencent.bugly.legu.proguard.a.a(context, "ro.build.version.incremental");
                        }
                    } else {
                        str2 = "HuaWei/EMOTION/" + a3;
                    }
                } else {
                    str2 = "XiaoMi/MIUI/" + a2;
                }
                this.T = sb.append(str2).toString();
                w.a("rom:%s", this.T);
            }
            str = this.T;
        }
        return str;
    }

    public final synchronized Map<String, String> w() {
        HashMap hashMap;
        if (this.Y.size() <= 0) {
            hashMap = null;
        } else {
            hashMap = new HashMap(this.Y);
        }
        return hashMap;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000d, code lost:
        if (r5.trim().length() > 0) goto L_0x000f;
     */
    public final synchronized String f(String str) {
        String str2;
        boolean z2 = false;
        synchronized (this) {
            if (str != null) {
            }
            z2 = true;
            if (z2) {
                w.d("key should not be empty %s", str);
                str2 = null;
            } else {
                str2 = (String) this.Y.remove(str);
            }
        }
        return str2;
    }

    public final synchronized void x() {
        this.Y.clear();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000d, code lost:
        if (r5.trim().length() > 0) goto L_0x000f;
     */
    public final synchronized String g(String str) {
        String str2;
        boolean z2 = false;
        synchronized (this) {
            if (str != null) {
            }
            z2 = true;
            if (z2) {
                w.d("key should not be empty %s", str);
                str2 = null;
            } else {
                str2 = (String) this.Y.get(str);
            }
        }
        return str2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x0012 A[ADDED_TO_REGION] */
    public final synchronized void a(String str, String str2) {
        boolean z2;
        boolean z3 = false;
        synchronized (this) {
            if (str != null) {
                if (str.trim().length() > 0) {
                    z2 = false;
                    if (!z2) {
                        if (str2 == null || str2.trim().length() <= 0) {
                            z3 = true;
                        }
                        if (!z3) {
                            this.Y.put(str, str2);
                        }
                    }
                    w.d("key&value should not be empty %s %s", str, str2);
                }
            }
            z2 = true;
            if (!z2) {
            }
            w.d("key&value should not be empty %s %s", str, str2);
        }
    }

    public final synchronized int y() {
        return this.Y.size();
    }

    public final synchronized Set<String> z() {
        return this.Y.keySet();
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x0012 A[ADDED_TO_REGION] */
    public final synchronized void b(String str, String str2) {
        boolean z2;
        boolean z3 = false;
        synchronized (this) {
            if (str != null) {
                if (str.trim().length() > 0) {
                    z2 = false;
                    if (!z2) {
                        if (str2 == null || str2.trim().length() <= 0) {
                            z3 = true;
                        }
                        if (!z3) {
                            this.Z.put(str, str2);
                        }
                    }
                    w.d("server key&value should not be empty %s %s", str, str2);
                }
            }
            z2 = true;
            if (!z2) {
            }
            w.d("server key&value should not be empty %s %s", str, str2);
        }
    }

    public final synchronized Map<String, String> A() {
        HashMap hashMap;
        if (this.Z.size() <= 0) {
            hashMap = null;
        } else {
            hashMap = new HashMap(this.Z);
        }
        return hashMap;
    }

    public final synchronized void a(int i2) {
        int i3 = this.W;
        if (i3 != i2) {
            this.W = i2;
            w.a("user scene tag %d changed to tag %d", Integer.valueOf(i3), Integer.valueOf(this.W));
        }
    }

    public final synchronized int B() {
        return this.W;
    }

    public final synchronized int C() {
        return this.X;
    }

    public final synchronized boolean D() {
        return AppInfo.e(this.z);
    }

    public final synchronized Map<String, PlugInBean> E() {
        return null;
    }

    public final synchronized int F() {
        return com.tencent.bugly.legu.proguard.a.d();
    }
}
