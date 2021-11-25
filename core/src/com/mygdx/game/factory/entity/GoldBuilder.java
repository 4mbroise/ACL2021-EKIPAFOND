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


/**
 * Entity builder for gold coin
 */
public class GoldBuilder extends PhysicalEntityBuilder{

    public GoldBuilder(Assets assets, PhysicsSystem physicsSystem) {
        super(assets, physicsSystem);
    }

    @Override
    public Entity buildEntity(float x, float y) {
        Entity gold = new Entity();

        TextureComponent textureComponent = new TextureComponent();
        textureComponent.setRegion(new TextureRegion(assets.getManager().get("tiles/gold.png", Texture.class)));
        gold.add(textureComponent);

        TransformComponent transformComponent = new TransformComponent(new Vector3(x , y,5));
        gold.add(transformComponent);

        Body body = physicsSystem.addSensorBody(x , y, World.CASE_DIMENSION,World.CASE_DIMENSION);
        body.setUserData(gold);
        gold.add(new SteeringComponent(body));
        gold.add(new CollisionComponent());
        gold.add(new TypeComponent(TypeComponent.TYPE_GOLD));
        return gold;
    }
}
