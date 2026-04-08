package com.example.websockettest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class GameState {
  private static final int GRID_WIDTH = 20;
  private static final int GRID_HEIGHT = 20;
  private static final int CELL_SIZE = 30;
  private static final int INITIAL_LENGTH = 3;
  private static final int APPLE_COUNT = 4;

  private String lastAction = "right";
  private boolean started = false;
  private boolean gameOver = false;
  private int score = 0;
  private final Random random = new Random();
  private final LinkedList<Tile> snake = new LinkedList<>();
  private final List<Tile> apples = new ArrayList<>();

  public GameState() {
    int startX = GRID_WIDTH / 2;
    int startY = GRID_HEIGHT / 2;
    for (int i = 0; i < INITIAL_LENGTH; i++) {
      snake.add(new Tile(startX - i, startY));
    }
    ensureAppleCount();
  }

  public void applyAction(String action) {
    if (gameOver) {
      return;
    }
    if (!isKnownDirection(action)) {
      return;
    }
    if (isOppositeDirection(action, lastAction) && snake.size() > 1) {
      return;
    }

    lastAction = action;
    started = true;
  }

  private boolean isKnownDirection(String action) {
    return Objects.equals(action, "left")
        || Objects.equals(action, "right")
        || Objects.equals(action, "up")
        || Objects.equals(action, "down");
  }

  private boolean isOppositeDirection(String next, String current) {
    return (Objects.equals(next, "left") && Objects.equals(current, "right"))
        || (Objects.equals(next, "right") && Objects.equals(current, "left"))
        || (Objects.equals(next, "up") && Objects.equals(current, "down"))
        || (Objects.equals(next, "down") && Objects.equals(current, "up"));
  }

  private void moveOneStep() {
    Tile head = snake.getFirst();
    int nextX = head.x();
    int nextY = head.y();

    switch (lastAction) {
      case "left" -> nextX -= 1;
      case "right" -> nextX += 1;
      case "up" -> nextY -= 1;
      case "down" -> nextY += 1;
      default -> {
        return;
      }
    }

    if (nextX < 0 || nextX >= GRID_WIDTH || nextY < 0 || nextY >= GRID_HEIGHT) {
      gameOver = true;
      return;
    }

    Tile nextHead = new Tile(nextX, nextY);
    if (snake.contains(nextHead)) {
      gameOver = true;
      return;
    }

    snake.addFirst(nextHead);
    if (apples.remove(nextHead)) {
      score += 1;
      ensureAppleCount();
      return;
    }

    snake.removeLast();
  }

  private void spawnApple() {
    if (snake.size() >= GRID_WIDTH * GRID_HEIGHT) {
      gameOver = true;
      apples.clear();
      return;
    }

    Tile candidate;
    do {
      candidate = new Tile(random.nextInt(GRID_WIDTH), random.nextInt(GRID_HEIGHT));
    } while (snake.contains(candidate) || apples.contains(candidate));

    apples.add(candidate);
  }

  private void ensureAppleCount() {
    while (!gameOver && apples.size() < APPLE_COUNT) {
      spawnApple();
    }
  }

  public void update() {
    if (gameOver || !started) {
      return;
    }
    moveOneStep();
  }

  public int getX() {
    return snake.getFirst().x() * CELL_SIZE;
  }

  public int getY() {
    return snake.getFirst().y() * CELL_SIZE;
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

  public int getScore() {
    return score;
  }

  public List<Tile> getSnake() {
    return new ArrayList<>(snake);
  }

  public List<Tile> getApples() {
    return new ArrayList<>(apples);
  }

  public record Tile(int x, int y) {}
}

