package com.tencent.bugly.legu;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.bugly.legu.BuglyStrategy.a;
import com.tencent.bugly.legu.crashreport.CrashReport;
import com.tencent.bugly.legu.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.legu.crashreport.crash.c;
import com.tencent.bugly.legu.crashreport.crash.d;
import com.tencent.bugly.legu.crashreport.inner.InnerAPI;
import com.tencent.bugly.legu.proguard.m;
import com.tencent.bugly.legu.proguard.w;

/* compiled from: BUGLY */
public class CrashModule extends a {
    public static final int MODULE_ID = 1004;
    private static int c = 0;
    private static boolean d = false;
    private static CrashModule e = new CrashModule();
    private long a;
    private a b;

    public static CrashModule getInstance() {
        e.id = MODULE_ID;
        return e;
    }

    public static boolean hasInitialized() {
        return d;
    }

    public void init(Context context, boolean z, BuglyStrategy buglyStrategy) {
        if (context != null && !d) {
            m a2 = m.a();
            int i = c + 1;
            c = i;
            a2.a(MODULE_ID, i);
            d = true;
            CrashReport.setContext(context);
            a(context, buglyStrategy);
            c.a(MODULE_ID, context, z, this.b, null, null);
            c a3 = c.a();
            a3.e();
            if (buglyStrategy == null || buglyStrategy.isEnableNativeCrashMonitor()) {
                a3.g();
            } else {
                w.a("[crash] Closed native crash monitor!", new Object[0]);
                a3.f();
            }
            if (buglyStrategy == null || buglyStrategy.isEnableANRCrashMonitor()) {
                a3.h();
            } else {
                w.a("[crash] Closed ANR monitor!", new Object[0]);
                a3.i();
            }
            InnerAPI.context = context;
            d.a(context);
            c.a().a(this.a);
            m a4 = m.a();
            int i2 = c - 1;
            c = i2;
            a4.a(MODULE_ID, i2);
        }
    }

    private synchronized void a(Context context, BuglyStrategy buglyStrategy) {
        if (buglyStrategy != null) {
            String libBuglySOFilePath = buglyStrategy.getLibBuglySOFilePath();
            if (!TextUtils.isEmpty(libBuglySOFilePath)) {
                com.tencent.bugly.legu.crashreport.common.info.a.a(context).k = libBuglySOFilePath;
                w.a("setted libBugly.so file path :%s", libBuglySOFilePath);
            }
            if (buglyStrategy.getCrashHandleCallback() != null) {
                this.b = buglyStrategy.getCrashHandleCallback();
                w.a("setted CrashHanldeCallback", new Object[0]);
            }
            if (buglyStrategy.getAppReportDelay() > 0) {
                this.a = buglyStrategy.getAppReportDelay();
                w.a("setted delay: %d", Long.valueOf(this.a));
            }
        }
    }

    public void onServerStrategyChanged(StrategyBean strategyBean) {
        if (strategyBean != null) {
            c a2 = c.a();
            if (a2 != null) {
                a2.a(strategyBean);
            }
            d.a(strategyBean);
        }
    }

    public String[] getTables() {
        return new String[]{"t_cr"};
    }
}
