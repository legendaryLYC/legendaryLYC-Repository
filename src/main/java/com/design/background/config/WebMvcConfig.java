package com.design.background.config;

import com.beust.jcommander.internal.Maps;
import com.design.background.service.FriendShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Spring mvc 配置
 * @author nicc
 *
 */
@Configuration
public class WebMvcConfig  implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/admin/login").setViewName("login");
//		registry.addViewController("/welcome").setViewName("welcome");
	}



}
