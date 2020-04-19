package com.tencent.bugly.legu.proguard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: BUGLY */
public final class o {
    private static o a = null;
    private static p b = null;
    private static boolean c = false;

    /* compiled from: BUGLY */
    class a extends Thread {
        private int a;
        private n b;
        private String c;
        private ContentValues d;
        private boolean e;
        private String[] f;
        private String g;
        private String[] h;
        private String i;
        private String j;
        private String k;
        private String l;
        private String m;
        private String[] n;
        private int o;
        private String p;
        private byte[] q;

        public a(int i2, n nVar) {
            this.a = i2;
            this.b = nVar;
        }

        public final void a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
            this.e = z;
            this.c = str;
            this.f = strArr;
            this.g = str2;
            this.h = strArr2;
            this.i = str3;
            this.j = str4;
            this.k = str5;
            this.l = str6;
        }

        public final void a(int i2, String str, byte[] bArr) {
            this.o = i2;
            this.p = str;
            this.q = bArr;
        }

        public final void run() {
            switch (this.a) {
                case com.tencent.bugly.legu.BuglyStrategy.a.CRASHTYPE_JAVA_CATCH /*1*/:
                    o.this.a(this.c, this.d, this.b);
                    return;
                case com.tencent.bugly.legu.BuglyStrategy.a.CRASHTYPE_NATIVE /*2*/:
                    o.this.a(this.c, this.m, this.n, this.b);
                    return;
                case com.tencent.bugly.legu.BuglyStrategy.a.CRASHTYPE_U3D /*3*/:
                    o.this.a(this.e, this.c, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.b);
                    return;
                case com.tencent.bugly.legu.BuglyStrategy.a.CRASHTYPE_ANR /*4*/:
                    o.this.a(this.o, this.p, this.q, this.b);
                    return;
                case com.tencent.bugly.legu.BuglyStrategy.a.CRASHTYPE_COCOS2DX_JS /*5*/:
                    o.this.a(this.o, this.b);
                    return;
                case com.tencent.bugly.legu.BuglyStrategy.a.CRASHTYPE_COCOS2DX_LUA /*6*/:
                    o oVar = o.this;
                    o.a(this.o, this.p, this.b);
                    return;
                default:
                    return;
            }
        }
    }

    private o(Context context, List<com.tencent.bugly.legu.a> list) {
        b = new p(context, list);
    }

    public static synchronized o a(Context context, List<com.tencent.bugly.legu.a> list) {
        o oVar;
        synchronized (o.class) {
            if (a == null) {
                a = new o(context, list);
            }
            oVar = a;
        }
        return oVar;
    }

    public static synchronized o a() {
        o oVar;
        synchronized (o.class) {
            oVar = a;
        }
        return oVar;
    }

    public final long a(String str, ContentValues contentValues, n nVar, boolean z) {
        return a(str, contentValues, (n) null);
    }

    public final Cursor a(String str, String[] strArr, String str2, String[] strArr2, n nVar, boolean z) {
        return a(false, str, strArr, str2, null, null, null, null, null, null);
    }

    public final int a(String str, String str2, String[] strArr, n nVar, boolean z) {
        return a(str, str2, (String[]) null, (n) null);
    }

    /* access modifiers changed from: private */
    public synchronized long a(String str, ContentValues contentValues, n nVar) {
        long j = 0;
        synchronized (this) {
            try {
                SQLiteDatabase writableDatabase = b.getWritableDatabase();
                if (!(writableDatabase == null || contentValues == null)) {
                    long replace = writableDatabase.replace(str, "_id", contentValues);
                    if (replace >= 0) {
                        w.c("[db] insert %s success", str);
                    } else {
                        w.d("[db] replace %s error", str);
                    }
                    j = replace;
                }
                if (nVar != null) {
                    Long.valueOf(j);
                }
            } catch (Throwable th) {
                if (nVar != null) {
                    Long.valueOf(0);
                }
                throw th;
            }
        }
        return j;
    }

    /* access modifiers changed from: private */
    public synchronized Cursor a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6, n nVar) {
        Cursor cursor;
        try {
            SQLiteDatabase writableDatabase = b.getWritableDatabase();
            if (writableDatabase != null) {
                cursor = writableDatabase.query(z, str, strArr, str2, strArr2, str3, str4, str5, str6);
            } else {
                cursor = null;
            }
            if (nVar != null) {
            }
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            if (nVar != null) {
                cursor = null;
            } else {
                cursor = null;
            }
        }
        return cursor;
    }

    /* access modifiers changed from: private */
    public synchronized int a(String str, String str2, String[] strArr, n nVar) {
        int i = 0;
        synchronized (this) {
            try {
                SQLiteDatabase writableDatabase = b.getWritableDatabase();
                if (writableDatabase != null) {
                    i = writableDatabase.delete(str, str2, strArr);
                }
                if (nVar != null) {
                    Integer.valueOf(i);
                }
            } catch (Throwable th) {
                if (nVar != null) {
                    Integer.valueOf(0);
                }
                throw th;
            }
        }
        return i;
    }

    public final boolean a(int i, String str, byte[] bArr, n nVar, boolean z) {
        if (z) {
            return a(i, str, bArr, (n) null);
        }
        a aVar = new a(4, null);
        aVar.a(i, str, bArr);
        v.a().b(aVar);
        return true;
    }

    public final Map<String, byte[]> a(int i, n nVar, boolean z) {
        return a(i, (n) null);
    }

    public final boolean a(int i, String str, n nVar, boolean z) {
        return a(555, str, (n) null);
    }

    /* access modifiers changed from: private */
    public boolean a(int i, String str, byte[] bArr, n nVar) {
        boolean z = false;
        try {
            q qVar = new q();
            qVar.a = (long) i;
            qVar.f = str;
            qVar.e = System.currentTimeMillis();
            qVar.g = bArr;
            z = b(qVar);
            if (nVar != null) {
                Boolean.valueOf(z);
            }
        } catch (Throwable th) {
            if (nVar != null) {
                Boolean.valueOf(z);
            }
            throw th;
        }
        return z;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x002e  */
    public Map<String, byte[]> a(int i, n nVar) {
        Throwable th;
        Map<String, byte[]> map;
        try {
            List<q> c2 = c(i);
            HashMap hashMap = new HashMap();
            try {
                for (q qVar : c2) {
                    byte[] bArr = qVar.g;
                    if (bArr != null) {
                        hashMap.put(qVar.f, bArr);
                    }
                }
                if (nVar != null) {
                    return hashMap;
                }
                return hashMap;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                map = hashMap;
                th = th3;
                if (!w.a(th)) {
                    th.printStackTrace();
                }
                if (nVar == null) {
                }
                return map;
            }
        } catch (Throwable th4) {
            th = th4;
            map = null;
            if (!w.a(th)) {
            }
            if (nVar == null) {
            }
            return map;
        }
    }

    public final boolean a(q qVar) {
        if (qVar == null) {
            return false;
        }
        try {
            SQLiteDatabase writableDatabase = b.getWritableDatabase();
            if (writableDatabase == null) {
                return false;
            }
            ContentValues c2 = c(qVar);
            if (c2 == null) {
                return false;
            }
            long replace = writableDatabase.replace("t_lr", "_id", c2);
            if (replace < 0) {
                return false;
            }
            w.c("insert %s success!", "t_lr");
            qVar.a = replace;
            return true;
        } catch (Throwable th) {
            if (w.a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    private boolean b(q qVar) {
        if (qVar == null) {
            return false;
        }
        try {
            SQLiteDatabase writableDatabase = b.getWritableDatabase();
            if (writableDatabase == null) {
                return false;
            }
            ContentValues d = d(qVar);
            if (d == null) {
                return false;
            }
            long replace = writableDatabase.replace("t_pf", "_id", d);
            if (replace < 0) {
                return false;
            }
            w.c("insert %s success!", "t_pf");
            qVar.a = replace;
            return true;
        } catch (Throwable th) {
            if (w.a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0050, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0051, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x008c, code lost:
        r0 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x008c A[ExcHandler: all (th java.lang.Throwable), Splitter:B:13:0x0036] */
    public final List<q> a(int i) {
        Cursor cursor;
        String str;
        SQLiteDatabase writableDatabase = b.getWritableDatabase();
        if (writableDatabase != null) {
            if (i >= 0) {
                try {
                    str = "_tp = " + i;
                } catch (Throwable th) {
                    th = th;
                    cursor = null;
                    cursor.close();
                    throw th;
                }
            } else {
                str = null;
            }
            cursor = writableDatabase.query("t_lr", null, str, null, null, null, null);
            if (cursor == null) {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                return null;
            }
            try {
                StringBuilder sb = new StringBuilder();
                ArrayList arrayList = new ArrayList();
                while (cursor.moveToNext()) {
                    q a2 = a(cursor);
                    if (a2 != null) {
                        arrayList.add(a2);
                    } else {
                        sb.append(" or _id").append(" = ").append(cursor.getLong(cursor.getColumnIndex("_id")));
                    }
                }
                String sb2 = sb.toString();
                if (sb2.length() > 0) {
                    w.d("deleted %s illegle data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", sb2.substring(4), null)));
                }
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                return arrayList;
            } catch (Throwable th2) {
            }
        }
        return null;
    }

    public static void a(List<q> list) {
        if (list != null && list.size() != 0) {
            SQLiteDatabase writableDatabase = b.getWritableDatabase();
            if (writableDatabase != null) {
                StringBuilder sb = new StringBuilder();
                for (q qVar : list) {
                    sb.append(" or _id").append(" = ").append(qVar.a);
                }
                String sb2 = sb.toString();
                if (sb2.length() > 0) {
                    sb2 = sb2.substring(4);
                }
                sb.setLength(0);
                try {
                    w.c("deleted %s data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", sb2, null)));
                } catch (Throwable th) {
                    if (!w.a(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
    }

    public static void b(int i) {
        String str = null;
        SQLiteDatabase writableDatabase = b.getWritableDatabase();
        if (writableDatabase != null) {
            if (i >= 0) {
                try {
                    str = "_tp = " + i;
                } catch (Throwable th) {
                    if (!w.a(th)) {
                        th.printStackTrace();
                        return;
                    }
                    return;
                }
            }
            w.c("deleted %s data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", str, null)));
        }
    }

    private static ContentValues c(q qVar) {
        if (qVar == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (qVar.a > 0) {
                contentValues.put("_id", Long.valueOf(qVar.a));
            }
            contentValues.put("_tp", Integer.valueOf(qVar.b));
            contentValues.put("_pc", qVar.c);
            contentValues.put("_th", qVar.d);
            contentValues.put("_tm", Long.valueOf(qVar.e));
            if (qVar.g != null) {
                contentValues.put("_dt", qVar.g);
            }
            return contentValues;
        } catch (Throwable th) {
            if (w.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    private static q a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            q qVar = new q();
            qVar.a = cursor.getLong(cursor.getColumnIndex("_id"));
            qVar.b = cursor.getInt(cursor.getColumnIndex("_tp"));
            qVar.c = cursor.getString(cursor.getColumnIndex("_pc"));
            qVar.d = cursor.getString(cursor.getColumnIndex("_th"));
            qVar.e = cursor.getLong(cursor.getColumnIndex("_tm"));
            qVar.g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return qVar;
        } catch (Throwable th) {
            if (w.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004c, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004d, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0088, code lost:
        r0 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0088 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:11:0x0032] */
    private List<q> c(int i) {
        Cursor cursor;
        try {
            SQLiteDatabase writableDatabase = b.getWritableDatabase();
            if (writableDatabase != null) {
                String str = "_id = " + i;
                cursor = writableDatabase.query("t_pf", null, str, null, null, null, null);
                if (cursor == null) {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    return null;
                }
                try {
                    StringBuilder sb = new StringBuilder();
                    ArrayList arrayList = new ArrayList();
                    while (cursor.moveToNext()) {
                        q b2 = b(cursor);
                        if (b2 != null) {
                            arrayList.add(b2);
                        } else {
                            sb.append(" or _tp").append(" = ").append(cursor.getString(cursor.getColumnIndex("_tp")));
                        }
                    }
                    if (sb.length() > 0) {
                        sb.append(" and _id").append(" = ").append(i);
                        w.d("deleted %s illegle data %d", "t_pf", Integer.valueOf(writableDatabase.delete("t_pf", str.substring(4), null)));
                    }
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    return arrayList;
                } catch (Throwable th) {
                }
            }
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
            cursor.close();
            throw th;
        }
        return null;
    }

    public static boolean a(int i, String str, n nVar) {
        String str2;
        boolean z = true;
        boolean z2 = false;
        try {
            SQLiteDatabase writableDatabase = b.getWritableDatabase();
            if (writableDatabase != null) {
                if (str == null || str.trim().length() <= 0) {
                    str2 = "_id = " + i;
                } else {
                    str2 = "_id = " + i + " and _tp" + " = \"" + str + "\"";
                }
                int delete = writableDatabase.delete("t_pf", str2, null);
                w.c("deleted %s data %d", "t_pf", Integer.valueOf(delete));
                if (delete <= 0) {
                    z = false;
                }
                z2 = z;
            }
            if (nVar != null) {
                Boolean.valueOf(z2);
            }
        } catch (Throwable th) {
            if (nVar != null) {
                Boolean.valueOf(false);
            }
            throw th;
        }
        return z2;
    }

    private static ContentValues d(q qVar) {
        boolean z;
        if (qVar != null) {
            String str = qVar.f;
            if (str == null || str.trim().length() <= 0) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                try {
                    ContentValues contentValues = new ContentValues();
                    if (qVar.a > 0) {
                        contentValues.put("_id", Long.valueOf(qVar.a));
                    }
                    contentValues.put("_tp", qVar.f);
                    contentValues.put("_tm", Long.valueOf(qVar.e));
                    if (qVar.g == null) {
                        return contentValues;
                    }
                    contentValues.put("_dt", qVar.g);
                    return contentValues;
                } catch (Throwable th) {
                    if (!w.a(th)) {
                        th.printStackTrace();
                    }
                    return null;
                }
            }
        }
        return null;
    }

    private static q b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            q qVar = new q();
            qVar.a = cursor.getLong(cursor.getColumnIndex("_id"));
            qVar.e = cursor.getLong(cursor.getColumnIndex("_tm"));
            qVar.f = cursor.getString(cursor.getColumnIndex("_tp"));
            qVar.g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return qVar;
        } catch (Throwable th) {
            if (w.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    public final boolean a(l lVar) {
        if (lVar == null) {
            return false;
        }
        try {
            SQLiteDatabase writableDatabase = b.getWritableDatabase();
            if (writableDatabase == null) {
                return false;
            }
            ContentValues b2 = b(lVar);
            if (b2 == null) {
                return false;
            }
            long replace = writableDatabase.replace("t_crd", "_id", b2);
            if (replace < 0) {
                return false;
            }
            w.c("insert %s success!", "t_crd");
            lVar.a = replace;
            return true;
        } catch (Throwable th) {
            if (w.a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0097 A[Catch:{ all -> 0x00c0 }] */
    public final List<l> a(int i, String str, long j, String str2) {
        Cursor cursor;
        Cursor cursor2 = null;
        SQLiteDatabase writableDatabase = b.getWritableDatabase();
        if (writableDatabase != null) {
            try {
                cursor = writableDatabase.query("t_crd", null, "_id = " + i + " and _pc" + " = '" + str + "' and _fl" + " > 0 and " + "_sv = '" + str2 + "' " + (j != 0 ? " and _tm > " + j : ""), null, null, null, "_tm ASC");
                if (cursor == null) {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    return null;
                }
                try {
                    ArrayList arrayList = new ArrayList();
                    while (cursor.moveToNext()) {
                        l c2 = c(cursor);
                        if (c2 != null) {
                            arrayList.add(c2);
                        }
                    }
                    if (cursor == null || cursor.isClosed()) {
                        return arrayList;
                    }
                    cursor.close();
                    return arrayList;
                } catch (Throwable th) {
                    th = th;
                    try {
                        if (!w.a(th)) {
                            th.printStackTrace();
                        }
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        cursor2 = cursor;
                        if (cursor2 != null && !cursor2.isClosed()) {
                            cursor2.close();
                        }
                        throw th;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                cursor2.close();
                throw th;
            }
        }
        return null;
    }

    public final l a(int i, String str) {
        Cursor cursor;
        l lVar;
        Cursor cursor2 = null;
        SQLiteDatabase writableDatabase = b.getWritableDatabase();
        if (writableDatabase == null) {
            return null;
        }
        try {
            cursor = writableDatabase.query("t_crd", null, "_id = " + i + " and _pc" + " = '" + str + "' and _fl" + " > 0", null, null, null, "_tm DESC");
            if (cursor != null) {
                if (cursor != null) {
                    try {
                        if (cursor.moveToFirst()) {
                            lVar = c(cursor);
                            if (cursor != null && !cursor.isClosed()) {
                                cursor.close();
                            }
                            return lVar;
                        }
                    } catch (Throwable th) {
                        th = th;
                        try {
                            if (!w.a(th)) {
                                th.printStackTrace();
                            }
                            if (cursor == null || cursor.isClosed()) {
                                return null;
                            }
                            cursor.close();
                            return null;
                        } catch (Throwable th2) {
                            th = th2;
                            cursor2 = cursor;
                            if (cursor2 != null && !cursor2.isClosed()) {
                                cursor2.close();
                            }
                            throw th;
                        }
                    }
                }
                lVar = null;
                cursor.close();
                return lVar;
            } else if (cursor == null || cursor.isClosed()) {
                return null;
            } else {
                cursor.close();
                return null;
            }
        } catch (Throwable th3) {
            th = th3;
            cursor2.close();
            throw th;
        }
    }

    public final int a(int i, String str, long j) {
        return a("t_crd", "_id = " + i + " and _pc" + " = '" + str + "' " + (j == 0 ? "" : " and _tm < " + j), (String[]) null, (n) null);
    }

    public final int b() {
        StringBuilder sb = new StringBuilder("_fl = 0 or _sv <> '");
        com.tencent.bugly.legu.crashreport.common.info.a.a().getClass();
        return a("t_crd", sb.append("2.1.9'").toString(), (String[]) null, (n) null);
    }

    private static ContentValues b(l lVar) {
        if (lVar == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (lVar.a <= 0 || TextUtils.isEmpty(lVar.b)) {
                return null;
            }
            contentValues.put("_id", Long.valueOf(lVar.a));
            contentValues.put("_pc", lVar.b);
            contentValues.put("_tm", Long.valueOf(lVar.c));
            contentValues.put("_fl", Integer.valueOf(lVar.d));
            contentValues.put("_av", lVar.f);
            contentValues.put("_sv", lVar.e);
            contentValues.put("_uid", Long.valueOf(lVar.g));
            return contentValues;
        } catch (Throwable th) {
            if (w.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    private static l c(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            l lVar = new l();
            lVar.a = cursor.getLong(cursor.getColumnIndex("_id"));
            lVar.b = cursor.getString(cursor.getColumnIndex("_pc"));
            lVar.e = cursor.getString(cursor.getColumnIndex("_sv"));
            lVar.f = cursor.getString(cursor.getColumnIndex("_av"));
            lVar.c = cursor.getLong(cursor.getColumnIndex("_tm"));
            lVar.d = cursor.getInt(cursor.getColumnIndex("_fl"));
            lVar.g = cursor.getLong(cursor.getColumnIndex("_uid"));
            return lVar;
        } catch (Throwable th) {
            if (w.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }
}
