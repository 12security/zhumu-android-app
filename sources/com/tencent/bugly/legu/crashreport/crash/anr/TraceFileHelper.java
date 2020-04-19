package com.tencent.bugly.legu.crashreport.crash.anr;

import com.tencent.bugly.legu.proguard.w;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: BUGLY */
public class TraceFileHelper {

    /* compiled from: BUGLY */
    public static class a {
        public long a;
        public String b;
        public long c;
        public Map<String, String[]> d;
    }

    /* compiled from: BUGLY */
    public interface b {
        boolean a(long j);

        boolean a(long j, long j2, String str);

        boolean a(String str, int i, String str2, String str3);
    }

    public static a readTargetDumpInfo(String str, String str2, final boolean z) {
        if (str == null || str2 == null) {
            return null;
        }
        final a aVar = new a();
        readTraceFile(str2, new b() {
            public final boolean a(String str, int i, String str2, String str3) {
                w.c("new thread %s", str);
                if (aVar.a > 0 && aVar.c > 0 && aVar.b != null) {
                    if (aVar.d == null) {
                        aVar.d = new HashMap();
                    }
                    aVar.d.put(str, new String[]{str2, str3, i});
                }
                return true;
            }

            public final boolean a(long j, long j2, String str) {
                w.c("new process %s", str);
                if (!str.equals(str)) {
                    return true;
                }
                aVar.a = j;
                aVar.b = str;
                aVar.c = j2;
                if (!z) {
                    return false;
                }
                return true;
            }

            public final boolean a(long j) {
                w.c("process end %d", Long.valueOf(j));
                if (aVar.a <= 0 || aVar.c <= 0 || aVar.b == null) {
                    return true;
                }
                return false;
            }
        });
        if (aVar.a <= 0 || aVar.c <= 0 || aVar.b == null) {
            return null;
        }
        return aVar;
    }

