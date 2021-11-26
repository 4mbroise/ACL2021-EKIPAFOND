package com.mygdx.game.systems.physics.collisionhandler;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.ACLGame;
import com.mygdx.game.components.*;
import com.mygdx.game.systems.physics.PhysicsSystem;

public class HeroMagicCollisionHandler implements CollisionHandler{

    private ComponentMapper<HeroComponent> hm = ComponentMapper.getFor(HeroComponent.class);
    private ComponentMapper<SteeringComponent> bm = ComponentMapper.getFor(SteeringComponent.class);
    private ComponentMapper<HealthComponent> healthMapper = ComponentMapper.getFor(HealthComponent.class);
    private Engine engine;
    private Sound sound;
    public HeroMagicCollisionHandler(Engine engine, Sound sound) {
        this.engine = engine;
        this.sound=sound;
    }

    @Override
    public void handle(Entity colliedA, Entity colliedB) {
        HeroComponent heroComponent = hm.get(colliedA);
        heroComponent.setState(HeroComponent.STATE_STATIC);
        SteeringComponent steeringComponent = bm.get(colliedB);
        if (colliedA.getComponent(HealthComponent.class).healthPoint < heroComponent.getStartHealth()) {
            sound.play();
            engine.removeEntity(colliedB);
            engine.getSystem(PhysicsSystem.class).getPhysicsWorld().destroyBody(steeringComponent.getBody());
            HealthComponent hc = healthMapper.get(colliedA);
            if(heroComponent.getState()!=heroComponent.STATE_DEATH) {
                //System.out.println("HERO///////////"+heroComponent.getState());
                hc.addHealthPoint(1);
            }
        }
    }
}
