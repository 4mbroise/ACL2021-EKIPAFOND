package com.mygdx.game.systems.physics.collisionhandler;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.ACLGame;
import com.mygdx.game.components.*;
import com.mygdx.game.screens.EndScreenLoose;
import com.mygdx.game.systems.physics.PhysicsSystem;

public class HeroTrapCollisionHandler implements CollisionHandler{

    ComponentMapper<HeroComponent> hm = ComponentMapper.getFor(HeroComponent.class);
    ComponentMapper<SteeringComponent> bm = ComponentMapper.getFor(SteeringComponent.class);
    ComponentMapper<HealthComponent> healthMapper = ComponentMapper.getFor(HealthComponent.class);
    Engine engine;
    Sound sound;
    public HeroTrapCollisionHandler(Engine engine, Sound sound) {
        this.engine = engine;
        this.sound=sound;
    }

    @Override
    public void handle(Entity colliedA, Entity colliedB) {
        sound.play();
        HeroComponent heroComponent = hm.get(colliedA);
        heroComponent.setState(HeroComponent.STATE_STATIC);
        SteeringComponent steeringComponent = bm.get(colliedB);
        engine.removeEntity(colliedB);
        engine.getSystem(PhysicsSystem.class).getPhysicsWorld().destroyBody(steeringComponent.getBody());
        HealthComponent hc = healthMapper.get(colliedA);
        if(heroComponent.getState()!=heroComponent.STATE_INVINCIBILITY&&heroComponent.getState()!=heroComponent.STATE_DEATH) {
            hc.reduceHealthPoint(1);
        }

    }
}
