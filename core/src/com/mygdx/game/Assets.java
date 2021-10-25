package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

    private final AssetManager assetManager = new AssetManager(new InternalFileHandleResolver());

    public Assets() {
        assetManager.load("sprites/cherry.png", Texture.class);
        assetManager.load("sprites/damage_up.png", Texture.class);
    }

    public AssetManager getManager() {
        return assetManager;
    }
}
