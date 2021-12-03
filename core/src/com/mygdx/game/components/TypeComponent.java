package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;

public class TypeComponent implements Component {

    public static final int TYPE_MONSTER = 0;
    public static final int TYPE_HERO = 1;
    public static final int TYPE_WALL = 2;
    public static final int TYPE_GROUND = 3;
    public static final int TYPE_TREASURE = 4;
    public static final int TYPE_TRAP = 5;
    public static final int TYPE_MAGIC = 6;
    public static final int TYPE_PORTAL = 7;
    public static final int TYPE_GHOST = 8;
    public static final int TYPE_GOLD = 9;
    public static final int TYPE_SLOW_MALUS = 10;


    private int type;

    public TypeComponent(int type){
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
