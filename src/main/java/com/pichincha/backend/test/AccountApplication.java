package com.pichincha.backend.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class AccountApplication {

  public static void main(String[] args) {
    SpringApplication.run(AccountApplication.class);
  }
}
