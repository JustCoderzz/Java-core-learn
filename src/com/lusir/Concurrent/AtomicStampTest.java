package com.lusir.Concurrent;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author lusir
 * @date 2022/4/8 - 16:02
 **/
public class AtomicStampTest {
   static AtomicStampedReference<String> atomicStampedReference=new AtomicStampedReference<>("A",0);
    public static void main(String[] args) {

        String pre=atomicStampedReference.getReference();
        int stamp=atomicStampedReference.getStamp();
        other();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(atomicStampedReference.compareAndSet(pre,"C",stamp,stamp+1)){
            System.out.println(true);
        }else System.out.println(false);

    }
    static void other() {
        new Thread(()->{
            int stamp=atomicStampedReference.getStamp();
            atomicStampedReference.compareAndSet("A","B",stamp,stamp+1);
        }).start();
        new Thread(()->{
            int stamp=atomicStampedReference.getStamp();
            atomicStampedReference.compareAndSet("B","A",stamp,stamp+1);
        }).start();
    }
}
