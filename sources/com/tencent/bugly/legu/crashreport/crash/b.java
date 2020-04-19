package com.tencent.bugly.legu.crashreport.crash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import com.tencent.bugly.legu.BuglyStrategy;
import com.tencent.bugly.legu.crashreport.common.info.PlugInBean;
import com.tencent.bugly.legu.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.legu.crashreport.common.strategy.a;
import com.tencent.bugly.legu.proguard.ah;
import com.tencent.bugly.legu.proguard.aj;
import com.tencent.bugly.legu.proguard.ak;
import com.tencent.bugly.legu.proguard.al;
import com.tencent.bugly.legu.proguard.am;
import com.tencent.bugly.legu.proguard.j;
import com.tencent.bugly.legu.proguard.n;
import com.tencent.bugly.legu.proguard.o;
import com.tencent.bugly.legu.proguard.q;
import com.tencent.bugly.legu.proguard.s;
import com.tencent.bugly.legu.proguard.t;
import com.tencent.bugly.legu.proguard.w;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: BUGLY */
public final class b {
    private static int a = 0;
    private Context b;
    private o c;
    private a d;
    private n e;
    private BuglyStrategy.a f;

    public b(int i, Context context, t tVar, o oVar, a aVar, BuglyStrategy.a aVar2, n nVar) {
        a = i;
        this.b = context;
        this.c = oVar;
        this.d = aVar;
        this.f = aVar2;
        this.e = nVar;
    }

