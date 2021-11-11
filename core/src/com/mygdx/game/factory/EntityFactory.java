package com.mygdx.game.factory;

import com.badlogic.ashley.core.Entity;
import com.mygdx.game.factory.entity.EntityBuilder;

import java.util.HashMap;
import java.util.Map;

public class EntityFactory {
    private Map<String, EntityBuilder> entityBuilderMap;

    public EntityFactory() {
        this.entityBuilderMap = new HashMap<String, EntityBuilder>();
    }

    public Entity createEntity(String code, float x, float y){
        if(entityBuilderMap.containsKey(code)){
            return entityBuilderMap.get(code).buildEntity(x, y);
        } else {
            System.out.println("Case "+code + "non implémentée");
        }
        return null;
    }

    public void addEntityBuilder(String code, EntityBuilder builder){
        this.entityBuilderMap.put(code, builder);
    }
}
