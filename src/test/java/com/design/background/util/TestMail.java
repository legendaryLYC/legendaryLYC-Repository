package com.design.background.util;

import com.design.background.BackgroundApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.TemplateEngine;

/**
 * 测试邮件发送
 */
public class TestMail extends BackgroundApplicationTests {

    @Autowired
    private TemplateEngine templateEngine;  //thymeleaf模板引擎

    @Test
    public void testSendMailsCode(){
        int code = SendMailUtils.getMailCode();
        boolean flag = SendMailUtils.sendVeriCode("839376636@qq.com",code);
        System.out.println("----------------code->"+code);
        System.out.println("----------------flag->"+flag);

    }

}
