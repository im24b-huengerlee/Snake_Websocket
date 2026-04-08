package com.example.websockettest;

public class GameState {
  private static final int GRID_WIDTH = 20;
  private static final int GRID_HEIGHT = 20;
  private static final int CELL_SIZE = 30;

  private int gridX = GRID_WIDTH / 2;
  private int gridY = GRID_HEIGHT / 2;
  private String lastAction = "right";
  private boolean gameOver = false;

  public void applyAction(String action) {
    if (gameOver) {
      return;
    }
    lastAction = action;

    // Move exactly one grid cell per key press.
    switch (action) {
      case "left" -> gridX -= 1;
      case "right" -> gridX += 1;
      case "up" -> gridY -= 1;
      case "down" -> gridY += 1;
      default -> {
        // ignore unknown actions
      }
    }

    if (gridX < 0 || gridX >= GRID_WIDTH || gridY < 0 || gridY >= GRID_HEIGHT) {
      gameOver = true;
    }
  }

  public void update() {
    if (gameOver) {
      return;
    }
    // No auto movement. Movement happens only when input arrives.
  }

  public int getX() {
    return gridX * CELL_SIZE;
  }

  public int getY() {
    return gridY * CELL_SIZE;
  }

  public String getLastAction() {
    return lastAction;
  }

  public boolean isGameOver() {
    return gameOver;
  }

  public int getGridWidth() {
    return GRID_WIDTH;
  }

  public int getGridHeight() {
    return GRID_HEIGHT;
  }

  public int getCellSize() {
    return CELL_SIZE;
  }
}

