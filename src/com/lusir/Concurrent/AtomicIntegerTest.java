package com.lusir.Concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lusir
 * @date 2022/4/8 - 14:08
 **/
public class AtomicIntegerTest {
    public static void main(String[] args) {
        Account account=new Account(100);
        for (int i=0;i<20;i++) {
            new Thread(()->{
                account.withDraw(5);
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(account.getBalance());
    }


  static   class Account {
        AtomicInteger balance;

        Account(int c) {
            this.balance=new AtomicInteger(c);
        }

        public int getBalance() {
            return balance.get();
        }

        public  void withDraw(Integer amount) {
//            while (true) {
//                int pre=balance.get();
//                int next=pre-amount;
//                if(balance.compareAndSet(pre,next)){
//                    break;
//                }
//            }
            balance.getAndAdd(-1*amount);
        }
    }
}
