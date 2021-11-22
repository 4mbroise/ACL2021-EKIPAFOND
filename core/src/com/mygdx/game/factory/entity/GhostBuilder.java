package com.mygdx.game.factory.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Assets;
import com.mygdx.game.World;
import com.mygdx.game.components.*;
import com.mygdx.game.systems.physics.PhysicsSystem;


/**
 * Entity builder for ghosts
 */
public class GhostBuilder implements EntityBuilder{


    private Assets assets;
    private PhysicsSystem physicsSystem;
    public GhostBuilder( Assets a, PhysicsSystem p) {
        this.assets = a;
        this.physicsSystem = p;
    }

    @Override
    public Entity buildEntity(float x, float y) {
        Entity monster = new Entity();


        // Add texture
        TextureComponent textureComponent = new TextureComponent();
        textureComponent.setRegion(new TextureRegion(this.assets.getManager().get("sprites/ghost.png", Texture.class)));
        monster.add(textureComponent);

        //Add Movement
        MovementComponent movementComponent = new MovementComponent(MonsterComponent.GHOST_VELOCITY);
        monster.add(movementComponent);

        // Add Monster component
        MonsterComponent monsterComponent = new MonsterComponent(1);
        monster.add(monsterComponent);

        monster.add(new CollisionComponent());

        // Add transform
        TransformComponent transformComponent = new TransformComponent(new Vector3(x,y,2));
        monster.add(transformComponent);

        //Add Health Point
        monster.add(new HealthComponent(3));

        Body body = physicsSystem.addSensorDynamicBody(x, y, World.CASE_DIMENSION - 1, World.CASE_DIMENSION - 1);
        body.setUserData(monster);
        body.setLinearVelocity(new Vector2(0,0));

        // Add steering
        SteeringComponent steeringComponent = new SteeringComponent(body);
        monster.add(steeringComponent);
        monster.add(new TypeComponent(TypeComponent.TYPE_GHOST));

        return monster;
    }
}