package com.lusir.JVM;

import java.lang.ref.WeakReference;

/**
 * @author lusir
 * @date 2022/4/1 - 19:12
 **/
public class _WeakReference {
    public static void main(String[] args) throws InterruptedException {
        WeakReference<M> m=new WeakReference<>(new M());
        System.out.println(m.get());
        System.gc();
        Thread.sleep(500);
        System.out.println(m.get());

    }


    static  class  M{
        @Override
        protected void finalize() throws Throwable {
            System.out.println("finalize");
        }
    }

}
