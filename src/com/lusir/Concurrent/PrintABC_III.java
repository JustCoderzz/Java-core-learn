package com.lusir.Concurrent;

import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lusir
 * @date 2022/4/5 - 20:46
 **/
public class PrintABC_III {
//    用ReentrantLock实现
    public static void main(String[] args) {
        AwaitSignal awaitSignal=new AwaitSignal(5);
        Condition a=awaitSignal.newCondition();
        Condition b=awaitSignal.newCondition();
        Condition c=awaitSignal.newCondition();
        new Thread(()->{
            awaitSignal.print("a",a,b);
        }).start();

        new Thread(()->{
            awaitSignal.print("b",b,c);
        }).start();

        new Thread(()->{
            awaitSignal.print("c",c,a);
        }).start();
        awaitSignal.lock();
        try {
            a.signal();
        }finally {
            awaitSignal.unlock();
        }
    }
  static   class AwaitSignal extends ReentrantLock {
        private  int loopNumber;

        public AwaitSignal(int loopNumber) {
            this.loopNumber=loopNumber;
        }

        public void print(String str,Condition cur,Condition next) {
            for (int i=0;i<loopNumber;i++) {
                lock();
                try {
                    cur.await();
                    System.out.println(str);
                    next.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                unlock();
            }
        }
    }

}


