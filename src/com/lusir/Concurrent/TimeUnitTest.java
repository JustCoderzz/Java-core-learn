package com.lusir.Concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author lusir
 * @date 2022/4/3 - 16:58
 **/
public class TimeUnitTest {
    public static void main(String[] args) {
        try {
            TimeUnit.SECONDS.sleep(1000);
            TimeUnit.HOURS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
