package com.lusir.JVM;

/**
 * @author lusir
 * @date 2022/4/1 - 20:01
 **/
public class _ThreadLocal {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal=new ThreadLocal();
        threadLocal.set("123");
    }
}
