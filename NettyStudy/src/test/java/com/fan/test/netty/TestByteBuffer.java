package com.fan.test.netty;


import com.fan.nettyStudy.utils.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

@Slf4j
public class TestByteBuffer {

    @Test
    public void test1 () {
        try (FileChannel channel = new FileInputStream("data.txt").getChannel()) {
            //准备缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while (true) {
                //从channel读, 并写入buffer
                int len = channel.read(buffer);
                log.debug("读取到的字节数: {}", len);
                if(len == -1) //没有内容了
                    break;

                buffer.flip(); //切换至读模式

                //是否还有剩余的未读数据
                while (buffer.hasRemaining()) {
                    byte b = buffer.get();
                    log.debug("读取到的内容: {}", (char) b);
                }
                buffer.clear();  //切换至写模式
            }
        } catch (IOException e) {
        }
    }

    @Test
    public void testAllocate() {
        System.out.println(ByteBuffer.allocate(16).getClass()); // 在java堆内存中分配, 读写效率较低, 会受到GC的影响
        System.out.println(ByteBuffer.allocateDirect(16).getClass()); // 直接内存, 读写效率高(少一次拷贝) 不会受到GC的影响, 使用操作系统的函数分配, 分配的效率较低
    }


    /**
     *  字符串与ByteBuffer的转换
     */
    @Test
    public void test2() {
        // 1. 字符串转为ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put("hello".getBytes());
        ByteBufferUtil.debugAll(buffer); //这种方式是没有切换到读模式的
        //System.out.println(buffer.get());

        // 2. 用Charset 字符串转为ByteBuffer
        ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("world");
        ByteBufferUtil.debugAll(buffer2); //这种是写入之后直接切换到了写模式
        System.out.println( (char) buffer2.get());

        // 3.wrap 字符串转为ByteBuffer
        ByteBuffer buffer3 = ByteBuffer.wrap("test".getBytes());
        ByteBufferUtil.debugAll(buffer3);  //这种是写入之后直接切换到了写模式
        System.out.println( (char) buffer3.get());

        // 4. ByteBuffer转为String
        String s2 = StandardCharsets.UTF_8.decode(buffer2).toString();
        System.out.println(s2);

        buffer.flip();
        String s1 = StandardCharsets.UTF_8.decode(buffer).toString();
        System.out.println(s1);
    }
}
