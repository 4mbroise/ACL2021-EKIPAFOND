package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;

public class HealthComponent implements Component {
    public int healthPoint;

    public HealthComponent(int healthPoint){
        this.healthPoint=healthPoint;
    }

    public void setHealthPoint(int healthPoint ) {
        this.healthPoint = healthPoint;
    }

    public void resetHealthPoint(){
        this.healthPoint = healthPoint;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void addHealthPoint(int point){
        healthPoint+=point;
    }

    public void reduceHealthPoint(int point){
        healthPoint-=point;
    }
}
