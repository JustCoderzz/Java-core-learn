package com.lusir.JVM;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lusir
 * @date 2022/4/1 - 19:12
 **/
public class _WeakReference {
    public static void main(String[] args) throws InterruptedException {
        WeakReference<M> m=new WeakReference<>(new M());
        System.out.println(m.get());
//        通过这样弱引用就没被垃圾回收
//        Set<M> set=new HashSet<>();
//        M t=m.get();
//        set.add(t);
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
