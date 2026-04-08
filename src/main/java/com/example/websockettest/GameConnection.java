package com.example.websockettest;

import org.springframework.web.socket.WebSocketSession;

public class GameConnection {
  private final WebSocketSession session;
  private GameState state;

  public GameConnection(WebSocketSession session, GameState state) {
    this.session = session;
    this.state = state;
  }

  public WebSocketSession getSession() {
    return session;
  }

  public GameState getState() {
    return state;
  }

  public void resetState() {
    this.state = new GameState();
  }
}

