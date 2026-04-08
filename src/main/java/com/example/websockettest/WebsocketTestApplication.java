package com.example.websockettest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // enables @Scheduled methods (our 1/sec broadcaster)
public class WebsocketTestApplication {
  public static void main(String[] args) {
    SpringApplication.run(WebsocketTestApplication.class, args);
  }
}

