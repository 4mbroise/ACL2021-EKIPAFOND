package com.mygdx.game.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.ACLGame;
import com.mygdx.game.tools.Font;

public class MenuScreen extends ScreenAdapter{
    //main game
    private ACLGame game;
    //stage
    private Stage stage;
    //Group
    private Group group;
    //textures
    private Texture backGroundTexture;
    private Texture startUpTexture;
    private Texture startDownTexture;
    private Texture setUpTexture;
    private Texture setDownTexture;

    //background
    private Image backGroud;

    //buttons
    private Button startButton;
    private Button setButton;
    //font
    private Font font;
    private BitmapFont title;
    //batch
    private Batch batch;

    public MenuScreen(final ACLGame game) {
        this.game = game;
        //initialization
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        batch=game.batcher;
        stage=new Stage();
        group=new Group();
        create();
    }

    public void create(){
        //background
        backGroundTexture=new Texture(Gdx.files.internal("menu/fajrbackground.png"));
        backGroud=new Image(backGroundTexture);
        backGroud.setPosition(0,0);
        backGroud.setOrigin(0,0);
        //listener
        Gdx.input.setInputProcessor(stage);
        //buttons
        startUpTexture=new Texture(Gdx.files.internal("menu/buttons_3x_2.png"));
        startDownTexture=new Texture(Gdx.files.internal("menu/buttons_3x_3.png"));
        setUpTexture=new Texture(Gdx.files.internal("menu/buttons_3x_8.png"));
        setDownTexture=new Texture(Gdx.files.internal("menu/buttons_3x_9.png"));
        //button style
        Button.ButtonStyle startStyle=new Button.ButtonStyle();
        //start button
        startStyle.up=new TextureRegionDrawable(new TextureRegion(startUpTexture));
        startStyle.down=new TextureRegionDrawable(new TextureRegion(startDownTexture));
        startButton=new Button(startStyle);

        startButton.setPosition(400-startUpTexture.getWidth()/2,200);
        startButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new GameScreen(game, game.getAssets()));
            }
        });
        //set button
        Button.ButtonStyle setStyle=new Button.ButtonStyle();
        setStyle.up=new TextureRegionDrawable(new TextureRegion(setUpTexture));
        setStyle.down=new TextureRegionDrawable(new TextureRegion(setDownTexture));
        setButton=new Button(setStyle);
        setButton.setPosition(400-setUpTexture.getWidth()/2,100);
        setButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new RegleScreen(game));
            }
        });

        //font
        font=new Font(50,Color.RED);
        title=font.getFont();

        group.addActor(backGroud);
        group.addActor(startButton);
        group.addActor(setButton);
        stage.addActor(group);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor( 0, 0, 0, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        batch.begin();
        stage.act();
        stage.draw();
        title.draw(batch, "Pacman",300,400);
        batch.end();

    }


    @Override
    public void dispose() {
        if (startUpTexture!=null){
            startUpTexture.dispose();
        }
        if (startDownTexture!=null){
            startDownTexture.dispose();
        }
        if (setUpTexture!=null){
            setUpTexture.dispose();
        }
        if (setDownTexture!=null){
            setDownTexture.dispose();
        }
        if(stage!=null){
            stage.dispose();
        }
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void pause() {

    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void resume() {
        super.resume();
    }


}