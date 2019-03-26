package com.design.background.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

public class SendMailUtils {
    /**
     * 验证码位数
     */
    private final static int MAIL_CODE_COUNT =4;

    private static final String HOST = "smtp.exmail.qq.com"; //发件服务器地址
    private static final String FROM_ADDRESS = "wangpengwei@itenscen.cn"; //发件邮箱地址
    private static final String PASSWORD = "gYtb4a23tcsqFd8J"; //发件邮箱密码

    /**
     * @param subject 邮件标题
     * @param content 邮件内容
     * @param toAddress 收件人邮箱地址
     * @param sfHtml 是否以html模板发送:默认使用模板发送邮件
     * @return
     */
    @Async
    public static boolean sendMail(String subject,String content,String toAddress,boolean sfHtml) {
        boolean status = false;
        try {
            JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
            javaMailSender.setHost(HOST);
            javaMailSender.setUsername(FROM_ADDRESS);
            javaMailSender.setPassword(PASSWORD);
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true,"UTF-8");
            helper.setFrom(FROM_ADDRESS);
            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setText(content,sfHtml);
            javaMailSender.send(mimeMessage);
            status = true;
        } catch (MailException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     * 发送验证码邮件
     * @param toAddress 收件人邮箱地址
     * @return
     */
    public static boolean sendVeriCode(String toAddress,int code){
        String subject = "重置密码";
        String content = "验证码:"+code;
        return sendMail(subject,content,toAddress,false);
    }
    /**
     * @Title: getMailCode
     * @Description:  获取验证码
     * @Author: lxt 
     * @Date: 2019-02-18 15:47 
     * @return: int
     * @throws: 
     */
    public static int getMailCode(){
       return Utility.getRandomByNum(MAIL_CODE_COUNT);
    }
}
