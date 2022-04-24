package com.lusir.JavaSE;

/**
 * @author lusir
 * @date 2022/4/14 - 22:52
 **/
public class StringTest {
    public static void main(String[] args) {
//        String s1="123";
//        String str=new String("123");
//        String s2=str.intern();
//        String s1="123";
//        System.out.println(s2==str);
//        System.out.println(s1==s2);

//        1.
        String s=new String("12")+new String("34");
        s.intern();
        String s1="1234";
        String s2="12";
        System.out.println(s==s1);

    }
}
