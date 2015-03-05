package com.smin.config;


import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.TextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

@Configuration
@ComponentScan(basePackages = "com.smin")
@PropertySources({
  @PropertySource("classpath:smin.properties"),
  @PropertySource(value = "classpath:override.properties", ignoreResourceNotFound = true)})
public class CoreConfiguration {

  @Resource
  private Environment environment;


  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  @Bean
  public TextEncryptor textEncryptor() {
    BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
    textEncryptor.setPassword(environment.getProperty("core.textEncryptor.password"));
    return textEncryptor;
  }



//  public static void main(String[] args) {
//    BasicTextEncryptor abc = new BasicTextEncryptor();
//    abc.setPassword("XR3hBZnJ6gLH");
//    System.out.println(abc.encrypt("password"));
//  }
}
