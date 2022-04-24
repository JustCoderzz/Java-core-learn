package com.lusir.JVM;

import sun.misc.Unsafe;

/**
 * @author lusir
 * @date 2022/4/14 - 22:56
 **/
public class GCRootTest {
    public static void main(String[] args) {


    }

}
class MyRoots {
    static String str;//静态属性引用的对象
    final static String STR=new String("123");//常量引用的对象
    MyRoots(){

    }

    void set(String s){
//        虚拟机栈中引用的对象
        Integer i=1;
        str=s;

//        Unsafe包下的Native方法引用的对象
//public native int getInt(Object var1, long var2);
    }
}
