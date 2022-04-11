package com.lusir.Concurrent;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lusir
 * @date 2022/4/9 - 22:35
 **/
public class ThreadPoolExecutorsTest {
    public static void main(String[] args) throws Exception{
//        ExecutorService service = Executors.newFixedThreadPool(2, new ThreadFactory() {
//            private AtomicInteger t = new AtomicInteger(1);
//
//            @Override
//            public Thread newThread(Runnable r) {
//                return new Thread(r, "myPool" + t.getAndIncrement());
//            }
//        });
        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Integer> submit1 = service.submit(() -> {

            Thread.sleep(1000);
            System.out.println("1----------");
            return 1;
        });
        Future<Integer> submit2 = service.submit(() -> {
            Thread.sleep(2000);
            System.out.println("2----------");
            return 2;
        });
        Future<Integer> submit3 = service.submit(() -> {
            System.out.println("3--------");
            Thread.sleep(3000);
            return 3;
        });

        Thread.sleep(1000);
        List<Runnable> runnables = service.shutdownNow();
        runnables.forEach((item)->{
            System.out.println(item);
        });
//        System.out.println(submit1.get());
//        System.out.println(submit2.get());
//        System.out.println(submit3.get());
    }
}
