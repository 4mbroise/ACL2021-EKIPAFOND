package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;

public class AttackerComponent implements Component {
    private int damage; // the damage value of the hero

    /**
     * Constructor
     * @param damage  the damage value of the hero
     */
    public AttackerComponent(int damage){
        this.damage=damage;
    }

    /**
     *
     * @return the damage value of the hero
     */
    public int getDamage() {
        return damage;
    }

    /**
     * demage setter
     * @param damage the damage value of the hero
     */
    public void setDamage(int damage ) {
        this.damage = damage;
    }

}
