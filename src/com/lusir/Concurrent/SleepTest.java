package com.lusir.Concurrent;

import java.util.Currency;

/**
 * @author lusir
 * @date 2022/4/5 - 16:25
 **/
public class SleepTest {
    public static void main(String[] args) {
      Thread t1=  new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getState());
                e.printStackTrace();
            }
        },"t1");
      t1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t1.getState());
        t1.interrupt();
        System.out.println();

    }
}
