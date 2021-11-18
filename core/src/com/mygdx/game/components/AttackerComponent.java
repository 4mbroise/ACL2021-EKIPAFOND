package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AttackerComponent implements Component {
    private int damage;
    private Sound sound;

    public AttackerComponent(int damage,Sound sound){
        this.damage=damage;
        this.sound=sound;
    }

    public void setDamage(int damage ) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void playAudio(){sound.play();}
}
