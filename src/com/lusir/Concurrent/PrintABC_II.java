package com.lusir.Concurrent;

import java.lang.reflect.Field;
import java.util.concurrent.locks.LockSupport;

/**
 * @author lusir
 * @date 2022/4/5 - 21:15
 **/
public class PrintABC_II {
   static Thread t1;
   static Thread t2;
   static Thread t3;
    public static void main(String[] args) {
        ParkUnpark parkUnpark=new ParkUnpark(5);
        t1 = new Thread(() -> {
            parkUnpark.print("a",t2);
        });
        t2 = new Thread(() -> {
            parkUnpark.print("b",t3);
        });
        t3 = new Thread(() -> {
            parkUnpark.print("c",t1);
        });
        t1.start();
        t2.start();
        t3.start();
        LockSupport.unpark(t1);


    }

  static   class ParkUnpark {
        private int loopNumber;

        ParkUnpark(int loopNumber) {
            this.loopNumber=loopNumber;
        }

        void print(String str,Thread thread) {
            for (int i = 0; i < loopNumber; i++) {
                LockSupport.park();
                System.out.println(str);
                LockSupport.unpark(thread);
            }
        }
    }
}
