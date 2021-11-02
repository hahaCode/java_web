package com.fan.rpc;

import com.fan.sms.remote.vo.RegisterServiceVo;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * RPC框架的客户端代理部分, 使用注册中心
 */
@Service
public class RpcClientRegCenterFrame {
    public static<T> T getRemoteProxyObject(final Class<?> serviceInterface) {
        InetSocketAddress address = getService(serviceInterface.getName());
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
    /**
     * 动态获得服务提供者
     */
    private static Random r = new Random();

    // 获得远程服务的地址
    private static InetSocketAddress getService(String serviceName){
        //获得服务提供者的地址列表
        List<InetSocketAddress> serviceVoList = getServiceList(serviceName);
        InetSocketAddress addr = serviceVoList.get(r.nextInt(serviceVoList.size()));
        System.out.println("本次选择了服务器: "+ addr);
        return addr;
    }

    //获得服务提供者的地址
    private static List<InetSocketAddress> getServiceList(String serviceName) {
        Socket socket = null;
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        List<InetSocketAddress> list = new ArrayList<>();

        try {
            socket = new Socket();
            //获取与服务注册中心的连接
            socket.connect(new InetSocketAddress("127.0.0.1", 9999));

            outputStream = new ObjectOutputStream(socket.getOutputStream());
            // true 表示是查询可用服务
            outputStream.writeBoolean(true);
            outputStream.writeUTF(serviceName);
            outputStream.flush();

            inputStream = new ObjectInputStream(socket.getInputStream());
            Set<RegisterServiceVo> result = (Set<RegisterServiceVo>) inputStream.readObject();
            System.out.println("注册中心可以提供 [" + serviceName + "] 服务的是: " + result.toString());

            for (RegisterServiceVo registerServiceVo : result) {
                list.add(new InetSocketAddress(registerServiceVo.getHost(), registerServiceVo.getPort()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket!=null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream!=null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStream!=null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
     }

}
