package com.tencent.bugly.legu.crashreport;

import android.util.Log;
import com.tencent.bugly.legu.b;
import com.tencent.bugly.legu.proguard.x;

/* compiled from: BUGLY */
public class BuglyLog {
    public static void v(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (b.b) {
            Log.v(str, str2);
        }
        x.a("V", str, str2);
    }

    public static void d(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (b.b) {
            Log.d(str, str2);
        }
        x.a("D", str, str2);
    }

    public static void i(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (b.b) {
            Log.i(str, str2);
        }
        x.a("I", str, str2);
    }

    public static void w(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (b.b) {
            Log.w(str, str2);
        }
        x.a("W", str, str2);
    }

    public static void e(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (b.b) {
            Log.e(str, str2);
        }
        x.a("E", str, str2);
    }

    public static void e(String str, String str2, Throwable th) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (b.b) {
            Log.e(str, str2, th);
        }
        x.a("E", str, th);
    }

    public static void setCache(int i) {
        x.a(i);
    }
}
