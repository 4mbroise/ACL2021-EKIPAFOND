package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;

public class AttackerComponent implements Component {
    private int damage;

    public AttackerComponent(int damage){
        this.damage=damage;
    }

    public void setDamage(int damage ) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
