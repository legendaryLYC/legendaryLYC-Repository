package com.design.background;

import com.beust.jcommander.internal.Maps;
import com.design.background.config.FrontConfig;
import com.design.background.pay.module.unionpay.sdk.SDKConfig;
import com.design.background.service.FriendShipService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import java.util.Map;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.design.background.mapper")
@EnableCaching
public class BackgroundApplication {
	public static void main(String[] args) {
		SpringApplication.run(BackgroundApplication.class, args);
	}

    /**
     * 加载自定义配置文件
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        // 加载其他第三方配置文件
        yaml.setResources(new ClassPathResource("third/application-third.yml"));
        configurer.setProperties(yaml.getObject());
        return configurer;
    }


}