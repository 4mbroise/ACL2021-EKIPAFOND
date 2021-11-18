package com.mygdx.game.systems.physics.collisionhandler;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.components.HealthComponent;
import com.mygdx.game.components.HeroComponent;
import com.mygdx.game.components.MonsterComponent;
import com.mygdx.game.components.SteeringComponent;

public class HeroWallCollisionHandler implements CollisionHandler{

    ComponentMapper<HeroComponent> hm = ComponentMapper.getFor(HeroComponent.class);
    ComponentMapper<SteeringComponent> bm = ComponentMapper.getFor(SteeringComponent.class);
    ComponentMapper<MonsterComponent> mm = ComponentMapper.getFor(MonsterComponent.class);
    ComponentMapper<HealthComponent> healm= ComponentMapper.getFor(HealthComponent.class);

    @Override
    public void handle(Entity colliedA, Entity colliedB) {
        HeroComponent heroComponent = hm.get(colliedA);
        heroComponent.setState(HeroComponent.STATE_STATIC);
        SteeringComponent steeringComponent = bm.get(colliedA);
        HealthComponent healthComponent = healm.get(colliedA);

       // System.out.println(healthComponent.getHealthPoint());
        if(mm.has(colliedB)) {
            System.out.println(healthComponent.getHealthPoint());

            healthComponent.reduceHealthPoint(1);
        }
        //steeringComponent.setLinearVelocity(new Vector2(0,0));
    }
}
