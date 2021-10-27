package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;

public class TypeComponent implements Component {

    public static final int TYPE_MONSTER = 0;
    public static final int TYPE_HERO = 1;
    public static final int TYPE_WALL = 2;

    private int type;

    public TypeComponent(int type){
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
