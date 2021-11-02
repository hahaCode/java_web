package com.fan.sms.rpc.sms;

import com.fan.sms.remote.SendSms;
import com.fan.sms.rpc.base.RpcServerFrame;
import com.fan.sms.rpc.base.RpcServerWithRegCenterFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Random;

/**
 * RPC的服务端, 提供服务
 */
@Service
public class SmsRPCServer {

    //@Autowired
    //private RpcServerFrame rpcServerFrame;

    @Autowired
    private RpcServerWithRegCenterFrame rpcServerWithRegCenterFrame;

    @PostConstruct
    public void server() throws Throwable {
        //int port = 8778;  //测试只有一个服务的案例
        //rpcServerFrame.startService(SendSms.class.getName(), "127.0.0.1", port, SendSmsImp.class);

        //随机端口号码, 起多个服务, 测试注册中心的案例
        Random r = new Random();
        int port = r.nextInt(100) + 8778;

        rpcServerWithRegCenterFrame.startService(SendSms.class.getName(), "127.0.0.1", port, SendSmsImp.class);
    }
}
