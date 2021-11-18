package com.mygdx.game.systems.physics.collisionhandler;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ACLGame;
import com.mygdx.game.components.*;
import com.mygdx.game.screens.EndScreen;
import com.mygdx.game.systems.HeroSystem;
import com.mygdx.game.systems.physics.PhysicsSystem;

public class HeroMonsterCollisionHandler implements CollisionHandler{

    ComponentMapper<HealthComponent> healm= ComponentMapper.getFor(HealthComponent.class);
    ComponentMapper<HeroComponent> hm=ComponentMapper.getFor(HeroComponent.class);
    Engine engine;
    ACLGame game;
    float stateTime;

    public HeroMonsterCollisionHandler(Engine engine, ACLGame game) {
        this.engine = engine;
        this.game = game;
        stateTime=0;

    }

    @Override
    public void handle(Entity colliedA, Entity colliedB) {
        HealthComponent healthComponent = healm.get(colliedA);
        HeroComponent herc= hm.get(colliedA);

        if(herc.getState()!=herc.STATE_INVINCIBILITY) {
            healthComponent.reduceHealthPoint(1);
            herc.setState(herc.STATE_INVINCIBILITY);
        }


        if (healthComponent.getHealthPoint() <= 0) {
            game.setScreen(new EndScreen(game));
        }

        System.out.println(healthComponent.getHealthPoint());
    }
}
