package com.example.websockettest.game;

public class GameState {
    private Integer playerX;
    private Integer playerY;

    public GameState(Integer playerX, Integer playerY) {
        this.playerX = playerX;
        this.playerY = playerY;
    }

    @Override
    public String toString() {
        return "GameState{" +
                "playerX=" + playerX +
                ", playerY=" + playerY +
                '}';
    }

    public Integer getPlayerX() {
        return playerX;
    }

    public void setPlayerX(Integer playerX) {
        this.playerX = playerX;
    }

    public Integer getPlayerY() {
        return playerY;
    }

    public void setPlayerY(Integer playerY) {
        this.playerY = playerY;
    }
}
