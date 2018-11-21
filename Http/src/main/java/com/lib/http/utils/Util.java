package com.lib.http.utils;

import java.io.Closeable;

/**
 * Created by thkcheng on 2018/11/21.
 */

public class Util {

    private Util() {
    }

    /**
     * 确保作为参数传递给调用方法的对象引用不为空.
     *
     * @param reference an object reference
     * @return the non-null reference that was validated
     * @throws NullPointerException if {@code reference} is null
     */
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException("reference == null");
        }
        return reference;
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception ignored) {
            }
        }
    }

    public static String convert(Object value) {
        if (value == null) {
            return "";
        }
        return value.toString();
    }

}
