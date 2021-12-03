package com.mygdx.game.factory.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Assets;
import com.mygdx.game.World;
import com.mygdx.game.components.*;
import com.mygdx.game.systems.physics.PhysicsSystem;

public class MagicBuilder extends PhysicalEntityBuilder{

    public MagicBuilder(Assets assetManager, PhysicsSystem physicsSystem) {
        super(assetManager, physicsSystem);
    }

    @Override
    public Entity buildEntity(float x, float y) {

        Entity magic = new Entity();

        TextureComponent textureComponent = new TextureComponent();
        textureComponent.setRegion(new TextureRegion(assets.getManager().get("tiles/magic.png", Texture.class)));
        magic.add(textureComponent);

        TransformComponent transformComponent = new TransformComponent(new Vector3(x , y,5));
        magic.add(transformComponent);

        Body body = physicsSystem.addSensorBody(x , y, World.CASE_DIMENSION,World.CASE_DIMENSION);
        body.setUserData(magic);
        magic.add(new SteeringComponent(body));
        magic.add(new CollisionComponent());
        magic.add(new TypeComponent(TypeComponent.TYPE_MAGIC));
        return magic;
    }
}