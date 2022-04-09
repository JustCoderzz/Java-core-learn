package com.lusir.Concurrent;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author lusir
 * @date 2022/4/8 - 15:56
 **/
public class AtomicReferenceTest {
   static AtomicReference<String> atomicReference=new AtomicReference<>("A");
    public static void main(String[] args) {

//      得到之前的值
        String pre=atomicReference.get();

        other();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(atomicReference.compareAndSet(pre,"C")){
            System.out.println(true);
        }


    }
    static void other() {
        new Thread(()->{
            atomicReference.compareAndSet("A","B");
        }).start();
        new Thread(()->{
            atomicReference.compareAndSet("B","A");
        }).start();
    }
}
