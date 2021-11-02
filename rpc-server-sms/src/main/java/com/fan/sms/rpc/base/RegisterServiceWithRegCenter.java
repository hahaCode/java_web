package com.fan.sms.rpc.base;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RegisterServiceWithRegCenter {
    /**
     * 本地可提供服务的一个名单, 用缓存实现
     */
    private static final Map<String, Class> serviceCache = new ConcurrentHashMap<>();

    /**
     * 往远程服务器注册本服务
     */
    public void regRemote(String serviceName, String host, int port, Class impl) throws Throwable{
        //登记到注册中心
        Socket socket = null;
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;

        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress("127.0.0.1", 9999));
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeBoolean(false);
            outputStream.writeUTF(serviceName);
            outputStream.writeUTF(host);
            outputStream.writeInt(port);
            outputStream.flush();

            inputStream = new ObjectInputStream(socket.getInputStream());
            if (inputStream.readBoolean()) {
                System.out.println("服务[" + serviceName + "]在注册中心注册成功");
            }
            //将可提供的服务放入本地缓存
            serviceCache.put(serviceName, impl);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket!=null) socket.close();
            if (outputStream!=null) outputStream.close();
            if (inputStream!=null) inputStream.close();
        }
    }

    public Class getLocalService(String serviceName) {
        return serviceCache.get(serviceName);
    }
}
