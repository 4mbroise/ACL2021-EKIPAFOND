package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;

public class HeroComponent implements Component{
    public static final float HERO_VELOCITY = 1;//
    public static final int START_HEALTH = 5; // starting health
    public static final int STATE_STATIC = 0;//state static,hero can't move
    public static final int STATE_WALKING = 1;//state walk,hero walks
    public static final int STATE_ATTACKING = 2;//state attack,hero attacks
    public static final int STATE_INVINCIBILITY =-1;//state invicibility,hero cannot be harmed
    public static final int STATE_DEATH=-2;//state death,hero is dead ,and all operations are forbidden
    private boolean slowedStatus =  false;

    private int state = 1;

    /**
     * get the hero's state
     * @return state of hero
     */
    public int getState() {
        return state;
    }

    /**
     * We can reset the hero's state without death and invincibility
     * @param newState new state of hero
     */
    public void setState(int newState) {
        if(state!=STATE_DEATH) {
            if (state != STATE_INVINCIBILITY) {
                this.state = newState;
            }
        }
    }

    /**
     * lift the state invincibility of hero
     */
    public void liftInvincibility(){
        state=1;
    }

    /**
     * state invincibility setter
     */
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

    /**
     * get the hero's initial HP
     * @return
     */
    public int getStartHealth(){
        return START_HEALTH;
    }

    /**
     * Set the state of the hero to the dead state
     */
    public void death(){
        state=STATE_DEATH;
    }


}
