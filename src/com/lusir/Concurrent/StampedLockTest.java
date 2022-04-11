package com.lusir.Concurrent;

import java.util.concurrent.locks.StampedLock;

/**
 * @author lusir
 * @date 2022/4/11 - 15:39
 **/
public class StampedLockTest {

    public static void main(String[] args) {
        DataContainer dataContainer=new DataContainer(1);
        new Thread(()->{
            try {
                dataContainer.read(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                dataContainer.read(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
class  DataContainer {
//StampedLock是一种在读取共享变量的过程中，允许后面的一个线程获取写锁对共享变量进行写操作，
// 使用乐观读避免数据不一致的问题，并且在读多写少的高并发环境下，比ReadWriteLock更快的一种锁。

    int data;
    private StampedLock rw=new StampedLock();

    public DataContainer(int data) {
        this.data = data;
    }
    public void  write(int newData) throws Exception{
        long stamp = rw.writeLock();
        System.out.println(stamp);
        try {
            Thread.sleep(1000);
            this.data=newData;
        }finally {
            rw.unlockWrite(stamp);
        }
    }

    public int read(int readTime) throws InterruptedException {
        long stamp = rw.tryOptimisticRead();
        System.out.println(stamp);
        Thread.sleep(readTime);
        if (rw.validate(stamp)) {
            System.out.println("read finish....");
            System.out.println(data);
        }
        try {
            stamp = rw.readLock();
            System.out.println(stamp);
            Thread.sleep(readTime);
            return data;
        }finally {
            rw.unlockRead(stamp);
        }
    }
}