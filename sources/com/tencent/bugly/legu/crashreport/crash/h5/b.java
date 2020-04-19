package com.tencent.bugly.legu.crashreport.crash.h5;

import android.content.Context;
import com.tencent.bugly.legu.BuglyStrategy;
import com.tencent.bugly.legu.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.legu.crashreport.common.strategy.a;
import com.tencent.bugly.legu.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.legu.crashreport.crash.c;
import com.tencent.bugly.legu.proguard.w;
import com.tencent.bugly.legu.proguard.x;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: BUGLY */
public final class b {
    private Context a;
    private com.tencent.bugly.legu.crashreport.crash.b b;
    private a c;
    private com.tencent.bugly.legu.crashreport.common.info.a d;

    public b(Context context, com.tencent.bugly.legu.crashreport.crash.b bVar, a aVar, com.tencent.bugly.legu.crashreport.common.info.a aVar2, BuglyStrategy.a aVar3) {
        this.a = context;
        this.b = bVar;
        this.c = aVar;
        this.d = aVar2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002a, code lost:
        if (r0 >= 5000) goto L_0x002c;
     */
    public final void a(Thread thread, String str, String str2, String str3, Map<String, String> map) {
        w.e("H5 Crash Happen", new Object[0]);
        if (!this.c.b()) {
            w.e("waiting for remote sync", new Object[0]);
            int i = 0;
            while (!this.c.b()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Throwable th) {
                    try {
                        if (!w.a(th)) {
                            th.printStackTrace();
                        }
                        return;
                    } finally {
                        w.e("handle end", new Object[0]);
                    }
                }
                i += 500;
            }
        }
        if (!this.c.b()) {
            w.d("no remote but still store!", new Object[0]);
        }
        StrategyBean c2 = this.c.c();
        if (!c2.d && this.c.b()) {
            w.e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
            com.tencent.bugly.legu.crashreport.crash.b.a("H5", com.tencent.bugly.legu.proguard.a.n(), this.d.d, thread, str + "\n" + str2 + "\n" + str3, null);
            w.e("handle end", new Object[0]);
        } else if (!c2.i) {
            w.e("cocos report is disabled.", new Object[0]);
            w.e("handle end", new Object[0]);
        } else {
            CrashDetailBean crashDetailBean = new CrashDetailBean();
            crashDetailBean.B = com.tencent.bugly.legu.proguard.a.i();
            crashDetailBean.C = com.tencent.bugly.legu.proguard.a.g();
            crashDetailBean.D = com.tencent.bugly.legu.proguard.a.k();
            crashDetailBean.E = this.d.o();
            crashDetailBean.F = this.d.n();
            crashDetailBean.G = this.d.p();
            crashDetailBean.w = com.tencent.bugly.legu.proguard.a.a(this.a, c.d, (String) null);
            crashDetailBean.b = 5;
            crashDetailBean.e = this.d.g();
            crashDetailBean.f = this.d.i;
            crashDetailBean.g = this.d.t();
            crashDetailBean.m = this.d.f();
            crashDetailBean.n = str;
            crashDetailBean.o = str2;
            crashDetailBean.p = "0";
            crashDetailBean.q = str3;
            crashDetailBean.r = System.currentTimeMillis();
            crashDetailBean.u = com.tencent.bugly.legu.proguard.a.b(crashDetailBean.q.getBytes());
            crashDetailBean.y = com.tencent.bugly.legu.proguard.a.a(c.e, false);
            crashDetailBean.z = this.d.d;
            crashDetailBean.A = thread.getName() + "(" + thread.getId() + ")";
            crashDetailBean.H = this.d.v();
            crashDetailBean.h = this.d.s();
            crashDetailBean.L = this.d.a;
            crashDetailBean.M = this.d.n;
            crashDetailBean.O = this.d.B();
            crashDetailBean.P = this.d.C();
            crashDetailBean.Q = this.d.w();
            crashDetailBean.R = this.d.A();
            this.b.b(crashDetailBean);
            crashDetailBean.x = x.a(false);
            if (crashDetailBean.N == null) {
                crashDetailBean.N = new LinkedHashMap();
            }
            if (map != null) {
                crashDetailBean.N.putAll(map);
            }
            if (crashDetailBean == null) {
                w.e("pkg crash datas fail!", new Object[0]);
                w.e("handle end", new Object[0]);
                return;
            }
            String str4 = "H5";
            com.tencent.bugly.legu.crashreport.crash.b.a(str4, com.tencent.bugly.legu.proguard.a.n(), this.d.d, thread, str + "\n" + str2 + "\n" + str3, crashDetailBean);
            if (!this.b.a(crashDetailBean)) {
                this.b.a(crashDetailBean, 5000, false);
            }
            w.e("handle end", new Object[0]);
        }
    }
}
