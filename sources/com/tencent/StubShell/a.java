package com.tencent.StubShell;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

@TargetApi(14)
class a extends PathClassLoader {
    private static Object a = null;
    private static String b = null;

    private a(String str, ClassLoader classLoader, Application application) {
        super(str, classLoader);
        try {
            b = application.getPackageCodePath();
        } catch (Throwable th) {
            Log.e("SecShell", "AndroidNClassLoader init:" + str, th);
        }
    }

    public static ClassLoader a(ClassLoader classLoader, Application application) {
        try {
            ClassLoader a2 = a("", classLoader, application);
            a(application, a2);
            return a2;
        } catch (Throwable th) {
            Log.e("SecShell", "AndroidNClassLoader inject", th);
            return null;
        }
    }

    private static ClassLoader a(String str, ClassLoader classLoader, Application application) {
        try {
            a aVar = new a(str, classLoader, application);
            Field a2 = c.a((Object) classLoader, "pathList");
            Object obj = a2.get(classLoader);
            a2.set(aVar, a(obj, (ClassLoader) aVar, false));
            c.a(obj, "definingContext").set(obj, aVar);
            a = obj;
            return aVar;
        } catch (Throwable th) {
            Log.e("SecShell", "AndroidNClassLoader createAndroidNClassLoader", th);
            return classLoader;
        }
    }

    private static Object a(Object obj, ClassLoader classLoader, boolean z) {
        boolean z2;
        try {
            Constructor a2 = c.a(obj, ClassLoader.class, String.class, String.class, File.class);
            if (z) {
                return a2.newInstance(new Object[]{classLoader, "", null, null});
            }
            Object[] objArr = (Object[]) c.a(obj, "dexElements").get(obj);
            List<File> list = (List) c.a(obj, "nativeLibraryDirectories").get(obj);
            StringBuilder sb = new StringBuilder();
            Field a3 = c.a(objArr.getClass().getComponentType(), "dexFile");
            boolean z3 = true;
            int length = objArr.length;
            int i = 0;
            while (i < length) {
                DexFile dexFile = (DexFile) a3.get(objArr[i]);
                if (dexFile != null) {
                    if (dexFile.getName() == null) {
                        z2 = z3;
                    } else if (!dexFile.getName().equals(b)) {
                        z2 = z3;
                    } else {
                        if (z3) {
                            z3 = false;
                        } else {
                            sb.append(File.pathSeparator);
                        }
                        sb.append(dexFile.getName());
                    }
                    i++;
                    z3 = z2;
                }
                z2 = z3;
                i++;
                z3 = z2;
            }
            String sb2 = sb.toString();
            Log.e("SecShell", "recreateDexPathList dexPath:" + sb2);
            StringBuilder sb3 = new StringBuilder();
            boolean z4 = true;
            for (File file : list) {
                if (file != null) {
                    if (z4) {
                        z4 = false;
                    } else {
                        sb3.append(File.pathSeparator);
                    }
                    sb3.append(file.getAbsolutePath());
                }
            }
            return a2.newInstance(new Object[]{classLoader, sb2, sb3.toString(), null});
        } catch (Throwable th) {
            Log.e("SecShell", "AndroidNClassLoader recreateDexPathList", th);
            return null;
        }
    }

    private static void a(Application application, ClassLoader classLoader) {
        try {
            Context context = (Context) c.a((Object) application, "mBase").get(application);
            Object obj = c.a((Object) context, "mPackageInfo").get(context);
            c.a(obj, "mClassLoader").set(obj, classLoader);
        } catch (Throwable th) {
            Log.e("SecShell", "AndroidNClassLoader reflectPackageInfoClassloader", th);
        }
    }
}
