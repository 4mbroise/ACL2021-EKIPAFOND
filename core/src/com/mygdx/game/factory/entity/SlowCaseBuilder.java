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

public class SlowCaseBuilder extends PhysicalEntityBuilder{
    public SlowCaseBuilder(Assets assets, PhysicsSystem physicsSystem) {
        super(assets, physicsSystem);
    }

    @Override
    public Entity buildEntity(float x, float y) {
        Entity slowedCase = new Entity();

        TextureComponent textureComponent = new TextureComponent();
        textureComponent.setRegion(new TextureRegion(assets.getManager().get("tiles/spiderWeb.png", Texture.class)));
        slowedCase.add(textureComponent);

        TransformComponent transformComponent = new TransformComponent(new Vector3(x , y,5));
        slowedCase.add(transformComponent);

        Body body = physicsSystem.addSensorBody(x , y, World.CASE_DIMENSION,World.CASE_DIMENSION);
        body.setUserData(slowedCase);
        slowedCase.add(new SteeringComponent(body));
        slowedCase.add(new CollisionComponent());
        slowedCase.add(new TypeComponent(TypeComponent.TYPE_SLOW_MALUS));
        return slowedCase;    }
}
