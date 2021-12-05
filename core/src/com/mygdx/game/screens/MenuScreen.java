package com.mygdx.game.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.ACLGame;
import com.mygdx.game.Assets;

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
    private Texture regleUpTexture;
    private Texture regleDownTexture;
    //background
    private Image backGroud;
    //buttons
    private Button startButton;
    private Button regleButton;
    //font
    private BitmapFont title;
    //batch
    private Batch batch;
    //assets
    private Assets assets;
    //audio
    private Music BGM;

    /**
     * Constructor
     * @param game
     */
    public MenuScreen(final ACLGame game) {
        this.game = game;
        //initialization
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        batch=game.batcher;
        this.stage=new Stage(new StretchViewport(800, 480));
        group=new Group();
        assets=game.getAssets();
        //BGM
        BGM=assets.getManager().get("audio/BGM/MusMus-BGM-115.mp3");
        BGM.setLooping(true);
        BGM.setVolume(0.6f);
        create();
    }

    /**
     * Create the stage of the screen and place all the ui components on it for drawing
     */
    public void create(){
        //background
        backGroundTexture=assets.getManager().get("UI/sunsetbackground.png");
        backGroud=new Image(backGroundTexture);
        backGroud.setPosition(0,0);
        backGroud.setOrigin(0,0);
        backGroud.setSize(stage.getWidth(),stage.getHeight());
        //listener
        Gdx.input.setInputProcessor(stage);
        //buttons
        startUpTexture=assets.getManager().get("UI/startUp.png");
        startDownTexture=assets.getManager().get("UI/startDown.png");
        regleUpTexture=assets.getManager().get("UI/regleUp.png");
        regleDownTexture=assets.getManager().get("UI/regleUp.png");
        //button style
        Button.ButtonStyle startStyle=new Button.ButtonStyle();
        //start button
        startStyle.up=new TextureRegionDrawable(new TextureRegion(startUpTexture));
        startStyle.down=new TextureRegionDrawable(new TextureRegion(startDownTexture));
        startButton=new Button(startStyle);
        startButton.setName("StartButton");
        startButton.setPosition(stage.getWidth()/2-regleDownTexture.getWidth()/2,200);
        startButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.resetLevel();
                game.setScreen(new MazeTestScreen(game));
            }
        });
        //regle button
        Button.ButtonStyle regleStyle=new Button.ButtonStyle();
        regleStyle.up=new TextureRegionDrawable(new TextureRegion(regleUpTexture));
        regleStyle.down=new TextureRegionDrawable(new TextureRegion(regleDownTexture));
        regleButton=new Button(regleStyle);
        regleButton.setName("ruleButton");
        regleButton.setPosition(stage.getWidth()/2-regleUpTexture.getWidth()/2,100);
        regleButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new RegleScreen(game));
            }
        });
        //font
        title=assets.getManager().get("fonts/Retro_Gaming.ttf");
        //add actors to the group
        group.addActor(backGroud);
        group.addActor(startButton);
        group.addActor(regleButton);
        //add the group to the stage
        stage.addActor(group);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        assets.getManager().update();
        Gdx.gl.glClearColor( 0, 0, 0, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        batch.begin();
        stage.act();
        stage.draw();
        GlyphLayout titleG = new GlyphLayout();
        titleG.setText(title,"Enescroc");
        float titleW = titleG.width;
        title.draw(batch, titleG, (stage.getWidth()-titleW)/2, 400);
        batch.end();

    }


    @Override
    public void dispose() {
        BGM.dispose();
    }

    @Override
    public void hide() {
        super.hide();
        BGM.stop();
    }

    @Override
    public void pause() {
        BGM.pause();
    }

    @Override
    public void show() {
        super.show();
        BGM.play();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height, true);

    }

    @Override
    public void resume() {
        super.resume();
        BGM.play();
    }


}
