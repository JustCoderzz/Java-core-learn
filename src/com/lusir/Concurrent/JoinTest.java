package com.lusir.Concurrent;

/**
 * @author lusir
 * @date 2022/4/4 - 10:18
 **/
public class JoinTest {
    static int r1=0;
    static  int r2=0;
    public static void main(String[] args) throws InterruptedException {

        System.out.println("开始");
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                r1=10;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(2000);
                r2=20;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        t1.start();
        t2.start();

        t1.join();
        System.out.println(r2);
        t2.join();
        System.out.println(r2);
        System.out.println(r1);

    }
}
