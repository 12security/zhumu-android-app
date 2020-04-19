package com.tencent.bugly.legu.proguard;

import android.util.Log;
import com.tencent.bugly.legu.BuglyStrategy.a;
import java.util.Locale;

/* compiled from: BUGLY */
public final class w {
    public static String a = "CrashReport";
    public static boolean b = false;
    private static String c = "CrashReportInfo";

    private static boolean a(int i, String str, Object... objArr) {
        if (!b) {
            return false;
        }
        if (str == null) {
            str = "null";
        } else if (!(objArr == null || objArr.length == 0)) {
            str = String.format(Locale.US, str, objArr);
        }
        switch (i) {
            case a.CRASHTYPE_JAVA_CRASH /*0*/:
                Log.i(a, str);
                return true;
            case a.CRASHTYPE_JAVA_CATCH /*1*/:
                Log.d(a, str);
                return true;
            case a.CRASHTYPE_NATIVE /*2*/:
                Log.w(a, str);
                return true;
            case a.CRASHTYPE_U3D /*3*/:
                Log.e(a, str);
                return true;
            case a.CRASHTYPE_COCOS2DX_JS /*5*/:
                Log.i(c, str);
                return true;
            default:
                return false;
        }
    }

    public static boolean a(String str, Object... objArr) {
        return a(0, str, objArr);
    }

    public static boolean b(String str, Object... objArr) {
        return a(5, str, objArr);
    }

    public static boolean c(String str, Object... objArr) {
        return a(1, str, objArr);
    }

    public static boolean d(String str, Object... objArr) {
        return a(2, str, objArr);
    }

    public static boolean a(Throwable th) {
        if (!b) {
            return false;
        }
        return a(2, a.a(th), new Object[0]);
    }

    public static boolean e(String str, Object... objArr) {
        return a(3, str, objArr);
    }

    public static boolean b(Throwable th) {
        if (!b) {
            return false;
        }
        return a(3, a.a(th), new Object[0]);
    }
}
