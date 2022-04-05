package com.lusir.Concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lusir
 * @date 2022/4/5 - 19:00
 **/
public class ReentrantLockTest {
    private final static ReentrantLock lock=new ReentrantLock();

    public static void main(String[] args) {
//      可重入的锁
        lock.lock();
        try {
            System.out.println("我是主方法");
            method1();
        }finally {
            lock.unlock();
        }
//        以可打断的形式进行加锁  需要捕获打断异常  这样提供了打断的机制就可以防止死锁
//        lock.lockInterruptibly();

    }

  static  void method1(){
      lock.lock();
      try {
          System.out.println("我是第一个方法");
          method2();
      }finally {
          lock.unlock();
      }
    }
    static  void method2(){
        lock.lock();
        try {
            System.out.println("我是第二个方法");
        }finally {
            lock.unlock();
        }
    }
}