    private static List<a> a(List<a> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        long o = com.tencent.bugly.legu.proguard.a.o();
        ArrayList arrayList = new ArrayList();
        for (a aVar : list) {
            if (aVar.d && aVar.b < o - 86400000) {
                arrayList.add(aVar);
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0145  */
    private CrashDetailBean a(List<a> list, CrashDetailBean crashDetailBean) {
        CrashDetailBean crashDetailBean2;
        CrashDetailBean crashDetailBean3;
        if (list == null || list.size() == 0) {
            return crashDetailBean;
        }
        CrashDetailBean crashDetailBean4 = null;
        ArrayList arrayList = new ArrayList(10);
        for (a aVar : list) {
            if (aVar.e) {
                arrayList.add(aVar);
            }
        }
        if (arrayList.size() > 0) {
            List b2 = b((List<a>) arrayList);
            if (b2 != null && b2.size() > 0) {
                Collections.sort(b2);
                int i = 0;
                while (i < b2.size()) {
                    CrashDetailBean crashDetailBean5 = (CrashDetailBean) b2.get(i);
                    if (i != 0) {
                        if (crashDetailBean5.s != null) {
                            String[] split = crashDetailBean5.s.split("\n");
                            if (split != null) {
                                int length = split.length;
                                for (int i2 = 0; i2 < length; i2++) {
                                    String str = split[i2];
                                    if (!crashDetailBean4.s.contains(str)) {
                                        crashDetailBean4.t++;
                                        crashDetailBean4.s += str + "\n";
                                    }
                                }
                            }
                        }
                        crashDetailBean5 = crashDetailBean4;
                    }
                    i++;
                    crashDetailBean4 = crashDetailBean5;
                }
                crashDetailBean2 = crashDetailBean4;
                if (crashDetailBean2 != null) {
                    crashDetailBean.j = true;
                    crashDetailBean.t = 0;
                    crashDetailBean.s = "";
                    crashDetailBean3 = crashDetailBean;
                } else {
                    crashDetailBean3 = crashDetailBean2;
                }
                for (a aVar2 : list) {
                    if (!aVar2.e && !aVar2.d && !crashDetailBean3.s.contains(aVar2.b)) {
                        crashDetailBean3.t++;
                        crashDetailBean3.s += aVar2.b + "\n";
                    }
                }
                if (crashDetailBean3.r == crashDetailBean.r && !crashDetailBean3.s.contains(crashDetailBean.r)) {
                    crashDetailBean3.t++;
                    crashDetailBean3.s += crashDetailBean.r + "\n";
                    return crashDetailBean3;
                }
            }
        }
        crashDetailBean2 = null;
        if (crashDetailBean2 != null) {
        }
        for (a aVar22 : list) {
        }
        return crashDetailBean3.r == crashDetailBean.r ? crashDetailBean3 : crashDetailBean3;
    }

    public final boolean a(CrashDetailBean crashDetailBean) {
        return a(crashDetailBean, -123456789);
    }

    public final boolean a(CrashDetailBean crashDetailBean, int i) {
        int i2 = crashDetailBean.b;
        String str = crashDetailBean.n;
        String str2 = crashDetailBean.p;
        String str3 = crashDetailBean.q;
        long j = crashDetailBean.r;
        String str4 = crashDetailBean.m;
        String str5 = crashDetailBean.e;
        String str6 = crashDetailBean.c;
        if (this.e != null && !this.e.c()) {
            return true;
        }
        if (crashDetailBean.b != 2) {
            q qVar = new q();
            qVar.b = 1;
            qVar.c = crashDetailBean.z;
            qVar.d = crashDetailBean.A;
            qVar.e = crashDetailBean.r;
            o oVar = this.c;
            o.b(1);
            this.c.a(qVar);
            w.b("[crash] a crash occur, handling...", new Object[0]);
        } else {
            w.b("[crash] a caught exception occur, handling...", new Object[0]);
        }
        List<a> b2 = b();
        List list = null;
        if (b2 != null && b2.size() > 0) {
            ArrayList arrayList = new ArrayList(10);
            ArrayList arrayList2 = new ArrayList(10);
            arrayList.addAll(a(b2));
            b2.removeAll(arrayList);
            if (!com.tencent.bugly.legu.b.b && c.c) {
                boolean z = false;
                for (a aVar : b2) {
                    if (crashDetailBean.u.equals(aVar.c)) {
                        if (aVar.e) {
                            z = true;
                        }
                        arrayList2.add(aVar);
                    }
                    z = z;
                }
                if (z || arrayList2.size() + 1 >= 5) {
                    w.a("same crash occur too much do merged!", new Object[0]);
                    CrashDetailBean a2 = a((List<a>) arrayList2, crashDetailBean);
                    a2.a = -1;
                    c(a2);
                    arrayList.addAll(arrayList2);
                    c((List<a>) arrayList);
                    w.b("[crash] save crash success. this device crash many times, won't upload crashes immediately", new Object[0]);
                    return true;
                }
            }
            list = arrayList;
        }
        c(crashDetailBean);
        c(list);
        w.b("[crash] save crash success", new Object[0]);
        return false;
    }

    public final List<CrashDetailBean> a() {
        StrategyBean c2 = a.a().c();
        if (c2 == null) {
            w.d("have not synced remote!", new Object[0]);
            return null;
        } else if (!c2.d) {
            w.d("Crashreport remote closed, please check your APPID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            w.b("[init] WARNING! Crashreport closed by server, please check your APPID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            return null;
        } else {
            long currentTimeMillis = System.currentTimeMillis();
            long o = com.tencent.bugly.legu.proguard.a.o();
            List b2 = b();
            if (b2 == null || b2.size() <= 0) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            Iterator it = b2.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (aVar.b < o - c.f) {
                    it.remove();
                    arrayList.add(aVar);
                } else if (aVar.d) {
                    if (aVar.b >= currentTimeMillis - 86400000) {
                        it.remove();
                    } else if (!aVar.e) {
                        it.remove();
                        arrayList.add(aVar);
                    }
                } else if (((long) aVar.f) >= 3 && aVar.b < currentTimeMillis - 86400000) {
                    it.remove();
                    arrayList.add(aVar);
                }
            }
            if (arrayList.size() > 0) {
                c((List<a>) arrayList);
            }
            ArrayList arrayList2 = new ArrayList();
            List b3 = b(b2);
            if (b3 != null && b3.size() > 0) {
                String str = com.tencent.bugly.legu.crashreport.common.info.a.a().i;
                Iterator it2 = b3.iterator();
                while (it2.hasNext()) {
                    CrashDetailBean crashDetailBean = (CrashDetailBean) it2.next();
                    if (!str.equals(crashDetailBean.f)) {
                        it2.remove();
                        arrayList2.add(crashDetailBean);
                    }
                }
            }
            if (arrayList2.size() > 0) {
                d((List<CrashDetailBean>) arrayList2);
            }
            return b3;
        }
    }

    public final void a(CrashDetailBean crashDetailBean, long j, boolean z) {
        w.a("try to upload right now", new Object[0]);
        ArrayList arrayList = new ArrayList();
        arrayList.add(crashDetailBean);
        a((List<CrashDetailBean>) arrayList, 5000, z);
        int i = crashDetailBean.b;
        if (this.e != null) {
            n nVar = this.e;
        }
    }

    public final void a(final List<CrashDetailBean> list, long j, boolean z) {
        al alVar = null;
        if (!this.d.c().d) {
            w.d("remote report is disable!", new Object[0]);
            w.b("[crash] server closed bugly in this app. please check your appid if is correct, and re-install it", new Object[0]);
        } else if (list != null && list.size() != 0) {
            try {
                Context context = this.b;
                com.tencent.bugly.legu.crashreport.common.info.a a2 = com.tencent.bugly.legu.crashreport.common.info.a.a();
                if (context == null || list == null || list.size() == 0 || a2 == null) {
                    w.d("enEXPPkg args == null!", new Object[0]);
                } else {
                    al alVar2 = new al();
                    alVar2.a = new ArrayList<>();
                    for (CrashDetailBean a3 : list) {
                        alVar2.a.add(a(context, a3, a2));
                    }
                    alVar = alVar2;
                }
                if (alVar == null) {
                    w.d("create eupPkg fail!", new Object[0]);
                    return;
                }
                byte[] a4 = com.tencent.bugly.legu.proguard.a.a((j) alVar);
                if (a4 == null) {
                    w.d("send encode fail!", new Object[0]);
                    return;
                }
                am a5 = com.tencent.bugly.legu.proguard.a.a(this.b, 630, a4);
                if (a5 == null) {
                    w.d("request package is null.", new Object[0]);
                    return;
                }
                AnonymousClass1 r4 = new s() {
                    public final void a(boolean z) {
                        b bVar = b.this;
                        b.a(z, list);
                    }
                };
                if (!z) {
                    t.a().a(a, a5, null, r4);
                    return;
                }
                t.a().a(a, a5, null, r4, true, j);
                w.a("wake up!", new Object[0]);
            } catch (Throwable th) {
                w.e("req cr error %s", th.toString());
                if (!w.b(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    public static void a(boolean z, List<CrashDetailBean> list) {
        if (list != null && list.size() > 0) {
            w.c("up finish update state %b", Boolean.valueOf(z));
            for (CrashDetailBean crashDetailBean : list) {
                w.c("pre uid:%s uc:%d re:%b me:%b", crashDetailBean.c, Integer.valueOf(crashDetailBean.l), Boolean.valueOf(crashDetailBean.d), Boolean.valueOf(crashDetailBean.j));
                crashDetailBean.l++;
                crashDetailBean.d = z;
                w.c("set uid:%s uc:%d re:%b me:%b", crashDetailBean.c, Integer.valueOf(crashDetailBean.l), Boolean.valueOf(crashDetailBean.d), Boolean.valueOf(crashDetailBean.j));
            }
            for (CrashDetailBean a2 : list) {
                c.a().a(a2);
            }
            w.c("update state size %d", Integer.valueOf(list.size()));
        }
        if (!z) {
            w.b("[crash] upload fail.", new Object[0]);
        }
    }

    public final void b(CrashDetailBean crashDetailBean) {
        int i;
        String str;
        if (crashDetailBean != null) {
            if (this.f != null || this.e != null) {
                try {
                    w.a("[crash callback] start user's callback:onCrashHandleStart()", new Object[0]);
                    switch (crashDetailBean.b) {
                        case BuglyStrategy.a.CRASHTYPE_JAVA_CRASH /*0*/:
                            i = 0;
                            break;
                        case BuglyStrategy.a.CRASHTYPE_JAVA_CATCH /*1*/:
                            i = 2;
                            break;
                        case BuglyStrategy.a.CRASHTYPE_NATIVE /*2*/:
                            i = 1;
                            break;
                        case BuglyStrategy.a.CRASHTYPE_U3D /*3*/:
                            i = 4;
                            break;
                        case BuglyStrategy.a.CRASHTYPE_ANR /*4*/:
                            i = 3;
                            break;
                        case BuglyStrategy.a.CRASHTYPE_COCOS2DX_JS /*5*/:
                            i = 5;
                            break;
                        case BuglyStrategy.a.CRASHTYPE_COCOS2DX_LUA /*6*/:
                            i = 6;
                            break;
                        default:
                            return;
                    }
                    int i2 = crashDetailBean.b;
                    String str2 = crashDetailBean.n;
                    String str3 = crashDetailBean.p;
                    String str4 = crashDetailBean.q;
                    long j = crashDetailBean.r;
                    Map map = null;
                    if (this.e != null) {
                        n nVar = this.e;
                        String b2 = this.e.b();
                        if (b2 != null) {
                            map = new HashMap(1);
                            map.put("userData", b2);
                        }
                    } else if (this.f != null) {
                        map = this.f.onCrashHandleStart(i, crashDetailBean.n, crashDetailBean.o, crashDetailBean.q);
                    }
                    if (map != null && map.size() > 0) {
                        crashDetailBean.N = new LinkedHashMap(map.size());
                        for (Entry entry : map.entrySet()) {
                            String str5 = (String) entry.getKey();
                            if (!(str5 == null || str5.trim().length() <= 0)) {
                                String str6 = (String) entry.getKey();
                                if (str6.length() > 100) {
                                    str6 = str6.substring(0, 100);
                                    w.d("setted key length is over limit %d substring to %s", Integer.valueOf(100), str6);
                                }
                                String str7 = str6;
                                String str8 = (String) entry.getValue();
                                if ((str8 == null || str8.trim().length() <= 0) || ((String) entry.getValue()).length() <= 30000) {
                                    str = ((String) entry.getValue());
                                } else {
                                    str = ((String) entry.getValue()).substring(((String) entry.getValue()).length() - 30000);
                                    w.d("setted %s value length is over limit %d substring", str7, Integer.valueOf(BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH));
                                }
                                crashDetailBean.N.put(str7, str);
                                w.a("add setted key %s value size:%d", str7, Integer.valueOf(str.length()));
                            }
                        }
                    }
                    w.a("[crash callback] start user's callback:onCrashHandleStart2GetExtraDatas()", new Object[0]);
                    byte[] bArr = null;
                    if (this.e != null) {
                        bArr = this.e.a();
                    } else if (this.f != null) {
                        bArr = this.f.onCrashHandleStart2GetExtraDatas(i, crashDetailBean.n, crashDetailBean.o, crashDetailBean.q);
                    }
                    crashDetailBean.S = bArr;
                    if (crashDetailBean.S != null) {
                        if (crashDetailBean.S.length > 30000) {
                            w.d("extra bytes size %d is over limit %d will drop over part", Integer.valueOf(crashDetailBean.S.length), Integer.valueOf(BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH));
                        }
                        w.a("add extra bytes %d ", Integer.valueOf(crashDetailBean.S.length));
                    }
                } catch (Throwable th) {
                    w.d("crash handle callback somthing wrong! %s", th.getClass().getName());
                    if (!w.a(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
    }

    private static ContentValues d(CrashDetailBean crashDetailBean) {
        int i;
        int i2 = 1;
        if (crashDetailBean == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (crashDetailBean.a > 0) {
                contentValues.put("_id", Long.valueOf(crashDetailBean.a));
            }
            contentValues.put("_tm", Long.valueOf(crashDetailBean.r));
            contentValues.put("_s1", crashDetailBean.u);
            String str = "_up";
            if (crashDetailBean.d) {
                i = 1;
            } else {
                i = 0;
            }
            contentValues.put(str, Integer.valueOf(i));
            String str2 = "_me";
            if (!crashDetailBean.j) {
                i2 = 0;
            }
            contentValues.put(str2, Integer.valueOf(i2));
            contentValues.put("_uc", Integer.valueOf(crashDetailBean.l));
            Parcel obtain = Parcel.obtain();
            crashDetailBean.writeToParcel(obtain, 0);
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

    private static CrashDetailBean a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex("_id"));
            CrashDetailBean crashDetailBean = (CrashDetailBean) com.tencent.bugly.legu.proguard.a.a(blob, CrashDetailBean.CREATOR);
            if (crashDetailBean == null) {
                return crashDetailBean;
            }
            crashDetailBean.a = j;
            return crashDetailBean;
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public final void c(CrashDetailBean crashDetailBean) {
        if (crashDetailBean != null) {
            ContentValues d2 = d(crashDetailBean);
            if (d2 != null) {
                long a2 = o.a().a("t_cr", d2, (n) null, true);
                if (a2 >= 0) {
                    w.c("insert %s success!", "t_cr");
                    crashDetailBean.a = a2;
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0077, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0078, code lost:
        r1 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00b4, code lost:
        r0 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00b4 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:21:0x0062] */
    private List<CrashDetailBean> b(List<a> list) {
        Cursor cursor;
        Cursor cursor2;
        if (list == null || list.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (a aVar : list) {
            sb.append(" or _id").append(" = ").append(aVar.a);
        }
        String sb2 = sb.toString();
        if (sb2.length() > 0) {
            sb2 = sb2.substring(4);
        }
        sb.setLength(0);
        try {
            cursor2 = o.a().a("t_cr", null, sb2, null, null, true);
            if (cursor2 == null) {
                if (cursor2 != null && !cursor2.isClosed()) {
                    cursor2.close();
                }
                return null;
            }
            try {
                ArrayList arrayList = new ArrayList();
                while (cursor2.moveToNext()) {
                    CrashDetailBean a2 = a(cursor2);
                    if (a2 != null) {
                        arrayList.add(a2);
                    } else {
                        sb.append(" or _id").append(" = ").append(cursor2.getLong(cursor2.getColumnIndex("_id")));
                    }
                }
                String sb3 = sb.toString();
                if (sb3.length() > 0) {
                    w.d("deleted %s illegle data %d", "t_cr", Integer.valueOf(o.a().a("t_cr", sb3.substring(4), (String[]) null, (n) null, true)));
                }
                if (cursor2 != null && !cursor2.isClosed()) {
                    cursor2.close();
                }
                return arrayList;
            } catch (Throwable th) {
            }
        } catch (Throwable th2) {
            th = th2;
            cursor2 = null;
            cursor2.close();
            throw th;
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

    private static a b(Cursor cursor) {
        boolean z = true;
        if (cursor == null) {
            return null;
        }
        try {
            a aVar = new a();
            aVar.a = cursor.getLong(cursor.getColumnIndex("_id"));
            aVar.b = cursor.getLong(cursor.getColumnIndex("_tm"));
            aVar.c = cursor.getString(cursor.getColumnIndex("_s1"));
            aVar.d = cursor.getInt(cursor.getColumnIndex("_up")) == 1;
            if (cursor.getInt(cursor.getColumnIndex("_me")) != 1) {
                z = false;
            }
            aVar.e = z;
            aVar.f = cursor.getInt(cursor.getColumnIndex("_uc"));
            return aVar;
        } catch (Throwable th) {
            if (w.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0059, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005a, code lost:
        r7 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0095, code lost:
        r0 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0095 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:9:0x0044] */
    private List<a> b() {
        Cursor cursor;
        Cursor cursor2 = null;
        ArrayList arrayList = new ArrayList();
        try {
            cursor = o.a().a("t_cr", new String[]{"_id", "_tm", "_s1", "_up", "_me", "_uc"}, null, null, null, true);
            if (cursor == null) {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                return null;
            }
            try {
                StringBuilder sb = new StringBuilder();
                while (cursor.moveToNext()) {
                    a b2 = b(cursor);
                    if (b2 != null) {
                        arrayList.add(b2);
                    } else {
                        sb.append(" or _id").append(" = ").append(cursor.getLong(cursor.getColumnIndex("_id")));
                    }
                }
                String sb2 = sb.toString();
                if (sb2.length() > 0) {
                    w.d("deleted %s illegle data %d", "t_cr", Integer.valueOf(o.a().a("t_cr", sb2.substring(4), (String[]) null, (n) null, true)));
                }
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                return arrayList;
            } catch (Throwable th) {
            }
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
            cursor.close();
            throw th;
        }
        try {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            if (cursor2 != null && !cursor2.isClosed()) {
                cursor2.close();
            }
            return arrayList;
        } catch (Throwable th3) {
            th = th3;
            cursor = cursor2;
            cursor.close();
            throw th;
        }
    }

    private static void c(List<a> list) {
        if (list != null && list.size() != 0) {
            StringBuilder sb = new StringBuilder();
            for (a aVar : list) {
                sb.append(" or _id").append(" = ").append(aVar.a);
            }
            String sb2 = sb.toString();
            if (sb2.length() > 0) {
                sb2 = sb2.substring(4);
            }
            sb.setLength(0);
            try {
                w.c("deleted %s data %d", "t_cr", Integer.valueOf(o.a().a("t_cr", sb2, (String[]) null, (n) null, true)));
            } catch (Throwable th) {
                if (!w.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    private static void d(List<CrashDetailBean> list) {
        if (list != null) {
            try {
                if (list.size() != 0) {
                    StringBuilder sb = new StringBuilder();
                    for (CrashDetailBean crashDetailBean : list) {
                        sb.append(" or _id").append(" = ").append(crashDetailBean.a);
                    }
                    String sb2 = sb.toString();
                    if (sb2.length() > 0) {
                        sb2 = sb2.substring(4);
                    }
                    sb.setLength(0);
                    w.c("deleted %s data %d", "t_cr", Integer.valueOf(o.a().a("t_cr", sb2, (String[]) null, (n) null, true)));
                }
            } catch (Throwable th) {
                if (!w.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    private static ak a(Context context, CrashDetailBean crashDetailBean, com.tencent.bugly.legu.crashreport.common.info.a aVar) {
        int i;
        boolean z = true;
        if (context == null || crashDetailBean == null || aVar == null) {
            w.d("enExp args == null", new Object[0]);
            return null;
        }
        ak akVar = new ak();
        switch (crashDetailBean.b) {
            case BuglyStrategy.a.CRASHTYPE_JAVA_CRASH /*0*/:
                akVar.a = crashDetailBean.j ? "200" : "100";
                break;
            case BuglyStrategy.a.CRASHTYPE_JAVA_CATCH /*1*/:
                akVar.a = crashDetailBean.j ? "201" : "101";
                break;
            case BuglyStrategy.a.CRASHTYPE_NATIVE /*2*/:
                akVar.a = crashDetailBean.j ? "202" : "102";
                break;
            case BuglyStrategy.a.CRASHTYPE_U3D /*3*/:
                akVar.a = crashDetailBean.j ? "203" : "103";
                break;
            case BuglyStrategy.a.CRASHTYPE_ANR /*4*/:
                akVar.a = crashDetailBean.j ? "204" : "104";
                break;
            case BuglyStrategy.a.CRASHTYPE_COCOS2DX_JS /*5*/:
                akVar.a = crashDetailBean.j ? "207" : "107";
                break;
            case BuglyStrategy.a.CRASHTYPE_COCOS2DX_LUA /*6*/:
                akVar.a = crashDetailBean.j ? "206" : "106";
                break;
            default:
                w.e("crash type error! %d", Integer.valueOf(crashDetailBean.b));
                break;
        }
        akVar.b = crashDetailBean.r;
        akVar.c = crashDetailBean.n;
        akVar.d = crashDetailBean.o;
        akVar.e = crashDetailBean.p;
        akVar.g = crashDetailBean.q;
        akVar.h = crashDetailBean.y;
        akVar.i = crashDetailBean.c;
        akVar.j = null;
        akVar.l = crashDetailBean.m;
        akVar.m = crashDetailBean.e;
        akVar.f = crashDetailBean.A;
        akVar.t = com.tencent.bugly.legu.crashreport.common.info.a.a().h();
        akVar.n = null;
        if (crashDetailBean.i != null && crashDetailBean.i.size() > 0) {
            akVar.o = new ArrayList<>();
            for (Entry entry : crashDetailBean.i.entrySet()) {
                ah ahVar = new ah();
                ahVar.a = ((PlugInBean) entry.getValue()).a;
                ahVar.c = ((PlugInBean) entry.getValue()).c;
                ahVar.d = ((PlugInBean) entry.getValue()).b;
                ahVar.b = aVar.q();
                akVar.o.add(ahVar);
            }
        }
        if (crashDetailBean.h != null && crashDetailBean.h.size() > 0) {
            akVar.p = new ArrayList<>();
            for (Entry entry2 : crashDetailBean.h.entrySet()) {
                ah ahVar2 = new ah();
                ahVar2.a = ((PlugInBean) entry2.getValue()).a;
                ahVar2.c = ((PlugInBean) entry2.getValue()).c;
                ahVar2.d = ((PlugInBean) entry2.getValue()).b;
                akVar.p.add(ahVar2);
            }
        }
        if (crashDetailBean.j) {
            akVar.k = crashDetailBean.t;
            if (crashDetailBean.s != null && crashDetailBean.s.length() > 0) {
                if (akVar.q == null) {
                    akVar.q = new ArrayList<>();
                }
                try {
                    akVar.q.add(new aj(1, "alltimes.txt", crashDetailBean.s.getBytes("utf-8")));
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                    akVar.q = null;
                }
            }
            String str = "crashcount:%d sz:%d";
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(akVar.k);
            if (akVar.q != null) {
                i = akVar.q.size();
            } else {
                i = 0;
            }
            objArr[1] = Integer.valueOf(i);
            w.c(str, objArr);
        }
        if (crashDetailBean.w != null) {
            if (akVar.q == null) {
                akVar.q = new ArrayList<>();
            }
            try {
                akVar.q.add(new aj(1, "log.txt", crashDetailBean.w.getBytes("utf-8")));
            } catch (UnsupportedEncodingException e3) {
                e3.printStackTrace();
                akVar.q = null;
            }
        }
        if (crashDetailBean.x != null && crashDetailBean.x.length > 0) {
            aj ajVar = new aj(2, "buglylog.zip", crashDetailBean.x);
            w.c("attach user log", new Object[0]);
            if (akVar.q == null) {
                akVar.q = new ArrayList<>();
            }
            akVar.q.add(ajVar);
        }
        if (crashDetailBean.b == 3) {
            if (akVar.q == null) {
                akVar.q = new ArrayList<>();
            }
            if (crashDetailBean.N != null && crashDetailBean.N.containsKey("BUGLY_CR_01")) {
                try {
                    akVar.q.add(new aj(1, "anrMessage.txt", ((String) crashDetailBean.N.get("BUGLY_CR_01")).getBytes("utf-8")));
                    w.c("attach anr message", new Object[0]);
                } catch (UnsupportedEncodingException e4) {
                    e4.printStackTrace();
                    akVar.q = null;
                }
                crashDetailBean.N.remove("BUGLY_CR_01");
            }
            if (crashDetailBean.v != null) {
                aj a2 = a("trace.zip", context, crashDetailBean.v);
                if (a2 != null) {
                    w.c("attach traces", new Object[0]);
                    akVar.q.add(a2);
                }
            }
        }
        if (crashDetailBean.b == 1) {
            if (akVar.q == null) {
                akVar.q = new ArrayList<>();
            }
            if (crashDetailBean.v != null) {
                aj a3 = a("tomb.zip", context, crashDetailBean.v);
                if (a3 != null) {
                    w.c("attach tombs", new Object[0]);
                    akVar.q.add(a3);
                }
            }
        }
        if (crashDetailBean.S != null && crashDetailBean.S.length > 0) {
            if (akVar.q == null) {
                akVar.q = new ArrayList<>();
            }
            akVar.q.add(new aj(1, "userExtraByteData", crashDetailBean.S));
            w.c("attach extraData", new Object[0]);
        }
        akVar.r = new HashMap();
        akVar.r.put("A9", crashDetailBean.B);
        akVar.r.put("A11", crashDetailBean.C);
        akVar.r.put("A10", crashDetailBean.D);
        akVar.r.put("A23", crashDetailBean.f);
        akVar.r.put("A7", aVar.e);
        akVar.r.put("A6", aVar.r());
        akVar.r.put("A5", aVar.q());
        akVar.r.put("A22", aVar.g());
        akVar.r.put("A2", crashDetailBean.F);
        akVar.r.put("A1", crashDetailBean.E);
        akVar.r.put("A24", aVar.g);
        akVar.r.put("A17", crashDetailBean.G);
        akVar.r.put("A3", aVar.j());
        akVar.r.put("A16", aVar.l());
        akVar.r.put("A25", aVar.m());
        akVar.r.put("A14", aVar.k());
        akVar.r.put("A15", aVar.t());
        akVar.r.put("A13", aVar.u());
        akVar.r.put("A34", crashDetailBean.z);
        if (aVar.w != null) {
            akVar.r.put("productIdentify", aVar.w);
        }
        try {
            akVar.r.put("A26", URLEncoder.encode(crashDetailBean.H, "utf-8"));
        } catch (UnsupportedEncodingException e5) {
            e5.printStackTrace();
        }
        if (crashDetailBean.b == 1) {
            akVar.r.put("A27", crashDetailBean.J);
            akVar.r.put("A28", crashDetailBean.I);
            akVar.r.put("A29", crashDetailBean.k);
        }
        akVar.r.put("A30", crashDetailBean.K);
        akVar.r.put("A18", crashDetailBean.L);
        akVar.r.put("A36", (!crashDetailBean.M));
        akVar.r.put("F02", aVar.p);
        akVar.r.put("F03", aVar.q);
        akVar.r.put("F04", aVar.d());
        akVar.r.put("F05", aVar.r);
        akVar.r.put("F06", aVar.o);
        akVar.r.put("F08", aVar.u);
        akVar.r.put("F09", aVar.v);
        akVar.r.put("F10", aVar.s);
        if (crashDetailBean.O >= 0) {
            akVar.r.put("C01", crashDetailBean.O);
        }
        if (crashDetailBean.P >= 0) {
            akVar.r.put("C02", crashDetailBean.P);
        }
        if (crashDetailBean.Q != null && crashDetailBean.Q.size() > 0) {
            for (Entry entry3 : crashDetailBean.Q.entrySet()) {
                akVar.r.put("C03_" + ((String) entry3.getKey()), entry3.getValue());
            }
        }
        if (crashDetailBean.R != null && crashDetailBean.R.size() > 0) {
            for (Entry entry4 : crashDetailBean.R.entrySet()) {
                akVar.r.put("C04_" + ((String) entry4.getKey()), entry4.getValue());
            }
        }
        akVar.s = null;
        if (crashDetailBean.N != null && crashDetailBean.N.size() > 0) {
            akVar.s = crashDetailBean.N;
            w.a("setted message size %d", Integer.valueOf(akVar.s.size()));
        }
        String str2 = "%s rid:%s sess:%s ls:%ds isR:%b isF:%b isM:%b isN:%b mc:%d ,%s ,isUp:%b ,vm:%d";
        Object[] objArr2 = new Object[12];
        objArr2[0] = crashDetailBean.n;
        objArr2[1] = crashDetailBean.c;
        objArr2[2] = aVar.d();
        objArr2[3] = Long.valueOf((crashDetailBean.r - crashDetailBean.L) / 1000);
        objArr2[4] = Boolean.valueOf(crashDetailBean.k);
        objArr2[5] = Boolean.valueOf(crashDetailBean.M);
        objArr2[6] = Boolean.valueOf(crashDetailBean.j);
        if (crashDetailBean.b != 1) {
            z = false;
        }
        objArr2[7] = Boolean.valueOf(z);
        objArr2[8] = Integer.valueOf(crashDetailBean.t);
        objArr2[9] = crashDetailBean.s;
        objArr2[10] = Boolean.valueOf(crashDetailBean.d);
        objArr2[11] = Integer.valueOf(akVar.r.size());
        w.c(str2, objArr2);
        return akVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0059 A[Catch:{ all -> 0x00e0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005e A[SYNTHETIC, Splitter:B:22:0x005e] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00c1 A[SYNTHETIC, Splitter:B:46:0x00c1] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:61:? A[RETURN, SYNTHETIC] */
    private static aj a(String str, Context context, String str2) {
        FileInputStream fileInputStream;
        Throwable th;
        if (str2 == null || context == null) {
            w.d("rqdp{  createZipAttachment sourcePath == null || context == null ,pls check}", new Object[0]);
            return null;
        }
        w.c("zip %s", str2);
        File file = new File(str2);
        File file2 = new File(context.getCacheDir(), str);
        if (!com.tencent.bugly.legu.proguard.a.a(file, file2, 5000)) {
            w.d("zip fail!", new Object[0]);
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            fileInputStream = new FileInputStream(file2);
            try {
                byte[] bArr = new byte[1000];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                    byteArrayOutputStream.flush();
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                w.c("read bytes :%d", Integer.valueOf(byteArray.length));
                aj ajVar = new aj(2, file2.getName(), byteArray);
                try {
                    fileInputStream.close();
                } catch (IOException e2) {
                    if (!w.a(e2)) {
                        e2.printStackTrace();
                    }
                }
                if (file2.exists()) {
                    w.c("del tmp", new Object[0]);
                    file2.delete();
                }
                return ajVar;
            } catch (Throwable th2) {
                th = th2;
                try {
                    if (!w.a(th)) {
                        th.printStackTrace();
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e3) {
                            if (!w.a(e3)) {
                                e3.printStackTrace();
                            }
                        }
                    }
                    if (file2.exists()) {
                        return null;
                    }
                    w.c("del tmp", new Object[0]);
                    file2.delete();
                    return null;
                } catch (Throwable th3) {
                    th = th3;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e4) {
                            if (!w.a(e4)) {
                                e4.printStackTrace();
                            }
                        }
                    }
                    if (file2.exists()) {
                        w.c("del tmp", new Object[0]);
                        file2.delete();
                    }
                    throw th;
                }
            }
        } catch (Throwable th4) {
            fileInputStream = null;
            th = th4;
            if (fileInputStream != null) {
            }
            if (file2.exists()) {
            }
            throw th;
        }
    }

    public static void a(String str, String str2, String str3, Thread thread, String str4, CrashDetailBean crashDetailBean) {
        boolean z;
        boolean z2;
        com.tencent.bugly.legu.crashreport.common.info.a a2 = com.tencent.bugly.legu.crashreport.common.info.a.a();
        if (a2 != null) {
            w.e("#++++++++++Record By Bugly++++++++++#", new Object[0]);
            w.e("# You can use Bugly(http:\\\\bugly.qq.com) to get more Crash Detail!", new Object[0]);
            w.e("# PKG NAME: %s", a2.c);
            w.e("# APP VER: %s", a2.i);
            w.e("# LAUNCH TIME: %s", com.tencent.bugly.legu.proguard.a.a(new Date(com.tencent.bugly.legu.crashreport.common.info.a.a().a)));
            w.e("# CRASH TYPE: %s", str);
            w.e("# CRASH TIME: %s", str2);
            w.e("# CRASH PROCESS: %s", str3);
            if (thread != null) {
                w.e("# CRASH THREAD: %s", thread.getName());
            }
            if (crashDetailBean != null) {
                w.e("# REPORT ID: %s", crashDetailBean.c);
                String str5 = "# CRASH DEVICE: %s %s";
                Object[] objArr = new Object[2];
                objArr[0] = a2.f;
                objArr[1] = a2.u().booleanValue() ? "ROOTED" : "UNROOT";
                w.e(str5, objArr);
                w.e("# RUNTIME AVAIL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.B), Long.valueOf(crashDetailBean.C), Long.valueOf(crashDetailBean.D));
                w.e("# RUNTIME TOTAL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.E), Long.valueOf(crashDetailBean.F), Long.valueOf(crashDetailBean.G));
                String str6 = crashDetailBean.J;
                if (str6 == null || str6.trim().length() <= 0) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (!z2) {
                    w.e("# EXCEPTION FIRED BY %s %s", crashDetailBean.J, crashDetailBean.I);
                } else if (crashDetailBean.b == 3) {
                    String str7 = "# EXCEPTION ANR MESSAGE:\n %s";
                    Object[] objArr2 = new Object[1];
                    objArr2[0] = crashDetailBean.N == null ? "null" : ((String) crashDetailBean.N.get("BUGLY_CR_01"));
                    w.e(str7, objArr2);
                }
            }
            if (str4 == null || str4.trim().length() <= 0) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                w.e("# CRASH STACK: ", new Object[0]);
                w.e(str4, new Object[0]);
            }
            w.e("#++++++++++++++++++++++++++++++++++++++++++#", new Object[0]);
        }
    }
}
