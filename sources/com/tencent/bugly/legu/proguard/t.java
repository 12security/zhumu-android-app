package com.tencent.bugly.legu.proguard;

import android.content.Context;
import android.os.Process;
import android.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: BUGLY */
public final class t {
    private static t a = null;
    private final o b;
    /* access modifiers changed from: private */
    public final Context c;
    private Map<Integer, Long> d = new HashMap();
    private LinkedBlockingQueue<Runnable> e = new LinkedBlockingQueue<>();
    private final Object f = new Object();
    private String g = null;
    private byte[] h = null;
    private long i = 0;
    /* access modifiers changed from: private */
    public byte[] j = null;
    private long k = 0;
    /* access modifiers changed from: private */
    public String l = null;
    private long m = 0;
    private final Object n = new Object();
    /* access modifiers changed from: private */
    public boolean o = false;
    /* access modifiers changed from: private */
    public final Object p = new Object();
    private boolean q = true;

    /* compiled from: BUGLY */
    class a implements Runnable {
        private final Context a;
        private final Runnable b;
        private final long c;

        public a(t tVar, Context context) {
            this(context, null, 0);
        }

        public a(Context context, Runnable runnable, long j) {
            this.a = context;
            this.b = runnable;
            this.c = j;
        }

        public final void run() {
            if (!a.a(this.a, "security_info", 30000)) {
                v a2 = v.a();
                if (a2 == null) {
                    w.e("[UploadManager] async task handler has not been initialized", new Object[0]);
                    return;
                }
                w.c("[UploadManager] sleep %d try to lock security file again", Integer.valueOf(5000));
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a2.c(new a(t.this, t.this.c));
                return;
            }
            if (!t.this.f()) {
                w.d("[UploadManager] failed to load security info from database", new Object[0]);
                t.this.a(false);
            }
            if (t.this.l != null) {
                if (t.this.c()) {
                    w.c("[UploadManager] sucessfully got session ID, try to execute upload tasks now (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                    if (this.b != null) {
                        t.this.a(this.b, this.c);
                    }
                    t.this.b(0);
                    a.b(this.a, "security_info");
                    synchronized (t.this.p) {
                        t.this.o = false;
                    }
                    return;
                }
                w.a("[UploadManager] session ID is expired, drop it", new Object[0]);
                t.this.a(true);
            }
            byte[] a3 = a.a(128);
            if (a3 == null || (a3.length << 3) != 128) {
                w.d("[UploadManager] failed to create AES key (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            } else {
                t.this.j = a3;
                w.c("[UploadManager] execute one upload task for requesting session ID (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                if (this.b != null) {
                    t.this.a(this.b, this.c);
                    return;
                } else if (!t.this.b(1)) {
                    w.d("[UploadManager] failed to execute one upload task (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                } else {
                    return;
                }
            }
            t.this.a(false);
            a.b(this.a, "security_info");
            synchronized (t.this.p) {
                t.this.o = false;
            }
        }
    }

    private t(Context context) {
        this.c = context;
        this.b = o.a();
        try {
            Class.forName("android.util.Base64");
        } catch (ClassNotFoundException e2) {
            w.a("[UploadManager] can not find Base64 class, will not use stronger security way to upload", new Object[0]);
            this.q = false;
        }
        if (this.q) {
            StringBuilder sb = new StringBuilder();
            sb.append("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDP9x32s5pPtZBXzJBz2GWM/sbTvVO2+RvW0PH01IdaBxc/").append("fB6fbHZocC9T3nl1+J5eAFjIRVuV8vHDky7Qo82Mnh0PVvcZIEQvMMVKU8dsMQopxgsOs2gkSHJwgWdinKNS8CmWobo6pFwPUW11lMv714jAUZRq2GBOqiO2vQI6iwIDAQAB");
            this.g = sb.toString();
        }
    }

    public static synchronized t a(Context context) {
        t tVar;
        synchronized (t.class) {
            if (a == null) {
                a = new t(context);
            }
            tVar = a;
        }
        return tVar;
    }

    public static synchronized t a() {
        t tVar;
        synchronized (t.class) {
            tVar = a;
        }
        return tVar;
    }

    public final void a(int i2, am amVar, String str, s sVar) {
        a(i2, amVar, null, sVar, false, 0);
    }

    public final void a(int i2, int i3, byte[] bArr, String str, s sVar) {
        try {
            if (this.q) {
                a((Runnable) new u(this.c, i2, i3, bArr, null, sVar, true), false, 0);
                return;
            }
            v.a().b(new u(this.c, i2, i3, bArr, null, sVar, false));
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
        }
    }

    public final void a(int i2, am amVar, String str, s sVar, boolean z, long j2) {
        try {
            if (this.q) {
                a((Runnable) new u(this.c, i2, amVar, str, sVar, true), z, j2);
                return;
            }
            u uVar = new u(this.c, i2, amVar, str, sVar, false);
            if (z) {
                a((Runnable) uVar, j2);
            } else {
                v.a().b(uVar);
            }
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
        }
    }

    public final long b() {
        long j2;
        long j3 = 0;
        long o2 = a.o();
        List a2 = this.b.a(3);
        if (a2 == null || a2.size() <= 0) {
            j2 = 0;
        } else {
            try {
                q qVar = (q) a2.get(0);
                if (qVar.e >= o2) {
                    j3 = a.c(qVar.g);
                    a2.remove(qVar);
                }
                j2 = j3;
            } catch (Throwable th) {
                j2 = 0;
                w.e("error local type %d", Integer.valueOf(3));
            }
            if (a2.size() > 0) {
                o oVar = this.b;
                o.a(a2);
            }
        }
        w.c("consume getted %d", Long.valueOf(j2));
        return j2;
    }

    /* access modifiers changed from: protected */
    public final synchronized void a(long j2) {
        q qVar = new q();
        qVar.b = 3;
        qVar.e = a.o();
        qVar.c = "";
        qVar.d = "";
        qVar.g = a.a(j2);
        o oVar = this.b;
        o.b(3);
        this.b.a(qVar);
        w.c("consume update %d", Long.valueOf(j2));
    }

    public final synchronized void a(int i2, long j2) {
        if (i2 >= 0) {
            this.d.put(Integer.valueOf(i2), Long.valueOf(j2));
            w.c("up %d %d", Integer.valueOf(i2), Long.valueOf(j2));
        } else {
            w.e("unknown up %d", Integer.valueOf(i2));
        }
    }

    public final synchronized long a(int i2) {
        long j2;
        if (i2 >= 0) {
            Long l2 = (Long) this.d.get(Integer.valueOf(i2));
            j2 = l2 == null ? -2 : l2.longValue();
        } else {
            w.e("unknown up %d", Integer.valueOf(i2));
            j2 = -2;
        }
        return j2;
    }

    private static boolean d() {
        w.c("[UploadManager] drop security info of database (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            o a2 = o.a();
            if (a2 != null) {
                return a2.a(555, "security_info", (n) null, true);
            }
            w.d("[UploadManager] failed to get Database", new Object[0]);
            return false;
        } catch (Throwable th) {
            w.a(th);
            return false;
        }
    }

    private boolean e() {
        w.c("[UploadManager] record security info to database (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            o a2 = o.a();
            if (a2 == null) {
                w.d("[UploadManager] failed to get Database", new Object[0]);
                return false;
            }
            StringBuilder sb = new StringBuilder();
            if (this.j != null) {
                sb.append(Base64.encodeToString(this.j, 0));
                sb.append("#");
                if (this.m != 0) {
                    sb.append(Long.toString(this.k));
                } else {
                    sb.append("null");
                }
                sb.append("#");
                if (this.l != null) {
                    sb.append(this.l);
                } else {
                    sb.append("null");
                }
                sb.append("#");
                if (this.m != 0) {
                    sb.append(Long.toString(this.m));
                } else {
                    sb.append("null");
                }
                a2.a(555, "security_info", sb.toString().getBytes(), (n) null, true);
                return true;
            }
            w.c("[UploadManager] AES key is null, will not record", new Object[0]);
            return false;
        } catch (Throwable th) {
            w.a(th);
            d();
            return false;
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    public boolean f() {
        boolean z;
        w.c("[UploadManager] load security info from dataBase (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            o a2 = o.a();
            if (a2 == null) {
                w.d("[UploadManager] failed to get Database", new Object[0]);
                return false;
            }
            Map a3 = a2.a(555, (n) null, true);
            if (a3 != null && a3.containsKey("security_info")) {
                String str = new String((byte[]) a3.get("security_info"));
                String[] split = str.split("#");
                if (split.length == 4) {
                    if (split[0].isEmpty() || split[0].equals("null")) {
                        z = false;
                    } else {
                        this.j = Base64.decode(split[0], 0);
                        z = false;
                    }
                    if (!z) {
                        if (!split[1].isEmpty() && !split[1].equals("null")) {
                            try {
                                this.k = Long.parseLong(split[1]);
                            } catch (Throwable th) {
                                w.a(th);
                                z = true;
                            }
                        }
                    }
                    if (!z) {
                        if (!split[2].isEmpty() && !split[2].equals("null")) {
                            this.l = split[2];
                        }
                    }
                    if (!z && !split[3].isEmpty() && !split[3].equals("null")) {
                        try {
                            this.m = Long.parseLong(split[3]);
                        } catch (Throwable th2) {
                            w.a(th2);
                            z = true;
                        }
                    }
                } else {
                    w.a("securityInfo = %s, strings.length = %d", str, Integer.valueOf(split.length));
                    z = true;
                }
                if (z) {
                    d();
                }
            }
            return true;
        } catch (Throwable th3) {
            w.a(th3);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public final boolean c() {
        if (this.l == null || this.m == 0) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis() + this.i;
        if (this.m >= currentTimeMillis) {
            return true;
        }
        w.c("[UploadManager] session ID expired time from server is: %d(%s), but now is: %d(%s)", Long.valueOf(this.m), new Date(this.m).toString(), Long.valueOf(currentTimeMillis), new Date(currentTimeMillis).toString());
        return false;
    }

    /* access modifiers changed from: protected */
    public final void a(boolean z) {
        synchronized (this.n) {
            w.c("[UploadManager] clear security context (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            this.j = null;
            this.l = null;
            this.m = 0;
        }
        if (z) {
            d();
        }
    }

    /* access modifiers changed from: private */
    public boolean b(int i2) {
        if (i2 < 0) {
            w.a("[UploadManager] number of task to execute should >= 0", new Object[0]);
            return false;
        }
        synchronized (this.f) {
            if (this.e.isEmpty()) {
                return true;
            }
            w.c("[UploadManager] execute upload tasks of queue which has %d tasks (pid=%d | tid=%d)", Integer.valueOf(this.e.size()), Integer.valueOf(Process.myPid()), Long.valueOf(Thread.currentThread().getId()));
            if (i2 == 0 || i2 > this.e.size()) {
                i2 = this.e.size();
            }
            v a2 = v.a();
            if (a2 == null) {
                w.d("[UploadManager] async task handler has not been initialized", new Object[0]);
                return false;
            }
            int i3 = 0;
            while (i3 < i2) {
                try {
                    Runnable runnable = (Runnable) this.e.poll();
                    if (runnable != null) {
                        a2.c(runnable);
                    } else {
                        w.d("[UploadManager] upload task poll from queue is null", new Object[0]);
                    }
                    i3++;
                } catch (Throwable th) {
                    w.e("[UploadManager] failed to execute upload task with message: %s", th.getMessage());
                    return false;
                }
            }
            return true;
        }
    }

    private boolean a(Runnable runnable) {
        if (runnable == null) {
            w.d("[UploadManager] upload task should not be null", new Object[0]);
            return false;
        }
        try {
            w.c("[UploadManager] add upload task to queue (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            synchronized (this.f) {
                this.e.put(runnable);
            }
            return true;
        } catch (Throwable th) {
            w.e("[UploadManager] failed to add upload task to queue: %s", th.getMessage());
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void a(Runnable runnable, long j2) {
        if (runnable == null) {
            w.d("[UploadManager] upload task should not be null", new Object[0]);
            return;
        }
        w.c("[UploadManager] execute synchronized upload task (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        Thread thread = new Thread(runnable);
        thread.setName("BuglySyncUploadThread");
        thread.start();
        try {
            thread.join(j2);
        } catch (Throwable th) {
            w.e("[UploadManager] failed to execute upload synchronized task with message: %s. Add it to queue", th.getMessage());
            a(runnable);
        }
    }

    private void a(Runnable runnable, boolean z, long j2) {
        if (runnable == null) {
            w.d("[UploadManager] upload task should not be null", new Object[0]);
        }
        v a2 = v.a();
        if (a2 == null) {
            w.d("[UploadManager] async task handler has not been initialized", new Object[0]);
        }
        w.c("[UploadManager] add upload task (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        if (this.l != null) {
            if (c()) {
                w.c("[UploadManager] sucessfully got session ID, try to execute upload task now (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                if (z) {
                    a(runnable, j2);
                } else if (a2 == null) {
                    a(runnable);
                } else {
                    a2.c(runnable);
                }
                b(0);
                return;
            }
            w.a("[UploadManager] session ID is expired, drop it (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            a(false);
        }
        synchronized (this.p) {
            if (!this.o) {
                this.o = true;
                w.c("[UploadManager] try to request session ID now (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                if (z) {
                    a((Runnable) new a(this.c, runnable, j2), 0);
                } else {
                    a(runnable);
                    a2.c(new a(this, this.c));
                }
            } else {
                a(runnable);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00fb  */
    public final void a(int i2, an anVar) {
        boolean z;
        if (this.q) {
            if (i2 == 2) {
                w.c("[UploadManager] Session ID is invalid, will clear security context (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                a(true);
            } else if (!this.o) {
                return;
            } else {
                if (anVar != null) {
                    w.c("[UploadManager] record security context (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                    try {
                        Map<String, String> map = anVar.h;
                        if (map != null && map.containsKey("S1") && map.containsKey("S2")) {
                            this.i = anVar.e - System.currentTimeMillis();
                            w.c("[UploadManager] time lag of server is: %d", Long.valueOf(this.i));
                            this.l = (String) map.get("S1");
                            w.c("[UploadManager] session ID from server is: %s", this.l);
                            if (this.l.length() > 0) {
                                this.m = Long.parseLong((String) map.get("S2"));
                                w.c("[UploadManager] session expired time from server is: %d(%s)", Long.valueOf(this.m), new Date(this.m).toString());
                                if (this.m < 1000) {
                                    w.d("[UploadManager] session expired time from server is less than 1 second, will set to default value", new Object[0]);
                                    this.m = 259200000;
                                }
                                b(0);
                                if (e()) {
                                    z = false;
                                } else {
                                    w.c("[UploadManager] failed to record database", new Object[0]);
                                    z = true;
                                }
                                if (z) {
                                    a(false);
                                }
                            } else {
                                w.c("[UploadManager] session ID from server is invalid, try next time", new Object[0]);
                            }
                        }
                        z = true;
                    } catch (NumberFormatException e2) {
                        w.d("[UploadManager] session expired time is invalid, will set to default value", new Object[0]);
                        this.m = 259200000;
                    } catch (Throwable th) {
                        w.a(th);
                        z = true;
                    }
                    if (z) {
                    }
                } else {
                    w.c("[UploadManager] fail to init security context and clear local info (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                    a(false);
                }
            }
            synchronized (this.p) {
                if (this.o) {
                    this.o = false;
                    a.b(this.c, "security_info");
                }
            }
        }
    }

    public final byte[] a(byte[] bArr) {
        if (this.j != null && (this.j.length << 3) == 128) {
            return a.a(1, bArr, this.j);
        }
        w.d("[UploadManager] AES key is invalid (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        return null;
    }

    public final byte[] b(byte[] bArr) {
        if (this.j != null && (this.j.length << 3) == 128) {
            return a.a(2, bArr, this.j);
        }
        w.d("[UploadManager] AES key is invalid (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        return null;
    }

    public final boolean a(Map<String, String> map) {
        if (map == null) {
            return false;
        }
        w.c("[UploadManager] integrate security to HTTP headers (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        if (this.l != null) {
            map.put("secureSessionId", this.l);
            return true;
        } else if (this.j == null || (this.j.length << 3) != 128) {
            w.d("[UploadManager] AES key is invalid", new Object[0]);
            return false;
        } else {
            if (this.h == null) {
                this.h = Base64.decode(this.g, 0);
                if (this.h == null) {
                    w.d("[UploadManager] failed to decode RSA public key", new Object[0]);
                    return false;
                }
            }
            byte[] b2 = a.b(1, this.j, this.h);
            if (b2 == null) {
                w.d("[UploadManager] failed to encrypt AES key", new Object[0]);
                return false;
            }
            String encodeToString = Base64.encodeToString(b2, 0);
            if (encodeToString == null) {
                w.d("[UploadManager] failed to encode AES key", new Object[0]);
                return false;
            }
            map.put("raKey", encodeToString);
            return true;
        }
    }
}
