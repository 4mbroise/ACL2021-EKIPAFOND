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
import com.mygdx.game.outils.Font;

public class RegleScreen extends ScreenAdapter {
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
    private Texture returnUpTexture;
    private Texture returnDownTexture;
    //
    private Button returnButton;
    public RegleScreen (ACLGame game){
        this.game=game;
        this.batch=game.batcher;
        stage=new Stage();
        create();
    }

    public void create(){
        //background
        backGroundTexture=new Texture(Gdx.files.internal("menu/fajrbackground.png"));
        backGroud=new Image(backGroundTexture);
        backGroud.setPosition(0,0);
        backGroud.setOrigin(0,0);
        //font
        titleFont=new Font(50, Color.RED);
        title= titleFont.getFont();
        contentFont=new Font(20, Color.BLACK);
        content=contentFont.getFont();
        //button
        returnUpTexture=new Texture(Gdx.files.internal("menu/buttons_3x_22.png"));
        returnDownTexture=new Texture(Gdx.files.internal("menu/buttons_3x_23.png"));
        //button style
        Button.ButtonStyle returnStyle=new Button.ButtonStyle();
        //start button
        returnStyle.up=new TextureRegionDrawable(new TextureRegion(returnUpTexture));
        returnStyle.down=new TextureRegionDrawable(new TextureRegion(returnDownTexture));
        returnButton=new Button(returnStyle);
        returnButton.setPosition(400-returnUpTexture.getWidth()/2,0);
        returnButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new MenuScreen(game));
            }
        });
        stage.addActor(backGroud);
        stage.addActor(returnButton);
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
        title.draw(batch, "Regle",300,400);
        content.draw(batch, "It's game pacman",0,300);
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
