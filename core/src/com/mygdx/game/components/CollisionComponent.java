package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.*;

public class CollisionComponent implements Component, Iterable<Entity>{
    private Set<Entity> entiesCollied;

    public CollisionComponent() {
        this.entiesCollied = new HashSet<>();
    }

    public void addEntityCollied(Entity entityCollied) {
        this.entiesCollied.add(entityCollied);
    }

    public void removeEntityCollied(Entity entityCollied) {
        this.entiesCollied.remove(entityCollied);
    }

    @Override
    public Iterator<Entity> iterator() {

        return new HashSet<>(this.entiesCollied).iterator();
    }
}
