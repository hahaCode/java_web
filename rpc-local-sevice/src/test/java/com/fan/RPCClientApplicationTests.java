package com.fan;

import com.fan.sms.remote.SendSms;
import com.fan.sms.remote.vo.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = RPCClientApplication.class)
@RunWith(SpringRunner.class)
public class RPCClientApplicationTests {

    @Autowired
    private SendSms sendSms;

    @Test
    public void rpcTest() {
        long start = System.currentTimeMillis();

        UserInfo userInfo = new UserInfo("Anna", "12345678");
        System.out.println("Send mail "+ sendSms.sendMail(userInfo));
        System.out.println("共耗时: " + (System.currentTimeMillis()-start) + " ms");
    }
}
