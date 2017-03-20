package com.hqkj.example;

import com.hqkj.example.filter.OperateFilter;
import com.hqkj.example.propertiesBean.Platform;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@EnableTransactionManagement    //启用事务注解
@SpringBootApplication
@EnableConfigurationProperties(value = Platform.class) //加载配置文件
@ComponentScan
public class PlatformApplication extends SpringBootServletInitializer {


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(PlatformApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(PlatformApplication.class, args);
	}


//	@Bean
//	public FilterRegistrationBean testFilterRegistration() {
//		//创建Filter过滤器适配
//		FilterRegistrationBean registration = new FilterRegistrationBean();
//		//添加过滤器
//		registration.setFilter(new OperateFilter());
//		//创建过滤的配置集合
//		List<String> urlPatterns = new ArrayList<String>();
//		urlPatterns.add("/user/*");
//		//将配置的url集合进行适配
//		registration.setUrlPatterns(urlPatterns);
//		//添加初始化参数
//		registration.addInitParameter("paramName", "paramValue");
//		//过滤器名称
//		registration.setName("loginFilter");
//		registration.setOrder(1);
//		return registration;
//	}


}
