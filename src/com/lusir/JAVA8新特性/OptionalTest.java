package com.lusir.JAVA8新特性;


import java.util.Optional;

/**
 * @author lusir
 * @date 2022/4/19 - 14:05
 **/
public class OptionalTest {
    public static void main(String[] args) {
        MyObj obj=new MyObj("zhangsan",null);
//        MyObj obj=null;
        Optional<MyObj> option=Optional.ofNullable(obj);
        System.out.println(option.isPresent());
        option.ifPresent((item)->item.name="wamgwu");//当不为空时执行的逻辑
        System.out.println(obj.name);
    }
   static class MyObj {
        String name;
        Integer age;

        public MyObj(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
    }
}
