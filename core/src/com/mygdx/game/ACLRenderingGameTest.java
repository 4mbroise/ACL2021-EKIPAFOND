package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.screens.*;

public class ACLRenderingGameTest extends ACLGame{

    GameScreen current;

    @Override
    public void create() {
        super.create();
        current = new MazeTestScreen(this);
        setScreen(current);
    }

    @Override
    public void render() {if (!current.active){
        setScreen((new MenuScreen(this)));
    }
        super.render();
    }
}
