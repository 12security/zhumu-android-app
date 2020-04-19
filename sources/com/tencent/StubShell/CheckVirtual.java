package com.tencent.StubShell;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class CheckVirtual {
    private static final String TAG = "CheckVirtual";

    /* JADX WARNING: type inference failed for: r5v1 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0056 A[SYNTHETIC, Splitter:B:25:0x0056] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x005b A[SYNTHETIC, Splitter:B:28:0x005b] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0076 A[SYNTHETIC, Splitter:B:39:0x0076] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x007b A[SYNTHETIC, Splitter:B:42:0x007b] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0080  */
    /* JADX WARNING: Unknown variable types count: 1 */
    private static String exec(String str) {
        BufferedOutputStream bufferedOutputStream;
        BufferedInputStream bufferedInputStream;
        Process process;
        Throwable th;
        BufferedOutputStream bufferedOutputStream2;
        BufferedInputStream bufferedInputStream2;
        Process process2;
        BufferedOutputStream bufferedOutputStream3;
        String str2 = 0;
        try {
            Process exec = Runtime.getRuntime().exec("sh");
            try {
                bufferedOutputStream3 = new BufferedOutputStream(exec.getOutputStream());
            } catch (Exception e) {
                bufferedInputStream2 = str2;
                bufferedOutputStream2 = str2;
                process2 = exec;
                if (bufferedOutputStream2 != 0) {
                }
                if (bufferedInputStream2 != 0) {
                }
                if (process2 != 0) {
                }
                return str2;
            } catch (Throwable th2) {
                bufferedOutputStream = str2;
                ? r5 = str2;
                th = th2;
                bufferedInputStream = r5;
                process = exec;
                if (bufferedOutputStream != 0) {
                }
                if (bufferedInputStream != 0) {
                }
                if (process != 0) {
                }
                throw th;
            }
            try {
                BufferedInputStream bufferedInputStream3 = new BufferedInputStream(exec.getInputStream());
                try {
                    bufferedOutputStream3.write(str.getBytes());
                    bufferedOutputStream3.write(10);
                    bufferedOutputStream3.flush();
                    bufferedOutputStream3.close();
                    exec.waitFor();
                    str2 = getStrFromBufferInputSteam(bufferedInputStream3);
                    if (bufferedOutputStream3 != 0) {
                        try {
                            bufferedOutputStream3.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (bufferedInputStream3 != 0) {
                        try {
                            bufferedInputStream3.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (exec != 0) {
                        exec.destroy();
                    }
                } catch (Exception e4) {
                    process2 = exec;
                    bufferedOutputStream2 = bufferedOutputStream3;
                    bufferedInputStream2 = bufferedInputStream3;
                } catch (Throwable th3) {
                    th = th3;
                    process = exec;
                    bufferedOutputStream = bufferedOutputStream3;
                    bufferedInputStream = bufferedInputStream3;
                    if (bufferedOutputStream != 0) {
                    }
                    if (bufferedInputStream != 0) {
                    }
                    if (process != 0) {
                    }
                    throw th;
                }
            } catch (Exception e5) {
                bufferedInputStream2 = str2;
                process2 = exec;
                bufferedOutputStream2 = bufferedOutputStream3;
                if (bufferedOutputStream2 != 0) {
                    try {
                        bufferedOutputStream2.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                if (bufferedInputStream2 != 0) {
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException e7) {
                        e7.printStackTrace();
                    }
                }
                if (process2 != 0) {
                    process2.destroy();
                }
                return str2;
            } catch (Throwable th4) {
                Throwable th5 = th4;
                bufferedInputStream = str2;
                th = th5;
                process = exec;
                bufferedOutputStream = bufferedOutputStream3;
                if (bufferedOutputStream != 0) {
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e8) {
                        e8.printStackTrace();
                    }
                }
                if (bufferedInputStream != 0) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e9) {
                        e9.printStackTrace();
                    }
                }
                if (process != 0) {
                    process.destroy();
                }
                throw th;
            }
        } catch (Exception e10) {
            process2 = str2;
            bufferedInputStream2 = str2;
            bufferedOutputStream2 = str2;
        } catch (Throwable th6) {
            bufferedInputStream = str2;
            bufferedOutputStream = str2;
            Throwable th7 = th6;
            process = str2;
            th = th7;
            if (bufferedOutputStream != 0) {
            }
            if (bufferedInputStream != 0) {
            }
            if (process != 0) {
            }
            throw th;
        }
        return str2;
    }

    private static String getStrFromBufferInputSteam(BufferedInputStream bufferedInputStream) {
        int read;
        if (bufferedInputStream == null) {
            return "";
        }
        byte[] bArr = new byte[512];
        StringBuilder sb = new StringBuilder();
        do {
            try {
                read = bufferedInputStream.read(bArr);
                if (read > 0) {
                    sb.append(new String(bArr, 0, read));
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (read >= 512);
        return sb.toString();
    }

    public static String getUidStrFormat() {
        String exec = exec("cat /proc/self/cgroup");
        if (exec == null || exec.length() == 0) {
            return null;
        }
        int lastIndexOf = exec.lastIndexOf("uid");
        int lastIndexOf2 = exec.lastIndexOf("/pid");
        if (lastIndexOf < 0) {
            return null;
        }
        if (lastIndexOf2 <= 0) {
            lastIndexOf2 = exec.length();
        }
        try {
            String replaceAll = exec.substring(lastIndexOf + 4, lastIndexOf2).replaceAll("\n", "");
            if (!isNumber(replaceAll)) {
                return null;
            }
            return String.format("u0_a%d", new Object[]{Integer.valueOf(Integer.valueOf(replaceAll).intValue() - 10000)});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isNumber(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isRunInVirtual() {
        String uidStrFormat = getUidStrFormat();
        String exec = exec("ps");
        if (exec == null || exec == "") {
            return false;
        }
        String[] split = exec.split("\n");
        if (split == null || split.length <= 0) {
            return false;
        }
        int i = 0;
        for (int i2 = 0; i2 < split.length; i2++) {
            if (split[i2].contains(uidStrFormat)) {
                int lastIndexOf = split[i2].lastIndexOf(" ");
                if (new File(String.format("/data/data/%s", new Object[]{split[i2].substring(lastIndexOf <= 0 ? 0 : lastIndexOf + 1, split[i2].length()), Locale.CHINA})).exists()) {
                    i++;
                }
            }
        }
        return i > 1;
    }
}
