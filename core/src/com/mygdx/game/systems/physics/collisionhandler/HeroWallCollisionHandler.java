package com.mygdx.game.systems.physics.collisionhandler;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.mygdx.game.components.HeroComponent;

public class HeroWallCollisionHandler implements CollisionHandler{

    private ComponentMapper<HeroComponent> hm = ComponentMapper.getFor(HeroComponent.class);

    @Override
    public void handle(Entity colliedA, Entity colliedB) {
        HeroComponent heroComponent = hm.get(colliedA);
        heroComponent.setState(HeroComponent.STATE_STATIC);
    }
}
