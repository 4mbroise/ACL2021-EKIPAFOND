package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
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
        this.engine.addSystem(new DebugRenderSystem(this.game.batcher, this.game.camera));
        CollisionsSystem collisionsSystem = new CollisionsSystem();
        collisionsSystem.addCollisionStrategy(new HeroWallCollisionHandler(), TypeComponent.TYPE_HERO, TypeComponent.TYPE_WALL);
        this.engine.addSystem(collisionsSystem);
        this.assets.getManager().finishLoading();
        this.world.createMap();

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.input.setInputProcessor(new ACLGameListener(this));
        this.world.updateMap();
    }

}

