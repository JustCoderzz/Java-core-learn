package com.lusir.Concurrent;

/**
 * @author lusir
 * @date 2022/4/4 - 10:44
 **/
public class TwoPhaseTermination {
    public static void main(String[] args) {

        TwoPhaseTerminationTest test=new TwoPhaseTerminationTest();
        test.start();

        try {
            Thread.sleep(3500);
            test.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

  static   class TwoPhaseTerminationTest {
        private Thread thread;

        public void start() {
            thread=new Thread(()->{
                while (true) {
                    Thread thread = Thread.currentThread();
                    if (thread.isInterrupted()){
                        System.out.println("料理后事");
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                        System.out.println("执行监控操作");
                    } catch (InterruptedException e) {
//                        正常运行时被打断  无需额外处理  因为打断标记不会被重置
//                        interrupted()方法会重置打断标记  会重置为假
                        e.printStackTrace();
                        thread.interrupt();
                    }
                }
            });
            thread.start();
        }

        public void stop() {
            thread.interrupt();

            System.out.println("打断。。。。");
        }

    }
}
