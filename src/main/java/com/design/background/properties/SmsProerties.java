package com.design.background.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.design.background.util.SmsUtil;

import javax.annotation.PostConstruct;

/**
 * @ClassName: SmsProerties
 * @Description: 短信工具类 配置类
 * @Author: lxt
 * @Date: 2019-02-14 09:37
 * @Version 1.0
 **/
@ConfigurationProperties(prefix = "sms.aliyun")
@Component
public class SmsProerties {

    private  String regionId;
    private  String accessKeyId;
    private  String accessSecret;
    private  String templateCode;
    private  String signName;


    @PostConstruct
    public void init(){
        //短信具类配置初始化
        SmsUtil.initConfig(this);
    }

    public String getRegionId() {
        return regionId;
    }

    public SmsProerties setRegionId(String regionId) {
        this.regionId = regionId;
        return this;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public SmsProerties setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
        return this;
    }

    public String getAccessSecret() {
        return accessSecret;
    }

    public SmsProerties setAccessSecret(String accessSecret) {
        this.accessSecret = accessSecret;
        return this;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public SmsProerties setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
        return this;
    }

    public String getSignName() {
        return signName;
    }

    public SmsProerties setSignName(String signName) {
        this.signName = signName;
        return this;
    }

    @Override
    public String toString() {
        return "SmsProerties{" +
                "regionId='" + regionId + '\'' +
                ", accessKeyId='" + accessKeyId + '\'' +
                ", accessSecret='" + accessSecret + '\'' +
                ", templateCode='" + templateCode + '\'' +
                ", signName='" + signName + '\'' +
                '}';
    }
}
