package com.lusir.JavaSE;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author lusir
 * @date 2022/4/23 - 10:45
 **/
public class MapTest {
    public static void main(String[] args) {

        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(16, 0.75F, false) {

            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size() > 3;
            }
        };
        map.put("Project1", "Valhalla");
        map.put("Project2", "Panama");
        map.put("Project3", "Loom");
        map.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        }); // 模拟访问
        map.get("Project1");
        map.get("Project1");
        map.get("Project3");
        System.out.println("Iterate over should be not affected:");
        map.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        }); // 触发删除
        map.put("Project4", "Mission Control");
        System.out.println("Oldest entry should be removed:");
        map.forEach((k, v) -> {// 遍历顺序不变
        System.out.println(k +":" + v); });
//        --------------------------------------------------------------------------

        TreeMap<String,String> treeMap=new TreeMap<>();
        treeMap.put("1","123");
//        compareTo 与   equals 唯一性  不能出现歧义

//        -----------------------------------------------------------------------------
//        HashMap map1=new HashMap();
//        map1.put()



        }
    }
