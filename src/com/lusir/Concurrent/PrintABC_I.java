package com.lusir.Concurrent;

/**
 * @author lusir
 * @date 2022/4/5 - 21:21
 **/
public class PrintABC_I {

//    这种思路 就是单独抽离出来
    static  int flag=1;
    static  Object lock=new Object();
    public static void main(String[] args) {

        new Thread(()->{
            synchronized (lock){
                while (true){
                    while (flag!=1){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("A");
                    flag=2;
                    lock.notifyAll();
                }

            }
        }).start();

        new Thread(()->{
            synchronized (lock){
                while (true){
                    while (flag!=2){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("B");
                    flag=3;
                    lock.notifyAll();
                }

            }
        }).start();
        new Thread(()->{
            synchronized (lock){
                while (true) {
                    while (flag!=3){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("C");
                    flag=1;
                    lock.notifyAll();
                }

            }
        }).start();

    }
}
