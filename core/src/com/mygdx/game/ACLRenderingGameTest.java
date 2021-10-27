package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.screens.GameTestScreen;
import com.mygdx.game.screens.MazeTestScreen;

public class ACLRenderingGameTest extends ACLGame{

    @Override
    public void create() {
        super.create();
        setScreen(new MazeTestScreen(this));
    }
}
