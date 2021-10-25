package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class MonsterComponent implements Component, Pool.Poolable {

    public static enum Type {MONSTER, GHOST}
    private boolean isDead;
    private float xPosCenter;
    private Type monsterType;

    public MonsterComponent() {
        this.isDead=false;
        this.xPosCenter=-1;
        this.monsterType=Type.MONSTER;
    }

    @Override
    public void reset() {
        this.isDead=false;
        this.xPosCenter=-1;
        this.monsterType=Type.MONSTER;
    }
}