    public static a readFirstDumpInfo(String str, final boolean z) {
        if (str == null) {
            w.e("path:%s", str);
            return null;
        }
        final a aVar = new a();
        readTraceFile(str, new b() {
            public final boolean a(String str, int i, String str2, String str3) {
                w.c("new thread %s", str);
                if (aVar.d == null) {
                    aVar.d = new HashMap();
                }
                aVar.d.put(str, new String[]{str2, str3, i});
                return true;
            }

            public final boolean a(long j, long j2, String str) {
                w.c("new process %s", str);
                aVar.a = j;
                aVar.b = str;
                aVar.c = j2;
                if (!z) {
                    return false;
                }
                return true;
            }

            public final boolean a(long j) {
                w.c("process end %d", Long.valueOf(j));
                return false;
            }
        });
        if (aVar.a > 0 && aVar.c > 0 && aVar.b != null) {
            return aVar;
        }
        w.e("first dump error %s", aVar.a + " " + aVar.c + " " + aVar.b);
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0190, code lost:
        if (r13.a(java.lang.Long.parseLong(r0[1].toString().split("\\s")[2])) != false) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0197, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x019c, code lost:
        if (com.tencent.bugly.legu.proguard.w.a(r0) == false) goto L_0x019e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x019e, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:?, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x01b8 A[SYNTHETIC, Splitter:B:68:0x01b8] */
    public static void readTraceFile(String str, b bVar) {
        BufferedReader bufferedReader;
        if (str != null && bVar != null) {
            File file = new File(str);
            if (file.exists()) {
                file.lastModified();
                file.length();
                BufferedReader bufferedReader2 = null;
                try {
                    bufferedReader = new BufferedReader(new FileReader(file));
                    try {
                        Pattern compile = Pattern.compile("-{5}\\spid\\s\\d+\\sat\\s\\d+-\\d+-\\d+\\s\\d{2}:\\d{2}:\\d{2}\\s-{5}");
                        Pattern compile2 = Pattern.compile("-{5}\\send\\s\\d+\\s-{5}");
                        Pattern compile3 = Pattern.compile("Cmd\\sline:\\s(\\S+)");
                        Pattern compile4 = Pattern.compile("\".+\"\\s(daemon\\s){0,1}prio=\\d+\\stid=\\d+\\s.*");
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
                        while (true) {
                            Object[] a2 = a(bufferedReader, compile);
                            if (a2 != null) {
                                String[] split = a2[1].toString().split("\\s");
                                long parseLong = Long.parseLong(split[2]);
                                long time = simpleDateFormat.parse(split[4] + " " + split[5]).getTime();
                                Object[] a3 = a(bufferedReader, compile3);
                                if (a3 != null) {
                                    Matcher matcher = compile3.matcher(a3[1].toString());
                                    matcher.find();
                                    matcher.group(1);
                                    if (bVar.a(parseLong, time, matcher.group(1))) {
                                        while (true) {
                                            Object[] a4 = a(bufferedReader, compile4, compile2);
                                            if (a4 != null) {
                                                if (a4[0] != compile4) {
                                                    break;
                                                }
                                                String obj = a4[1].toString();
                                                Matcher matcher2 = Pattern.compile("\".+\"").matcher(obj);
                                                matcher2.find();
                                                String group = matcher2.group();
                                                String substring = group.substring(1, group.length() - 1);
                                                obj.contains("NATIVE");
                                                Matcher matcher3 = Pattern.compile("tid=\\d+").matcher(obj);
                                                matcher3.find();
                                                String group2 = matcher3.group();
                                                bVar.a(substring, Integer.parseInt(group2.substring(group2.indexOf("=") + 1)), a(bufferedReader), b(bufferedReader));
                                            } else {
                                                break;
                                            }
                                        }
                                    } else {
                                        try {
                                            bufferedReader.close();
                                            return;
                                        } catch (IOException e) {
                                            if (!w.a(e)) {
                                                e.printStackTrace();
                                                return;
                                            }
                                            return;
                                        }
                                    }
                                } else {
                                    try {
                                        bufferedReader.close();
                                        return;
                                    } catch (IOException e2) {
                                        if (!w.a(e2)) {
                                            e2.printStackTrace();
                                            return;
                                        }
                                        return;
                                    }
                                }
                            } else {
                                try {
                                    bufferedReader.close();
                                    return;
                                } catch (IOException e3) {
                                    if (!w.a(e3)) {
                                        e3.printStackTrace();
                                        return;
                                    }
                                    return;
                                }
                            }
                        }
                    } catch (Exception e4) {
                        e = e4;
                        bufferedReader2 = bufferedReader;
                    } catch (Throwable th) {
                        th = th;
                        if (bufferedReader != null) {
                        }
                        throw th;
                    }
                } catch (Exception e5) {
                    e = e5;
                    try {
                        w.d("trace open fail:%s : %s", e.getClass().getName(), e.getMessage());
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (IOException e6) {
                                if (!w.a(e6)) {
                                    e6.printStackTrace();
                                }
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader = null;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e7) {
                            if (!w.a(e7)) {
                                e7.printStackTrace();
                            }
                        }
                    }
                    throw th;
                }
            }
        }
    }

    private static Object[] a(BufferedReader bufferedReader, Pattern... patternArr) throws IOException {
        if (bufferedReader == null || patternArr == null) {
            return null;
        }
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return null;
            }
            int length = patternArr.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    Pattern pattern = patternArr[i];
                    if (pattern.matcher(readLine).matches()) {
                        return new Object[]{pattern, readLine};
                    }
                    i++;
                }
            }
        }
    }

    private static String a(BufferedReader bufferedReader) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 3; i++) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return null;
            }
            stringBuffer.append(readLine + "\n");
        }
        return stringBuffer.toString();
    }

    private static String b(BufferedReader bufferedReader) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null && readLine.trim().length() > 0) {
                stringBuffer.append(readLine + "\n");
            }
        }
        return stringBuffer.toString();
    }
}
