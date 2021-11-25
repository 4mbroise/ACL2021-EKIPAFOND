package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.MenuScreen;


public class ACLGame extends Game {

    public SpriteBatch batcher;
    public OrthographicCamera camera;
    private Assets assets;
    private int level;
    private int score;
    private final static int scoreIncrease = 30;



    @Override
    public void create() {
        this.assets = new Assets();
        batcher = new SpriteBatch();
        this.level = 1;

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //batcher.setProjectionMatrix(camera.combined);
        //camera.translate((float) (Gdx.graphics.getWidth()*0.5), (float) (Gdx.graphics.getHeight()*0.5));
        camera.update();
        /*camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());*/
        batcher.setProjectionMatrix(camera.combined);
        assets.getManager().finishLoading();
        this.score=0;
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
