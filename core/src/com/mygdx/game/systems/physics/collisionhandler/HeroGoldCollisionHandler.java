package com.mygdx.game.systems.physics.collisionhandler;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.ACLGame;
import com.mygdx.game.components.HeroComponent;
import com.mygdx.game.components.SteeringComponent;
import com.mygdx.game.systems.physics.PhysicsSystem;

public class HeroGoldCollisionHandler implements CollisionHandler{

    private ComponentMapper<HeroComponent> hm = ComponentMapper.getFor(HeroComponent.class);
    private ComponentMapper<SteeringComponent> sm = ComponentMapper.getFor(SteeringComponent.class);

    private Engine engine;
    private Sound sound;
    private ACLGame game;

    public HeroGoldCollisionHandler(Engine engine, Sound sound, ACLGame game) {
        this.engine = engine;
        this.sound=sound;
        this.game=game;

    }
    @Override
    public void handle(Entity colliedA, Entity colliedB) {
        HeroComponent heroComponent = hm.get(colliedA);
        heroComponent.setState(HeroComponent.STATE_STATIC);
        SteeringComponent steeringComponent = sm.get(colliedB);
        engine.removeEntity(colliedB);
        sound.play();
        engine.getSystem(PhysicsSystem.class).getPhysicsWorld().destroyBody(steeringComponent.getBody());
        game.increaseScore();

    }
}
