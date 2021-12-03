package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;

public class ScoreComponent implements Component {
    private int score=0;

    public void addScore(int score) {
        this.score+=score;
    }

    public void reduceScore(int score) {
        this.score-=score;
    }

    public int getScore (){
        return score;
    }

}
