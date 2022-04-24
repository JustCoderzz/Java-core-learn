package com.lusir.JavaSE;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * @author lusir
 * @date 2022/4/24 - 12:37
 **/
public class ByteBufferTest {
    public static void main(String[] args) {
        ByteBuffer buffer=ByteBuffer.allocate(4);
        buffer.put(new byte[]{1,2,3,4});

        buffer.flip();
        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
        //重新将position切换至开始读的第一个byte
        buffer.rewind();
        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }

        //

    }
}
