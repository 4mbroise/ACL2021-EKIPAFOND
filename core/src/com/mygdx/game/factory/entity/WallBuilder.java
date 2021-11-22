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

public class WallBuilder extends PhysicalEntityBuilder{

    public WallBuilder(Assets assetManager, PhysicsSystem physicsSystem) {
        super(assetManager, physicsSystem);
    }

    @Override
    public Entity buildEntity(float x, float y) {
        Entity wall = new Entity();
        TextureComponent textureComponent = new TextureComponent();
        textureComponent.setRegion(new TextureRegion(assets.getManager().get("tiles/wall.png", Texture.class)));
        wall.add(textureComponent);
        TransformComponent transformComponent = new TransformComponent(new Vector3(x , y,0));
        wall.add(transformComponent);
        Body body = physicsSystem.addStaticBody(x , y, World.CASE_DIMENSION,World.CASE_DIMENSION);
        body.setUserData(wall);
        //System.out.print("  Wall  ");
        wall.add(new SteeringComponent(body));
        wall.add(new CollisionComponent());
        wall.add(new TypeComponent(TypeComponent.TYPE_WALL));
        return wall;
    }
}
