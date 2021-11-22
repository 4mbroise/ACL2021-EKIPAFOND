package com.mygdx.game.factory.entity;

import com.mygdx.game.Assets;

public abstract class GraphicalEntityBuilder implements EntityBuilder {
    protected Assets assets;

    public GraphicalEntityBuilder(Assets assets) {
        this.assets = assets;
    }
}
