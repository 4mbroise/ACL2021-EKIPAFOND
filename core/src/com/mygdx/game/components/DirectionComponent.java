package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;

public class DirectionComponent implements Component {
    public static final int UP      = 1;
    public static final int DOWN    = 2;
    public static final int LEFT    = 3;
    public static final int RIGHT   = 4;

    private int direction = DOWN;

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }
}
