package com.lusir.Concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author lusir
 * @date 2022/4/6 - 15:59
 **/
public class VolatileTest {
    //    static volatile   boolean flag;
    static boolean flag;
    static final Object lock = new Object();

    public static void main(String[] args) {

//        使用volatile
        new Thread(() -> {
            while (!flag) {
//                System.out.println(flag);
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//              JVM会尽力保证内存的可见性，即便这个变量没有加同步关键字。换句话说，只要CPU有时间，JVM会尽力去保证变量值的更新。
//              这种与volatile关键字的不同在于，volatile关键字会强制的保证线程的可见性。而不加这个关键字，JVM也会尽力去保证可见性，但是如果CPU一直有其他的事情在处理，
//              它也没办法。最开始的代码，一直处于试了循环中，CPU处于一直被饱受占用的时候，这个时候CPU没有时间，JVM也不能强制要求CPU分点时间去取最新的变量值。
//              而加了System.out.println之后，由于内部代码的同步关键字的存在，导致CPU的输出其实是比较耗时的。这个时候CPU就有可能有时间去保证内存的可见性，于是while循环可以被终止。
//              其实，也可以在while循环里面加上sleep，让run方法放弃cpu，但是不放弃锁，这个时候由于CPU有空闲的时候就去按照JVM的要求去保证内存的可见性。如下图所示。
//              run方法里面休息了20毫秒，cpu有充足的空闲时间去取变量的最新值，所以循环也能停止了。
//

            }

            System.out.println("跳出循环");
        }).start();

        try {
            Thread.sleep(1000);
            flag = true;
            System.out.println("设置条件变量");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        使用synchronized
//        new Thread(()->{
//             while (true) {
//                 synchronized (lock)  {
//                     if (flag){
//                         System.out.println("跳出循环");
//                         break;
//                     }
//                 }
//
//             }
//            System.out.println();;
//        }).start();
//
//        try {
//            Thread.sleep(1000);
//            synchronized (lock) {
//                flag=true;
//                System.out.println("设置标志");
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }
}
