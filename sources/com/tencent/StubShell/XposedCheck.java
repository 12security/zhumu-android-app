package com.tencent.StubShell;

import java.lang.reflect.Field;

public class XposedCheck {
    private static final String XPOSED_BRIDGE = "de.robv.android.xposed.XposedBridge";
    private static final String XPOSED_HELPERS = "de.robv.android.xposed.XposedHelpers";

    private static boolean isXposedExistByThrow() {
        try {
            throw new Exception("exe xp");
        } catch (Exception e) {
            for (StackTraceElement className : e.getStackTrace()) {
                if (className.getClassName().contains(XPOSED_BRIDGE)) {
                    return true;
                }
            }
            return false;
        }
    }

    private static boolean isXposedExists() {
        try {
            ClassLoader.getSystemClassLoader().loadClass(XPOSED_HELPERS).newInstance();
            try {
                ClassLoader.getSystemClassLoader().loadClass(XPOSED_BRIDGE).newInstance();
                return true;
            } catch (InstantiationException e) {
                e.printStackTrace();
                return true;
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
                return true;
            } catch (ClassNotFoundException e3) {
                e3.printStackTrace();
                return false;
            }
        } catch (InstantiationException e4) {
            e4.printStackTrace();
            return true;
        } catch (IllegalAccessException e5) {
            e5.printStackTrace();
            return true;
        } catch (ClassNotFoundException e6) {
            e6.printStackTrace();
            return false;
        }
    }

    public static boolean tryShutdownXposed() {
        if (!isXposedExistByThrow()) {
            return true;
        }
        try {
            Field declaredField = ClassLoader.getSystemClassLoader().loadClass(XPOSED_BRIDGE).getDeclaredField("disableHooks");
            declaredField.setAccessible(true);
            declaredField.set(null, Boolean.valueOf(true));
            return true;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return false;
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            return false;
        }
    }
}
