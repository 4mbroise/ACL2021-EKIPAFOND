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

    ComponentMapper<HeroComponent> hm = ComponentMapper.getFor(HeroComponent.class);
    ComponentMapper<SteeringComponent> bm = ComponentMapper.getFor(SteeringComponent.class);
    ComponentMapper<HealthComponent> healthMapper = ComponentMapper.getFor(HealthComponent.class);
    Engine engine;
    private Sound sound;
    public HeroMagicCollisionHandler(Engine engine, ACLGame game) {
        this.engine = engine;
        sound=game.getAssets().getManager().get("audio/game/Heal.ogg");

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
            hc.addHealthPoint(1);
            System.out.println(hc.getHealthPoint());
        }
    }
}
