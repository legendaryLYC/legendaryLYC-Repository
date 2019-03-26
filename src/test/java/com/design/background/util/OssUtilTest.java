package com.design.background.util;

import com.design.background.BackgroundApplicationTests;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @ClassName: OssUtillTest
 * @Description: 阿里云 对象云存储工具类 测试类
 * @Author: lxt
 * @Date: 2019-02-13 17:36
 * @Version 1.0
 **/
public class OssUtilTest extends BackgroundApplicationTests {
    @Test
    public void testUpload(){
        File file = new File("/home/lxt/Desktop/1.jpg");
        Assert.assertNotEquals(OssUtil.upload(OssUtil.generateKey(file.getName()),file),null);
    }
    @Test
    public void testDownload(){
       String url = "http://design-lxt.oss-cn-beijing.aliyuncs.com/20190213.1.O1139N.jpg?Expires=186567" +
               "3055&OSSAccessKeyId=LTAIkkI5bUH6MT0W&Signature=Kiff3JAkT7vYhAfaYbvlT6C9uWA%3D";
       String dir = "/home/lxt/Desktop/";
       Assert.assertNotEquals(OssUtil.download2File(url,dir),null);
    }
    @Test
    public void testDelete(){
        String url = "http://design-lxt.oss-cn-beijing.aliyuncs.com/20190213.1.6F1E09.jpg?Expir" +
                "es=1865673117&OSSAccessKeyId=LTAIkkI5bUH6MT0W&Signature=GC2Om%2BqYKbxMbbpBhcCOV7%2B0knQ%3D";
        OssUtil.delete(url);
    }

    @Test
    public void testUpload1(){
        File file = new File("/home/lxt/Desktop/8M图片.jpg");
        File test = new File( ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/upload/test1.jpg");
        System.out.println("test:"+test);
        try {
            FileUtils.copyFile(file,test);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
