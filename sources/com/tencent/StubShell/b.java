package com.tencent.StubShell;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

public class b {
    private static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String a(InputStream inputStream) {
        String str = "";
        byte[] bArr = new byte[8192];
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            while (true) {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    return a(instance.digest());
                }
                instance.update(bArr, 0, read);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x000e A[SYNTHETIC, Splitter:B:6:0x000e] */
    public static String a(String str) {
        FileInputStream fileInputStream;
        String str2;
        String str3 = "";
        try {
            fileInputStream = new FileInputStream(str);
            try {
                str2 = a((InputStream) fileInputStream);
            } catch (Exception e) {
                e = e;
            }
        } catch (Exception e2) {
            e = e2;
            fileInputStream = null;
            e.printStackTrace();
            str2 = str3;
            if (fileInputStream != null) {
            }
            return str2;
        }
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
        return str2;
    }

    public static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            sb.append(a[(bArr[i] & 240) >>> 4]);
            sb.append(a[bArr[i] & 15]);
        }
        return sb.toString();
    }
}
