package com.lusir.Concurrent;

/**
 * @author lusir
 * @date 2022/4/5 - 15:14
 **/
public class GuardeObject {
    public static void main(String[] args) {
        GuardeObject dto=new GuardeObject();
        new Thread(()->{
            System.out.println("获取结果");
            Object o = dto.get();
            System.out.println("拿到结果");

        }).start();

        new Thread(()->{
            System.out.println("设置结果");
            dto.complete(new Object());

        }).start();

    }
    private Object response;

    public Object get(){
        synchronized (this) {
            while (response==null) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return this.response;
    }

    public void  complete(Object response) {
        synchronized (this) {
            this.response=response;
            this.notifyAll();
        }

    }
}
