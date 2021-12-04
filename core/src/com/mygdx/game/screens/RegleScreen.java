package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.ACLGame;
import com.mygdx.game.Assets;

public class RegleScreen extends ScreenAdapter {
    //main game
    private ACLGame game;
    //stage
    private Stage stage;
    //font
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
    //button
    private Button homeButton;
    //assets
    private Assets assets;

    /**
     * Constructor
     * @param game
     */
    public RegleScreen (ACLGame game){
        this.game=game;
        this.batch=game.batcher;
        this.stage=new Stage(new StretchViewport(800, 480));
        assets=game.getAssets();
        create();
    }

    /**
     * Create the stage of the screen and place all the ui components on it for drawing
     */
    public void create(){
        //background
        backGroundTexture=assets.getManager().get("UI/noonbackground.png");
        backGroud=new Image(backGroundTexture);
        backGroud.setPosition(0,0);
        backGroud.setOrigin(0,0);
        backGroud.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        //font
        title= assets.getManager().get("fonts/Retro_Gaming.ttf");
        content=assets.getManager().get("fonts/Retro_Gaming2.ttf");
        //button
        homeUpTexture=assets.getManager().get("UI/homeUp.png");
        homeDownTexture=assets.getManager().get("UI/homeUp.png");
        //button style
        Button.ButtonStyle homeStyle=new Button.ButtonStyle();
        //start button
        homeStyle.up=new TextureRegionDrawable(new TextureRegion(homeUpTexture));
        homeStyle.down=new TextureRegionDrawable(new TextureRegion(homeDownTexture));
        homeButton=new Button(homeStyle);
        homeButton.setPosition(stage.getWidth()/2-homeUpTexture.getWidth()/2,50);
        homeButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new MenuScreen(game));
            }
        });
        //add actors to the stage
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
        //draw the font
        GlyphLayout titleG = new GlyphLayout();
        titleG.setText(title,"Rule");
        float titleW = titleG.width;
        title.draw(batch, titleG, (stage.getWidth()-titleW)/2, 400);
        GlyphLayout contentG = new GlyphLayout();
        String contentS= "- 'Z/W' to move upwards\n" +
                " - 'S' to move downwards\n" +
                " - 'Q/A' to move left\n" +
                " - 'D' to move right\n" +
                " - 'J' to attack";
        contentG.setText(content,contentS);
        content.draw(batch,contentS, 20, 300);
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
        stage.getViewport().update(width, height, true);
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