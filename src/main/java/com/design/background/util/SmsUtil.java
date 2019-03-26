package com.design.background.util;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.design.background.properties.SmsProerties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: SmsUtill
 * @Description: 短信工具类
 * @Author: lxt
 * @Date: 2019-02-13 17:31
 * @Version 1.0
 **/
public class SmsUtil {
    private final static Logger logger = LoggerFactory.getLogger(SmsUtil.class);
    /**
     * 发送异常结果
     */
    private final static String EXCEPTION = "发送短信异常！";
    /**
     * 验证码位数
     */
    private final static int SMS_CODE_COUNT =4;
    /**
     * 验证码发送成功结果
     */
    public final static String OK = "OK";
    private static SmsProerties smsProerties;
    /**
     * @Title: initConfig
     * @Description: 配置初始化
     * @Author: lxt
     * @param: smsProertiesInit
     * @Date: 2019-02-14 09:32
     * @throws:
     */
    public static void initConfig(SmsProerties smsProertiesInit){
        smsProerties = smsProertiesInit;
    }
    /**
     * @Title: sendSmsCode
     * @Description: 
     * @Author: lxt 
     * @param: phoneNumbers 手机号，多个手机号用英文逗号隔开，最多支持1000个
     * @param: code 短信验证码
     * @Date: 2019-02-14 11:32
     * @return: java.lang.String 返回调用结果 成功=>"OK"；失败=>错误信息
     * @throws: 
     */
    public  static  String sendSms(String phoneNumbers,int code)  {
        DefaultProfile profile = DefaultProfile.getProfile(smsProerties.getRegionId(), smsProerties.getAccessKeyId(), smsProerties.getAccessSecret());
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("TemplateCode", smsProerties.getTemplateCode());
        request.putQueryParameter("SignName", smsProerties.getSignName());
        request.putQueryParameter("PhoneNumbers", phoneNumbers);
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            return JSON.parseObject(response.getData()).getString("Message");
        } catch (ServerException e) {
            logger.error(EXCEPTION,e);
        } catch (ClientException e) {
            logger.error(EXCEPTION,e);
        }catch (Exception e){
            logger.error(EXCEPTION,e);
        }
        return EXCEPTION;
    }
    /**
     * @Title: getSmsCode
     * @Description: 获取验证码 暂定默认为4位
     * @Author: lxt 
     * @Date: 2019-02-18 14:40 
     * @return: int
     * @throws: 
     */
    public static int getSmsCode(){
        return Utility.getRandomByNum(SMS_CODE_COUNT);
    }
}
