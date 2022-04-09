package com.lusir.Concurrent;

/**
 * @author lusir
 * @date 2022/4/8 - 13:48
 **/
public class SingletonTest {

}
final class  Singleton {
//    双重检查
//    private  static volatile   Singleton INSTANCE;
//    public Singleton (){};
//
//    public Singleton getInstance(){
//        if (INSTANCE==null) {
//            synchronized (Singleton.class) {
//                if (INSTANCE==null) {
//                    INSTANCE=new Singleton();
//                }
//            }
//        }
//        return INSTANCE;
//    }

//    静态内部类
    private  static  class  InstanceHolder {
        private static  Singleton instance=new Singleton();
}
    public static  Singleton getInstance(){
        return InstanceHolder.instance;
    }


}