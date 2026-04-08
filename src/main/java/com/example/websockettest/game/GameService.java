package com.example.websockettest.game;

import org.springframework.stereotype.Service;

@Service
public class GameService {
    private GameState gameState;

    public GameService() {
        this.gameState = new GameState(0, 0);
    }

    public GameState getGameState() {
        return gameState;
    }

    public GameState updateGameState(InputMessage message) {
        if (message.getDirection() == null) {
            return gameState;
        }

        switch (message.getDirection()) {
            case UP:
                gameState.setPlayerY(gameState.getPlayerY() + 1);
                break;
            case DOWN:
                gameState.setPlayerY(gameState.getPlayerY() - 1);
                break;
            case LEFT:
                gameState.setPlayerX(gameState.getPlayerX() - 1);
                break;
            case RIGHT:
                gameState.setPlayerX(gameState.getPlayerX() + 1);
                break;
        }

        return gameState;
    }

    public void resetGameState() {
        this.gameState = new GameState(0, 0);
    }
}

