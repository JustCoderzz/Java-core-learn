package com.lusir.Concurrent;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lusir
 * @date 2022/4/9 - 14:12
 **/
public class ThreadPoolTest {



}

class ThreadPool {
    private BlockingQueue<Runnable> taskQueue;
    private Set<Worker> workers=new HashSet<>();
    private  int coreSize;
    private long timeout;
    private TimeUnit timeUnit;

    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit,int capcity) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.taskQueue=new BlockingQueue<>(capcity);
    }

    class Worker{

    }
}

class  BlockingQueue<T> {

    private Deque<T> queue = new ArrayDeque<>();

    private ReentrantLock lock=new ReentrantLock();

    private Condition fullCondition = lock.newCondition();

    private Condition emptyCondition=lock.newCondition();

    private int capcity;

    BlockingQueue(int size) {
        this.capcity=size;
    }
//    阻塞获取
    public T take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    emptyCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.pollFirst();
            fullCondition.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }

    public  void put(T element) {
        lock.lock();
        try {
            while (queue.size()==capcity) {
                try {
                    fullCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(element);
            emptyCondition.signal();
        }finally {
            lock.unlock();
        }
    }

    public  int getSize() {
        lock.lock();
        try {
            return queue.size();
        }finally {
            lock.unlock();
        }
    }

    public T poll(long time, TimeUnit timeUnit) {
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(time);
            while (queue.isEmpty()) {
                try {
                    if (nanos<=0) return null;
                    nanos = emptyCondition.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.pollFirst();
            fullCondition.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }
}
