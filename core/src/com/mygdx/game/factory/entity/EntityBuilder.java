package com.mygdx.game.factory.entity;

import com.badlogic.ashley.core.Entity;

public interface EntityBuilder {
    Entity buildEntity(float x, float y);
}
