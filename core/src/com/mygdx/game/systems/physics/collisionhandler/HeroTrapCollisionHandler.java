package com.mygdx.game.systems.physics.collisionhandler;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.mygdx.game.components.HeroComponent;
import com.mygdx.game.components.SteeringComponent;

public class HeroTrapCollisionHandler implements CollisionHandler{

    ComponentMapper<HeroComponent> hm = ComponentMapper.getFor(HeroComponent.class);
    ComponentMapper<SteeringComponent> bm = ComponentMapper.getFor(SteeringComponent.class);

    @Override
    public void handle(Entity colliedA, Entity colliedB) {
        HeroComponent heroComponent = hm.get(colliedA);
        heroComponent.setState(HeroComponent.STATE_STATIC);
        SteeringComponent steeringComponent = bm.get(colliedA);


        System.out.println("Health--");
    }
}
