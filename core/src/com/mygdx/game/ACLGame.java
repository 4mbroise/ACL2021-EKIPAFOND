package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.MenuScreen;


public class ACLGame extends Game {

    public SpriteBatch batcher;
    public OrthographicCamera camera;
    private Assets assets;




    @Override
    public void create() {
        this.assets = new Assets();
        batcher = new SpriteBatch();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batcher.setProjectionMatrix(camera.combined);
        assets.getManager().finishLoading();
        setScreen(new MenuScreen(this));

    }

    @Override
    public void render() {
        GL20 gl = Gdx.gl;
        gl.glClearColor(0.15f, 0.15f, 0.15f, 0.0f);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        super.render();
    }

    @Override
    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Assets getAssets() {
        return this.assets;
    }

    @Override
    public void dispose() {
        if(assets!=null){
            assets.getManager().dispose();
        }
    }
}
