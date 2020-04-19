package com.tencent.bugly.legu.crashreport.crash.jni;

import android.content.Context;
import com.tencent.bugly.legu.crashreport.common.info.a;
import com.tencent.bugly.legu.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.legu.proguard.j;
import com.tencent.bugly.legu.proguard.w;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: BUGLY */
public class b {
    private StringBuilder a;
    private int b = 0;

    private void d(String str) {
        for (int i = 0; i < this.b; i++) {
            this.a.append(9);
        }
        if (str != null) {
            this.a.append(str).append(": ");
        }
    }

    public b(StringBuilder sb, int i) {
        this.a = sb;
        this.b = i;
    }

    private static Map<String, Integer> c(String str) {
        String[] split;
        if (str == null) {
            return null;
        }
        try {
            HashMap hashMap = new HashMap();
            for (String str2 : str.split(",")) {
                String[] split2 = str2.split(":");
                if (split2.length != 2) {
                    w.e("error format at %s", str2);
                    return null;
                }
                hashMap.put(split2[0], Integer.valueOf(Integer.parseInt(split2[1])));
            }
            return hashMap;
        } catch (Exception e) {
            w.e("error format intStateStr %s", str);
            e.printStackTrace();
            return null;
        }
    }

    public b a(boolean z, String str) {
        d(str);
        this.a.append(z ? 'T' : 'F').append(10);
        return this;
    }

    public b a(byte b2, String str) {
        d(str);
        this.a.append(b2).append(10);
        return this;
    }

    public b a(char c, String str) {
        d(str);
        this.a.append(c).append(10);
        return this;
    }

    public b a(short s, String str) {
        d(str);
        this.a.append(s).append(10);
        return this;
    }

    protected static String a(String str) {
        if (str == null) {
            return "";
        }
        String[] split = str.split("\n");
        if (split == null || split.length == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : split) {
            if (!str2.contains("java.lang.Thread.getStackTrace(")) {
                sb.append(str2).append("\n");
            }
        }
        return sb.toString();
    }

    public b a(int i, String str) {
        d(str);
        this.a.append(i).append(10);
        return this;
    }

    public b a(long j, String str) {
        d(str);
        this.a.append(j).append(10);
        return this;
    }

    public b a(float f, String str) {
        d(str);
        this.a.append(f).append(10);
        return this;
    }

