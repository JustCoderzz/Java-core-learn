package com.lusir.JAVA8新特性;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author lusir
 * @date 2022/4/19 - 13:04
 **/
public class LambdaTest {
    public static void main(String[] args) {

        Consumer consumer=(s)-> System.out.println(s);
        consumer.accept(new Object());
        Consumer<String> consumer1=System.out::println;
        consumer.accept("123");
//---------------------------------------------------------
        Supplier supplier=()->new Object();
        supplier.get();
        Supplier<String> supplier1=String::new;
//---------------------------------------------------------
        FunctionInterfaceTest functionInterfaceTest=()->new Object();
        functionInterfaceTest.get();
//---------------------------------------------------------
        Function<Object,Integer> function=o1->1;
        function.apply(new Object());
        Function<String,MyObj> function1=MyObj::new;
        MyObj zhangsan = function1.apply("zhangsan");
        System.out.println(zhangsan.toString());
//---------------------------------------------------------

        Predicate<String> predicate=(s)->s.length()>0;
        Predicate<List<Integer>> predicate1=List::isEmpty;

    }
    static  class MyObj{
        String name;
        MyObj(String name) {
            this.name=name;
        }

        @Override
        public String toString() {
            return "MyObj{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
