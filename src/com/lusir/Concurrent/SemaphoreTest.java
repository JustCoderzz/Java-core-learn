package com.lusir.Concurrent;

import java.util.HashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * @author lusir
 * @date 2022/4/11 - 15:59
 **/
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore s=new Semaphore(3);

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    s.acquire();
                    System.out.println("begin");
                    System.out.println(1);
                    System.out.println("end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    s.release();
                }
            }).start();
//            CyclicBarrier cyclicBarrier=new CyclicBarrier(2);
//            cyclicBarrier.reset();
        }
        HashMap map=new HashMap();
        map.put()
    }
}
