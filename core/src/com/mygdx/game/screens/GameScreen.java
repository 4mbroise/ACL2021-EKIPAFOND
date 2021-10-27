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
    public World world;
    public boolean active;

    public GameScreen(ACLGame game, Assets assets) {
        this.engine = new PooledEngine();
        this.game = game;
        this.assets = assets;
        this.world = new World(this.engine, this.assets);
        this.active = true;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        this.engine.update(delta);
        this.active = !world.isWon();
    }

    @Override
    public void show() {
        super.show();
    }
}