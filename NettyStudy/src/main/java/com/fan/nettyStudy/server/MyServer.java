package com.fan.nettyStudy.server;

import com.fan.nettyStudy.utils.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * 阻塞模式
 * 单线程
 */
@Slf4j
public class MyServer {
    public static void main(String[] args) throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(16);

        // 1.创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();

        // 2. 绑定监听端口
        ssc.bind(new InetSocketAddress(8080));


        List<SocketChannel> channelList = new ArrayList<>();
        while (true) {
            // socketChannel 用于与客户端通信
            // 3. 建立与客户端的连接
            log.debug("connecting....");
            SocketChannel socketChannel = ssc.accept();  //阻塞方法 线程暂停
            log.debug("connected.... {}", socketChannel);
            channelList.add(socketChannel);

            // 4. 接收客户端发送的数据
            for (SocketChannel channel : channelList) {
                log.debug("before read.... {}", channel);
                channel.read(buffer); // 阻塞方法
                buffer.flip();
                ByteBufferUtil.debugAll(buffer);
                buffer.clear();
                log.debug("after read.... {}", channel);
            }
        }
    }
}
