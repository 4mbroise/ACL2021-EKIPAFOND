package com.mygdx.game.systems.physics.collisionhandler;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.components.BodyComponent;
import com.mygdx.game.components.HeroComponent;

public class HeroWallCollisionHandler implements CollisionHandler{

    ComponentMapper<HeroComponent> hm = ComponentMapper.getFor(HeroComponent.class);
    ComponentMapper<BodyComponent> bm = ComponentMapper.getFor(BodyComponent.class);

    @Override
    public void handle(Entity colliedA, Entity colliedB) {
        HeroComponent heroComponent = hm.get(colliedA);
        heroComponent.setState(HeroComponent.STATE_STATIC);
        BodyComponent bodyComponent = bm.get(colliedA);
        bodyComponent.setLinearVelocity(new Vector2(0,0));
    }
}
