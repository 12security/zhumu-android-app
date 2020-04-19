package com.tencent.StubShell;

import android.os.Build;
import com.tencent.bugly.legu.crashreport.BuglyLog;
import java.io.File;
import org.json.JSONObject;

public class SystemInfoException extends Throwable {
    public SystemInfoException(String str) {
        if (str != null) {
            try {
                BuglyLog.i("Legu", str);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public SystemInfoException(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("fingerprint", Build.FINGERPRINT);
            if (new File("/system/lib/libdvm.so").exists()) {
                jSONObject.put("libdvm32", b.a("/system/lib/libdvm.so"));
            }
            if (new File("/system/lib/libart.so").exists()) {
                jSONObject.put("libart32", b.a("/system/lib/libart.so"));
            }
            if (new File("/system/lib64/libdvm.so").exists()) {
                jSONObject.put("libdvm64", b.a("/system/lib64/libdvm.so"));
            }
            if (new File("/system/lib64/libart.so").exists()) {
                jSONObject.put("libart64", b.a("/system/lib64/libart.so"));
            }
            if (new File(str).exists()) {
                jSONObject.put("apk_md5", b.a(str));
            }
            jSONObject.put("apk_version", str2);
            BuglyLog.i("Legu", jSONObject.toString());
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
