package com.fan.test.netty;

import com.fan.nettyStudy.utils.ByteBufferUtil;
import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class TestByteBufferReadWrite {
    @Test
    public void test1() {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        buffer.put((byte) 0x61);  // 'a'

        //调用工具类
        ByteBufferUtil.debugAll(buffer);

        buffer.put(new byte[]{0x62, 0x63, 0x64}); //'b' , 'c', 'd'
        ByteBufferUtil.debugAll(buffer);
        //不切换读模式就读取
        //System.out.println(buffer.get());

        //切换读模式
        System.out.println("@@@@@@@@@@@@------flip------@@@@@@@");
        buffer.flip();
        System.out.println(buffer.get());
        ByteBufferUtil.debugAll(buffer);

        //切换至写模式
        System.out.println("@@@@@@@@@@@@------compact------@@@@@@@");
        buffer.compact();
        ByteBufferUtil.debugAll(buffer);

        buffer.put(new byte[]{0x65, 0x66, 0x67});
        ByteBufferUtil.debugAll(buffer);
    }

    @Test
    public void testRead() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{'a', 'b', 'c', 'd'});
        buffer.flip();

        //从头读取
//        buffer.get(new byte[3]);
//        ByteBufferUtil.debugAll(buffer);

//        System.out.println("------------------------------------------");
//        buffer.rewind();   // 把position置为0, 从头读
//        buffer.get(new byte[3]);
//        ByteBufferUtil.debugAll(buffer);

        //获取指定位置的内容, 但position指针不变
        System.out.println((char) buffer.get(3));
        ByteBufferUtil.debugAll(buffer);

        // mark & rest
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        buffer.mark(); //对索引为2的标记加索引
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        buffer.reset(); //将position的位置到索引标志的位置
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
    }

    /**
     * 分段读取
     */
    @Test
    public void testScatteringReads() {
        try (FileChannel channel = new RandomAccessFile("word.txt", "r").getChannel()) {

            ByteBuffer b1 = ByteBuffer.allocate(3);
            ByteBuffer b2 = ByteBuffer.allocate(3);
            ByteBuffer b3 = ByteBuffer.allocate(5);

            channel.read(new ByteBuffer[] {b1, b2, b3});

            ByteBufferUtil.debugAll(b1);
            ByteBufferUtil.debugAll(b2);
            ByteBufferUtil.debugAll(b3);
        } catch (IOException e) {

        };
    }


    /**
     * 集中写入
     */
    @Test
    public void testGatheringWrites() {
        ByteBuffer b1 = StandardCharsets.UTF_8.encode("hello");
        ByteBuffer b2 = StandardCharsets.UTF_8.encode("world");
        ByteBuffer b3 = StandardCharsets.UTF_8.encode("你好");

        try (FileChannel channel = new RandomAccessFile("word2.txt", "rw").getChannel()) {
            channel.write(new ByteBuffer[] {b1, b2, b3});
        } catch (IOException e) {
        }
    }
}
