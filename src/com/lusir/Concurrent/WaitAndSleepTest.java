package com.lusir.Concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author lusir
 * @date 2022/4/5 - 14:32
 **/
public class WaitAndSleepTest {
    static final Object lock=new Object();
    public static void main(String[] args) {
        new Thread(()->{
            synchronized (lock){
                System.out.println("获得锁");
                try {
//                    Thread.sleep(20000);
//                    sleep不会释放锁  wait会释放锁
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (lock) {
            System.out.println("主线程获得锁");
        }
    }
}
