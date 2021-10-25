package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.ACLGame;
import com.mygdx.game.Assets;
import com.mygdx.game.outils.Font;

public class EndScreen extends ScreenAdapter {
    //main game
    private ACLGame game;
    //stage
    private Stage stage;
    //font
    private Font titleFont;
    private Font contentFont;
    private BitmapFont title;
    private BitmapFont content;
    //batch
    private Batch batch;
    //background
    private Image backGroud;
    //texture
    private Texture backGroundTexture;
    private Texture homeUpTexture;
    private Texture homeDownTexture;
    //Button
    private Button homeButton;
    //assets
    private Assets assets;
    public  EndScreen (ACLGame game){
        this.game=game;
        this.batch=game.batcher;
        stage=new Stage();
        assets=game.getAssets();
        create();
    }

    public void create(){
        //background
        backGroundTexture=assets.getManager().get("UI/nightbackgroundwithmoon.png");
        backGroud=new Image(backGroundTexture);
        backGroud.setPosition(0,0);
        backGroud.setOrigin(0,0);
        backGroud.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        //font
        titleFont=new Font(50, Color.RED);
        title= titleFont.getFont();
        contentFont=new Font(20, Color.BLACK);
        content=contentFont.getFont();
        //button
        homeUpTexture=assets.getManager().get("UI/homeUp.png");
        homeDownTexture=assets.getManager().get("UI/homeDown.png");
        //button style
        Button.ButtonStyle homeStyle=new Button.ButtonStyle();
        //start button
        homeStyle.up=new TextureRegionDrawable(new TextureRegion(homeUpTexture));
        homeStyle.down=new TextureRegionDrawable(new TextureRegion(homeDownTexture));
        homeButton=new Button(homeStyle);
        homeButton.setPosition(Gdx.graphics.getWidth()/2-homeUpTexture.getWidth()/2,50);
        homeButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new MenuScreen(game));
            }
        });
        stage.addActor(backGroud);
        stage.addActor(homeButton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor( 0, 0, 0, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        batch.begin();
        stage.act();
        stage.draw();
        title=assets.getManager().get("fonts/Retro_Gaming.ttf");
        title.draw(batch, "Game Over",250,400);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void show() {
        super.show();
    }
}

