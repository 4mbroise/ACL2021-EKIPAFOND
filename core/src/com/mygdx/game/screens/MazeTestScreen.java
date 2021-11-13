package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.ACLGame;
import com.mygdx.game.components.TypeComponent;
import com.mygdx.game.listeners.ACLGameListener;
import com.mygdx.game.systems.*;
import com.mygdx.game.systems.physics.CollisionsSystem;
import com.mygdx.game.systems.physics.PhysicsSystem;
import com.mygdx.game.systems.physics.collisionhandler.HeroWallCollisionHandler;

public class MazeTestScreen extends GameScreen {


    public MazeTestScreen(ACLGame game) {
        super(game);
        this.engine.addSystem(new RenderSystem(this.game.batcher));
        this.engine.addSystem(new MovementSystem());
        this.engine.addSystem(new HeroSystem());
        this.engine.addSystem(new PhysicsSystem());
        //this.engine.addSystem(new RandomMovementSystem());

        this.engine.addSystem(new DebugRenderSystem(this.game.batcher, this.game.camera));
        this.engine.addSystem(new AttackSystem());
        this.engine.addSystem(new DeathSystem());
        CollisionsSystem collisionsSystem = new CollisionsSystem();
        collisionsSystem.addCollisionStrategy(new HeroWallCollisionHandler(), TypeComponent.TYPE_HERO, TypeComponent.TYPE_WALL);
        this.engine.addSystem(collisionsSystem);
        this.assets.getManager().finishLoading();
        this.world.createMap();
        this.engine.addSystem(new MonsterSystem(this.world.getHero()));

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.act();
        stage.draw();
        this.world.updateMap();
    }

}

