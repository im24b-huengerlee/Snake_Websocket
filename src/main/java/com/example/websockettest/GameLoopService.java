package com.example.websockettest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

@Service
public class GameLoopService {
  private final GameWebSocketHandler webSocketHandler;
  private final ObjectMapper objectMapper = new ObjectMapper();

  public GameLoopService(GameWebSocketHandler webSocketHandler) {
    this.webSocketHandler = webSocketHandler;
  }

  // 1 second between updates (one grid move per tick).
  @Scheduled(fixedRate = 1000)
  public void processGameTick() {
    Map<String, GameConnection> connections = webSocketHandler.getActiveConnections();

    for (GameConnection connection : connections.values()) {
      GameState state = connection.getState();
      state.update();
      String stateJson = serializeStateToJson(state);

      try {
        if (connection.getSession().isOpen()) {
          synchronized (connection.getSession()) {
            connection.getSession().sendMessage(new TextMessage(stateJson));
          }
        }
      } catch (IOException e) {
        System.err.println("Failed to send update to " + connection.getSession().getId());
      }
    }
  }

  private String serializeStateToJson(GameState state) {
    try {
      return objectMapper.writeValueAsString(state);
    } catch (JsonProcessingException e) {
      return "{\"error\":\"failed_to_serialize_state\"}";
    }
  }
}

