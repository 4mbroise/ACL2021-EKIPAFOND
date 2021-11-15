package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;

public class TypeComponent implements Component {

    public static final int TYPE_MONSTER = 0;
    public static final int TYPE_HERO = 1;
    public static final int TYPE_WALL = 2;
    public static final int TYPE_TREASURE = 3;
    public static final int TYPE_TRAP = 4;
    public static final int TYPE_MAGIC = 5;
    public static final int TYPE_PORTAL = 6;

    private int type;

    public TypeComponent(int type){
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
