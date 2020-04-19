package com.tencent.bugly.legu.proguard;

import android.content.Context;
import android.os.Process;
import android.os.SystemClock;
import com.tencent.bugly.legu.BuglyStrategy.a;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: BUGLY */
public final class r {
    private static r b;
    public Map<String, String> a = null;
    private Context c;

    private r(Context context) {
        this.c = context;
    }

    public static r a(Context context) {
        if (b == null) {
            b = new r(context);
        }
        return b;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:111:?, code lost:
        return r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00a3, code lost:
        r18.b(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r11.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00b1, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00b6, code lost:
        if (com.tencent.bugly.legu.proguard.w.a(r2) == false) goto L_0x00b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00b8, code lost:
        r2.printStackTrace();
     */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x015c A[Catch:{ all -> 0x016e, Throwable -> 0x0173 }] */
    public final byte[] a(String str, byte[] bArr, u uVar, Map<String, String> map) {
        long length;
        long j;
        if (str == null) {
            w.e("rqdp{  no destUrl!}", new Object[0]);
            return null;
        }
        int i = 0;
        if (bArr == null) {
            length = 0;
        } else {
            length = (long) bArr.length;
        }
        w.c("req %s sz:%d (pid=%d | tid=%d)", str, Long.valueOf(length), Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        boolean z = false;
        int i2 = 0;
        String str2 = str;
        while (true) {
            int i3 = i2 + 1;
            if (i2 < 3 && i < 2) {
                if (z) {
                    z = false;
                } else if (i3 > 1) {
                    w.c("try time " + i3, new Object[0]);
                    SystemClock.sleep(10000);
                }
                String e = a.e(this.c);
                if (e == null) {
                    w.d("req but network not avail,so drop!", new Object[0]);
                    i2 = i3;
                } else {
                    uVar.a(length);
                    HttpURLConnection a2 = a(str2, bArr, e, map);
                    if (a2 != null) {
                        try {
                            int responseCode = a2.getResponseCode();
                            if (responseCode == 200) {
                                this.a = a(a2);
                                byte[] b2 = b(a2);
                                if (b2 != null) {
                                    j = (long) b2.length;
                                    break;
                                }
                                j = 0;
                                break;
                            }
                            if (responseCode == 301 || responseCode == 302 || responseCode == 303 || responseCode == 307) {
                                try {
                                    String headerField = a2.getHeaderField("Location");
                                    if (headerField == null) {
                                        w.e("rqdp{  redirect code:}" + responseCode + "rqdp{   Location is null! return}", new Object[0]);
                                        try {
                                            a2.disconnect();
                                        } catch (Throwable th) {
                                            if (!w.a(th)) {
                                                th.printStackTrace();
                                            }
                                        }
                                        return null;
                                    }
                                    i++;
                                    i3 = 0;
                                    try {
                                        w.c("redirect code: %d ,to:%s", Integer.valueOf(responseCode), headerField);
                                        z = true;
                                        str2 = headerField;
                                    } catch (IOException e2) {
                                        str2 = headerField;
                                        e = e2;
                                        z = true;
                                        try {
                                            if (!w.a(e)) {
                                                e.printStackTrace();
                                            }
                                            try {
                                                a2.disconnect();
                                            } catch (Throwable th2) {
                                                if (!w.a(th2)) {
                                                    th2.printStackTrace();
                                                }
                                            }
                                            i2 = i3;
                                        } catch (Throwable th3) {
                                            if (!w.a(th3)) {
                                                th3.printStackTrace();
                                            }
                                        }
                                    }
                                } catch (IOException e3) {
                                    IOException iOException = e3;
                                    z = true;
                                    e = iOException;
                                    if (!w.a(e)) {
                                    }
                                    a2.disconnect();
                                    i2 = i3;
                                }
                            }
                            w.d("response code " + responseCode, new Object[0]);
                            long contentLength = (long) a2.getContentLength();
                            if (contentLength < 0) {
                                contentLength = 0;
                            }
                            uVar.b(contentLength);
                            try {
                                a2.disconnect();
                            } catch (Throwable th4) {
                                if (!w.a(th4)) {
                                    th4.printStackTrace();
                                }
                            }
                        } catch (IOException e4) {
                            e = e4;
                        }
                    } else {
                        w.c("Failed to execute post.", new Object[0]);
                        uVar.b(0);
                    }
                    i2 = i3;
                }
            }
        }
        return null;
        throw th;
    }

    private static Map<String, String> a(HttpURLConnection httpURLConnection) {
        HashMap hashMap = new HashMap();
        Map headerFields = httpURLConnection.getHeaderFields();
        if (headerFields == null || headerFields.size() == 0) {
            return null;
        }
        for (String str : headerFields.keySet()) {
            List list = (List) headerFields.get(str);
            if (list.size() > 0) {
                hashMap.put(str, list.get(0));
            }
        }
        return hashMap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x004b A[SYNTHETIC, Splitter:B:29:0x004b] */
    private static byte[] b(HttpURLConnection httpURLConnection) {
        BufferedInputStream bufferedInputStream;
        BufferedInputStream bufferedInputStream2;
        Throwable th;
        byte[] bArr = 0;
        if (httpURLConnection != null) {
            try {
                BufferedInputStream bufferedInputStream3 = new BufferedInputStream(httpURLConnection.getInputStream());
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr2 = new byte[1024];
                    while (true) {
                        int read = bufferedInputStream3.read(bArr2);
                        if (read <= 0) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr2, 0, read);
                    }
                    byteArrayOutputStream.flush();
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    try {
                        bufferedInputStream3.close();
                        bArr = byteArray;
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                        bArr = byteArray;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bufferedInputStream = bufferedInputStream3;
                }
            } catch (Throwable th4) {
                bufferedInputStream2 = bArr;
                th = th4;
                if (bufferedInputStream2 != 0) {
                }
                throw th;
            }
        }
        return bArr;
    }

    private HttpURLConnection a(String str, byte[] bArr, String str2, Map<String, String> map) {
        if (str == null) {
            w.e("destUrl is null.", new Object[0]);
            return null;
        }
        HttpURLConnection a2 = a(str2, str);
        if (a2 == null) {
            w.e("Failed to get HttpURLConnection object.", new Object[0]);
            return null;
        }
        try {
            a2.setRequestProperty("wup_version", "3.0");
            if (map != null && map.size() > 0) {
                for (Entry entry : map.entrySet()) {
                    a2.setRequestProperty((String) entry.getKey(), URLEncoder.encode((String) entry.getValue(), "utf-8"));
                }
            }
            a2.setRequestProperty("A37", URLEncoder.encode(str2, "utf-8"));
            a2.setRequestProperty("A38", URLEncoder.encode(a.e(this.c), "utf-8"));
            a2.connect();
            OutputStream outputStream = a2.getOutputStream();
            if (bArr == null) {
                outputStream.write(0);
            } else {
                outputStream.write(bArr);
            }
            return a2;
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            w.e("Failed to upload crash, please check your network.", new Object[0]);
            return null;
        }
    }

    private static HttpURLConnection a(String str, String str2) {
        HttpURLConnection httpURLConnection;
        try {
            URL url = new URL(str2);
            if (str == null || !str.toLowerCase(Locale.US).contains("wap")) {
                httpURLConnection = (HttpURLConnection) url.openConnection();
            } else {
                httpURLConnection = (HttpURLConnection) url.openConnection(new Proxy(Type.HTTP, new InetSocketAddress(System.getProperty("http.proxyHost"), Integer.parseInt(System.getProperty("http.proxyPort")))));
            }
            httpURLConnection.setConnectTimeout(a.MAX_USERDATA_VALUE_LENGTH);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setInstanceFollowRedirects(false);
            return httpURLConnection;
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }
}
