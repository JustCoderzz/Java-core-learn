package com.lusir.Concurrent;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author lusir
 * @date 2022/4/10 - 20:41
 **/
public class ReentrantReadWriteLockTest {
    public static void main(String[] args) {

    }
    class CacheData{
        Object data;
        volatile  boolean cacheValid;
        final ReentrantReadWriteLock rw=new ReentrantReadWriteLock();

        void processCacheData() {
            rw.readLock().lock();
            if (!cacheValid) {
                rw.readLock().unlock();
                rw.writeLock().lock();
                try {
                    if (!cacheValid){
                        data=new Object();
                        cacheValid=true;
                    }
                    rw.readLock().lock();
                }finally {
                    rw.writeLock().unlock();
                }
            }
            try {
                System.out.println("使用数据"+data.toString());
            }finally {
                rw.readLock().unlock();
            }
        }
    }
}
