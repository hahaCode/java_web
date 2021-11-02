package com.fan.rpc;

import org.springframework.stereotype.Service;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * RPC框架的客户端代理部分
 */
@Service
public class RpcClientFrame {
    public static<T> T getRemoteProxyObject(final Class<?> serviceInterface) {
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8778);
        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class<?>[]{serviceInterface}, new DynProxy(serviceInterface, address));
    }

    /**
     * 动态代理类, 实现对远程服务的访问
     */
    private static class DynProxy implements InvocationHandler{

        private Class<?> serviceInterface;
        private InetSocketAddress address;

        public DynProxy(Class<?> serviceInterface, InetSocketAddress address) {
            this.serviceInterface = serviceInterface;
            this.address = address;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Socket socket = null;
            ObjectInputStream inputStream = null;
            ObjectOutputStream outputStream = null;

            try {
                socket = new Socket();
                socket.connect(address);
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                inputStream = new ObjectInputStream(socket.getInputStream());

                //所要调用方法 所实现的接口名
                outputStream.writeUTF(serviceInterface.getName());
                //方法名
                outputStream.writeUTF(method.getName());
                //方法的入参类型
                outputStream.writeObject(method.getParameterTypes());
                //方法的传入参数
                outputStream.writeObject(args);

                outputStream.flush();

                //接收服务器的处理结果
                System.out.println(serviceInterface + " remote exec success!");
                return inputStream.readObject();
            } finally {
                if (socket!=null) socket.close();
                if (outputStream != null)
                    outputStream.close();
                if (inputStream != null)
                    inputStream.close();
            }
        }
    }
}
