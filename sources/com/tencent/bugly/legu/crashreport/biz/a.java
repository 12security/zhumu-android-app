package com.tencent.bugly.legu.crashreport.biz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import com.tencent.bugly.legu.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.legu.proguard.am;
import com.tencent.bugly.legu.proguard.ar;
import com.tencent.bugly.legu.proguard.j;
import com.tencent.bugly.legu.proguard.n;
import com.tencent.bugly.legu.proguard.o;
import com.tencent.bugly.legu.proguard.s;
import com.tencent.bugly.legu.proguard.t;
import com.tencent.bugly.legu.proguard.v;
import com.tencent.bugly.legu.proguard.w;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: BUGLY */
public final class a {
    private Context a;
    /* access modifiers changed from: private */
    public long b;
    /* access modifiers changed from: private */
    public int c;

    /* renamed from: com.tencent.bugly.legu.crashreport.biz.a$a reason: collision with other inner class name */
    /* compiled from: BUGLY */
    class C0000a implements Runnable {
        private boolean a;
        private UserInfoBean b;

        public C0000a(UserInfoBean userInfoBean, boolean z) {
            this.b = userInfoBean;
            this.a = z;
        }

        public final void run() {
            try {
                if (this.b != null) {
                    UserInfoBean userInfoBean = this.b;
                    if (userInfoBean != null) {
                        com.tencent.bugly.legu.crashreport.common.info.a a2 = com.tencent.bugly.legu.crashreport.common.info.a.a();
                        if (a2 != null) {
                            userInfoBean.j = a2.d();
                        }
                    }
                    w.c("record userinfo", new Object[0]);
                    a.a(a.this, this.b);
                }
                if (this.a) {
                    a.this.b();
                }
            } catch (Throwable th) {
                if (!w.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* compiled from: BUGLY */
    class b implements Runnable {
        b() {
        }

        public final void run() {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis < a.this.b) {
                v.a().a(new b(), (a.this.b - currentTimeMillis) + 5000);
                return;
            }
            a.this.c = a.this.c + 1;
            a.this.a(3, false, 0);
            a.this.a();
        }
    }

    static /* synthetic */ void a(a aVar, UserInfoBean userInfoBean) {
        if (userInfoBean != null) {
            long a2 = o.a().a("t_ui", a(userInfoBean), (n) null, true);
            if (a2 >= 0) {
                w.c("insert %s success! %d", "t_ui", Long.valueOf(a2));
                userInfoBean.a = a2;
            }
        }
    }

    public a(Context context) {
        this.a = context;
    }

    public final void a(int i, boolean z, long j) {
        int i2 = 1;
        StrategyBean c2 = com.tencent.bugly.legu.crashreport.common.strategy.a.a().c();
        if (c2 == null || c2.e || i == 1 || i == 3) {
            if (i == 1) {
                this.c++;
            }
            Context context = this.a;
            if (i != 1) {
                i2 = 0;
            }
            com.tencent.bugly.legu.crashreport.common.info.a a2 = com.tencent.bugly.legu.crashreport.common.info.a.a(context);
            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.b = i;
            userInfoBean.c = a2.d;
            userInfoBean.d = a2.f();
            userInfoBean.e = System.currentTimeMillis();
            userInfoBean.f = -1;
            userInfoBean.n = a2.i;
            userInfoBean.o = i2;
            userInfoBean.l = a2.n;
            userInfoBean.m = a2.o;
            userInfoBean.g = a2.p;
            userInfoBean.h = a2.q;
            userInfoBean.i = a2.r;
            userInfoBean.k = a2.s;
            userInfoBean.r = a2.w();
            userInfoBean.s = a2.A();
            userInfoBean.p = a2.B();
            userInfoBean.q = a2.C();
            v.a().a(new C0000a(userInfoBean, z), 0);
            return;
        }
        w.e("UserInfo is disable", new Object[0]);
    }

    public final void a() {
        this.b = com.tencent.bugly.legu.proguard.a.o() + 86400000;
        v.a().a(new b(), (this.b - System.currentTimeMillis()) + 5000);
    }

    public final synchronized void b() {
        final List arrayList;
        boolean z;
        boolean z2;
        int i;
        int i2 = 1;
        synchronized (this) {
            String str = com.tencent.bugly.legu.crashreport.common.info.a.a(this.a).d;
            ArrayList arrayList2 = new ArrayList();
            List a2 = a(str);
            if (a2 != null) {
                int size = a2.size() - 10;
                if (size > 0) {
                    for (int i3 = 0; i3 < a2.size() - 1; i3++) {
                        for (int i4 = i3 + 1; i4 < a2.size(); i4++) {
                            if (((UserInfoBean) a2.get(i3)).e > ((UserInfoBean) a2.get(i4)).e) {
                                UserInfoBean userInfoBean = (UserInfoBean) a2.get(i3);
                                a2.set(i3, a2.get(i4));
                                a2.set(i4, userInfoBean);
                            }
                        }
                    }
                    for (int i5 = 0; i5 < size; i5++) {
                        arrayList2.add(a2.get(i5));
                    }
                }
                Iterator it = a2.iterator();
                int i6 = 0;
                while (it.hasNext()) {
                    UserInfoBean userInfoBean2 = (UserInfoBean) it.next();
                    if (userInfoBean2.f != -1) {
                        it.remove();
                        if (userInfoBean2.e < com.tencent.bugly.legu.proguard.a.o()) {
                            arrayList2.add(userInfoBean2);
                        }
                    }
                    if (userInfoBean2.e <= System.currentTimeMillis() - 600000 || !(userInfoBean2.b == 1 || userInfoBean2.b == 4)) {
                        i = i6;
                    } else {
                        i = i6 + 1;
                    }
                    i6 = i;
                }
                if (i6 > 15) {
                    w.d("[userinfo] userinfo too many times in 10 min: %d", Integer.valueOf(i6));
                    z2 = false;
                } else {
                    z2 = true;
                }
                z = z2;
                arrayList = a2;
            } else {
                arrayList = new ArrayList();
                z = true;
            }
            if (arrayList2.size() > 0) {
                a((List<UserInfoBean>) arrayList2);
            }
            if (!(!z || arrayList == null || arrayList.size() == 0)) {
                w.c("[userinfo] do userinfo, size: %d", Integer.valueOf(arrayList.size()));
                if (this.c != 1) {
                    i2 = 2;
                }
                ar a3 = com.tencent.bugly.legu.proguard.a.a(arrayList, i2);
                if (a3 == null) {
                    w.d("[biz] create uPkg fail!", new Object[0]);
                } else {
                    byte[] a4 = com.tencent.bugly.legu.proguard.a.a((j) a3);
                    if (a4 == null) {
                        w.d("[biz] send encode fail!", new Object[0]);
                    } else {
                        am a5 = com.tencent.bugly.legu.proguard.a.a(this.a, 640, a4);
                        if (a5 == null) {
                            w.d("request package is null.", new Object[0]);
                        } else {
                            t.a().a(1001, a5, null, new s() {
                                public final void a(boolean z) {
                                    if (z) {
                                        w.c("up success do final", new Object[0]);
                                        long currentTimeMillis = System.currentTimeMillis();
                                        for (UserInfoBean userInfoBean : arrayList) {
                                            userInfoBean.f = currentTimeMillis;
                                            a.a(a.this, userInfoBean);
                                        }
                                    }
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0061, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0062, code lost:
        r1 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x009d, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000d, code lost:
        if (r10.trim().length() > 0) goto L_0x000f;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x009d A[ExcHandler: all (th java.lang.Throwable), Splitter:B:17:0x0047] */
    public final List<UserInfoBean> a(String str) {
        Cursor cursor;
        Cursor cursor2;
        boolean z = false;
        if (str != null) {
            try {
            } catch (Throwable th) {
                th = th;
                cursor2 = null;
                cursor2.close();
                throw th;
            }
        }
        z = true;
        cursor2 = o.a().a("t_ui", null, z ? null : "_pc = '" + str + "'", null, null, true);
        if (cursor2 == null) {
            if (cursor2 != null && !cursor2.isClosed()) {
                cursor2.close();
            }
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            ArrayList arrayList = new ArrayList();
            while (cursor2.moveToNext()) {
                UserInfoBean a2 = a(cursor2);
                if (a2 != null) {
                    arrayList.add(a2);
                } else {
                    sb.append(" or _id").append(" = ").append(cursor2.getLong(cursor2.getColumnIndex("_id")));
                }
            }
            String sb2 = sb.toString();
            if (sb2.length() > 0) {
                w.d("[session] deleted %s error data %d", "t_ui", Integer.valueOf(o.a().a("t_ui", sb2.substring(4), (String[]) null, (n) null, true)));
            }
            if (cursor2 != null && !cursor2.isClosed()) {
                cursor2.close();
            }
            return arrayList;
        } catch (Throwable th2) {
        }
        try {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            cursor2 = cursor;
            cursor2.close();
            throw th;
        }
    }

    private static void a(List<UserInfoBean> list) {
        if (list != null && list.size() != 0) {
            StringBuilder sb = new StringBuilder();
            for (UserInfoBean userInfoBean : list) {
                sb.append(" or _id").append(" = ").append(userInfoBean.a);
            }
            String sb2 = sb.toString();
            if (sb2.length() > 0) {
                sb2 = sb2.substring(4);
            }
            sb.setLength(0);
            try {
                w.c("deleted %s data %d", "t_ui", Integer.valueOf(o.a().a("t_ui", sb2, (String[]) null, (n) null, true)));
            } catch (Throwable th) {
                if (!w.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    private static ContentValues a(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (userInfoBean.a > 0) {
                contentValues.put("_id", Long.valueOf(userInfoBean.a));
            }
            contentValues.put("_tm", Long.valueOf(userInfoBean.e));
            contentValues.put("_ut", Long.valueOf(userInfoBean.f));
            contentValues.put("_tp", Integer.valueOf(userInfoBean.b));
            contentValues.put("_pc", userInfoBean.c);
            Parcel obtain = Parcel.obtain();
            userInfoBean.writeToParcel(obtain, 0);
            byte[] marshall = obtain.marshall();
            obtain.recycle();
            contentValues.put("_dt", marshall);
            return contentValues;
        } catch (Throwable th) {
            if (w.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    private static UserInfoBean a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex("_id"));
            UserInfoBean userInfoBean = (UserInfoBean) com.tencent.bugly.legu.proguard.a.a(blob, UserInfoBean.CREATOR);
            if (userInfoBean == null) {
                return userInfoBean;
            }
            userInfoBean.a = j;
            return userInfoBean;
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }
}
