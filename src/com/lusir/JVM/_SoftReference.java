package com.lusir.JVM;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * @author lusir
 * @date 2022/4/1 - 18:53
 **/
public class _SoftReference {
    public static void main(String[] args) {
        SoftReference<M> m=new SoftReference<>(new M(), new ReferenceQueue<M>());

        System.out.println(m.get());
        System.gc();
        try {
            Thread.sleep(500);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(m.get());

//      再分配一个数组，heap装不下，系统会会垃圾回收，先回收一次，如果不够，会把软引用干掉
        byte[]b=new byte[1024*1024*10];
        System.out.println(m.get());
//        [B@4554617c
//        [B@4554617c
//        null
    }
    static class M {
        @Override
        protected void finalize() throws Throwable {
            System.out.println(111);
        }
    }
}
