package com.example.inventory2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Inventory2Application {

  public static void main(String[] args) {
    SpringApplication.run(Inventory2Application.class, args);
  }
}
