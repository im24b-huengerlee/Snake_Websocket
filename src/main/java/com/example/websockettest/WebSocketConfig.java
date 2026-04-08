package com.example.websockettest;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@EnableScheduling // enables @Scheduled game loop ticks
public class WebSocketConfig implements WebSocketConfigurer {
  private final GameWebSocketHandler gameWebSocketHandler;

  public WebSocketConfig(GameWebSocketHandler gameWebSocketHandler) {
    this.gameWebSocketHandler = gameWebSocketHandler;
  }

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(gameWebSocketHandler, "/game").setAllowedOriginPatterns("*");
  }
}

