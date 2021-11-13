package com.mygdx.game.systems.pathfinding;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class PathFindingSystem extends IteratingSystem {
    public PathFindingSystem() {
        super(family);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }
}
