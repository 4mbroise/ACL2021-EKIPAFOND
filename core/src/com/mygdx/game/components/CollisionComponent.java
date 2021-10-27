package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

public class CollisionComponent implements Component {
    private Entity entityCollied = null;

    public Entity getEntityCollied() {
        return entityCollied;
    }

    public void setEntityCollied(Entity entityCollied) {
        this.entityCollied = entityCollied;
    }
}
