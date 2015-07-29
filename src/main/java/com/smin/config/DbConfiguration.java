package com.smin.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by phuonghqh on 2/15/15.
 */
@Configuration
public class DbConfiguration {

  @Value("${db.host}")
  private String dbHost;

  @Value("${db.username}")
  private String dbUsername;

  @Value("${db.password}")
  private String dbPassword;

  @Bean
  public Flyway flyway() {
    Flyway flyway = new Flyway();
    flyway.setDataSource(dbHost, dbUsername, dbPassword);
    flyway.isInitOnMigrate();
    return flyway;
  }
}
