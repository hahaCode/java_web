package com.fan.nettyStudy.server;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Selector
 *
 */
@Slf4j
public class MyServer3 {
    public static void main(String[] args) throws IOException {

        // 1. 创建Selector, 可以管理多个channel
        Selector selector = Selector.open();

        ByteBuffer buffer = ByteBuffer.allocate(16);
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        // 2. 将channel注册到selector上, selectionKey就是将来事件发生时, 可以知道是哪个channel发生的
        SelectionKey sscKey = ssc.register(selector, 0, null);  // 0 表示不关注任何事件
        // 表示sscKey只关注accept事件
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        log.debug("Register key: {}", sscKey);

        ssc.bind(new InetSocketAddress(8080));
        while (true) {
            // 3. select方法, 没有事件发生就阻塞, 有事件发生时线程才会回复运行
            selector.select();  //select 在事件未处理时, 它不会阻塞, 事件发生后要么处理, 要么取消, 不能置之不理

            // 4. 处理事件 selectionKeys 里面包含了所有发生的事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                log.debug("key: {}", key);
                ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                SocketChannel accept = channel.accept();
                log.debug("socket channel: {}", accept);
            }
        }
    }
}

// https://www.bilibili.com/video/BV1py4y1E7oA?p=35&spm_id_from=pageDriver