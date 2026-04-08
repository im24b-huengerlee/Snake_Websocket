package com.example.websockettest.game;


public class InputMessage {
    private Direction direction;

    public InputMessage(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
