package com.tencent.bugly.legu.crashreport.crash;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.tencent.bugly.legu.crashreport.biz.b;
import com.tencent.bugly.legu.proguard.a;
import com.tencent.bugly.legu.proguard.t;
import com.tencent.bugly.legu.proguard.v;
import com.tencent.bugly.legu.proguard.w;

/* compiled from: BUGLY */
public class BuglyBroadcastRecevier extends BroadcastReceiver {
    public static String ACTION_PROCESS_CRASHED = "com.tencent.feedback.A02";
    public static String ACTION_PROCESS_LAUNCHED = "com.tencent.feedback.A01";
    public static final long UPLOADLIMITED = 60000;
    private static BuglyBroadcastRecevier d = null;
    private IntentFilter a = new IntentFilter();
    private Context b;
    private String c;

    public static synchronized BuglyBroadcastRecevier getInstance() {
        BuglyBroadcastRecevier buglyBroadcastRecevier;
        synchronized (BuglyBroadcastRecevier.class) {
            if (d == null) {
                d = new BuglyBroadcastRecevier();
            }
            buglyBroadcastRecevier = d;
        }
        return buglyBroadcastRecevier;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        if (this.b != null) {
            this.b.unregisterReceiver(this);
        }
    }

    public synchronized void addFilter(String str) {
        if (!this.a.hasAction(str)) {
            this.a.addAction(str);
        }
        w.c("add action %s", str);
    }

    public synchronized void regist(Context context, b bVar) {
        try {
            w.a("regis BC", new Object[0]);
            this.b = context;
            context.registerReceiver(this, this.a);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return;
    }

    public synchronized void unregist(Context context) {
        try {
            w.a("unregis BC", new Object[0]);
            context.unregisterReceiver(this);
            this.b = context;
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
        }
    }

    public final void onReceive(Context context, Intent intent) {
        try {
            a(context, intent);
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
        }
    }

    private synchronized boolean a(Context context, Intent intent) {
        boolean z = true;
        synchronized (this) {
            if (!(context == null || intent == null)) {
                if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    String e = a.e(this.b);
                    w.c("is Connect BC " + e, new Object[0]);
                    w.a("network %s changed to %s", this.c, e);
                    if (e == null) {
                        this.c = null;
                    } else {
                        String str = this.c;
                        this.c = e;
                        long currentTimeMillis = System.currentTimeMillis();
                        com.tencent.bugly.legu.crashreport.common.strategy.a a2 = com.tencent.bugly.legu.crashreport.common.strategy.a.a();
                        t a3 = t.a();
                        com.tencent.bugly.legu.crashreport.common.info.a a4 = com.tencent.bugly.legu.crashreport.common.info.a.a(context);
                        if (a2 == null || a3 == null || a4 == null) {
                            w.d("not inited BC not work", new Object[0]);
                        } else if (!e.equals(str)) {
                            if (currentTimeMillis - a3.a(c.a) > UPLOADLIMITED) {
                                w.a("try to upload crash on network changed.", new Object[0]);
                                c.a().a(0);
                            }
                            if (currentTimeMillis - a3.a(1001) > UPLOADLIMITED) {
                                w.a("try to upload userinfo on network changed.", new Object[0]);
                                v.a().b(new Runnable(this) {
                                    public final void run() {
                                        b.a.b();
                                    }
                                });
                            }
                        }
                    }
                }
            }
            z = false;
        }
        return z;
    }
}
