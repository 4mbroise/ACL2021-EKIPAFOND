package com.mygdx.game.systems.physics.collisionhandler;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.mygdx.game.ACLGame;
import com.mygdx.game.components.HealthComponent;
import com.mygdx.game.components.HeroComponent;
import com.mygdx.game.screens.EndScreenLoose;


public class HeroPhantomCollisionHandler implements CollisionHandler{

    ComponentMapper<HealthComponent> healm= ComponentMapper.getFor(HealthComponent.class);
    ComponentMapper<HeroComponent> hm=ComponentMapper.getFor(HeroComponent.class);
    Engine engine;
    ACLGame game;
    float stateTime;

    public HeroPhantomCollisionHandler(Engine engine, ACLGame game) {
        this.engine = engine;
        this.game = game;
        stateTime=0;
    }

    @Override
    public void handle(Entity colliedA, Entity colliedB) {
        HealthComponent healthComponent = healm.get(colliedA);
        HeroComponent herc= hm.get(colliedA);

        if(herc.getState()!=herc.STATE_INVINCIBILITY&&herc.getState()!=herc.STATE_DEATH) {
            healthComponent.reduceHealthPoint(1);
            herc.setStateInvincibility();
        }
    }
}
