package com.fan.sms.rpc.sms;

import com.fan.sms.remote.SendSms;
import com.fan.sms.remote.vo.UserInfo;

public class SendSmsImp implements SendSms {
    @Override
    public boolean sendMail(UserInfo userInfo) {
        System.out.printf("Receive the Mail " + userInfo.toString());
        return true;
    }
}
