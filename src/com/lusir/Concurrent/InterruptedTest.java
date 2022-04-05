package com.lusir.Concurrent;

/**
 * @author lusir
 * @date 2022/4/4 - 10:33
 **/
public class InterruptedTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
//            正常运行打断
            while (true) {
                if (Thread.currentThread().isInterrupted() == true) {
                    System.out.println("被打断了！推出循环");
                    break;
                }
            }
        }, "t1");
        t1.start();
        Thread.sleep(1000);
        System.out.println("interrupt");
        t1.interrupt();
    }
}
