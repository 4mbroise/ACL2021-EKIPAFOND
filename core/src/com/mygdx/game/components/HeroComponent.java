package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;

public class HeroComponent implements Component {
    public static final float HERO_VELOCITY = 20f;
    public static final int STATE_STATIC = 0;
    public static final int STATE_WALKING = 1;
    public static final int STATE_ATTACKING = 2;
    private int state = 1;

    public void setState(int newState) {
        this.state = newState;
    }

    public int getState() {
        return state;
    }
}
