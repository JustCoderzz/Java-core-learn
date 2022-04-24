package com.lusir.JAVA8新特性;

/**
 * @author lusir
 * @date 2022/4/19 - 13:07
 **/
@FunctionalInterface
public interface FunctionInterfaceTest<T> {
    T get();

    default Object set(){
        return new Object();
    }
}
