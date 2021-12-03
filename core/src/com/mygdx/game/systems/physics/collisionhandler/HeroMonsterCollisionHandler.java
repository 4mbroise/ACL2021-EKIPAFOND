package com.mygdx.game.systems.physics.collisionhandler;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.mygdx.game.components.*;


public class HeroMonsterCollisionHandler implements CollisionHandler{

    private ComponentMapper<HealthComponent> healm= ComponentMapper.getFor(HealthComponent.class);
    private ComponentMapper<HeroComponent> hm=ComponentMapper.getFor(HeroComponent.class);

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
