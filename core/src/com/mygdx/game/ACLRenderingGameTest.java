package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.screens.GameAITestScreen;
import com.mygdx.game.screens.GameTestScreen;

public class ACLRenderingGameTest extends ACLGame{


    public ACLRenderingGameTest() {
    }

    @Override
    public void create() {
        super.create();
        setScreen(new GameAITestScreen(this, getAssets()));
    }
}
