package com.hqkj.example.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * MyBatis扫描接口
 *
 */
@Configuration
//TODO 注意，由于MapperScannerConfigurer执行的比较早，所以必须有下面的注解
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperScannerConfig {

  @Bean
  public MapperScannerConfigurer mapperScannerConfigurer() {
      MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
      mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
      mapperScannerConfigurer.setBasePackage("com.hqkj.example.dao");
      Properties properties = new Properties();
      properties.setProperty("mappers", "com.hqkj.example.util.MyMapper");
      properties.setProperty("notEmpty", "false");
      properties.setProperty("IDENTITY", "MYSQL");
      mapperScannerConfigurer.setProperties(properties);
      return mapperScannerConfigurer;
  }
}
