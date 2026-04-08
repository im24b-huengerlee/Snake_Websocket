package com.example.websockettest;

import com.example.websockettest.game.GameState;
import com.example.websockettest.game.InputMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {
    @MessageMapping("/input")
    @SendTo("/game/state")
    public GameState handleInput(InputMessage message) {
        System.out.println("message = " + message);

        GameState state = new GameState(0, 0); // initial state
        switch (message.getDirection()) {
            case UP:
                // move player up
                state.setPlayerY(state.getPlayerY() + 1);
            case DOWN:
                // move player down
                state.setPlayerY(state.getPlayerY() - 1);
            case LEFT:
                // move player left
                state.setPlayerX(state.getPlayerX() - 1);
            case RIGHT:
                // move player right
                state.setPlayerX(state.getPlayerX() + 1);
        }
        // In a real game, you'd have some game logic here to update the game state
        // based on the input. For this example, we'll just return a dummy game state.
        return state;
    }
}
