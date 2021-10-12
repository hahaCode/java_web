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
 * 非阻塞
 * 单线程
 */
@Slf4j
public class MyServer2 {
    public static void main(String[] args) throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(16);

        // 1.创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false); // 设置非阻塞

        // 2. 绑定监听端口
        ssc.bind(new InetSocketAddress(8080));


        List<SocketChannel> channelList = new ArrayList<>();
        while (true) {
            // socketChannel 用于与客户端通信
            // 3. 建立与客户端的连接
            SocketChannel socketChannel = ssc.accept();  // 非阻塞方法 线程继续运行, 如果没有连接建立, socketChannel返回null

            if(socketChannel!=null) {
                log.debug("connected.... {}", socketChannel);
                socketChannel.configureBlocking(false); // 设置非阻塞
                channelList.add(socketChannel);
            }

            // 4. 接收客户端发送的数据
            for (SocketChannel channel : channelList) {
                int read = channel.read(buffer);  // 非阻塞方法 线程继续运行, 如果没有读到数据, read返回0
                if(read > 0) {
                    buffer.flip();
                    ByteBufferUtil.debugAll(buffer);
                    buffer.clear();
                    log.debug("after read.... {}", channel);
                }
            }
        }
    }
}
