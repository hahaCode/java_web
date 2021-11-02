package com.fan.config;

import com.fan.rpc.RpcClientRegCenterFrame;
import com.fan.sms.remote.SendSms;
import com.fan.rpc.RpcClientFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    //@Autowired
    //private RpcClientFrame rpcClientFrame;

    @Autowired
    private RpcClientRegCenterFrame rpcClientRegCenterFrame;

    @Bean
    public SendSms getSmsService() throws Exception{
        //return rpcClientFrame.getRemoteProxyObject(SendSms.class);
        return rpcClientRegCenterFrame.getRemoteProxyObject(SendSms.class);
    }
}
