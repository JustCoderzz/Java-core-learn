package com.lusir.Concurrent;

/**
 * @author lusir
 * @date 2022/4/5 - 16:39
 **/
public class PrintABC {

    public static void main(String[] args) {
        WaitNotify waitNotify=new WaitNotify(5,1);
        new Thread(()->{
            waitNotify.print("a",1,2);
        }).start();
        new Thread(()->{
            waitNotify.print("b",2,3);
        }).start();
        new Thread(()->{
            waitNotify.print("c",3,1);
        }).start();
    }

   static class WaitNotify {
        private int loopNumber;
        private int flag;

        WaitNotify(int loopNumber,int flag) {
            this.loopNumber=loopNumber;
            this.flag=flag;
        }

        public void print(String str,int waitFlag,int nextFlag) {
            for (int i = 0; i < loopNumber; i++) {
                synchronized (this) {
                   while (flag!=waitFlag) {
                       try {
                           this.wait();
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
                    System.out.println(str);
                   flag=nextFlag;
                   this.notifyAll();
                }
            }
        }
    }
}
