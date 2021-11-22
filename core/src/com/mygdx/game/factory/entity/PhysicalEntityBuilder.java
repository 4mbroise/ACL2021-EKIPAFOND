package com.mygdx.game.factory.entity;

import com.mygdx.game.Assets;
import com.mygdx.game.systems.physics.PhysicsSystem;

public abstract class PhysicalEntityBuilder implements EntityBuilder{
    protected Assets assets;
    protected PhysicsSystem physicsSystem;

    public PhysicalEntityBuilder(Assets assets, PhysicsSystem physicsSystem) {
        this.assets = assets;
        this.physicsSystem = physicsSystem;
    }
}
