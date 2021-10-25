package com.mygdx.game.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ScreenAdapter;
import com.mygdx.game.ACLGame;
import com.mygdx.game.Assets;
import com.mygdx.game.World;

public class GameScreen extends ScreenAdapter {

    public ACLGame game;
    public Assets assets;
    public Engine engine;

    public GameScreen(ACLGame game, Assets assets) {
        this.engine = new PooledEngine();
        this.game = game;
        this.assets = assets;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        this.engine.update(delta);
    }

    @Override
    public void show() {
        super.show();
    }
}
