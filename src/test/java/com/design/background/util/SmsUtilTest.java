package com.design.background.util;

import com.design.background.BackgroundApplicationTests;
import org.junit.Assert;
import org.junit.Test;

/**
 * @ClassName: SmsUtillTest
 * @Description: 短信工具测试类
 * @Author: lxt
 * @Date: 2019-02-13 17:59
 * @Version 1.0
 **/
public class SmsUtilTest extends BackgroundApplicationTests {
    @Test
    public void testSendCode(){
        int code = SmsUtil.getSmsCode();
        System.out.println("code:"+code);
        String phoneNumbers = "15011108691";
        Assert.assertEquals(SmsUtil.OK, SmsUtil.sendSms(phoneNumbers,code));
    }
}
