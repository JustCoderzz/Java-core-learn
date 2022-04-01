package com.lusir.JVM;

import java.util.Map;

/**
 * @author lusir
 * @date 2022/4/1 - 19:15
 **/
public class _PhantomReference {
    public static void main(String[] args) {

    }
    static class M {
        @Override
        protected void finalize() throws Throwable {
            System.out.println(111);

        }
    }
}
