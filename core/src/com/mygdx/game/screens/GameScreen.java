package com.mygdx.game.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.mygdx.game.ACLGame;

public class GameScreen extends ScreenAdapter {

    private ACLGame game;

    public GameScreen(ACLGame game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        game.setScreen(new EndScreen(game));
    }


}
