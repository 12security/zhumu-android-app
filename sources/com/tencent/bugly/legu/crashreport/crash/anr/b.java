package com.tencent.bugly.legu.crashreport.crash.anr;

import android.app.ActivityManager;
import android.app.ActivityManager.ProcessErrorStateInfo;
import android.content.Context;
import android.os.FileObserver;
import com.tencent.bugly.legu.crashreport.common.info.a;
import com.tencent.bugly.legu.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.legu.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.legu.crashreport.crash.c;
import com.tencent.bugly.legu.proguard.v;
import com.tencent.bugly.legu.proguard.w;
import com.tencent.bugly.legu.proguard.x;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: BUGLY */
public final class b {
    private AtomicInteger a = new AtomicInteger(0);
    private long b = -1;
    private final Context c;
    private final a d;
    private final v e;
    private final com.tencent.bugly.legu.crashreport.common.strategy.a f;
    private final String g;
    private final com.tencent.bugly.legu.crashreport.crash.b h;
    private FileObserver i;
    private boolean j = true;

    public b(Context context, com.tencent.bugly.legu.crashreport.common.strategy.a aVar, a aVar2, v vVar, com.tencent.bugly.legu.crashreport.crash.b bVar) {
        Context applicationContext;
        if (context == null) {
            applicationContext = context;
        } else {
            applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                applicationContext = context;
            }
        }
        this.c = applicationContext;
        this.g = context.getDir("bugly", 0).getAbsolutePath();
        this.d = aVar2;
        this.e = vVar;
        this.f = aVar;
        this.h = bVar;
    }

    private static ProcessErrorStateInfo a(Context context, long j2) {
        long j3 = 10000 < 0 ? 0 : 10000;
        w.c("to find!", new Object[0]);
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        long j4 = j3 / 500;
        int i2 = 0;
        while (true) {
            w.c("waiting!", new Object[0]);
            List<ProcessErrorStateInfo> processesInErrorState = activityManager.getProcessesInErrorState();
            if (processesInErrorState != null) {
                for (ProcessErrorStateInfo processErrorStateInfo : processesInErrorState) {
                    if (processErrorStateInfo.condition == 2) {
                        w.c("found!", new Object[0]);
                        return processErrorStateInfo;
                    }
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            int i3 = i2 + 1;
            if (((long) i2) >= j4) {
                w.c("end!", new Object[0]);
                return null;
            }
            i2 = i3;
        }
    }

    private CrashDetailBean a(a aVar) {
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        try {
            crashDetailBean.B = com.tencent.bugly.legu.proguard.a.i();
            crashDetailBean.C = com.tencent.bugly.legu.proguard.a.g();
            crashDetailBean.D = com.tencent.bugly.legu.proguard.a.k();
            crashDetailBean.E = this.d.o();
            crashDetailBean.F = this.d.n();
            crashDetailBean.G = this.d.p();
            crashDetailBean.w = com.tencent.bugly.legu.proguard.a.a(this.c, c.d, (String) null);
            crashDetailBean.x = x.a(true);
            crashDetailBean.b = 3;
            crashDetailBean.e = this.d.g();
            crashDetailBean.f = this.d.i;
            crashDetailBean.g = this.d.t();
            crashDetailBean.m = this.d.f();
            crashDetailBean.n = "ANR_EXCEPTION";
            crashDetailBean.o = aVar.f;
            crashDetailBean.q = aVar.g;
            crashDetailBean.N = new HashMap();
            crashDetailBean.N.put("BUGLY_CR_01", aVar.e);
            int indexOf = crashDetailBean.q.indexOf("\n");
            crashDetailBean.p = indexOf > 0 ? crashDetailBean.q.substring(0, indexOf) : "GET_FAIL";
            crashDetailBean.r = aVar.c;
            crashDetailBean.u = com.tencent.bugly.legu.proguard.a.b(crashDetailBean.q.getBytes());
            crashDetailBean.y = aVar.b;
            crashDetailBean.z = this.d.d;
            crashDetailBean.A = "main(1)";
            crashDetailBean.H = this.d.v();
            crashDetailBean.h = this.d.s();
            crashDetailBean.i = this.d.E();
            crashDetailBean.v = aVar.d;
            crashDetailBean.K = this.d.l;
            crashDetailBean.L = this.d.a;
            crashDetailBean.M = this.d.n;
            crashDetailBean.O = this.d.B();
            crashDetailBean.P = this.d.C();
            crashDetailBean.Q = this.d.w();
            crashDetailBean.R = this.d.A();
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
        }
        return crashDetailBean;
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x017d A[Catch:{ all -> 0x01e7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x01b0 A[SYNTHETIC, Splitter:B:52:0x01b0] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x01d6 A[SYNTHETIC, Splitter:B:69:0x01d6] */
    private static boolean a(String str, String str2, String str3) {
        BufferedWriter bufferedWriter;
        TraceFileHelper.a readTargetDumpInfo = TraceFileHelper.readTargetDumpInfo(str3, str, true);
        if (readTargetDumpInfo == null || readTargetDumpInfo.d == null || readTargetDumpInfo.d.size() <= 0) {
            w.e("not found trace dump for %s", str3);
            return false;
        }
        File file = new File(str2);
        try {
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
            if (!file.exists() || !file.canWrite()) {
                w.e("backup file create fail %s", str2);
                return false;
            }
            BufferedWriter bufferedWriter2 = null;
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(file, false));
                try {
                    String[] strArr = (String[]) readTargetDumpInfo.d.get("main");
                    if (strArr != null && strArr.length >= 3) {
                        String str4 = strArr[0];
                        bufferedWriter.write("\"main\" tid=" + strArr[2] + " :\n" + str4 + "\n" + strArr[1] + "\n\n");
                        bufferedWriter.flush();
                    }
                    for (Entry entry : readTargetDumpInfo.d.entrySet()) {
                        if (!((String) entry.getKey()).equals("main") && entry.getValue() != null && ((String[]) entry.getValue()).length >= 3) {
                            String str5 = ((String[]) entry.getValue())[0];
                            bufferedWriter.write("\"" + ((String) entry.getKey()) + "\" tid=" + ((String[]) entry.getValue())[2] + " :\n" + str5 + "\n" + ((String[]) entry.getValue())[1] + "\n\n");
                            bufferedWriter.flush();
                        }
                    }
                    try {
                        bufferedWriter.close();
                    } catch (IOException e2) {
                        if (!w.a(e2)) {
                            e2.printStackTrace();
                        }
                    }
                    return true;
                } catch (IOException e3) {
                    e = e3;
                    bufferedWriter2 = bufferedWriter;
                    try {
                        if (!w.a(e)) {
                        }
                        w.e("dump trace fail %s", e.getClass().getName() + ":" + e.getMessage());
                        if (bufferedWriter2 != null) {
                        }
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        bufferedWriter = bufferedWriter2;
                        if (bufferedWriter != null) {
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedWriter != null) {
                    }
                    throw th;
                }
            } catch (IOException e4) {
                e = e4;
                if (!w.a(e)) {
                    e.printStackTrace();
                }
                w.e("dump trace fail %s", e.getClass().getName() + ":" + e.getMessage());
                if (bufferedWriter2 != null) {
                    try {
                        bufferedWriter2.close();
                    } catch (IOException e5) {
                        if (!w.a(e5)) {
                            e5.printStackTrace();
                        }
                    }
                }
                return false;
            } catch (Throwable th3) {
                th = th3;
                bufferedWriter = null;
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.close();
                    } catch (IOException e6) {
                        if (!w.a(e6)) {
                            e6.printStackTrace();
                        }
                    }
                }
                throw th;
            }
        } catch (Exception e7) {
            if (!w.a(e7)) {
                e7.printStackTrace();
            }
            w.e("backup file create error! %s  %s", e7.getClass().getName() + ":" + e7.getMessage(), str2);
            return false;
        }
    }

    public final boolean a() {
        return this.a.get() != 0;
    }

    private boolean a(Context context, String str, ProcessErrorStateInfo processErrorStateInfo, long j2, Map<String, String> map) {
        int size;
        this.f.c();
        if (!this.f.b()) {
            w.e("waiting for remote sync", new Object[0]);
            int i2 = 0;
            while (!this.f.b()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                i2 += 500;
                if (i2 >= 5000) {
                    break;
                }
            }
        }
        File file = new File(context.getFilesDir(), "bugly/bugly_trace_" + j2 + ".txt");
        a aVar = new a();
        aVar.c = j2;
        aVar.d = file.getAbsolutePath();
        aVar.a = processErrorStateInfo.processName;
        aVar.f = processErrorStateInfo.shortMsg;
        aVar.e = processErrorStateInfo.longMsg;
        aVar.b = map;
        if (map != null) {
            for (String str2 : map.keySet()) {
                if (str2.startsWith("main(")) {
                    aVar.g = (String) map.get(str2);
                }
            }
        }
        String str3 = "anr tm:%d\ntr:%s\nproc:%s\nsMsg:%s\n lMsg:%s\n threads:%d";
        Object[] objArr = new Object[6];
        objArr[0] = Long.valueOf(aVar.c);
        objArr[1] = aVar.d;
        objArr[2] = aVar.a;
        objArr[3] = aVar.f;
        objArr[4] = aVar.e;
        if (aVar.b == null) {
            size = 0;
        } else {
            size = aVar.b.size();
        }
        objArr[5] = Integer.valueOf(size);
        w.c(str3, objArr);
        if (!this.f.b()) {
            w.e("crash report sync remote fail, will not upload to Bugly , print local for helpful!", new Object[0]);
            com.tencent.bugly.legu.crashreport.crash.b.a("ANR", com.tencent.bugly.legu.proguard.a.n(), aVar.a, null, aVar.e, null);
            return false;
        } else if (!this.f.c().g) {
            w.d("ANR Report is closed!", new Object[0]);
            return false;
        } else {
            w.a("found visiable anr , start to upload!", new Object[0]);
            CrashDetailBean a2 = a(aVar);
            if (a2 == null) {
                w.e("pack anr fail!", new Object[0]);
                return false;
            }
            c.a().a(a2);
            if (a2.a >= 0) {
                w.a("backup anr record success!", new Object[0]);
            } else {
                w.d("backup anr record fail!", new Object[0]);
            }
            if (str != null && new File(str).exists()) {
                this.a.set(3);
                if (a(str, aVar.d, aVar.a)) {
                    w.a("backup trace success", new Object[0]);
                }
            }
            com.tencent.bugly.legu.crashreport.crash.b.a("ANR", com.tencent.bugly.legu.proguard.a.n(), aVar.a, null, aVar.e, a2);
            if (!this.h.a(a2)) {
                this.h.a(a2, 5000, true);
            }
            this.h.b(a2);
            return true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        com.tencent.bugly.legu.proguard.w.c("read trace first dump for create time!", new java.lang.Object[0]);
        r2 = com.tencent.bugly.legu.crashreport.crash.anr.TraceFileHelper.readFirstDumpInfo(r11, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002c, code lost:
        if (r2 == null) goto L_0x0114;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002e, code lost:
        r4 = r2.c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0032, code lost:
        if (r4 != -1) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0034, code lost:
        com.tencent.bugly.legu.proguard.w.d("trace dump fail could not get time!", new java.lang.Object[0]);
        r4 = java.lang.System.currentTimeMillis();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004a, code lost:
        if (java.lang.Math.abs(r4 - r10.b) >= 10000) goto L_0x0066;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004c, code lost:
        com.tencent.bugly.legu.proguard.w.d("should not process ANR too Fre in %d", java.lang.Integer.valueOf(10000));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005d, code lost:
        r10.a.set(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r10.b = r4;
        r10.a.set(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        r6 = com.tencent.bugly.legu.proguard.a.a(com.tencent.bugly.legu.crashreport.crash.c.e, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0075, code lost:
        if (r6 == null) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x007b, code lost:
        if (r6.size() > 0) goto L_0x009e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x007d, code lost:
        com.tencent.bugly.legu.proguard.w.d("can't get all thread skip this anr", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0085, code lost:
        r10.a.set(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x008b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        com.tencent.bugly.legu.proguard.w.a(r0);
        com.tencent.bugly.legu.proguard.w.e("get all thread stack fail!", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0097, code lost:
        r10.a.set(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        r3 = a(r10.c, 10000);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a6, code lost:
        if (r3 != null) goto L_0x00b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a8, code lost:
        com.tencent.bugly.legu.proguard.w.c("proc state is unvisiable!", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00b0, code lost:
        r10.a.set(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00bd, code lost:
        if (r3.pid == android.os.Process.myPid()) goto L_0x00d3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00bf, code lost:
        com.tencent.bugly.legu.proguard.w.c("not mind proc!", r3.processName);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00cc, code lost:
        r10.a.set(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        com.tencent.bugly.legu.proguard.w.a("found visiable anr , start to process!", new java.lang.Object[0]);
        a(r10.c, r11, r3, r4, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00e2, code lost:
        r10.a.set(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00e9, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00ee, code lost:
        if (com.tencent.bugly.legu.proguard.w.a(r0) == false) goto L_0x00f0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00f0, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00f3, code lost:
        com.tencent.bugly.legu.proguard.w.e("handle anr error %s", r0.getClass().toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x010d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x010e, code lost:
        r10.a.set(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0113, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0114, code lost:
        r4 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:?, code lost:
        return;
     */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    public final void a(String str) {
        synchronized (this) {
            if (this.a.get() != 0) {
                w.c("trace started return ", new Object[0]);
                return;
            }
            this.a.set(1);
        }
    }

    private synchronized void c() {
        if (e()) {
            w.d("start when started!", new Object[0]);
        } else {
            this.i = new FileObserver("/data/anr/", 8) {
                public final void onEvent(int i, String str) {
                    if (str != null) {
                        String str2 = "/data/anr/" + str;
                        if (!str2.contains("trace")) {
                            w.d("not anr file %s", str2);
                            return;
                        }
                        b.this.a(str2);
                    }
                }
            };
            try {
                this.i.startWatching();
                w.a("start anr monitor!", new Object[0]);
                this.e.b(new Runnable() {
                    public final void run() {
                        b.this.b();
                    }
                });
            } catch (Throwable th) {
                this.i = null;
                w.d("start anr monitor failed!", new Object[0]);
                if (!w.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    private synchronized void d() {
        if (!e()) {
            w.d("close when closed!", new Object[0]);
        } else {
            try {
                this.i.stopWatching();
                this.i = null;
                w.d("close anr monitor!", new Object[0]);
            } catch (Throwable th) {
                w.d("stop anr monitor failed!", new Object[0]);
                if (!w.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    private synchronized boolean e() {
        return this.i != null;
    }

    private synchronized void b(boolean z) {
        if (z) {
            c();
        } else {
            d();
        }
    }

    private synchronized boolean f() {
        return this.j;
    }

    private synchronized void c(boolean z) {
        if (this.j != z) {
            w.a("user change anr %b", Boolean.valueOf(z));
            this.j = z;
        }
    }

    public final void a(boolean z) {
        c(z);
        boolean z2 = com.tencent.bugly.legu.crashreport.common.strategy.a.a().c().g && f();
        if (z2 != e()) {
            w.a("anr changed to %b", Boolean.valueOf(z2));
            b(z2);
        }
    }

    /* access modifiers changed from: protected */
    public final void b() {
        long o = com.tencent.bugly.legu.proguard.a.o() - c.f;
        File file = new File(this.g);
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                String str = "bugly_trace_";
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

    public final synchronized void a(StrategyBean strategyBean) {
        boolean z = true;
        synchronized (this) {
            if (strategyBean != null) {
                if (strategyBean.g != e()) {
                    w.d("server anr changed to %b", Boolean.valueOf(strategyBean.g));
                }
            }
            if (!strategyBean.g || !f()) {
                z = false;
            }
            if (z != e()) {
                w.a("anr changed to %b", Boolean.valueOf(z));
                b(z);
            }
        }
    }
}
