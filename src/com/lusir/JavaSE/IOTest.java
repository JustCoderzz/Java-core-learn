package com.lusir.JavaSE;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.spi.FileSystemProvider;

/**
 * @author lusir
 * @date 2022/4/23 - 20:42
 **/
public class IOTest {
    public static void main(String[] args) {
//        405
//------------------------------
//        52
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            IOTest.copyFileByStream(new File("test.txt"),new File("dest1.txt"));
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        System.out.println("------------------------------");
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            IOTest.copyByChannel(new File("test,txt"),new File("dest2.txt"));
        }
        long end1 = System.currentTimeMillis();
        System.out.println(end1-start1);

        System.out.println("--------------------------------------");

    }

    /**
     *    基于FileInputStream的文件拷贝
     * @param source    拷贝目标位置
     * @param dest     拷贝文件位置
     */

    public static void copyFileByStream(File source,File dest)  {
        try(InputStream in=new FileInputStream(source)){
            OutputStream out=new FileOutputStream(dest);
            byte[] buffer=new byte[1024];
            int length;
            while ((length=in.read(buffer))>0){
                out.write(buffer,0,length);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public  static void copyByChannel(File source,File dest) {
        try(FileChannel in=new FileInputStream(source).getChannel();
            FileChannel out=new FileOutputStream(dest).getChannel();
        ) {
            for (long count=in.size();count>0;){
                long transferLen = in.transferTo(in.position(), count, out);
                count-=transferLen;
            }

        }catch (IOException e){

        }

    }
}
