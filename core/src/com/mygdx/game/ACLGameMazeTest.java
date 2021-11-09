package com.mygdx.game;

import com.mygdx.game.screens.MazeTestScreen;
import com.mygdx.game.screens.MenuScreen;

public class ACLGameMazeTest extends ACLGame{

    @Override
    public void create() {
        super.create();
        setScreen(new MenuScreen(this));
    }
}
