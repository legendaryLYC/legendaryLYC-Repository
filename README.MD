###添加maven包
mvn install:install-file -Dfile=G:/ITtools/gitlab/repository/itenscen/design-background/lib/UEditor-for-aliyun-OSS-1.4.3.jar -DgroupId=cn.xiexianbin.ueditor -DartifactId=UEditor-for-aliyun-OSS -Dversion=1.4.3 -Dpackaging=jar

> 关于第三方支付

- 演示地址：http://localhost:8080/thirdPay/test
- 测试信息
    - 支付宝
         - 电脑支付： 账号:txdslh1900@sandbox.com   
         登录密码: 111111   
         支付密码: 111111 
         - 扫码支付：钉钉群内下载支付宝沙箱apk 登录以上账户
    - 微信:`真实普通商户二维码，注意控制金额^_^.`
    - 银联
        - 姓名：全渠道
        - 卡号：6216261000000000018
        - 身份证号码：341126197709218366
        - 手机号：13552535506
        - 验证码：123456
- 关于第三方支付回调验签等说明
    - 支付回调验签均需配置外网可访问的地址，故需做内网穿透，让自己电脑可被外网访问，此处列举一种方案，也可使用其他，目的达到即可。
    - 使用natapp，官网：https://natapp.cn/
    - 可参考：https://blog.csdn.net/ejinxian/article/details/76061059/
    - 获取到本机外网地址之后,修改如【application.properties】中的【design.internet.address】配置为外网地址即可
    - 注：外网地址是动态的，注意同步修改配置。