    private static CrashDetailBean a(Context context, Map<String, String> map, NativeExceptionHandler nativeExceptionHandler) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        if (map == null) {
            return null;
        }
        if (a.a(context) == null) {
            w.e("abnormal com info not created", new Object[0]);
            return null;
        }
        String str7 = (String) map.get("intStateStr");
        if (str7 == null || str7.trim().length() <= 0) {
            w.e("no intStateStr", new Object[0]);
            return null;
        }
        Map c = c(str7);
        if (c == null) {
            w.e("parse intSateMap fail", Integer.valueOf(map.size()));
            return null;
        }
        try {
            int intValue = ((Integer) c.get("ep")).intValue();
            int intValue2 = ((Integer) c.get("et")).intValue();
            ((Integer) c.get("sino")).intValue();
            int intValue3 = ((Integer) c.get("sico")).intValue();
            int intValue4 = ((Integer) c.get("spd")).intValue();
            ((Integer) c.get("sud")).intValue();
            long intValue5 = (long) ((Integer) c.get("ets")).intValue();
            long intValue6 = (long) ((Integer) c.get("etms")).intValue();
            String str8 = (String) map.get("soVersion");
            if (str8 == null) {
                w.e("error format at version", new Object[0]);
                return null;
            }
            String str9 = (String) map.get("errorAddr");
            String str10 = str9 == null ? "unknown2" : str9;
            String str11 = (String) map.get("codeMsg");
            if (str11 == null) {
                str = "unknown2";
            } else {
                str = str11;
            }
            String str12 = (String) map.get("tombPath");
            if (str12 == null) {
                str2 = "unknown2";
            } else {
                str2 = str12;
            }
            String str13 = (String) map.get("signalName");
            if (str13 == null) {
                str3 = "unknown2";
            } else {
                str3 = str13;
            }
            map.get("errnoMsg");
            String str14 = (String) map.get("stack");
            if (str14 == null) {
                str4 = "unknown2";
            } else {
                str4 = str14;
            }
            String str15 = (String) map.get("jstack");
            if (str15 != null) {
                str5 = str4 + "java:\n" + str15;
            } else {
                str5 = str4;
            }
            long j = (intValue5 * 1000) + (intValue6 / 1000);
            String a2 = a(str5);
            String str16 = (String) map.get("sendingProcess");
            if (str16 == null) {
                str16 = "UNKNOWN";
            }
            String str17 = str16 + "(" + intValue4 + ")";
            if (intValue3 > 0) {
                str3 = str3 + "(" + str + ")";
                str = "KERNEL";
            }
            String str18 = (String) map.get("nativeLog");
            byte[] bArr = null;
            if (str18 != null && !str18.isEmpty()) {
                bArr = com.tencent.bugly.legu.proguard.a.a((File) null, str18);
            }
            String str19 = (String) map.get("processName");
            if (str19 == null) {
                str6 = "unknown(" + intValue + ")";
            } else {
                str6 = str19;
            }
            String str20 = (String) map.get("threadName");
            if (str20 == null) {
                str20 = "unknown";
            }
            String str21 = str20 + "(" + intValue2 + ")";
            HashMap hashMap = null;
            String str22 = (String) map.get("key-value");
            if (str22 != null) {
                hashMap = new HashMap();
                for (String split : str22.split("\n")) {
                    String[] split2 = split.split("=");
                    if (split2.length == 2) {
                        hashMap.put(split2[0], split2[1]);
                    }
                }
            }
            CrashDetailBean packageCrashDatas = nativeExceptionHandler.packageCrashDatas(str6, str21, j, str3, str10, a2, str, str17, str2, str8, bArr, hashMap, false);
            if (packageCrashDatas != null) {
                String str23 = (String) map.get("userId");
                if (str23 != null) {
                    packageCrashDatas.m = str23;
                }
                String str24 = (String) map.get("sysLog");
                if (str24 != null) {
                    packageCrashDatas.w = str24;
                }
                String str25 = (String) map.get("appVersion");
                if (str24 != null) {
                    packageCrashDatas.f = str25;
                }
                packageCrashDatas.y = null;
                packageCrashDatas.k = true;
            }
            return packageCrashDatas;
        } catch (Throwable th) {
            w.e("error format", new Object[0]);
            th.printStackTrace();
            return null;
        }
    }

    public b a(double d, String str) {
        d(str);
        this.a.append(d).append(10);
        return this;
    }

    public b a(String str, String str2) {
        d(str2);
        if (str == null) {
            this.a.append("null\n");
        } else {
            this.a.append(str).append(10);
        }
        return this;
    }

    public b a(byte[] bArr, String str) {
        d(str);
        if (bArr == null) {
            this.a.append("null\n");
        } else if (bArr.length == 0) {
            this.a.append(bArr.length).append(", []\n");
        } else {
            this.a.append(bArr.length).append(", [\n");
            b bVar = new b(this.a, this.b + 1);
            for (byte a2 : bArr) {
                bVar.a(a2, (String) null);
            }
            a(']', (String) null);
        }
        return this;
    }

    public b a(short[] sArr, String str) {
        d(str);
        if (sArr == null) {
            this.a.append("null\n");
        } else if (sArr.length == 0) {
            this.a.append(sArr.length).append(", []\n");
        } else {
            this.a.append(sArr.length).append(", [\n");
            b bVar = new b(this.a, this.b + 1);
            for (short a2 : sArr) {
                bVar.a(a2, (String) null);
            }
            a(']', (String) null);
        }
        return this;
    }

    public b a(int[] iArr, String str) {
        d(str);
        if (iArr == null) {
            this.a.append("null\n");
        } else if (iArr.length == 0) {
            this.a.append(iArr.length).append(", []\n");
        } else {
            this.a.append(iArr.length).append(", [\n");
            b bVar = new b(this.a, this.b + 1);
            for (int a2 : iArr) {
                bVar.a(a2, (String) null);
            }
            a(']', (String) null);
        }
        return this;
    }

    public b a(long[] jArr, String str) {
        d(str);
        if (jArr == null) {
            this.a.append("null\n");
        } else if (jArr.length == 0) {
            this.a.append(jArr.length).append(", []\n");
        } else {
            this.a.append(jArr.length).append(", [\n");
            b bVar = new b(this.a, this.b + 1);
            for (long a2 : jArr) {
                bVar.a(a2, (String) null);
            }
            a(']', (String) null);
        }
        return this;
    }

    public b a(float[] fArr, String str) {
        d(str);
        if (fArr == null) {
            this.a.append("null\n");
        } else if (fArr.length == 0) {
            this.a.append(fArr.length).append(", []\n");
        } else {
            this.a.append(fArr.length).append(", [\n");
            b bVar = new b(this.a, this.b + 1);
            for (float a2 : fArr) {
                bVar.a(a2, (String) null);
            }
            a(']', (String) null);
        }
        return this;
    }

    public b a(double[] dArr, String str) {
        d(str);
        if (dArr == null) {
            this.a.append("null\n");
        } else if (dArr.length == 0) {
            this.a.append(dArr.length).append(", []\n");
        } else {
            this.a.append(dArr.length).append(", [\n");
            b bVar = new b(this.a, this.b + 1);
            for (double a2 : dArr) {
                bVar.a(a2, (String) null);
            }
            a(']', (String) null);
        }
        return this;
    }

    private static String a(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            int read = inputStream.read();
            if (read == -1) {
                return null;
            }
            if (read == 0) {
                return sb.toString();
            }
            sb.append((char) read);
        }
    }

    public <K, V> b a(Map<K, V> map, String str) {
        d(str);
        if (map == null) {
            this.a.append("null\n");
        } else if (map.isEmpty()) {
            this.a.append(map.size()).append(", {}\n");
        } else {
            this.a.append(map.size()).append(", {\n");
            b bVar = new b(this.a, this.b + 1);
            b bVar2 = new b(this.a, this.b + 2);
            for (Entry entry : map.entrySet()) {
                bVar.a('(', (String) null);
                bVar2.a((T) entry.getKey(), (String) null);
                bVar2.a((T) entry.getValue(), (String) null);
                bVar.a(')', (String) null);
            }
            a('}', (String) null);
        }
        return this;
    }

    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v1, types: [com.tencent.bugly.legu.crashreport.crash.CrashDetailBean] */
    /* JADX WARNING: type inference failed for: r2v5, types: [java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* JADX WARNING: type inference failed for: r2v7, types: [java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: type inference failed for: r2v9, types: [java.io.FileInputStream, java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: type inference failed for: r1v11, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r3v3, types: [java.lang.Object, java.lang.String] */
    /* JADX WARNING: type inference failed for: r0v7, types: [com.tencent.bugly.legu.crashreport.crash.CrashDetailBean] */
    /* JADX WARNING: type inference failed for: r4v4, types: [java.lang.Object[]] */
    /* JADX WARNING: type inference failed for: r1v14 */
    /* JADX WARNING: type inference failed for: r1v15 */
    /* JADX WARNING: type inference failed for: r1v16 */
    /* JADX WARNING: type inference failed for: r0v8 */
    /* JADX WARNING: type inference failed for: r2v10 */
    /* JADX WARNING: type inference failed for: r2v11 */
    /* JADX WARNING: type inference failed for: r0v9 */
    /* JADX WARNING: type inference failed for: r0v10 */
    /* JADX WARNING: type inference failed for: r1v17 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v14
  assigns: []
  uses: []
  mth insns count: 72
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0098 A[SYNTHETIC, Splitter:B:53:0x0098] */
    /* JADX WARNING: Unknown variable types count: 9 */
    public static CrashDetailBean a(Context context, String str, NativeExceptionHandler nativeExceptionHandler) {
        ? r2;
        Throwable th;
        ? r22;
        ? r1;
        ? r0 = 0;
        if (context == null || str == null || nativeExceptionHandler == null) {
            w.e("get eup record file args error", new Object[0]);
            r0 = r0;
        } else {
            File file = new File(str, "rqd_record.eup");
            if (file.exists() && file.canRead()) {
                try {
                    ? fileInputStream = new FileInputStream(file);
                    try {
                        String a2 = a((InputStream) fileInputStream);
                        if (a2 == null || !a2.equals("NATIVE_RQD_REPORT")) {
                            w.e("record read fail! %s", a2);
                            try {
                                fileInputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            HashMap hashMap = new HashMap();
                            ? r12 = r0;
                            while (true) {
                                ? a3 = a((InputStream) fileInputStream);
                                if (a3 == 0) {
                                    break;
                                }
                                if (r12 == 0) {
                                    r1 = a3;
                                } else {
                                    hashMap.put(r12, a3);
                                    r1 = r0;
                                }
                                r12 = r1;
                            }
                            if (r12 != 0) {
                                w.e("record not pair! drop! %s", new Object[]{r12});
                                try {
                                    fileInputStream.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            } else {
                                ? a4 = a(context, (Map<String, String>) hashMap, nativeExceptionHandler);
                                try {
                                    fileInputStream.close();
                                    r0 = a4;
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                    r0 = a4;
                                }
                            }
                        }
                    } catch (IOException e4) {
                        e = e4;
                        r22 = fileInputStream;
                    }
                } catch (IOException e5) {
                    e = e5;
                    r22 = r0;
                    try {
                        e.printStackTrace();
                        if (r22 != 0) {
                            try {
                                r22.close();
                            } catch (IOException e6) {
                                e6.printStackTrace();
                            }
                        }
                        return r0;
                    } catch (Throwable th2) {
                        th = th2;
                        r2 = r22;
                        if (r2 != 0) {
                            try {
                                r2.close();
                            } catch (IOException e7) {
                                e7.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    r2 = r0;
                    th = th3;
                    if (r2 != 0) {
                    }
                    throw th;
                }
            }
        }
        return r0;
    }

    public <T> b a(T[] tArr, String str) {
        d(str);
        if (tArr == null) {
            this.a.append("null\n");
        } else if (tArr.length == 0) {
            this.a.append(tArr.length).append(", []\n");
        } else {
            this.a.append(tArr.length).append(", [\n");
            b bVar = new b(this.a, this.b + 1);
            for (T a2 : tArr) {
                bVar.a(a2, (String) null);
            }
            a(']', (String) null);
        }
        return this;
    }

    public <T> b a(Collection<T> collection, String str) {
        if (collection != null) {
            return a((T[]) collection.toArray(), str);
        }
        d(str);
        this.a.append("null\t");
        return this;
    }

    public static void b(String str) {
        File file = new File(str, "rqd_record.eup");
        if (file.exists() && file.canWrite()) {
            file.delete();
            w.c("delete record file %s", file.getAbsoluteFile());
        }
    }

    public <T> b a(T t, String str) {
        if (t == null) {
            this.a.append("null\n");
        } else if (t instanceof Byte) {
            a(((Byte) t).byteValue(), str);
        } else if (t instanceof Boolean) {
            a(((Boolean) t).booleanValue(), str);
        } else if (t instanceof Short) {
            a(((Short) t).shortValue(), str);
        } else if (t instanceof Integer) {
            a(((Integer) t).intValue(), str);
        } else if (t instanceof Long) {
            a(((Long) t).longValue(), str);
        } else if (t instanceof Float) {
            a(((Float) t).floatValue(), str);
        } else if (t instanceof Double) {
            a(((Double) t).doubleValue(), str);
        } else if (t instanceof String) {
            a((String) t, str);
        } else if (t instanceof Map) {
            a((Map) t, str);
        } else if (t instanceof List) {
            a((Collection<T>) (List) t, str);
        } else if (t instanceof j) {
            a((j) t, str);
        } else if (t instanceof byte[]) {
            a((byte[]) t, str);
        } else if (t instanceof boolean[]) {
            a((T) (boolean[]) t, str);
        } else if (t instanceof short[]) {
            a((short[]) t, str);
        } else if (t instanceof int[]) {
            a((int[]) t, str);
        } else if (t instanceof long[]) {
            a((long[]) t, str);
        } else if (t instanceof float[]) {
            a((float[]) t, str);
        } else if (t instanceof double[]) {
            a((double[]) t, str);
        } else if (t.getClass().isArray()) {
            a((T[]) (Object[]) t, str);
        } else {
            throw new com.tencent.bugly.legu.proguard.b("write object error: unsupport type.");
        }
        return this;
    }

    public b a(j jVar, String str) {
        a('{', str);
        if (jVar == null) {
            this.a.append(9).append("null");
        } else {
            jVar.a(this.a, this.b + 1);
        }
        a('}', (String) null);
        return this;
    }
}
