package com.example.websockettest;

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

  @Override
  public void afterConnectionEstablished(WebSocketSession session) {
    GameState initialState = new GameState();
    GameConnection connection = new GameConnection(session, initialState);
    activeConnections.put(session.getId(), connection);
    System.out.println("Player connected: " + session.getId());
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) {
    GameConnection connection = activeConnections.get(session.getId());
    if (connection == null) {
      return;
    }

    // Expected payload: "left", "right", "up", "down"
    String action = message.getPayload().trim().toLowerCase();
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
}

