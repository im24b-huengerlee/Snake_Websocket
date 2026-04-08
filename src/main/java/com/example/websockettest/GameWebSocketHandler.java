package com.example.websockettest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class GameWebSocketHandler extends TextWebSocketHandler {
  private final Map<String, GameConnection> activeConnections = new ConcurrentHashMap<>();
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void afterConnectionEstablished(WebSocketSession session) {
    GameState initialState = new GameState();
    GameConnection connection = new GameConnection(session, initialState);
    activeConnections.put(session.getId(), connection);
    System.out.println("Player connected: " + session.getId());
    sendCurrentState(connection);
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) {
    GameConnection connection = activeConnections.get(session.getId());
    if (connection == null) {
      return;
    }

    // Expected payload: "left", "right", "up", "down", "restart"
    String action = message.getPayload().trim().toLowerCase();
    if ("restart".equals(action)) {
      connection.resetState();
      sendCurrentState(connection);
      return;
    }
    connection.getState().applyAction(action);
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
    activeConnections.remove(session.getId());
    System.out.println("Player disconnected: " + session.getId());
  }

  public Map<String, GameConnection> getActiveConnections() {
    return activeConnections;
  }

  private void sendCurrentState(GameConnection connection) {
    try {
      connection.getSession().sendMessage(new TextMessage(objectMapper.writeValueAsString(connection.getState())));
    } catch (JsonProcessingException e) {
      System.err.println("Failed to serialize initial state");
    } catch (IOException e) {
      System.err.println("Failed to send initial state");
    }
  }
}

