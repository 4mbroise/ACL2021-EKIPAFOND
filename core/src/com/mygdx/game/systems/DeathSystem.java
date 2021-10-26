package com.mygdx.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.game.components.HealthComponent;

public class DeathSystem extends IteratingSystem {
    private ComponentMapper<HealthComponent> hm;

    public DeathSystem() {
        super(Family.all(HealthComponent.class).get());
        hm=ComponentMapper.getFor(HealthComponent.class);
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
       HealthComponent hc=hm.get(entity);
       if(hc.getHealthPoint()==0){
            getEngine().removeEntity(entity);
       }
    }

}
