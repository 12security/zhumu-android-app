package com.tencent.StubShell;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipUtil {
    public static int exist(String str, String str2) {
        int i = -1;
        try {
            ZipFile zipFile = new ZipFile(new File(str));
            if (zipFile.getEntry(str2) != null) {
                i = 0;
            }
            zipFile.close();
        } catch (Exception e) {
        }
        return i;
    }

    public static int extract(String str, String str2, String str3) {
        try {
            File file = new File(str3);
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ZipFile zipFile = new ZipFile(new File(str));
            ZipEntry entry = zipFile.getEntry(str2);
            if (entry == null) {
            }
            InputStream inputStream = zipFile.getInputStream(entry);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.close();
                    inputStream.close();
                    zipFile.close();
                    return 0;
                }
            }
        } catch (Exception e) {
            return e.getMessage() != null ? 1 : 1;
        }
    }
}
