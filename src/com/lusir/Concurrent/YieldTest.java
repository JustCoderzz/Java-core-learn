package com.lusir.Concurrent;

/**
 * @author lusir
 * @date 2022/4/3 - 17:02
 **/
public class YieldTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            int count = 0;
            while (true) {
                count++;
                System.out.println("t1----------------------"+count);
            }
        });
        Thread t2 = new Thread(() -> {
            int count = 0;
            while (true) {
//                Thread.yield();
                count++;
                System.out.println("t2---"+count);
            }
        });
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);
        t1.start();
        t2.start();

    }
}
