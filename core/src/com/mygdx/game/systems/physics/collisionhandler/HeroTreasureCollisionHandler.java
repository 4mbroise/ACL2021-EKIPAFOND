package com.mygdx.game.systems.physics.collisionhandler;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.mygdx.game.ACLGame;
import com.mygdx.game.components.HeroComponent;
import com.mygdx.game.components.SteeringComponent;
import com.mygdx.game.screens.EndScreenWin;
import com.mygdx.game.screens.MazeTestScreen;
import com.mygdx.game.systems.physics.PhysicsSystem;

public class HeroTreasureCollisionHandler implements CollisionHandler{

    private ComponentMapper<HeroComponent> hm = ComponentMapper.getFor(HeroComponent.class);
    private ComponentMapper<SteeringComponent> bm = ComponentMapper.getFor(SteeringComponent.class);
    private Engine engine;
    private ACLGame game;

    public HeroTreasureCollisionHandler(Engine engine, ACLGame game) {
        this.engine = engine;
        this.game = game;
    }

    @Override
    public void handle(Entity colliedA, Entity colliedB) {
        HeroComponent heroComponent = hm.get(colliedA);
        heroComponent.setState(HeroComponent.STATE_STATIC);
        SteeringComponent steeringComponent = bm.get(colliedB);
        engine.removeEntity(colliedB);
        engine.getSystem(PhysicsSystem.class).getPhysicsWorld().destroyBody(steeringComponent.getBody());
        if (game.getLevel() <= 2){
            game.levelUp();
            game.setScreen(new MazeTestScreen(game));
        } else {
            //game.resetScore();
            game.setScreen(new EndScreenWin(game));
        }
    }
}
