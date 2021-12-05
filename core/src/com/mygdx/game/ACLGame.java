package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.MenuScreen;


public class ACLGame extends Game {

    private Assets assets;
    private int level;
    private int score;
    private boolean sound;
    public SpriteBatch batcher;
    public OrthographicCamera camera;
    private final static int scoreIncrease = 30;



    @Override
    public void create() {

        this.assets = new Assets();
        this.level = 1;
        this.score = 0;
        this.sound = true;
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        batcher = new SpriteBatch();
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


    public Assets getAssets() {
        return this.assets;
    }

    public int getLevel(){
        return level;
    }

    public void levelUp(){
        level++;
    }

    public void resetLevel() {level = 1;}

    public boolean isSoundon() {
        return sound;
    }

    public void setSoundon(boolean soundon) {
        this.sound = soundon;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        this.score +=scoreIncrease;
    }

    public void resetScore(){
        this.score=0;
    }
    @Override
    public void dispose() {
        if(assets!=null){
            assets.getManager().dispose();
        }
    }
}
