package com.lusir.Concurrent;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author lusir
 * @date 2022/4/10 - 15:41
 **/
public class ScheduleExecutorServiceTest {
    public static void main(String[] args) throws Exception{
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
//        service.schedule(()->{
//            System.out.println("1秒后执行");
//        },1, TimeUnit.SECONDS);

//        service.scheduleAtFixedRate(()->{
//            System.out.println("running...");
//        },1,1,TimeUnit.SECONDS);
//
//        service.scheduleWithFixedDelay(()->{
//            System.out.println("running...");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        },1,1,TimeUnit.SECONDS);


//        让每周四18：00：00 执行定时任务
        LocalDateTime now=LocalDateTime.now();
        System.out.println(now);
        LocalDateTime time=now.withHour(18).withMinute(0).withSecond(0).withNano(0).with(DayOfWeek.THURSDAY);
        System.out.println(time);
        if (now.compareTo(time)>0) {
            time=time.plusWeeks(1);
        }
        System.out.println(time);
        long initDelay = Duration.between(now, time).toMillis();
        long period=1000*60*60*24*7;
        service.scheduleAtFixedRate(()->{
            System.out.println("running...");
        },initDelay,period,TimeUnit.MILLISECONDS);

    }
}
