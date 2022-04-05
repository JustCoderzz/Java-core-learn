package com.lusir.Concurrent;

/**
 * @author lusir
 * @date 2022/4/4 - 12:50
 **/
public class StateTest {
    public static void main(String[] args) {
        Object lock=new Object();
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                System.out.println("running...");
            }
        };

        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                while (true) {

                }
            }
        };

        Thread t3 = new Thread("t3") {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread t4 = new Thread("t4") {
            @Override
            public void run() {
                try {
                    t3.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t5 = new Thread("t5") {
            @Override
            public void run() {
                synchronized (lock) {

                }
            }
        };
        Thread t6 = new Thread("t6") {
            @Override
            public void run() {
                System.out.println();
            }
        };
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        System.out.println("-----------------");

        System.out.println(t1.getState());
        System.out.println(t2.getState());
        System.out.println(t3.getState());
        System.out.println(t4.getState());
        System.out.println(t5.getState());
        System.out.println(t6.getState());
    }
}
