package com.tencent.StubShell;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

public final class SystemClassLoaderInjector {
    private static final String CLASS_LOADER_ALIYUN = "dalvik.system.LexClassLoader";
    private static final String CLASS_LOADER_BASE_DEX = "dalvik.system.BaseDexClassLoader";
    private static final String LOG_TAG = "SecShell";

    private static Object appendArray(Object obj, Object obj2, boolean z) {
        Class componentType = obj.getClass().getComponentType();
        int length = Array.getLength(obj);
        int i = length + 1;
        Object newInstance = Array.newInstance(componentType, i);
        if (z) {
            Array.set(newInstance, 0, obj2);
            for (int i2 = 1; i2 < i; i2++) {
                Array.set(newInstance, i2, Array.get(obj, i2 - 1));
            }
        } else {
            for (int i3 = 0; i3 < i; i3++) {
                if (i3 < length) {
                    Array.set(newInstance, i3, Array.get(obj, i3));
                } else {
                    Array.set(newInstance, i3, obj2);
                }
            }
        }
        return newInstance;
    }

    private static Object combineArray(Object obj, Object obj2, boolean z) {
        if (!z) {
            Object obj3 = obj2;
            obj2 = obj;
            obj = obj3;
        }
        Class componentType = obj2.getClass().getComponentType();
        int length = Array.getLength(obj2);
        int length2 = Array.getLength(obj) + length;
        Object newInstance = Array.newInstance(componentType, length2);
        for (int i = 0; i < length2; i++) {
            if (i < length) {
                Array.set(newInstance, i, Array.get(obj2, i));
            } else {
                Array.set(newInstance, i, Array.get(obj, i - length));
            }
        }
        return newInstance;
    }

    public static void fixAndroid(Context context, Application application) {
        if (VERSION.SDK_INT >= 28) {
            try {
                Log.e(LOG_TAG, "fixAndroid");
                ClassLoader classLoader = context.getClassLoader();
                Log.e(LOG_TAG, "fixAndroid mClassLoader:" + classLoader);
                Log.e(LOG_TAG, "fixAndroid mpathList:" + getPathList(classLoader));
                TxAppEntry.mPClassLoader = a.a(classLoader, application);
                Log.e(LOG_TAG, "fixAndroid fClassLoader:" + TxAppEntry.mPClassLoader);
                Log.e(LOG_TAG, "fixAndroid fpathList:" + getPathList(TxAppEntry.mPClassLoader));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private static Object getDexElements(Object obj) {
        return getField(obj, obj.getClass(), "dexElements");
    }

    private static Object getField(Object obj, Class cls, String str) {
        Field declaredField = cls.getDeclaredField(str);
        declaredField.setAccessible(true);
        return declaredField.get(obj);
    }

    private static Object getPathList(Object obj) {
        return getField(obj, Class.forName(CLASS_LOADER_BASE_DEX), "pathList");
    }

    private static boolean hasBaseDexClassLoader() {
        try {
            Class.forName(CLASS_LOADER_BASE_DEX);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static void inject(Context context, String str, String str2, boolean z) {
        if (str == null || !new File(str).exists()) {
            return;
        }
        if (isAliyunOs()) {
            injectInAliyunOs(context, str, str2, z);
        } else if (!hasBaseDexClassLoader()) {
            try {
                injectBelowApiLevel14(context, str, str2, z);
            } catch (Throwable th) {
                throw new Exception(th);
            }
        } else {
            injectAboveEqualApiLevel14(context, str, str2, z);
        }
    }

    private static void injectAboveEqualApiLevel14(Context context, String str, String str2, boolean z) {
        PathClassLoader pathClassLoader = (PathClassLoader) context.getClassLoader();
        Object combineArray = combineArray(getDexElements(getPathList(pathClassLoader)), getDexElements(getPathList(new DexClassLoader(str, context.getDir("dex", 0).getAbsolutePath(), str, context.getClassLoader()))), z);
        Object pathList = getPathList(pathClassLoader);
        setField(pathList, pathList.getClass(), "dexElements", combineArray);
        pathClassLoader.loadClass(str2);
    }

    @TargetApi(14)
    private static void injectBelowApiLevel14(Context context, String str, String str2, boolean z) {
        PathClassLoader pathClassLoader = (PathClassLoader) context.getClassLoader();
        DexClassLoader dexClassLoader = new DexClassLoader(str, context.getDir("dex", 0).getAbsolutePath(), str, context.getClassLoader());
        dexClassLoader.loadClass(str2);
        setField(pathClassLoader, PathClassLoader.class, "mPaths", appendArray(getField(pathClassLoader, PathClassLoader.class, "mPaths"), getField(dexClassLoader, DexClassLoader.class, "mRawDexPath"), z));
        setField(pathClassLoader, PathClassLoader.class, "mFiles", combineArray(getField(pathClassLoader, PathClassLoader.class, "mFiles"), getField(dexClassLoader, DexClassLoader.class, "mFiles"), z));
        setField(pathClassLoader, PathClassLoader.class, "mZips", combineArray(getField(pathClassLoader, PathClassLoader.class, "mZips"), getField(dexClassLoader, DexClassLoader.class, "mZips"), z));
        setField(pathClassLoader, PathClassLoader.class, "mDexs", combineArray(getField(pathClassLoader, PathClassLoader.class, "mDexs"), getField(dexClassLoader, DexClassLoader.class, "mDexs"), z));
        pathClassLoader.loadClass(str2);
    }

    private static void injectInAliyunOs(Context context, String str, String str2, boolean z) {
        PathClassLoader pathClassLoader = (PathClassLoader) context.getClassLoader();
        new DexClassLoader(str, context.getDir("dex", 0).getAbsolutePath(), str, pathClassLoader);
        String replaceAll = new File(str).getName().replaceAll("\\.[a-zA-Z0-9]+", ".lex");
        Class cls = Class.forName(CLASS_LOADER_ALIYUN);
        Object newInstance = cls.getConstructor(new Class[]{String.class, String.class, String.class, ClassLoader.class}).newInstance(new Object[]{context.getDir("dex", 0).getAbsolutePath() + File.separator + replaceAll, context.getDir("dex", 0).getAbsolutePath(), str, pathClassLoader});
        cls.getMethod("loadClass", new Class[]{String.class}).invoke(newInstance, new Object[]{str2});
        setField(pathClassLoader, PathClassLoader.class, "mPaths", appendArray(getField(pathClassLoader, PathClassLoader.class, "mPaths"), getField(newInstance, cls, "mRawDexPath"), z));
        setField(pathClassLoader, PathClassLoader.class, "mFiles", combineArray(getField(pathClassLoader, PathClassLoader.class, "mFiles"), getField(newInstance, cls, "mFiles"), z));
        setField(pathClassLoader, PathClassLoader.class, "mZips", combineArray(getField(pathClassLoader, PathClassLoader.class, "mZips"), getField(newInstance, cls, "mZips"), z));
        setField(pathClassLoader, PathClassLoader.class, "mLexs", combineArray(getField(pathClassLoader, PathClassLoader.class, "mLexs"), getField(newInstance, cls, "mDexs"), z));
    }

    private static boolean isAliyunOs() {
        try {
            Class.forName(CLASS_LOADER_ALIYUN);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    private static void setField(Object obj, Class cls, String str, Object obj2) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(obj, obj2);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
        }
    }
}
