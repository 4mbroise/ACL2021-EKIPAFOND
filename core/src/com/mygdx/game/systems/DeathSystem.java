package com.mygdx.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.game.components.*;
import com.mygdx.game.systems.physics.PhysicsSystem;

public class DeathSystem extends IteratingSystem {
    private ComponentMapper<HealthComponent> hm;
    private ComponentMapper<SteeringComponent> sm;


    public DeathSystem() {
        super(Family.all(HealthComponent.class, SteeringComponent.class).get());
        hm=ComponentMapper.getFor(HealthComponent.class);
        sm=ComponentMapper.getFor(SteeringComponent.class);

    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
       HealthComponent hc=hm.get(entity);
       SteeringComponent sc=sm.get(entity);
        if(hc.getHealthPoint()<=0){
            getEngine().removeEntity(entity);
            getEngine().getSystem(PhysicsSystem.class).getPhysicsWorld().destroyBody(sc.getBody());
       }
    }

}
