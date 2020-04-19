package com.tencent.bugly.legu.crashreport.common.strategy;

import android.content.Context;
import android.os.Parcel;
import com.tencent.bugly.legu.crashreport.biz.b;
import com.tencent.bugly.legu.proguard.ap;
import com.tencent.bugly.legu.proguard.n;
import com.tencent.bugly.legu.proguard.o;
import com.tencent.bugly.legu.proguard.q;
import com.tencent.bugly.legu.proguard.v;
import com.tencent.bugly.legu.proguard.w;
import java.util.List;
import java.util.Map;

/* compiled from: BUGLY */
public final class a {
    public static int a = 1000;
    private static a b = null;
    private final List<com.tencent.bugly.legu.a> c;
    private final v d;
    private final StrategyBean e;
    /* access modifiers changed from: private */
    public StrategyBean f = null;
    /* access modifiers changed from: private */
    public Context g;

    private a(Context context, List<com.tencent.bugly.legu.a> list) {
        this.g = context;
        this.e = new StrategyBean();
        this.c = list;
        this.d = v.a();
        this.d.b(new Thread() {
            public final void run() {
                try {
                    Map a2 = o.a().a(a.a, (n) null, true);
                    if (a2 != null) {
                        byte[] bArr = (byte[]) a2.get("key_imei");
                        byte[] bArr2 = (byte[]) a2.get("key_ip");
                        if (bArr != null) {
                            com.tencent.bugly.legu.crashreport.common.info.a.a(a.this.g).e(new String(bArr));
                        }
                        if (bArr2 != null) {
                            com.tencent.bugly.legu.crashreport.common.info.a.a(a.this.g).d(new String(bArr2));
                        }
                    }
                    a aVar = a.this;
                    a.this.f = a.d();
                    a.this.a(a.this.f);
                } catch (Throwable th) {
                    if (!w.a(th)) {
                        th.printStackTrace();
                    }
                }
            }
        });
    }

    public static synchronized a a(Context context, List<com.tencent.bugly.legu.a> list) {
        a aVar;
        synchronized (a.class) {
            if (b == null) {
                b = new a(context, list);
            }
            aVar = b;
        }
        return aVar;
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            aVar = b;
        }
        return aVar;
    }

    public final synchronized boolean b() {
        return this.f != null;
    }

    public final StrategyBean c() {
        if (this.f != null) {
            return this.f;
        }
        return this.e;
    }

    /* access modifiers changed from: protected */
    public final void a(StrategyBean strategyBean) {
        for (com.tencent.bugly.legu.a aVar : this.c) {
            try {
                w.c("[strategy] Notify %s", aVar.getClass().getName());
                aVar.onServerStrategyChanged(strategyBean);
            } catch (Throwable th) {
                if (!w.a(th)) {
                    th.printStackTrace();
                }
            }
        }
        b.a(strategyBean);
    }

    public final void a(ap apVar) {
        boolean z;
        boolean z2;
        if (apVar != null) {
            if (this.f == null || apVar.h != this.f.l) {
                StrategyBean strategyBean = new StrategyBean();
                strategyBean.d = apVar.a;
                strategyBean.f = apVar.c;
                strategyBean.e = apVar.b;
                String str = apVar.d;
                if (!(str == null || str.trim().length() <= 0)) {
                    w.c("upload url changes to %s", apVar.d);
                    strategyBean.n = apVar.d;
                }
                String str2 = apVar.e;
                if (str2 == null || str2.trim().length() <= 0) {
                    z = true;
                } else {
                    z = false;
                }
                if (!z) {
                    w.c("exception upload url changes to %s", apVar.e);
                    strategyBean.o = apVar.e;
                }
                if (apVar.f != null) {
                    String str3 = apVar.f.a;
                    if (str3 == null || str3.trim().length() <= 0) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (!z2) {
                        strategyBean.q = apVar.f.a;
                    }
                }
                if (apVar.h != 0) {
                    strategyBean.l = apVar.h;
                }
                if (apVar.g != null && apVar.g.size() > 0) {
                    strategyBean.r = apVar.g;
                    String str4 = (String) apVar.g.get("B11");
                    if (str4 == null || !str4.equals("1")) {
                        strategyBean.g = false;
                    } else {
                        strategyBean.g = true;
                    }
                    strategyBean.m = (long) apVar.i;
                    strategyBean.t = (long) apVar.i;
                    String str5 = (String) apVar.g.get("B27");
                    if (str5 != null && str5.length() > 0) {
                        try {
                            int parseInt = Integer.parseInt(str5);
                            if (parseInt > 0) {
                                strategyBean.s = parseInt;
                            }
                        } catch (Exception e2) {
                            if (!w.a(e2)) {
                                e2.printStackTrace();
                            }
                        }
                    }
                    String str6 = (String) apVar.g.get("B25");
                    if (str6 == null || !str6.equals("1")) {
                        strategyBean.h = false;
                    } else {
                        strategyBean.h = true;
                    }
                }
                w.a("cr:%b,qu:%b,uin:%b,an:%b,ss:%b,ssT:%b,ssOT:%d,cos:%b,lstT:%d", Boolean.valueOf(strategyBean.d), Boolean.valueOf(strategyBean.f), Boolean.valueOf(strategyBean.e), Boolean.valueOf(strategyBean.g), Boolean.valueOf(strategyBean.j), Boolean.valueOf(strategyBean.k), Long.valueOf(strategyBean.m), Boolean.valueOf(strategyBean.h), Long.valueOf(strategyBean.l));
                this.f = strategyBean;
                o.a();
                o.b(2);
                q qVar = new q();
                qVar.b = 2;
                qVar.a = strategyBean.b;
                qVar.e = strategyBean.c;
                Parcel obtain = Parcel.obtain();
                strategyBean.writeToParcel(obtain, 0);
                byte[] marshall = obtain.marshall();
                obtain.recycle();
                qVar.g = marshall;
                o.a().a(qVar);
                a(strategyBean);
            }
        }
    }

    public static StrategyBean d() {
        List a2 = o.a().a(2);
        if (a2 != null && a2.size() > 0) {
            q qVar = (q) a2.get(0);
            if (qVar.g != null) {
                return (StrategyBean) com.tencent.bugly.legu.proguard.a.a(qVar.g, StrategyBean.CREATOR);
            }
        }
        return null;
    }
}
