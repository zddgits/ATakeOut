package com.zd.takeout.common;

public class BaseContext {
    private static ThreadLocal<Long> threadLocal= new ThreadLocal<>();
    public static void serCurrentId(Long id) {
        threadLocal.set(id);
    }
    public static Long getCurrentId() {
        return threadLocal.get();
    }
}
