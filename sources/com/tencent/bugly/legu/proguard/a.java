package com.tencent.bugly.legu.proguard;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Process;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.tencent.bugly.legu.crashreport.biz.UserInfoBean;
import com.tencent.bugly.legu.crashreport.common.info.AppInfo;
import com.tencent.bugly.legu.crashreport.common.info.PlugInBean;
import com.tencent.bugly.legu.crashreport.common.strategy.StrategyBean;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: BUGLY */
public class a {
    protected HashMap<String, HashMap<String, byte[]>> a = new HashMap<>();
    protected String b;
    h c;
    private HashMap<String, Object> d;

    a() {
        new HashMap();
        this.d = new HashMap<>();
        this.b = "GBK";
        this.c = new h();
    }

    public static String b() {
        try {
            return Build.MODEL;
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    public static aq a(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        aq aqVar = new aq();
        aqVar.a = userInfoBean.e;
        aqVar.e = userInfoBean.j;
        aqVar.d = userInfoBean.c;
        aqVar.c = userInfoBean.d;
        aqVar.g = com.tencent.bugly.legu.crashreport.common.info.a.a().h();
        aqVar.h = userInfoBean.o == 1;
        switch (userInfoBean.b) {
            case com.tencent.bugly.legu.BuglyStrategy.a.CRASHTYPE_JAVA_CATCH /*1*/:
                aqVar.b = 1;
                break;
            case com.tencent.bugly.legu.BuglyStrategy.a.CRASHTYPE_NATIVE /*2*/:
                aqVar.b = 4;
                break;
            case com.tencent.bugly.legu.BuglyStrategy.a.CRASHTYPE_U3D /*3*/:
                aqVar.b = 2;
                break;
            case com.tencent.bugly.legu.BuglyStrategy.a.CRASHTYPE_ANR /*4*/:
                aqVar.b = 3;
                break;
            default:
                if (userInfoBean.b >= 10 && userInfoBean.b < 20) {
                    aqVar.b = (byte) userInfoBean.b;
                    break;
                } else {
                    w.e("unknown uinfo type %d ", Integer.valueOf(userInfoBean.b));
                    return null;
                }
                break;
        }
        aqVar.f = new HashMap();
        if (userInfoBean.p >= 0) {
            aqVar.f.put("C01", userInfoBean.p);
        }
        if (userInfoBean.q >= 0) {
            aqVar.f.put("C02", userInfoBean.q);
        }
        if (userInfoBean.r != null && userInfoBean.r.size() > 0) {
            for (Entry entry : userInfoBean.r.entrySet()) {
                aqVar.f.put("C03_" + ((String) entry.getKey()), entry.getValue());
            }
        }
        if (userInfoBean.s != null && userInfoBean.s.size() > 0) {
            for (Entry entry2 : userInfoBean.s.entrySet()) {
                aqVar.f.put("C04_" + ((String) entry2.getKey()), entry2.getValue());
            }
        }
        aqVar.f.put("A36", (!userInfoBean.l));
        aqVar.f.put("F02", userInfoBean.g);
        aqVar.f.put("F03", userInfoBean.h);
        aqVar.f.put("F04", userInfoBean.j);
        aqVar.f.put("F05", userInfoBean.i);
        aqVar.f.put("F06", userInfoBean.m);
        aqVar.f.put("F10", userInfoBean.k);
        w.c("summary type %d vm:%d", Byte.valueOf(aqVar.b), Integer.valueOf(aqVar.f.size()));
        return aqVar;
    }

    public void a(String str) {
        this.b = str;
    }

    public static String c() {
        try {
            return VERSION.RELEASE;
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    public static int d() {
        try {
            return VERSION.SDK_INT;
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            return -1;
        }
    }

    public static String a(Throwable th) {
        if (th == null) {
            return "";
        }
        try {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            return stringWriter.getBuffer().toString();
        } catch (Throwable th2) {
            if (!w.a(th2)) {
                th2.printStackTrace();
            }
            return "fail";
        }
    }

    public static String a(Context context) {
        String str;
        if (!AppInfo.a(context, "android.permission.READ_PHONE_STATE")) {
            w.d("no READ_PHONE_STATE permission to get IMEI", new Object[0]);
            return "null";
        } else if (context == null) {
            return null;
        } else {
            try {
                String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                if (deviceId == null) {
                    return deviceId;
                }
                try {
                    return deviceId.toLowerCase();
                } catch (Throwable th) {
                }
            } catch (Throwable th2) {
                str = null;
            }
        }
        Log.i(w.a, "failed to get IMEI");
        return str;
    }

    public static String n() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date());
        } catch (Exception e) {
            return new Date().toString();
        }
    }

    public static String a(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayList.size(); i++) {
            String str = (String) arrayList.get(i);
            if (str.equals("java.lang.Integer") || str.equals("int")) {
                str = "int32";
            } else if (str.equals("java.lang.Boolean") || str.equals("boolean")) {
                str = "bool";
            } else if (str.equals("java.lang.Byte") || str.equals("byte")) {
                str = "char";
            } else if (str.equals("java.lang.Double") || str.equals("double")) {
                str = "double";
            } else if (str.equals("java.lang.Float") || str.equals("float")) {
                str = "float";
            } else if (str.equals("java.lang.Long") || str.equals("long")) {
                str = "int64";
            } else if (str.equals("java.lang.Short") || str.equals("short")) {
                str = "short";
            } else if (str.equals("java.lang.Character")) {
                throw new IllegalArgumentException("can not support java.lang.Character");
            } else if (str.equals("java.lang.String")) {
                str = "string";
            } else if (str.equals("java.util.List")) {
                str = "list";
            } else if (str.equals("java.util.Map")) {
                str = "map";
            }
            arrayList.set(i, str);
        }
        Collections.reverse(arrayList);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            String str2 = (String) arrayList.get(i2);
            if (str2.equals("list")) {
                arrayList.set(i2 - 1, "<" + ((String) arrayList.get(i2 - 1)));
                arrayList.set(0, ((String) arrayList.get(0)) + ">");
            } else if (str2.equals("map")) {
                arrayList.set(i2 - 1, "<" + ((String) arrayList.get(i2 - 1)) + ",");
                arrayList.set(0, ((String) arrayList.get(0)) + ">");
            } else if (str2.equals("Array")) {
                arrayList.set(i2 - 1, "<" + ((String) arrayList.get(i2 - 1)));
                arrayList.set(0, ((String) arrayList.get(0)) + ">");
            }
        }
        Collections.reverse(arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer.append((String) it.next());
        }
        return stringBuffer.toString();
    }

    public <T> void a(String str, T t) {
        if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        } else if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        } else if (t instanceof Set) {
            throw new IllegalArgumentException("can not support Set");
        } else {
            i iVar = new i();
            iVar.a(this.b);
            iVar.a((Object) t, 0);
            byte[] a2 = k.a(iVar.a());
            HashMap hashMap = new HashMap(1);
            ArrayList arrayList = new ArrayList(1);
            a(arrayList, (Object) t);
            hashMap.put(a(arrayList), a2);
            this.d.remove(str);
            this.a.put(str, hashMap);
        }
    }

    public static String a(Date date) {
        if (date == null) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(date);
        } catch (Exception e) {
            return new Date().toString();
        }
    }

    public static String b(Context context) {
        String str;
        if (!AppInfo.a(context, "android.permission.READ_PHONE_STATE")) {
            w.d("no READ_PHONE_STATE permission to get IMSI", new Object[0]);
            return "null";
        } else if (context == null) {
            return null;
        } else {
            try {
                String subscriberId = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
                if (subscriberId == null) {
                    return subscriberId;
                }
                try {
                    return subscriberId.toLowerCase();
                } catch (Throwable th) {
                }
            } catch (Throwable th2) {
                str = null;
            }
        }
        Log.i(w.a, "failed to get IMSI");
        return str;
    }

    public static String c(Context context) {
        String str = "fail";
        if (context == null) {
            return str;
        }
        try {
            String string = Secure.getString(context.getContentResolver(), "android_id");
            if (string == null) {
                return "null";
            }
            try {
                return string.toLowerCase();
            } catch (Throwable th) {
                Throwable th2 = th;
                str = string;
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
        }
        if (w.a(th)) {
            return str;
        }
        Log.i(w.a, "failed to get Android ID");
        return str;
    }

    public static ar a(List<UserInfoBean> list, int i) {
        if (list == null || list.size() == 0) {
            return null;
        }
        com.tencent.bugly.legu.crashreport.common.info.a a2 = com.tencent.bugly.legu.crashreport.common.info.a.a();
        ar arVar = new ar();
        arVar.b = a2.d;
        arVar.c = a2.g();
        ArrayList<aq> arrayList = new ArrayList<>();
        for (UserInfoBean a3 : list) {
            aq a4 = a(a3);
            if (a4 != null) {
                arrayList.add(a4);
            }
        }
        arVar.d = arrayList;
        arVar.e = new HashMap();
        arVar.e.put("A7", a2.e);
        arVar.e.put("A6", a2.r());
        arVar.e.put("A5", a2.q());
        arVar.e.put("A2", a2.o());
        arVar.e.put("A1", a2.o());
        arVar.e.put("A24", a2.g);
        arVar.e.put("A17", a2.p());
        arVar.e.put("A15", a2.t());
        arVar.e.put("A13", a2.u());
        arVar.e.put("F08", a2.u);
        arVar.e.put("F09", a2.v);
        Map A = a2.A();
        if (A != null && A.size() > 0) {
            for (Entry entry : A.entrySet()) {
                arVar.e.put("C04_" + ((String) entry.getKey()), entry.getValue());
            }
        }
        switch (i) {
            case com.tencent.bugly.legu.BuglyStrategy.a.CRASHTYPE_JAVA_CATCH /*1*/:
                arVar.a = 1;
                break;
            case com.tencent.bugly.legu.BuglyStrategy.a.CRASHTYPE_NATIVE /*2*/:
                arVar.a = 2;
                break;
            default:
                w.e("unknown up type %d ", Integer.valueOf(i));
                return null;
        }
        return arVar;
    }

    public static byte[] a(byte[] bArr, int i, String str) {
        ag agVar;
        if (bArr == null || i == -1) {
            return bArr;
        }
        if (i == 1) {
            try {
                agVar = new af();
            } catch (Throwable th) {
                if (!w.a(th)) {
                    th.printStackTrace();
                }
                w.d("encrytype %d %s", Integer.valueOf(i), str);
                return null;
            }
        } else {
            agVar = i == 3 ? new ae() : null;
        }
        if (agVar == null) {
            return null;
        }
        agVar.a(str);
        return agVar.a(bArr);
    }

    public static String d(Context context) {
        String str = "fail";
        if (context == null) {
            return str;
        }
        try {
            String macAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            if (macAddress == null) {
                return "null";
            }
            return macAddress.toLowerCase();
        } catch (Throwable th) {
            Throwable th2 = th;
            String str2 = str;
            Throwable th3 = th2;
            if (w.a(th3)) {
                return str2;
            }
            th3.printStackTrace();
            return str2;
        }
    }

    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v1, types: [java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r2v1, types: [java.util.zip.ZipOutputStream] */
    /* JADX WARNING: type inference failed for: r3v2, types: [java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r2v3, types: [java.util.zip.ZipOutputStream] */
    /* JADX WARNING: type inference failed for: r3v3, types: [java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: type inference failed for: r2v11 */
    /* JADX WARNING: type inference failed for: r3v4 */
    /* JADX WARNING: type inference failed for: r2v12 */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: type inference failed for: r2v15, types: [java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r3v6 */
    /* JADX WARNING: type inference failed for: r2v16 */
    /* JADX WARNING: type inference failed for: r3v7 */
    /* JADX WARNING: type inference failed for: r2v17 */
    /* JADX WARNING: type inference failed for: r3v8 */
    /* JADX WARNING: type inference failed for: r3v9 */
    /* JADX WARNING: type inference failed for: r2v18 */
    /* JADX WARNING: type inference failed for: r3v10 */
    /* JADX WARNING: type inference failed for: r3v11 */
    /* JADX WARNING: type inference failed for: r3v12 */
    /* JADX WARNING: type inference failed for: r2v19 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004b A[Catch:{ Throwable -> 0x0056 }, LOOP:0: B:18:0x004b->B:20:0x0051, LOOP_START] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005d A[Catch:{ all -> 0x007d }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0062 A[SYNTHETIC, Splitter:B:27:0x0062] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0067 A[SYNTHETIC, Splitter:B:30:0x0067] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0078 A[Catch:{ Throwable -> 0x0056 }, LOOP:1: B:34:0x0072->B:37:0x0078, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0080 A[SYNTHETIC, Splitter:B:40:0x0080] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0085 A[SYNTHETIC, Splitter:B:43:0x0085] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0090 A[EDGE_INSN: B:47:0x0090->B:48:? ?: BREAK  
EDGE_INSN: B:47:0x0090->B:48:? ?: BREAK  
EDGE_INSN: B:47:0x0090->B:48:? ?: BREAK  , SYNTHETIC, Splitter:B:47:0x0090] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0090 A[EDGE_INSN: B:47:0x0090->B:48:? ?: BREAK  
EDGE_INSN: B:47:0x0090->B:48:? ?: BREAK  , SYNTHETIC, Splitter:B:47:0x0090] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x009f A[SYNTHETIC, Splitter:B:50:0x009f] */
    /* JADX WARNING: Unknown variable types count: 8 */
    public static byte[] a(File file, String str) {
        ? r3;
        ? r32;
        ? r2;
        byte[] bArr;
        ? r33;
        ? r22;
        ? r34;
        ByteArrayInputStream byteArrayInputStream;
        ZipOutputStream zipOutputStream;
        byte[] bArr2;
        int read;
        ? r0 = 0;
        w.c("rqdp{  ZF start}", new Object[0]);
        String str2 = "buglyCacheLog.txt";
        if (file != null) {
            try {
                if (file.exists() && file.canRead()) {
                    ? fileInputStream = new FileInputStream(file);
                    try {
                        str2 = file.getName();
                        r3 = fileInputStream;
                        byteArrayInputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
                        try {
                            zipOutputStream.setMethod(8);
                            zipOutputStream.putNextEntry(new ZipEntry(str2));
                            bArr2 = new byte[1024];
                            if (r34 != 0) {
                                while (true) {
                                    int read2 = r34.read(bArr2);
                                    if (read2 > 0) {
                                        zipOutputStream.write(bArr2, 0, read2);
                                    }
                                }
                                while (true) {
                                    read = byteArrayInputStream.read(bArr2);
                                    if (read <= 0) {
                                        break;
                                    }
                                    zipOutputStream.write(bArr2, 0, read);
                                }
                                zipOutputStream.closeEntry();
                                zipOutputStream.flush();
                                zipOutputStream.finish();
                                byte[] byteArray = byteArrayOutputStream.toByteArray();
                                if (r34 != 0) {
                                    try {
                                        r34.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                zipOutputStream.close();
                                w.c("rqdp{  ZF end}", new Object[0]);
                                bArr = byteArray;
                                return bArr;
                            }
                            while (true) {
                                read = byteArrayInputStream.read(bArr2);
                                if (read <= 0) {
                                }
                                zipOutputStream.write(bArr2, 0, read);
                            }
                            zipOutputStream.closeEntry();
                            zipOutputStream.flush();
                            zipOutputStream.finish();
                            byte[] byteArray2 = byteArrayOutputStream.toByteArray();
                            if (r34 != 0) {
                            }
                            zipOutputStream.close();
                            w.c("rqdp{  ZF end}", new Object[0]);
                            bArr = byteArray2;
                        } catch (Throwable th) {
                            th = th;
                            r33 = r34;
                            r22 = zipOutputStream;
                        }
                    } catch (Throwable th2) {
                        r32 = fileInputStream;
                        r2 = r0;
                        th = th2;
                        if (r32 != 0) {
                        }
                        if (r2 != 0) {
                        }
                        w.c("rqdp{  ZF end}", new Object[0]);
                        throw th;
                    }
                    return bArr;
                }
            } catch (Throwable th3) {
                r2 = r0;
                r32 = r0;
                th = th3;
                if (r32 != 0) {
                    try {
                        r32.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                if (r2 != 0) {
                    try {
                        r2.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                w.c("rqdp{  ZF end}", new Object[0]);
                throw th;
            }
        }
        r3 = r0;
        try {
            byteArrayInputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            zipOutputStream = new ZipOutputStream(byteArrayOutputStream2);
            zipOutputStream.setMethod(8);
            zipOutputStream.putNextEntry(new ZipEntry(str2));
            bArr2 = new byte[1024];
            if (r34 != 0) {
            }
            while (true) {
                read = byteArrayInputStream.read(bArr2);
                if (read <= 0) {
                }
                zipOutputStream.write(bArr2, 0, read);
            }
            zipOutputStream.closeEntry();
            zipOutputStream.flush();
            zipOutputStream.finish();
            byte[] byteArray22 = byteArrayOutputStream2.toByteArray();
            if (r34 != 0) {
            }
            try {
                zipOutputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            w.c("rqdp{  ZF end}", new Object[0]);
            bArr = byteArray22;
        } catch (Throwable th4) {
            r2 = r0;
            th = th4;
            r32 = r34;
            if (r32 != 0) {
            }
            if (r2 != 0) {
            }
            w.c("rqdp{  ZF end}", new Object[0]);
            throw th;
        }
        return bArr;
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x006e A[Catch:{ all -> 0x00ba }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0073 A[SYNTHETIC, Splitter:B:39:0x0073] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0078 A[SYNTHETIC, Splitter:B:42:0x0078] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0098 A[SYNTHETIC, Splitter:B:56:0x0098] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x009d A[SYNTHETIC, Splitter:B:59:0x009d] */
    private static String p() {
        FileReader fileReader;
        BufferedReader bufferedReader;
        String str;
        try {
            fileReader = new FileReader("/system/build.prop");
            try {
                bufferedReader = new BufferedReader(fileReader, 2048);
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            str = null;
                            break;
                        }
                        String[] split = readLine.split("=", 2);
                        if (split.length == 2) {
                            if (split[0].equals("ro.product.cpu.abilist")) {
                                str = split[1];
                                break;
                            } else if (split[0].equals("ro.product.cpu.abi")) {
                                str = split[1];
                                break;
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        try {
                            if (!w.a(th)) {
                            }
                            if (bufferedReader != null) {
                            }
                            if (fileReader != null) {
                            }
                            return null;
                        } catch (Throwable th2) {
                            th = th2;
                            if (bufferedReader != null) {
                            }
                            if (fileReader != null) {
                            }
                            throw th;
                        }
                    }
                }
                if (str != null) {
                    str = str.split(",")[0];
                }
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    if (!w.a(e)) {
                        e.printStackTrace();
                    }
                }
                try {
                    fileReader.close();
                    return str;
                } catch (IOException e2) {
                    if (w.a(e2)) {
                        return str;
                    }
                    e2.printStackTrace();
                    return str;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
                if (bufferedReader != null) {
                }
                if (fileReader != null) {
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
            fileReader = null;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e3) {
                    if (!w.a(e3)) {
                        e3.printStackTrace();
                    }
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e4) {
                    if (!w.a(e4)) {
                        e4.printStackTrace();
                    }
                }
            }
            throw th;
        }
    }

    public static <T extends j> T a(byte[] bArr, Class<T> cls) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        try {
            T t = (j) cls.newInstance();
            h hVar = new h(bArr);
            hVar.a("utf-8");
            t.a(hVar);
            return t;
        } catch (Throwable th) {
            if (!w.b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static am a(Context context, int i, byte[] bArr) {
        com.tencent.bugly.legu.crashreport.common.info.a a2 = com.tencent.bugly.legu.crashreport.common.info.a.a();
        StrategyBean c2 = com.tencent.bugly.legu.crashreport.common.strategy.a.a().c();
        if (a2 == null || c2 == null) {
            w.e("illigle access to create req pkg!", new Object[0]);
            return null;
        }
        try {
            am amVar = new am();
            synchronized (a2) {
                amVar.a = 1;
                amVar.b = a2.e();
                amVar.c = a2.c;
                amVar.d = a2.i;
                amVar.e = a2.j;
                a2.getClass();
                amVar.f = "2.1.9";
                amVar.g = i;
                if (bArr == null) {
                    bArr = "".getBytes();
                }
                amVar.h = bArr;
                amVar.i = a2.f;
                amVar.j = a2.g;
                amVar.k = new HashMap();
                amVar.l = a2.d();
                amVar.m = c2.l;
                amVar.o = a2.g();
                amVar.p = e(context);
                amVar.q = System.currentTimeMillis();
                amVar.r = a2.j();
                amVar.s = a2.i();
                amVar.t = a2.l();
                amVar.u = a2.k();
                amVar.v = a2.m();
                amVar.w = amVar.p;
                a2.getClass();
                amVar.n = "com.tencent.bugly";
            }
            amVar.k.put("F11", a2.y);
            amVar.k.put("F12", a2.x);
            return amVar;
        } catch (Throwable th) {
            if (!w.b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static byte[] a(byte[] bArr, int i) {
        if (bArr == null) {
            return bArr;
        }
        w.c("rqdp{  zp:} %s rqdp{  len:} %s", Integer.valueOf(2), Integer.valueOf(bArr.length));
        try {
            ab a2 = aa.a(2);
            if (a2 == null) {
                return null;
            }
            return a2.a(bArr);
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static String e() {
        try {
            String p = p();
            if (p == null) {
                p = System.getProperty("os.arch");
            }
            return p;
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    public static long f() {
        long j = -1;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (w.a(th)) {
                return j;
            }
            th.printStackTrace();
            return j;
        }
    }

    public static byte[] b(byte[] bArr, int i) {
        if (bArr == null || i == -1) {
            return bArr;
        }
        w.c("rqdp{  unzp:} %s rqdp{  len:} %s", Integer.valueOf(i), Integer.valueOf(bArr.length));
        try {
            ab a2 = aa.a(i);
            if (a2 == null) {
                return null;
            }
            return a2.b(bArr);
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static long g() {
        long j = -1;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (w.a(th)) {
                return j;
            }
            th.printStackTrace();
            return j;
        }
    }

    public static byte[] a(am amVar) {
        try {
            d dVar = new d();
            dVar.p();
            dVar.a("utf-8");
            dVar.b(1);
            dVar.b("RqdServer");
            dVar.c("sync");
            dVar.a("detail", amVar);
            return dVar.a();
        } catch (Throwable th) {
            if (!w.b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private void a(ArrayList<String> arrayList, Object obj) {
        if (obj.getClass().isArray()) {
            if (!obj.getClass().getComponentType().toString().equals("byte")) {
                throw new IllegalArgumentException("only byte[] is supported");
            } else if (Array.getLength(obj) > 0) {
                arrayList.add("java.util.List");
                a(arrayList, Array.get(obj, 0));
            } else {
                arrayList.add("Array");
                arrayList.add("?");
            }
        } else if (obj instanceof Array) {
            throw new IllegalArgumentException("can not support Array, please use List");
        } else if (obj instanceof List) {
            arrayList.add("java.util.List");
            List list = (List) obj;
            if (list.size() > 0) {
                a(arrayList, list.get(0));
            } else {
                arrayList.add("?");
            }
        } else if (obj instanceof Map) {
            arrayList.add("java.util.Map");
            Map map = (Map) obj;
            if (map.size() > 0) {
                Object next = map.keySet().iterator().next();
                Object obj2 = map.get(next);
                arrayList.add(next.getClass().getName());
                a(arrayList, obj2);
                return;
            }
            arrayList.add("?");
            arrayList.add("?");
        } else {
            arrayList.add(obj.getClass().getName());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0059 A[Catch:{ all -> 0x00a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005e A[SYNTHETIC, Splitter:B:27:0x005e] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0063 A[SYNTHETIC, Splitter:B:30:0x0063] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0084 A[SYNTHETIC, Splitter:B:44:0x0084] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0089 A[SYNTHETIC, Splitter:B:47:0x0089] */
    public static long h() {
        FileReader fileReader;
        BufferedReader bufferedReader;
        FileReader fileReader2;
        BufferedReader bufferedReader2 = null;
        try {
            fileReader = new FileReader("/proc/meminfo");
            try {
                bufferedReader = new BufferedReader(fileReader, 2048);
                try {
                    long parseLong = Long.parseLong(bufferedReader.readLine().split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) << 10;
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        if (!w.a(e)) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        fileReader.close();
                        return parseLong;
                    } catch (IOException e2) {
                        if (w.a(e2)) {
                            return parseLong;
                        }
                        e2.printStackTrace();
                        return parseLong;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (bufferedReader != null) {
                    }
                    if (fileReader != null) {
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
                if (bufferedReader != null) {
                }
                if (fileReader != null) {
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
            fileReader = null;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e3) {
                    if (!w.a(e3)) {
                        e3.printStackTrace();
                    }
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e4) {
                    if (!w.a(e4)) {
                        e4.printStackTrace();
                    }
                }
            }
            throw th;
        }
    }

    public static an a(byte[] bArr, boolean z) {
        an anVar;
        if (bArr != null) {
            try {
                d dVar = new d();
                dVar.p();
                dVar.a("utf-8");
                dVar.a(bArr);
                Object b2 = dVar.b("detail", new an());
                if (an.class.isInstance(b2)) {
                    anVar = (an) an.class.cast(b2);
                } else {
                    anVar = null;
                }
                if (z || anVar == null || anVar.c == null || anVar.c.length <= 0) {
                    return anVar;
                }
                w.c("resp buf %d", Integer.valueOf(anVar.c.length));
                anVar.c = a(anVar.c, 2, 1, StrategyBean.a);
                if (anVar.c != null) {
                    return anVar;
                }
                w.e("resp sbuffer error!", new Object[0]);
                return null;
            } catch (Throwable th) {
                if (!w.b(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    public static byte[] a(byte[] bArr, int i, int i2, String str) {
        try {
            return b(a(bArr, 1, str), 2);
        } catch (Exception e) {
            if (!w.a(e)) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static long o() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            return simpleDateFormat.parse(simpleDateFormat.format(new Date())).getTime();
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            return -1;
        }
    }

    public byte[] a() {
        i iVar = new i(0);
        iVar.a(this.b);
        iVar.a((Map<K, V>) this.a, 0);
        return k.a(iVar.a());
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x00a4 A[Catch:{ all -> 0x00f3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a9 A[SYNTHETIC, Splitter:B:27:0x00a9] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00ae A[SYNTHETIC, Splitter:B:30:0x00ae] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00cf A[SYNTHETIC, Splitter:B:44:0x00cf] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00d4 A[SYNTHETIC, Splitter:B:47:0x00d4] */
    public static long i() {
        FileReader fileReader;
        BufferedReader bufferedReader;
        FileReader fileReader2;
        BufferedReader bufferedReader2 = null;
        try {
            fileReader = new FileReader("/proc/meminfo");
            try {
                bufferedReader = new BufferedReader(fileReader, 2048);
            } catch (Throwable th) {
                th = th;
                bufferedReader = null;
                if (bufferedReader != null) {
                }
                if (fileReader != null) {
                }
                throw th;
            }
            try {
                bufferedReader.readLine();
                long parseLong = (Long.parseLong(bufferedReader.readLine().split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) << 10) + (Long.parseLong(bufferedReader.readLine().split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) << 10) + (Long.parseLong(bufferedReader.readLine().split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) << 10);
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    if (!w.a(e)) {
                        e.printStackTrace();
                    }
                }
                try {
                    fileReader.close();
                    return parseLong;
                } catch (IOException e2) {
                    if (w.a(e2)) {
                        return parseLong;
                    }
                    e2.printStackTrace();
                    return parseLong;
                }
            } catch (Throwable th2) {
                th = th2;
                if (bufferedReader != null) {
                }
                if (fileReader != null) {
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
            fileReader = null;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e3) {
                    if (!w.a(e3)) {
                        e3.printStackTrace();
                    }
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e4) {
                    if (!w.a(e4)) {
                        e4.printStackTrace();
                    }
                }
            }
            throw th;
        }
    }

    public void a(byte[] bArr) {
        this.c.a(bArr);
        this.c.a(this.b);
        HashMap hashMap = new HashMap(1);
        HashMap hashMap2 = new HashMap(1);
        hashMap2.put("", new byte[0]);
        hashMap.put("", hashMap2);
        this.a = this.c.a((Map<K, V>) hashMap, 0, false);
    }

    public static byte[] a(j jVar) {
        try {
            i iVar = new i();
            iVar.a("utf-8");
            jVar.a(iVar);
            return iVar.b();
        } catch (Throwable th) {
            if (!w.b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static String b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "NULL";
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(bArr);
            byte[] digest = instance.digest();
            if (digest == null) {
                return "";
            }
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : digest) {
                String hexString = Integer.toHexString(b2 & 255);
                if (hexString.length() == 1) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(hexString);
            }
            return stringBuffer.toString().toUpperCase();
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static long j() {
        if (!(Environment.getExternalStorageState().equals("mounted"))) {
            return 0;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount());
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            return -2;
        }
    }

    public static long k() {
        if (!(Environment.getExternalStorageState().equals("mounted"))) {
            return 0;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks());
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            return -2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0092 A[Catch:{ all -> 0x0101 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0097 A[SYNTHETIC, Splitter:B:41:0x0097] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x009c A[SYNTHETIC, Splitter:B:44:0x009c] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00e2 A[SYNTHETIC, Splitter:B:69:0x00e2] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00e7 A[SYNTHETIC, Splitter:B:72:0x00e7] */
    public static boolean a(File file, File file2, int i) {
        ZipOutputStream zipOutputStream;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        w.c("rqdp{  ZF start}", new Object[0]);
        if (file == null || file2 == null || file.equals(file2)) {
            w.d("rqdp{  err ZF 1R!}", new Object[0]);
            return false;
        } else if (!file.exists() || !file.canRead()) {
            w.d("rqdp{  !sFile.exists() || !sFile.canRead(),pls check ,return!}", new Object[0]);
            return false;
        } else {
            try {
                if (file2.getParentFile() != null && !file2.getParentFile().exists()) {
                    file2.getParentFile().mkdirs();
                }
                if (!file2.exists()) {
                    file2.createNewFile();
                }
            } catch (Throwable th) {
                if (!w.a(th)) {
                    th.printStackTrace();
                }
            }
            if (!file2.exists() || !file2.canRead()) {
                return false;
            }
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    zipOutputStream = new ZipOutputStream(new FileOutputStream(file2));
                    try {
                        zipOutputStream.setMethod(8);
                        zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                        byte[] bArr = new byte[5000];
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            zipOutputStream.write(bArr, 0, read);
                        }
                        zipOutputStream.flush();
                        zipOutputStream.closeEntry();
                        try {
                            fileInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            zipOutputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        w.c("rqdp{  ZF end}", new Object[0]);
                        return true;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileInputStream != null) {
                        }
                        if (zipOutputStream != null) {
                        }
                        w.c("rqdp{  ZF end}", new Object[0]);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    zipOutputStream = null;
                    if (fileInputStream != null) {
                    }
                    if (zipOutputStream != null) {
                    }
                    w.c("rqdp{  ZF end}", new Object[0]);
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                zipOutputStream = null;
                fileInputStream = null;
                if (fileInputStream != null) {
                }
                if (zipOutputStream != null) {
                }
                w.c("rqdp{  ZF end}", new Object[0]);
                throw th;
            }
        }
    }

    public static String l() {
        String str = "fail";
        try {
            return Locale.getDefault().getCountry();
        } catch (Throwable th) {
            if (w.a(th)) {
                return str;
            }
            th.printStackTrace();
            return str;
        }
    }

    public static String m() {
        String str = "fail";
        try {
            return Build.BRAND;
        } catch (Throwable th) {
            if (w.a(th)) {
                return str;
            }
            th.printStackTrace();
            return str;
        }
    }

    public static String e(Context context) {
        String str;
        String str2 = "unknown";
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            if (activeNetworkInfo.getType() == 1) {
                return "WIFI";
            }
            if (activeNetworkInfo.getType() == 0) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    int networkType = telephonyManager.getNetworkType();
                    switch (networkType) {
                        case com.tencent.bugly.legu.BuglyStrategy.a.CRASHTYPE_JAVA_CATCH /*1*/:
                            return "GPRS";
                        case com.tencent.bugly.legu.BuglyStrategy.a.CRASHTYPE_NATIVE /*2*/:
                            return "EDGE";
                        case com.tencent.bugly.legu.BuglyStrategy.a.CRASHTYPE_U3D /*3*/:
                            return "UMTS";
                        case com.tencent.bugly.legu.BuglyStrategy.a.CRASHTYPE_ANR /*4*/:
                            return "CDMA";
                        case com.tencent.bugly.legu.BuglyStrategy.a.CRASHTYPE_COCOS2DX_JS /*5*/:
                            return "EVDO_0";
                        case com.tencent.bugly.legu.BuglyStrategy.a.CRASHTYPE_COCOS2DX_LUA /*6*/:
                            return "EVDO_A";
                        case 7:
                            return "1xRTT";
                        case 8:
                            return "HSDPA";
                        case 9:
                            return "HSUPA";
                        case 10:
                            return "HSPA";
                        case 11:
                            return "iDen";
                        case 12:
                            return "EVDO_B";
                        case 13:
                            return "LTE";
                        case 14:
                            return "eHRPD";
                        case 15:
                            return "HSPA+";
                        default:
                            str = "MOBILE(" + networkType + ")";
                            break;
                    }
                    return str;
                }
            }
            str = str2;
            return str;
        } catch (Exception e) {
            if (w.a(e)) {
                return str2;
            }
            e.printStackTrace();
            return str2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0048 A[Catch:{ all -> 0x00a5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x004d A[SYNTHETIC, Splitter:B:16:0x004d] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0052 A[SYNTHETIC, Splitter:B:19:0x0052] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0090 A[SYNTHETIC, Splitter:B:44:0x0090] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0095 A[SYNTHETIC, Splitter:B:47:0x0095] */
    public static ArrayList<String> a(Context context, String[] strArr) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        BufferedReader bufferedReader3 = null;
        ArrayList<String> arrayList = new ArrayList<>();
        if (com.tencent.bugly.legu.crashreport.common.info.a.a(context).D()) {
            ArrayList<String> arrayList2 = new ArrayList<>();
            arrayList2.add(new String("unknown(low memory)"));
            return arrayList2;
        }
        try {
            Process exec = Runtime.getRuntime().exec(strArr);
            bufferedReader2 = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            while (true) {
                try {
                    String readLine = bufferedReader2.readLine();
                    if (readLine == null) {
                        break;
                    }
                    arrayList.add(readLine);
                } catch (Throwable th) {
                    th = th;
                    if (bufferedReader2 != null) {
                    }
                    if (bufferedReader3 != null) {
                    }
                    throw th;
                }
            }
            bufferedReader = new BufferedReader(new InputStreamReader(exec.getErrorStream()));
            while (true) {
                try {
                    String readLine2 = bufferedReader.readLine();
                    if (readLine2 != null) {
                        arrayList.add(readLine2);
                    } else {
                        try {
                            break;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (!w.a(th)) {
                        th.printStackTrace();
                    }
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return null;
                }
            }
            bufferedReader2.close();
            try {
                bufferedReader.close();
                return arrayList;
            } catch (IOException e4) {
                e4.printStackTrace();
                return arrayList;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader2 = null;
            if (bufferedReader2 != null) {
            }
            if (bufferedReader3 != null) {
            }
            throw th;
        }
    }

    public static String a(Context context, String str) {
        if (str == null || str.trim().equals("")) {
            return "";
        }
        ArrayList a2 = a(context, new String[]{"/system/bin/sh", "-c", "getprop " + str});
        if (a2 == null || a2.size() <= 0) {
            return "fail";
        }
        return (String) a2.get(0);
    }

    public static boolean f(Context context) {
        boolean z;
        Boolean bool;
        boolean z2 = Build.TAGS != null && Build.TAGS.contains("test-keys");
        try {
            z = new File("/system/app/Superuser.apk").exists();
        } catch (Throwable th) {
            z = false;
        }
        Boolean bool2 = null;
        ArrayList a2 = a(context, new String[]{"/system/bin/sh", "-c", "type su"});
        if (a2 != null && a2.size() > 0) {
            Iterator it = a2.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                w.c(str, new Object[0]);
                if (str.contains("not found")) {
                    bool = Boolean.valueOf(false);
                } else {
                    bool = bool2;
                }
                bool2 = bool;
            }
            if (bool2 == null) {
                bool2 = Boolean.valueOf(true);
            }
        }
        Boolean valueOf = Boolean.valueOf(bool2 == null ? false : bool2.booleanValue());
        if (z2 || z || valueOf.booleanValue()) {
            return true;
        }
        return false;
    }

    public static byte[] a(long j) {
        try {
            return (j).getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static long c(byte[] bArr) {
        long j = -1;
        if (bArr == null) {
            return j;
        }
        try {
            return Long.parseLong(new String(bArr, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return j;
        }
    }

    public static Object a(String str, String str2, Object obj, Class<?>[] clsArr, Object[] objArr) {
        boolean z = false;
        try {
            Method declaredMethod = Class.forName(str).getDeclaredMethod(str2, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(null, objArr);
        } catch (Exception e) {
            return z;
        }
    }

    public static void a(Parcel parcel, Map<String, PlugInBean> map) {
        if (map == null || map == null || map.size() <= 0) {
            parcel.writeBundle(null);
            return;
        }
        int size = map.size();
        ArrayList arrayList = new ArrayList(size);
        ArrayList arrayList2 = new ArrayList(size);
        for (Entry entry : map.entrySet()) {
            arrayList.add(entry.getKey());
            arrayList2.add(entry.getValue());
        }
        Bundle bundle = new Bundle();
        bundle.putInt("pluginNum", arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            bundle.putString("pluginKey" + i, (String) arrayList.get(i));
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            bundle.putString("pluginVal" + i2 + "plugInId", ((PlugInBean) arrayList2.get(i2)).a);
            bundle.putString("pluginVal" + i2 + "plugInUUID", ((PlugInBean) arrayList2.get(i2)).c);
            bundle.putString("pluginVal" + i2 + "plugInVersion", ((PlugInBean) arrayList2.get(i2)).b);
        }
        parcel.writeBundle(bundle);
    }

    public static Map<String, PlugInBean> a(Parcel parcel) {
        HashMap hashMap;
        Bundle readBundle = parcel.readBundle();
        if (readBundle == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int intValue = ((Integer) readBundle.get("pluginNum")).intValue();
        for (int i = 0; i < intValue; i++) {
            arrayList.add(readBundle.getString("pluginKey" + i));
        }
        for (int i2 = 0; i2 < intValue; i2++) {
            arrayList2.add(new PlugInBean(readBundle.getString("pluginVal" + i2 + "plugInId"), readBundle.getString("pluginVal" + i2 + "plugInVersion"), readBundle.getString("pluginVal" + i2 + "plugInUUID")));
        }
        if (arrayList.size() == arrayList2.size()) {
            HashMap hashMap2 = new HashMap(arrayList.size());
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                hashMap2.put(arrayList.get(i3), PlugInBean.class.cast(arrayList2.get(i3)));
            }
            hashMap = hashMap2;
        } else {
            w.e("map plugin parcel error!", new Object[0]);
            hashMap = null;
        }
        return hashMap;
    }

    public static void b(Parcel parcel, Map<String, String> map) {
        if (map == null || map == null || map.size() <= 0) {
            parcel.writeBundle(null);
            return;
        }
        int size = map.size();
        ArrayList arrayList = new ArrayList(size);
        ArrayList arrayList2 = new ArrayList(size);
        for (Entry entry : map.entrySet()) {
            arrayList.add(entry.getKey());
            arrayList2.add(entry.getValue());
        }
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("keys", arrayList);
        bundle.putStringArrayList("values", arrayList2);
        parcel.writeBundle(bundle);
    }

    public static Map<String, String> b(Parcel parcel) {
        HashMap hashMap;
        Bundle readBundle = parcel.readBundle();
        if (readBundle == null) {
            return null;
        }
        ArrayList stringArrayList = readBundle.getStringArrayList("keys");
        ArrayList stringArrayList2 = readBundle.getStringArrayList("values");
        if (stringArrayList == null || stringArrayList2 == null || stringArrayList.size() != stringArrayList2.size()) {
            w.e("map parcel error!", new Object[0]);
            hashMap = null;
        } else {
            HashMap hashMap2 = new HashMap(stringArrayList.size());
            for (int i = 0; i < stringArrayList.size(); i++) {
                hashMap2.put(stringArrayList.get(i), stringArrayList2.get(i));
            }
            hashMap = hashMap2;
        }
        return hashMap;
    }

    public static <T> T a(byte[] bArr, Creator<T> creator) {
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        try {
            T createFromParcel = creator.createFromParcel(obtain);
            if (obtain == null) {
                return createFromParcel;
            }
            obtain.recycle();
            return createFromParcel;
        } catch (Throwable th) {
            if (obtain != null) {
                obtain.recycle();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x0108 A[SYNTHETIC, Splitter:B:55:0x0108] */
    public static String a(Context context, int i, String str) {
        Process process;
        StringBuilder sb;
        String sb2;
        if (!AppInfo.a(context, "android.permission.READ_LOGS")) {
            w.d("no read_log permission!", new Object[0]);
            return null;
        }
        String[] strArr = str == null ? new String[]{"logcat", "-d", "-v", "threadtime"} : new String[]{"logcat", "-d", "-v", "threadtime", "-s", str};
        process = null;
        sb = new StringBuilder();
        try {
            Process exec = Runtime.getRuntime().exec(strArr);
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb.append(readLine).append("\n");
                    if (i > 0 && sb.length() > i) {
                        sb.delete(0, sb.length() - i);
                    }
                }
                String sb3 = sb.toString();
                if (exec == null) {
                    return sb3;
                }
                try {
                    exec.getOutputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    exec.getInputStream().close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                try {
                    exec.getErrorStream().close();
                    return sb3;
                } catch (IOException e3) {
                    e3.printStackTrace();
                    return sb3;
                }
            } catch (Throwable th) {
                th = th;
                process = exec;
                if (process != null) {
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            process.getErrorStream().close();
            return sb2;
        } catch (IOException e4) {
            e4.printStackTrace();
            return sb2;
        }
        try {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            sb2 = sb.append("\n[error:" + th.toString() + "]").toString();
            if (process == null) {
                return sb2;
            }
            try {
                process.getOutputStream().close();
            } catch (IOException e5) {
                e5.printStackTrace();
            }
            try {
                process.getInputStream().close();
            } catch (IOException e6) {
                e6.printStackTrace();
            }
            process.getErrorStream().close();
            return sb2;
        } catch (Throwable th3) {
            th = th3;
            if (process != null) {
                try {
                    process.getOutputStream().close();
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
                try {
                    process.getInputStream().close();
                } catch (IOException e8) {
                    e8.printStackTrace();
                }
                try {
                    process.getErrorStream().close();
                } catch (IOException e9) {
                    e9.printStackTrace();
                }
            }
            throw th;
        }
        process.getInputStream().close();
        process.getErrorStream().close();
        return sb2;
    }

    public static Map<String, String> a(int i, boolean z) {
        HashMap hashMap = new HashMap(12);
        Map allStackTraces = Thread.getAllStackTraces();
        if (allStackTraces == null) {
            return null;
        }
        Thread.currentThread().getId();
        StringBuilder sb = new StringBuilder();
        for (Entry entry : allStackTraces.entrySet()) {
            sb.setLength(0);
            if (!(entry.getValue() == null || ((StackTraceElement[]) entry.getValue()).length == 0)) {
                StackTraceElement[] stackTraceElementArr = (StackTraceElement[]) entry.getValue();
                int length = stackTraceElementArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    StackTraceElement stackTraceElement = stackTraceElementArr[i2];
                    if (i > 0 && sb.length() >= i) {
                        sb.append("\n[Stack over limit size :" + i + " , has been cutted !]");
                        break;
                    }
                    sb.append(stackTraceElement.toString()).append("\n");
                    i2++;
                }
                hashMap.put(((Thread) entry.getKey()).getName() + "(" + ((Thread) entry.getKey()).getId() + ")", sb.toString());
            }
        }
        return hashMap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0030 A[SYNTHETIC, Splitter:B:17:0x0030] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0050 A[Catch:{ Exception -> 0x0054 }] */
    public static synchronized byte[] a(int i) {
        byte[] bArr;
        DataInputStream dataInputStream;
        synchronized (a.class) {
            try {
                bArr = new byte[16];
                dataInputStream = new DataInputStream(new FileInputStream(new File("/dev/urandom")));
                try {
                    dataInputStream.readFully(bArr);
                    try {
                        dataInputStream.close();
                    } catch (Exception e) {
                        if (!w.b(e)) {
                            e.printStackTrace();
                        }
                        bArr = null;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        w.e("Failed to read from /dev/urandom : %s", e);
                        if (dataInputStream != null) {
                        }
                        KeyGenerator instance = KeyGenerator.getInstance("AES");
                        instance.init(128, new SecureRandom());
                        bArr = instance.generateKey().getEncoded();
                        return bArr;
                    } catch (Throwable th) {
                        th = th;
                        if (dataInputStream != null) {
                            dataInputStream.close();
                        }
                        throw th;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                dataInputStream = null;
                w.e("Failed to read from /dev/urandom : %s", e);
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
                KeyGenerator instance2 = KeyGenerator.getInstance("AES");
                instance2.init(128, new SecureRandom());
                bArr = instance2.generateKey().getEncoded();
                return bArr;
            } catch (Throwable th2) {
                th = th2;
                dataInputStream = null;
                if (dataInputStream != null) {
                }
                throw th;
            }
        }
        return bArr;
    }

    public static byte[] a(int i, byte[] bArr, byte[] bArr2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
            Cipher instance = Cipher.getInstance("AES/GCM/NoPadding");
            instance.init(i, secretKeySpec, new IvParameterSpec(bArr2));
            return instance.doFinal(bArr);
        } catch (Exception e) {
            if (!w.b(e)) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static byte[] b(int i, byte[] bArr, byte[] bArr2) {
        try {
            PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bArr2));
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(1, generatePublic);
            return instance.doFinal(bArr);
        } catch (Exception e) {
            if (!w.b(e)) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static boolean a(Context context, String str, long j) {
        w.c("[Util] try to lock file:%s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            File file = new File(context.getFilesDir() + File.separator + str);
            if (file.exists()) {
                if (System.currentTimeMillis() - file.lastModified() < j) {
                    return false;
                }
                w.c("[Util] lock file(%s) is expired, unlock it", str);
                b(context, str);
            }
            if (!file.createNewFile()) {
                return false;
            }
            w.c("[Util] successfully locked file:%s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            return true;
        } catch (Throwable th) {
            w.a(th);
            return false;
        }
    }

    public static boolean b(Context context, String str) {
        w.c("[Util] try to unlock file:%s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            File file = new File(context.getFilesDir() + File.separator + str);
            if (!file.exists()) {
                return true;
            }
            if (!file.delete()) {
                return false;
            }
            w.c("[Util] successfully unlocked file:%s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            return true;
        } catch (Throwable th) {
            w.a(th);
            return false;
        }
    }
}
