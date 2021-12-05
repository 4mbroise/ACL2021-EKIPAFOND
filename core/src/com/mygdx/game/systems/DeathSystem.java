package com.mygdx.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.game.ACLGame;
import com.mygdx.game.components.*;
import com.mygdx.game.systems.physics.PhysicsSystem;

public class DeathSystem extends IteratingSystem {
    //componentMappers
    private ComponentMapper<HealthComponent> hm;
    private ComponentMapper<SteeringComponent> sm;
    private ComponentMapper<MonsterComponent> mm;

    /**
     * Constructor
     * @param game
     */
    public DeathSystem(ACLGame game) {
        super(Family.all(HealthComponent.class, SteeringComponent.class,MonsterComponent.class).get());
        hm=ComponentMapper.getFor(HealthComponent.class);
        sm=ComponentMapper.getFor(SteeringComponent.class);
        mm=ComponentMapper.getFor(MonsterComponent.class);
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
       HealthComponent hc=hm.get(entity);
       SteeringComponent sc=sm.get(entity);
       //if the health point <=0,the entity and the body will be destroyed
        if(hc.getHealthPoint()<=0&&mm.has(entity)){
            getEngine().removeEntity(entity);
            getEngine().getSystem(PhysicsSystem.class).getPhysicsWorld().destroyBody(sc.getBody());
       }
    }

}
