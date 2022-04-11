package com.lusir.Concurrent;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lusir
 * @date 2022/4/9 - 14:12
 **/
public class ThreadPoolTest {
    public static void main(String[] args) {
        ThreadPool threadPool=new ThreadPool(2,1000,TimeUnit.MICROSECONDS,10,((queue, task) -> {
//             1.死等
//            queue.put(task);
//            2.带超时等待
//            queue.offer(task,1000,TimeUnit.MICROSECONDS);
//            3.让调用者放弃
//            System.out.println("放弃了");
//            4.让调用者抛出异常
//            throw new RuntimeException("任务执行失败"+task);
//            5.让调用者自己执行任务
//            task.run();
        }));
        for (int i = 0; i < 15; i++) {
            int j=i;
            threadPool.execute(()->{
                try {
                    System.out.println("线程"+j);
                    Thread.sleep(1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }
    }


}
@FunctionalInterface
interface RejectPolicy<T>{
    void reject(BlockingQueue<T> queue,T t);
}
class ThreadPool {
    private BlockingQueue<Runnable> taskQueue;
    private Set<Worker> workers=new HashSet<>();
    private  int coreSize;
    private long timeout;
    private TimeUnit timeUnit;
    private  RejectPolicy rejectPolicy;

    public void execute(Runnable task) {
        synchronized (workers) {
            if (workers.size()<coreSize) {
                Worker worker=new Worker(task);
                workers.add(worker);
                worker.start();
            }else{
//                taskQueue.put(task);
                taskQueue.tryPut(rejectPolicy,task);
            }

        }

    }

    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit,int capcity,RejectPolicy rejectPolicy) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.taskQueue=new BlockingQueue<>(capcity);
        this.rejectPolicy=rejectPolicy;
    }

    class Worker extends Thread{

        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            while (task!=null||(task=taskQueue.poll(1000,TimeUnit.MICROSECONDS))!=null) {
                 try {
                     task.run();
                 }catch (Exception e) {
                     e.printStackTrace();
                 }finally {
                     task=null;
                 }
            }
            synchronized (workers){
                workers.remove(this);
            }
        }
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
                    System.out.println("等待加入队列");
                    fullCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(element);
            System.out.println("加入任务队列，剩余容量"+(capcity-getSize()));
            emptyCondition.signal();
        }finally {
            lock.unlock();
        }
    }
    public boolean offer(T task,long time,TimeUnit timeUnit) {
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(time);
            while (queue.size()==capcity) {
                try {
                    System.out.println("等待加入队列");
                    if (nanos<=0) return false;
                   nanos= fullCondition.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(task);
            System.out.println("加入任务队列，剩余容量"+(capcity-getSize()));
            emptyCondition.signal();
            return true;
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

    public void tryPut(RejectPolicy rejectPolicy, T task) {
        lock.lock();
        try {
            if (queue.size()==capcity){
                rejectPolicy.reject(this,task);
            }else {
                queue.addLast(task);
                emptyCondition.signal();
            }
        }finally {
            lock.unlock();
        }
    }
}
