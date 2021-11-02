package com.fan.sms.rpc.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * RPC 框架的服务部分
 *
 * 实现步骤:
 *  1. 服务端(rpc-server-sms)定义接口和服务实现类并注册服务
 *  2. 客户端(rpc-local-service)使用动态代理调用服务
 *  3. 客户端代理把调用对象, 方法, 参数序列化成数据
 *  4. 客户端代理与服务端通过Socket通讯传输数据
 *  5. 服务端反序列化数据成对象, 方法, 参数
 *  6. 服务端代理拿到这些对象和参数后通过反射的机制调用服务的实例
 *
 */
//@Service
public class RpcServerFrame {

    @Autowired
    private RegisterService registerService;

    private int port;

    public void startService(String serviceName, String host, int port, Class impl) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(port));
        System.out.println("RPC server on:" + port + ":运行");

        registerService.regService(serviceName, impl);

        try {
            while (true) {
                new Thread(new ServerTask(serverSocket.accept(), registerService)).start();
            }
        } finally {

        }
    }

    private static class ServerTask implements Runnable {

        private Socket client;
        private RegisterService registerService;

        public ServerTask(Socket client, RegisterService registerService) {
            this.client = client;
            this.registerService = registerService;
        }

        @Override
        public void run() {
            try (
                    ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());
                    ObjectOutputStream outputStream = new ObjectOutputStream(client.getOutputStream())
            ) {
                    //服务名
                    String serviceName = inputStream.readUTF();
                    //方法名
                    String methodName = inputStream.readUTF();
                    //参数类型
                    Class<?>[] paramType = (Class<?>[]) inputStream.readObject();
                    //参数值
                    Object[] args = (Object[])inputStream.readObject();

                    Class serviceClass = registerService.getLocalService(serviceName);
                    if(serviceClass == null) {
                        throw new ClassNotFoundException(serviceName + " not found!");
                    }

                    Method method = serviceClass.getMethod(methodName, paramType);
                    Object result = method.invoke(serviceClass.newInstance(), args);
                    //返回服务调用的结果
                    outputStream.writeObject(result);
                    outputStream.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
