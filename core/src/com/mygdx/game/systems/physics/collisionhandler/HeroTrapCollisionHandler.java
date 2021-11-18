package com.mygdx.game.systems.physics.collisionhandler;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.mygdx.game.ACLGame;
import com.mygdx.game.components.*;
import com.mygdx.game.screens.EndScreen;
import com.mygdx.game.systems.physics.PhysicsSystem;

public class HeroTrapCollisionHandler implements CollisionHandler{

    ComponentMapper<HeroComponent> hm = ComponentMapper.getFor(HeroComponent.class);
    ComponentMapper<SteeringComponent> bm = ComponentMapper.getFor(SteeringComponent.class);
    ComponentMapper<HealthComponent> healthMapper = ComponentMapper.getFor(HealthComponent.class);
    Engine engine;
    ACLGame game;

    public HeroTrapCollisionHandler(Engine engine, ACLGame game) {
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
        HealthComponent hc = healthMapper.get(colliedA);
        if (hc.getHealthPoint() <= 1) {
            game.setScreen(new EndScreen(game));
        }
        hc.reduceHealthPoint(1);
        System.out.println(hc.getHealthPoint());
    }
}
