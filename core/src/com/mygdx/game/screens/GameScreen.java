package com.mygdx.game.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.ACLGame;
import com.mygdx.game.Assets;
import com.mygdx.game.World;
import com.mygdx.game.components.TypeComponent;
import com.mygdx.game.listeners.ACLGameListener;
import com.mygdx.game.systems.*;
import com.mygdx.game.systems.pathfinding.PathFindingSystem;
import com.mygdx.game.systems.physics.CollisionsSystem;
import com.mygdx.game.systems.physics.PhysicsSystem;
import com.mygdx.game.systems.physics.collisionhandler.*;

public class GameScreen extends ScreenAdapter {

    public ACLGame game;// game
    public Assets assets;
    public Engine engine;
    public static World world;
    protected InputMultiplexer multiplexer;
    //button
    private Texture homeUpTexture;
    private Texture homeDownTexture;
    protected Button homeButton;
    protected Sound soundButton;
    //stage
    protected Stage stage;
    //audio
    private Music BGM;
    private SpriteBatch batch;
    //font
    private BitmapFont scoreRenderer;

    /**
     * Constructor
     * @param game the game
     */
    public GameScreen(ACLGame game) {
        this.engine = new PooledEngine();
        this.assets = game.getAssets();
        this.stage=new Stage(new StretchViewport(800, 480));
        this.game = game;
        //BGM
        this.BGM=assets.getManager().get("audio/BGM/MusMus-BGM-125.mp3");
        this.BGM.setLooping(true);
        this.BGM.setVolume(0.5f);
        //creat button
        createButton();
        //Add all the Systems
        this.engine.addSystem(new RenderSystem(game.batcher));
        this.engine.addSystem(new AnimationSystem(game));
        this.engine.addSystem(new MovementSystem());
        this.engine.addSystem(new HeroSystem());
        this.engine.addSystem(new PhysicsSystem());
        this.engine.addSystem(new RandomMovementSystem());
        this.engine.addSystem(new AttackSystem(game));
        this.engine.addSystem(new DeathSystem(game));
        this.engine.addSystem(new PathFindingSystem());
        this.engine.addSystem(new MonsterSystem());
        this.engine.addSystem(new HealthRenderSystem(game.batcher, game.getAssets(), stage));
        //create the world
        this.world = new World(this.engine, this.assets);
        CollisionsSystem collisionsSystem = new CollisionsSystem();
        collisionsSystem.addCollisionStrategy(new HeroWallCollisionHandler(), TypeComponent.TYPE_HERO, TypeComponent.TYPE_WALL);
        collisionsSystem.addCollisionStrategy(new HeroTreasureCollisionHandler(this.engine, this.game), TypeComponent.TYPE_HERO, TypeComponent.TYPE_TREASURE);
        collisionsSystem.addCollisionStrategy(new HeroTrapCollisionHandler(this.engine, (Sound) assets.getManager().get("audio/game/Fire.ogg")), TypeComponent.TYPE_HERO, TypeComponent.TYPE_TRAP);
        collisionsSystem.addCollisionStrategy(new HeroMagicCollisionHandler(this.engine, (Sound) assets.getManager().get("audio/game/Heal.ogg")), TypeComponent.TYPE_HERO, TypeComponent.TYPE_MAGIC);
        collisionsSystem.addCollisionStrategy(new HeroGoldCollisionHandler(this.engine, (Sound) assets.getManager().get("audio/game/coin_effect.mp3"), game), TypeComponent.TYPE_HERO, TypeComponent.TYPE_GOLD);
        collisionsSystem.addCollisionStrategy(new HeroMonsterCollisionHandler(), TypeComponent.TYPE_HERO, TypeComponent.TYPE_MONSTER);
        collisionsSystem.addCollisionStrategy(new HeroPhantomCollisionHandler(), TypeComponent.TYPE_HERO, TypeComponent.        TYPE_GHOST);
        collisionsSystem.addCollisionStrategy(new HeroSlowMalusCollisionHandler(this.engine, (Sound) assets.getManager().get("audio/game/web_effect.mp3"), game), TypeComponent.TYPE_HERO, TypeComponent.TYPE_SLOW_MALUS   );
        collisionsSystem.addCollisionStrategy(new HeroPortalCollisionHandler(this.engine, this.world), TypeComponent.TYPE_HERO, TypeComponent.TYPE_PORTAL);
        this.engine.addSystem(collisionsSystem);
        //add inputmultiplexer to the screen for the player operate
        this.multiplexer=new InputMultiplexer();
        multiplexer.addProcessor(new ACLGameListener(this));
        scoreRenderer = assets.getManager().get("fonts/Retro_Gaming2.ttf");
        this.batch=game.batcher;
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);
    }

    /**
     * create the return home button on the screen
     */
    public void createButton(){
        //button
        homeUpTexture=assets.getManager().get("UI/homeUp.png");
        homeDownTexture=assets.getManager().get("UI/homeDown.png");
        //button style
        Button.ButtonStyle homeStyle=new Button.ButtonStyle();
        //home button
        homeStyle.up=new TextureRegionDrawable(new TextureRegion(homeUpTexture));
        homeStyle.down=new TextureRegionDrawable(new TextureRegion(homeDownTexture));
        homeButton=new Button(homeStyle);
        homeButton.setName("HomeButton");
        homeButton.setPosition(stage.getWidth()-100, stage.getHeight()-100);
        homeButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.resetLevel();
                game.resetScore();
                game.setScreen(new MenuScreen(game));

            }
        });
        stage.addActor(homeButton);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        this.engine.update(delta);
        for(Actor actor : stage.getActors()) {
            if(actor.getName().equals("ScoreButton")){
                actor.addAction(Actions.removeActor());
            }
        }
        batch.begin();
        GlyphLayout scoreContent = new GlyphLayout();
        String scoreText= String.valueOf(game.getScore());
        scoreContent.setText(scoreRenderer,scoreText);
        TextButton scoreButton = new TextButton("Score: " + game.getScore(), new TextButton.TextButtonStyle(null, null, null, scoreRenderer));
        scoreButton.setPosition(0,stage.getHeight()-scoreButton.getHeight());
        scoreButton.setName("ScoreButton");
        stage.addActor(scoreButton);
        this.batch.end();
        //game.setScreen(new GameAITestScreen(game, game.getAssets()));
        stage.act();
        stage.draw();
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
    public void pause() {
        super.pause();
        BGM.pause();
    }

    @Override
    public void hide() {
        super.hide();
        BGM.dispose();
    }

    @Override
    public void dispose() {
        super.dispose();
        BGM.dispose();
    }

    @Override
    public void resume() {
        super.resume();
        BGM.play();
    }
}