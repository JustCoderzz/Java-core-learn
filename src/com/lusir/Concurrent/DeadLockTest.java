package com.lusir.Concurrent;

/**
 * @author lusir
 * @date 2022/4/5 - 16:55
 **/
public class DeadLockTest {


    public static void main(String[] args) {
        final  Object lockA=new Object();
        final  Object lockB=new Object();
        new Thread(()->{
            synchronized (lockA){
                System.out.println("lockA");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockB){

                }
            }
        },"t1").start();
        new Thread(()->{
            synchronized (lockB){
                System.out.println("lockB");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockA){

                }
            }
        },"t2").start();
    }
}
