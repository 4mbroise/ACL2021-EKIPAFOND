package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;

public class HealthComponent implements Component {
    public int healthPoint;


    public void setHealthPoint(int healthPoint ) {
        this.healthPoint = healthPoint;
    }

    public int getHealthPoint() {
        return healthPoint;
    }


}
