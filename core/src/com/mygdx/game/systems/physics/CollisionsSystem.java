package com.mygdx.game.systems.physics;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.game.components.CollisionComponent;
import com.mygdx.game.components.TypeComponent;
import com.mygdx.game.systems.physics.collisionhandler.CollisionHandler;

import java.util.HashMap;
import java.util.Map;

public class CollisionsSystem extends IteratingSystem {

    private ComponentMapper<CollisionComponent> cm;
    private ComponentMapper<TypeComponent>      tm;
    private Map<Integer, HashMap<Integer, CollisionHandler>> collisionStrategies = new HashMap<Integer,HashMap<Integer, CollisionHandler>>();

    public CollisionsSystem() {
        super(Family.all(CollisionComponent.class).get());
        this.cm = ComponentMapper.getFor(CollisionComponent.class);
        this.tm = ComponentMapper.getFor(TypeComponent.class);
    }

    public void addCollisionStrategy(CollisionHandler collisionStrategy, int colliedTypeA, int colliedTypeB){
        if(!collisionStrategies.containsKey(colliedTypeA)){
            collisionStrategies.put(colliedTypeA, new HashMap<Integer, CollisionHandler>());
        }
        if(!collisionStrategies.get(colliedTypeA).containsKey(colliedTypeB)){
            collisionStrategies.get(colliedTypeA).put(colliedTypeB, collisionStrategy);
        }
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CollisionComponent  cComp = cm.get(entity);
        TypeComponent       tComp = tm.get(entity);

        if(cComp.getEntityCollied()!=null){
            TypeComponent tCompCollied = tm.get(cComp.getEntityCollied());
            if(collisionStrategies.containsKey(tComp.getType()) && collisionStrategies.get(tComp.getType()).containsKey(tCompCollied.getType())){
                collisionStrategies.get(tComp.getType()).get(tCompCollied.getType()).handle(entity, cComp.getEntityCollied());
            }
        }
    }
}
