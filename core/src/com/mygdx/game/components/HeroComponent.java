package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;

public class HeroComponent implements Component{
    public static final float HERO_VELOCITY = 1;
    public static final int START_HEALTH = 5; // starting health
    public static final int STATE_STATIC = 0;
    public static final int STATE_WALKING = 1;
    public static final int STATE_ATTACKING = 2;
    public static final int STATE_INVINCIBILITY =-1;
    public static final int STATE_DEATH=-2;
    private boolean slowedStatus =  false;

    private int state = 1;

    public void setState(int newState) {
        if(state!=STATE_DEATH) {
            if (state != STATE_INVINCIBILITY) {
                this.state = newState;
            }
        }
    }

    public int getState() {
        return state;
    }

    public void liftInvincibility(){
        state=1;
    }

    public void setStateInvincibility(){
        if(state!=STATE_DEATH){
            state=STATE_INVINCIBILITY;
        }
    }

    public boolean isSlowed(){
        return this.slowedStatus;
    }

    public void setSlowedStatus(boolean status){
        slowedStatus = status;
    }

    public int getStartHealth(){
        return START_HEALTH;
    }

    public void death(){
        state=STATE_DEATH;
    }


}
