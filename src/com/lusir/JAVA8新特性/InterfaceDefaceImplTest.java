package com.lusir.JAVA8新特性;

/**
 * @author lusir
 * @date 2022/4/19 - 13:13
 **/
public interface InterfaceDefaceImplTest {
//    int add(int a ,int b);
    default int add(int a,int b) {
        return a+b;
    }
}
