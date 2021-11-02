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
 * RPC 框架的服务部分, 使用注册中心
 *
 */
@Service
public class RpcServerWithRegCenterFrame {

    @Autowired
    private RegisterServiceWithRegCenter registerServiceWithRegCenter;

    public void startService(String serviceName, String host, int port, Class impl) throws Throwable {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(port));
        System.out.println("RPC server on:" + port + ":运行");

        registerServiceWithRegCenter.regRemote(serviceName, host, port, impl);

        try {
            while (true) {
                new Thread(new ServerTask(serverSocket.accept(), registerServiceWithRegCenter)).start();
            }
        } finally {

        }
    }

    private static class ServerTask implements Runnable {

        private Socket client;
        private RegisterServiceWithRegCenter registerServiceWithRegCenter;

        public ServerTask(Socket client, RegisterServiceWithRegCenter registerServiceWithRegCenter) {
            this.client = client;
            this.registerServiceWithRegCenter = registerServiceWithRegCenter;
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

                    Class serviceClass = registerServiceWithRegCenter.getLocalService(serviceName);
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
