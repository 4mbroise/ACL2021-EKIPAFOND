package com.mygdx.game.systems.physics.collisionhandler;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.systems.IteratingSystem;

public interface CollisionHandler {
    void handle(Entity colliedA, Entity colliedB);
}
