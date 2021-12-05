package com.mygdx.game;

import com.mygdx.game.screens.GameAITestScreen;

public class ACLRenderingGameTest extends ACLGame{


    public ACLRenderingGameTest() {
    }

    @Override
    public void create() {
        super.create();
        setScreen(new GameAITestScreen(this));
    }
}


