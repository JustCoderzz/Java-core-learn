package com.lusir.JavaSE;

import com.sun.xml.internal.ws.api.model.CheckedException;
import com.sun.xml.internal.ws.model.CheckedExceptionImpl;
import sun.misc.Cleaner;

import java.io.UncheckedIOException;
import java.lang.reflect.Proxy;

/**
 * @author lusir
 * @date 2022/4/13 - 18:23
 **/
public class ThrowableTest {
    public static void main(String[] args) {
//        Exception e=new RuntimeException();
//        Error error=new Error();
//        CheckedException checkedException=new CheckedExceptionImpl();
//        UncheckedIOException uncheckedIOException=new UncheckedIOException();
//        OutOfMemoryError
//        Cleaner

        Test t1 = new Test();
        Test t2=new Test();
        t1.set();
        System.out.println(t2.get());

    }




}
class Test {
    static int num = 1;

    void set() {
        num++;
    }
    int get() {
       return num;
    }
}
