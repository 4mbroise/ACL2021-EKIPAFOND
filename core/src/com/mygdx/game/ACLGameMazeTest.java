package com.mygdx.game;

import com.mygdx.game.screens.MazeTestScreen;

public class ACLGameMazeTest extends ACLGame{

    @Override
    public void create() {
        super.create();
        setScreen(new MazeTestScreen(this));
    }
}
