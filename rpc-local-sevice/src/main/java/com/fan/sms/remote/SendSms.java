package com.fan.sms.remote;


import com.fan.sms.remote.vo.UserInfo;

/**
 * 短信发送的接口
 */
public interface SendSms {

    boolean sendMail(UserInfo userInfo);
}
