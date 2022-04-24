package com.lusir.JavaSE;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;

/**
 * @author lusir
 * @date 2022/4/24 - 15:48
 **/
public class DesignPatterns {
    public static void main(String[] args) {
//        InputStream
//        FileInputStream
        People people = new People.Builder("张三", 18)
                .Age(12)
                .Name("王五")
                .School("shun")
                .build();
        System.out.println(people.toString());


    }




}
//建造器模式
class People {

    private  final   String name;
    private  final  int age;
    private  final  String school;

    private People(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.school = builder.school;
    }

    static class  Builder{

        private String name;
        private int age;
        private String school;
        Builder(String name,int age) {
            this.name=name;
            this.age=age;
        }

        Builder Name(String name) {
            this.name=name;
            return this;
        }
        Builder Age(int age) {
            this.age=age;
            return this;
        }
        Builder School(String school) {
            this.school=school;
            return this;
        }
      public   People build(){
            return new People(this);
        }
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", school='" + school + '\'' +
                '}';
    }
}