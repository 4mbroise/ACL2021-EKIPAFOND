package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;

public class MovementComponent implements Component {
    private float velocity;

    public MovementComponent(float velocity) {
        this.velocity = velocity;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }
}
