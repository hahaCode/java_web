package com.fan.test.netty;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class TestFileChannel {

    /**
     * 两个channel之间数据传输
     */
    @Test
    public void test1() {
        try (
                FileChannel from = new FileInputStream("data.txt").getChannel();
                FileChannel to = new FileOutputStream("to.txt").getChannel()
        ) {
            long size = from.size();
            for(long content = size; content > 0; ){  // content表示还剩多少没有传输的
                // 效率高, 底层会利用操作系统的零拷贝进行优化   一次只能传输2g的内容
                content -= from.transferTo((size-content), size, to);  //大于两个G多次传输
            }

        } catch (IOException e) {
        }
    }
}
