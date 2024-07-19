package com.example.inventory2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Inventory2Application {

  public static void main(String[] args) {
    SpringApplication.run(Inventory2Application.class, args);
    // 7월 19 일 버전 용 주석
  }
}
