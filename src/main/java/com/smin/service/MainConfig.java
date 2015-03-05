package com.smin.service;

import com.smin.config.CoreConfiguration;
import com.smin.task.Bean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by phuonghqh on 3/4/15.
 */
public class MainConfig {

  public static void main(String[] ar) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CoreConfiguration.class);
    Bean abc = context.getBean(Bean.class);
    System.out.println(abc);
  }
}
