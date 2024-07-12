package com.example.inventory2.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class HomeController {

  @GetMapping("/")
  public String getHome() {
    log.info("home 요청");
    return "inventory/home";
  }

  @GetMapping("/ex")
  public String getEx() {
    return "inventory/ex";
  }
}
