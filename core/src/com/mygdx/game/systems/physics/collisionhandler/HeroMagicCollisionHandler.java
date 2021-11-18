package com.mygdx.game.systems.physics.collisionhandler;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.mygdx.game.components.*;
import com.mygdx.game.systems.physics.PhysicsSystem;

public class HeroMagicCollisionHandler implements CollisionHandler{

    ComponentMapper<HeroComponent> hm = ComponentMapper.getFor(HeroComponent.class);
    ComponentMapper<SteeringComponent> bm = ComponentMapper.getFor(SteeringComponent.class);
    ComponentMapper<HealthComponent> healthMapper = ComponentMapper.getFor(HealthComponent.class);
    Engine engine;

    public HeroMagicCollisionHandler(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void handle(Entity colliedA, Entity colliedB) {
        HeroComponent heroComponent = hm.get(colliedA);
        heroComponent.setState(HeroComponent.STATE_STATIC);
        SteeringComponent steeringComponent = bm.get(colliedB);
        if (colliedA.getComponent(HealthComponent.class).healthPoint < heroComponent.getStartHealth()) {
        engine.removeEntity(colliedB);
        engine.getSystem(PhysicsSystem.class).getPhysicsWorld().destroyBody(steeringComponent.getBody());
        HealthComponent hc = healthMapper.get(colliedA);
        hc.addHealthPoint(1);
        System.out.println(hc.getHealthPoint());
        }
    }
}
