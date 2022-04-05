package com.lusir.Concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lusir
 * @date 2022/4/5 - 19:24
 **/
public class PhilosopherTest {
    public static void main(String[] args) {

        Chopstick c1=new Chopstick("1");
        Chopstick c2=new Chopstick("2");
        Chopstick c3=new Chopstick("3");
        Chopstick c4=new Chopstick("4");
        Chopstick c5=new Chopstick("5");

        Philosopher m1=new Philosopher(c1,c2,"张三");
        Philosopher m2=new Philosopher(c2,c3,"李四");
        Philosopher m3=new Philosopher(c3,c4,"王五");
        Philosopher m4=new Philosopher(c4,c5,"赵六");
        Philosopher m5=new Philosopher(c5,c1,"山八");
        m1.start();
        m2.start();
        m3.start();
        m4.start();
        m5.start();

    }

  static   class Philosopher extends Thread {
        Chopstick left;
        Chopstick right;
        String name;

        public Philosopher(Chopstick left, Chopstick right, String name) {
            this.left = left;
            this.right = right;
            this.name = name;
        }

        @Override
        public void run() {
            while (true) {
                if (left.tryLock()){
                    try {
                        if (right.tryLock()) {
                            try {
                                eat();
                            }finally {
                                right.unlock();
                            }
                        }
                    }finally {
                        left.unlock();
                    }
                }
            }
        }

        void eat(){
            System.out.println(this.name+"eating ......");
        }

    }


  static   class Chopstick extends ReentrantLock {
        String name;

        Chopstick(String name) {
            this.name=name;
        }
    }
}
