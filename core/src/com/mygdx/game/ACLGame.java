package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.GameScreen;
import com.sun.org.apache.xpath.internal.operations.Or;

public class ACLGame extends Game {

    public SpriteBatch batcher;
    public OrthographicCamera camera;

    @Override
    public void create() {
        batcher = new SpriteBatch();
        setScreen(new GameScreen(this));
        camera = new OrthographicCamera(800, 480);
        batcher.setProjectionMatrix(camera.combined);
    }

    @Override
    public void render() {
        GL20 gl = Gdx.gl;
        gl.glClearColor(0.15f, 0.15f, 0.15f, 0.0f);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        super.render();
    }
}
