package com.lusir.Concurrent;

import java.util.concurrent.locks.LockSupport;

/**
 * @author lusir
 * @date 2022/4/5 - 15:59
 **/
public class LockSupportTest {
    public static void main(String[] args) {
        LockSupport.park();
        LockSupport.unpark(Thread.currentThread());
        System.out.println(1111);
    }
}
