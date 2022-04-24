package com.lusir.JAVA8新特性;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

/**
 * @author lusir
 * @date 2022/4/19 - 13:31
 **/
public class StreamAPITest {
    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
//        stream 流是支持顺序和并行的。顺序流操作是单线程操作，而并行流是通过多线程来处理的，
//        能够充分利用物理机 多核 CPU 的优势，同时处理速度更快
//        顺序流
        Stream<Integer> stream = list.stream();
//        并行流
        Stream<Integer> parallelStream = list.parallelStream();
        System.out.println(stream);
        System.out.println("--------------------");
//        Stream<Integer> integerStream = stream.filter((s) -> s > 80);
//        System.out.println(stream==integerStream);//返回false
//        System.out.println("--------------------");
//        stream.forEach(System.out::println);//遍历
//        stream.sorted((o1,o2)->o2-o1).forEach(System.out::println);//排序
//        stream.map(i->i-i).forEach(System.out::println);//对集合中的元素进行处理
//        注意：foreach 是一个终端操作，它的返参是 void, 我们无法对其再次进行流操作。
//        ----------------------------------------------
        Map<Integer,Integer> map=new HashMap<>();
        map.forEach((key,value)-> System.out.println(value));

//       -----------------------------------------

    }
}
