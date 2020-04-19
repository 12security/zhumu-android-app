package com.tencent.bugly.legu.proguard;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.tencent.bugly.legu.a;
import java.io.File;
import java.util.List;

/* compiled from: BUGLY */
public final class p extends SQLiteOpenHelper {
    private static int a = 11;
    private Context b;
    private List<a> c;

    public p(Context context, List<a> list) {
        StringBuilder sb = new StringBuilder("bugly_db_");
        com.tencent.bugly.legu.crashreport.common.info.a.a(context).getClass();
        super(context, sb.append("legu").toString(), null, a);
        this.b = context;
        this.c = list;
    }

    public final synchronized void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS t_ui").append(" ( _id").append(" INTEGER PRIMARY KEY").append(" , _tm").append(" int").append(" , _ut").append(" int").append(" , _tp").append(" int").append(" , _dt").append(" blob").append(" , _pc").append(" text").append(" ) ");
            w.c("create %s", sb.toString());
            sQLiteDatabase.execSQL(sb.toString());
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS t_lr").append(" ( _id").append(" INTEGER PRIMARY KEY").append(" , _tp").append(" int").append(" , _tm").append(" int").append(" , _pc").append(" text").append(" , _th").append(" text").append(" , _dt").append(" blob").append(" ) ");
            w.c("create %s", sb.toString());
            sQLiteDatabase.execSQL(sb.toString());
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS t_pf").append(" ( _id").append(" integer").append(" , _tp").append(" text").append(" , _tm").append(" int").append(" , _dt").append(" blob").append(",primary key(_id").append(",_tp").append(" )) ");
            w.c("create %s", sb.toString());
            sQLiteDatabase.execSQL(sb.toString());
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS t_crd").append(" ( _id").append(" integer").append(" , _pc").append(" text").append(" , _tm").append(" int").append(" , _fl").append(" int").append(" , _sv").append(" text").append(" , _av").append(" text").append(" , _uid").append(" integer").append(",primary key(_id").append(",_pc").append(",_uid").append(" )) ");
            w.c("create %s", sb.toString());
            sQLiteDatabase.execSQL(sb.toString());
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS t_cr").append(" ( _id").append(" INTEGER PRIMARY KEY").append(" , _tm").append(" int").append(" , _s1").append(" text").append(" , _up").append(" int").append(" , _me").append(" int").append(" , _uc").append(" int").append(" , _dt").append(" blob").append(" ) ");
            w.c("create %s", sb);
            sQLiteDatabase.execSQL(sb.toString());
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS dl_1002").append(" (_id").append(" integer primary key autoincrement, _dUrl").append(" varchar(100), _sFile").append(" varchar(100), _sLen").append(" INTEGER, _tLen").append(" INTEGER, _MD5").append(" varchar(100), _DLTIME").append(" INTEGER)");
            w.c("create %s", sb);
            sQLiteDatabase.execSQL(sb.toString());
            sb.setLength(0);
            sb.append("CREATE TABLE IF NOT EXISTS ge_1002").append(" (_id").append(" integer primary key autoincrement, _time").append(" INTEGER, _datas").append(" blob)");
            w.c("create %s", sb);
            sQLiteDatabase.execSQL(sb.toString());
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS st_1002").append(" ( _id").append(" integer").append(" , _tp").append(" text").append(" , _tm").append(" int").append(" , _dt").append(" blob").append(",primary key(_id").append(",_tp").append(" )) ");
            w.c("create %s", sb.toString());
            sQLiteDatabase.execSQL(sb.toString());
        } catch (Throwable th) {
            if (!w.b(th)) {
                th.printStackTrace();
            }
        }
        if (this.c != null) {
            for (a onDbCreate : this.c) {
                try {
                    onDbCreate.onDbCreate(sQLiteDatabase);
                } catch (Throwable th2) {
                    if (!w.b(th2)) {
                        th2.printStackTrace();
                    }
                }
            }
        }
    }

    private synchronized boolean a(SQLiteDatabase sQLiteDatabase) {
        boolean z = true;
        synchronized (this) {
            try {
                String[] strArr = {"t_crd", "t_lr", "t_ui", "t_pf"};
                int length = strArr.length;
                for (int i = 0; i < length; i++) {
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + strArr[i]);
                }
            } catch (Throwable th) {
                if (!w.b(th)) {
                    th.printStackTrace();
                }
                z = false;
            }
        }
        return z;
    }

    public final synchronized void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        w.d("upgrade %d to %d , drop tables!", Integer.valueOf(i), Integer.valueOf(i2));
        if (this.c != null) {
            for (a onDbUpgrade : this.c) {
                try {
                    onDbUpgrade.onDbUpgrade(sQLiteDatabase, i, i2);
                } catch (Throwable th) {
                    if (!w.b(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
        if (a(sQLiteDatabase)) {
            onCreate(sQLiteDatabase);
        } else {
            w.d("drop fail delete db", new Object[0]);
            File databasePath = this.b.getDatabasePath("bugly_db");
            if (databasePath != null && databasePath.canWrite()) {
                databasePath.delete();
            }
        }
    }

    @TargetApi(11)
    public final synchronized void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (a.d() >= 11) {
            w.d("drowngrade %d to %d drop tables!}", Integer.valueOf(i), Integer.valueOf(i2));
            if (this.c != null) {
                for (a onDbDowngrade : this.c) {
                    try {
                        onDbDowngrade.onDbDowngrade(sQLiteDatabase, i, i2);
                    } catch (Throwable th) {
                        if (!w.b(th)) {
                            th.printStackTrace();
                        }
                    }
                }
            }
            if (a(sQLiteDatabase)) {
                onCreate(sQLiteDatabase);
            } else {
                w.d("drop fail delete db", new Object[0]);
                File databasePath = this.b.getDatabasePath("bugly_db");
                if (databasePath != null && databasePath.canWrite()) {
                    databasePath.delete();
                }
            }
        }
    }

    public final synchronized SQLiteDatabase getReadableDatabase() {
        SQLiteDatabase sQLiteDatabase;
        int i = 0;
        synchronized (this) {
            sQLiteDatabase = null;
            while (sQLiteDatabase == null && i < 5) {
                i++;
                try {
                    sQLiteDatabase = super.getReadableDatabase();
                } catch (Throwable th) {
                    w.d("try db count %d", Integer.valueOf(i));
                    if (i == 5) {
                        w.e("get db fail!", new Object[0]);
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return sQLiteDatabase;
    }

    public final synchronized SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase sQLiteDatabase;
        int i = 0;
        synchronized (this) {
            sQLiteDatabase = null;
            while (sQLiteDatabase == null && i < 5) {
                i++;
                try {
                    sQLiteDatabase = super.getWritableDatabase();
                } catch (Throwable th) {
                    w.d("try db %d", Integer.valueOf(i));
                    if (i == 5) {
                        w.e("get db fail!", new Object[0]);
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (sQLiteDatabase == null) {
                w.d("db error delay error record 1min", new Object[0]);
            }
        }
        return sQLiteDatabase;
    }
}
