package com.mygdx.game.factory.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Assets;
import com.mygdx.game.components.*;

public class GroundBuilder extends GraphicalEntityBuilder {

    public GroundBuilder(Assets assetManager) {
        super(assetManager);
    }

    @Override
    public Entity buildEntity(float x, float y) {
        Entity ground = new Entity();
        TextureComponent textureComponent = new TextureComponent();
        textureComponent.setRegion(new TextureRegion(assets.getManager().get("tiles/ground.png", Texture.class)));
        ground.add(textureComponent);
        TransformComponent transformComponent = new TransformComponent(new Vector3(x , y,0));
        ground.add(transformComponent);
        ground.add(new TypeComponent(TypeComponent.TYPE_GROUND));
        return ground;
    }
}
