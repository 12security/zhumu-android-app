package com.tencent.bugly.legu.proguard;

import android.content.Context;
import com.tencent.bugly.legu.BuglyStrategy;
import com.tencent.bugly.legu.crashreport.common.strategy.a;
import com.tencent.bugly.legu.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.legu.crashreport.crash.b;
import com.tencent.bugly.legu.crashreport.crash.c;

/* compiled from: BUGLY */
public final class z {
    private Context a;
    private b b;
    private a c;
    private com.tencent.bugly.legu.crashreport.common.info.a d;

    public z(Context context, b bVar, a aVar, com.tencent.bugly.legu.crashreport.common.info.a aVar2, BuglyStrategy.a aVar3) {
        this.a = context;
        this.b = bVar;
        this.c = aVar;
        this.d = aVar2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002f, code lost:
        if (r0 >= 5000) goto L_0x0031;
     */
    public final void a(Thread thread, String str, String str2, String str3) {
        w.e("U3D Crash Happen", new Object[0]);
        this.c.c();
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
        if (this.c.c().d || !this.c.b()) {
            CrashDetailBean crashDetailBean = new CrashDetailBean();
            crashDetailBean.B = a.i();
            crashDetailBean.C = a.g();
            crashDetailBean.D = a.k();
            crashDetailBean.E = this.d.o();
            crashDetailBean.F = this.d.n();
            crashDetailBean.G = this.d.p();
            crashDetailBean.w = a.a(this.a, c.d, (String) null);
            crashDetailBean.x = x.a(false);
            crashDetailBean.b = 4;
            crashDetailBean.e = this.d.g();
            crashDetailBean.f = this.d.i;
            crashDetailBean.g = this.d.t();
            crashDetailBean.m = this.d.f();
            crashDetailBean.n = str;
            crashDetailBean.o = str2;
            String str4 = "";
            if (str3 != null) {
                String[] split = str3.split("\n");
                if (split != null && split.length > 0) {
                    str4 = split[0];
                }
            }
            crashDetailBean.p = str4;
            crashDetailBean.q = str3;
            crashDetailBean.r = System.currentTimeMillis();
            crashDetailBean.u = a.b(crashDetailBean.q.getBytes());
            crashDetailBean.y = a.a(c.e, false);
            crashDetailBean.z = this.d.d;
            crashDetailBean.A = thread.getName() + "(" + thread.getId() + ")";
            crashDetailBean.H = this.d.v();
            crashDetailBean.h = this.d.s();
            crashDetailBean.i = this.d.E();
            crashDetailBean.L = this.d.a;
            crashDetailBean.M = this.d.n;
            crashDetailBean.O = this.d.B();
            crashDetailBean.P = this.d.C();
            crashDetailBean.Q = this.d.w();
            crashDetailBean.R = this.d.A();
            this.b.b(crashDetailBean);
            if (crashDetailBean == null) {
                w.e("pkg crash datas fail!", new Object[0]);
                w.e("handle end", new Object[0]);
                return;
            }
            String str5 = "U3D";
            b.a(str5, a.n(), this.d.d, thread, str + "\n" + str2 + "\n" + str3, crashDetailBean);
            if (!this.b.a(crashDetailBean)) {
                this.b.a(crashDetailBean, 5000, true);
            }
            w.e("handle end", new Object[0]);
            return;
        }
        w.e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
        b.a("U3D", a.n(), this.d.d, thread, str + "\n" + str2 + "\n" + str3, null);
        w.e("handle end", new Object[0]);
    }
}
