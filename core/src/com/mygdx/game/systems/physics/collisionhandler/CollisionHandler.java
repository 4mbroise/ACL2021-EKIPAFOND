package com.mygdx.game.systems.physics.collisionhandler;

import com.badlogic.ashley.core.Entity;

public interface CollisionHandler {
    void handle(Entity colliedA, Entity colliedB);
}
