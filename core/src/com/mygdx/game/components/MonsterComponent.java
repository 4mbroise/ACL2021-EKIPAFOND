package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class MonsterComponent implements Component, Pool.Poolable {

    public static enum Type {MONSTER, GHOST} // enum types of the monster
    private boolean isDead; // monster's life status
    private float xPosCenter; //position of the monster
    public Type monsterType; // type of the monster
    public static final float MONSTER_VELOCITY = 1;
    public static final float GHOST_VELOCITY = 1/2;

    /**
     * First constructor which creates a monster with the MONSTER type
     */
    public MonsterComponent() {
        this.isDead=false;
        this.xPosCenter=-1;
        this.monsterType=Type.MONSTER;
    }

    /**
     * First constructor which creates a monster with the GHOST type
     * @param a random int to distinguish both constructors
     */
    public MonsterComponent(int a){
        this.isDead=false;
        this.xPosCenter=-1;
        this.monsterType=Type.GHOST;
    }

    @Override
    public void reset() {
        this.isDead=false;
        this.xPosCenter=-1;
        this.monsterType=Type.MONSTER;
    }

    /**
     * Type getter
     * @return monster's type
     */
    public Type getMonsterType() {
        return monsterType;
    }

    public void dead(){
        isDead=true;
    }

    public boolean getDead(){
        return isDead;
    }
}
