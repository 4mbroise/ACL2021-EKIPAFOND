package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;

public class HealthComponent implements Component {


    public int healthPoint;//the value of health point

    /**
     * Constructor
     * @param healthPoint the value of health point
     */
    public HealthComponent(int healthPoint){
        this.healthPoint=healthPoint;
    }

    /**
     * reset the value of health point
     */
    public void resetHealthPoint(){
        this.healthPoint = healthPoint;
    }

    /**
     * get the value of health point
     * @return the value of health point
     */
    public int getHealthPoint() {
        return healthPoint;
    }

    /**
     * healthPoint setter
     * @param healthPoint the value of health point
     */
    public void setHealthPoint(int healthPoint ) {
        this.healthPoint = healthPoint;
    }

    /**
     * add value of point to the health point
     * @param point the value added of health point
     */
    public void addHealthPoint(int point){
        healthPoint+=point;
    }

    /**
     * reduce value of point from the health point
     * @param point the value reduced of health point
     */
    public void reduceHealthPoint(int point){
        healthPoint-=point;
    }
}
