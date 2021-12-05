package com.mygdx.game.systems.physics.collisionhandler;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.mygdx.game.components.HealthComponent;
import com.mygdx.game.components.HeroComponent;


public class HeroPhantomCollisionHandler implements CollisionHandler{

    private ComponentMapper<HealthComponent> healm= ComponentMapper.getFor(HealthComponent.class);
    private ComponentMapper<HeroComponent> hm=ComponentMapper.getFor(HeroComponent.class);


    @Override
    public void handle(Entity colliedA, Entity colliedB) {
        //get components
        HealthComponent healthComponent = healm.get(colliedA);
        HeroComponent herc= hm.get(colliedA);

        //if state of the hero is not the state invincibility or the state death,the hero is gonna be reduced 1 health point
        if(herc.getState()!=herc.STATE_INVINCIBILITY&&herc.getState()!=herc.STATE_DEATH) {
            healthComponent.reduceHealthPoint(1);
            herc.setStateInvincibility();
        }
    }
}
