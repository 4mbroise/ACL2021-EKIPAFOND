package com.mygdx.game.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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

    public ACLGame game;
    public Assets assets;
    public Engine engine;
    public static World world;
    public boolean active;
    protected InputMultiplexer multiplexer;
    //button
    private Texture homeUpTexture;
    private Texture homeDownTexture;
    protected Button homeButton;
    //stage
    protected Stage stage;
    public GameScreen(ACLGame game) {
        this.engine = new PooledEngine();

        //Add System
        this.engine.addSystem(new RenderSystem(game.batcher));
        this.engine.addSystem(new AnimationSystem());
        this.engine.addSystem(new MovementSystem());
        this.engine.addSystem(new HeroSystem());
        this.engine.addSystem(new PhysicsSystem());
        this.engine.addSystem(new RandomMovementSystem());
        this.engine.addSystem(new DebugRenderSystem(game.batcher, game.camera));
        this.engine.addSystem(new AttackSystem());
        this.engine.addSystem(new DeathSystem());
        this.engine.addSystem(new PathFindingSystem());
        this.engine.addSystem(new HealthRenderSystem(game.batcher, game.getAssets()));
        CollisionsSystem collisionsSystem = new CollisionsSystem();
        collisionsSystem.addCollisionStrategy(new HeroWallCollisionHandler(), TypeComponent.TYPE_HERO, TypeComponent.TYPE_WALL);
        collisionsSystem.addCollisionStrategy(new HeroTreasureCollisionHandler(this.engine), TypeComponent.TYPE_HERO, TypeComponent.TYPE_TREASURE);
        collisionsSystem.addCollisionStrategy(new HeroTrapCollisionHandler(this.engine), TypeComponent.TYPE_HERO, TypeComponent.TYPE_TRAP);
        collisionsSystem.addCollisionStrategy(new HeroMagicCollisionHandler(this.engine), TypeComponent.TYPE_HERO, TypeComponent.TYPE_MAGIC);
        collisionsSystem.addCollisionStrategy(new HeroPortalCollisionHandler(this.engine), TypeComponent.TYPE_HERO, TypeComponent.TYPE_PORTAL);

        this.engine.addSystem(collisionsSystem);

        this.game = game;
        this.assets = game.getAssets();
        this.world = new World(this.engine, this.assets);
        this.stage=new Stage();
        this.multiplexer=new InputMultiplexer();
        multiplexer.addProcessor(new ACLGameListener(this));
        createBouton();
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);
    }

    public void createBouton(){
        //button
        homeUpTexture=assets.getManager().get("UI/homeUp.png");
        homeDownTexture=assets.getManager().get("UI/homeDown.png");
        //button style
        Button.ButtonStyle homeStyle=new Button.ButtonStyle();
        //start button
        homeStyle.up=new TextureRegionDrawable(new TextureRegion(homeUpTexture));
        homeStyle.down=new TextureRegionDrawable(new TextureRegion(homeDownTexture));
        homeButton=new Button(homeStyle);
        homeButton.setPosition(700,400);
        homeButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new MenuScreen(game));
            }
        });
        stage.addActor(homeButton);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        this.engine.update(delta);
        //game.setScreen(new GameAITestScreen(game, game.getAssets()));
    }



    @Override
    public void show() {
        super.show();
    }
}