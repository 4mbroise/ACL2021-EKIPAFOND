package com.mygdx.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.components.BodyComponent;
import com.mygdx.game.components.DirectionComponent;
import com.mygdx.game.components.MovementComponent;
import com.mygdx.game.components.TransformComponent;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class PhysicsSystemTest {

    Engine engine;
    PhysicsSystem physicsSystem;
    TransformComponent transformComponent;
    MovementSystem movementSystem = new MovementSystem();
    World physicsWorld;
    Random r = new Random();
    MovementComponent movementComponent;
    DirectionComponent directionComponent;

    @Before
    public void setUp() throws Exception {
        physicsSystem = new PhysicsSystem();
        engine = new PooledEngine();
        engine.addSystem(movementSystem);
        engine.addSystem(physicsSystem);
        physicsWorld = engine.getSystem(PhysicsSystem.class).getPhysicsWorld();
        transformComponent = new TransformComponent(new Vector3(0,0,0));

    }

    @Test
    public void collisionStaticDynamic() {

        /**
         * Create Static Body
         * Width=40, Heigth=20
         * Coord : (0;0)
         */
        Entity staticEntity = new Entity();
        Body body1 = physicsSystem.addStaticBody(0,0,100,5);
        TransformComponent transformComponent = new TransformComponent(new Vector3(0,0,0));
        BodyComponent bodyComponent = new BodyComponent(body1);
        staticEntity.add(transformComponent);
        staticEntity.add(bodyComponent);

        /**
         * Create Dynamic
         * Width=10, Heigth=10
         * Coord : (0;40)
         */

        Entity dynamicEntity = new Entity();
        Body dynamicBody = physicsSystem.addDynamicBody(0,100,5,5);
        transformComponent = new TransformComponent(new Vector3(0,20,0));
        bodyComponent = new BodyComponent(dynamicBody);
        dynamicEntity.add(transformComponent);
        dynamicEntity.add(bodyComponent);

        engine.addEntity(staticEntity);
        engine.addEntity(dynamicEntity);


        /**
         * we apply set linear velocity down to the dynamic body located above the static body
         * the dynamic body should not overlap the static body
         * 1 contact should be counter
         *
         */

        System.out.println(dynamicEntity.getComponent(BodyComponent.class).getBody().getLinearVelocity());

        /**
         * We let the dynamic body "fall" on the static one
         */
        float deltaTime;
        for(int i = 0; i<30 ; i++){
            engine.update(1/50);
            physicsSystem.update(1/50);
            System.out.println("***");
            System.out.println(transformComponent.getPosition());
            System.out.println("***");
        }

        /**
         * We let the dynamic body "fall" on the static one
         */
        for(int i = 0; i<30 ; i++){
            deltaTime = 1/50 + r.nextFloat() * (1/75 - 1/50);
            engine.update(deltaTime);


        }
    }


    @Test
    public void collisionStaticDynamic2() {



        /**
         * Create Static Body
         * Width=40, Heigth=20
         * Coord : (0;0)
         */
        Entity staticEntity = new Entity();
        Body body1 = physicsSystem.addStaticBody(0,0,100,5);
        TransformComponent transformComponent = new TransformComponent(new Vector3(0,0,0));
        BodyComponent bodyComponent = new BodyComponent(body1);
        staticEntity.add(transformComponent);
        staticEntity.add(bodyComponent);

        /**
         * Create Dynamic
         * Width=10, Heigth=10
         * Coord : (0;40)
         */

        Entity dynamicEntity = new Entity();
        Body dynamicBody = physicsSystem.addDynamicBody(0,100,5,5);
        transformComponent = new TransformComponent(new Vector3(0,20,0));
        bodyComponent = new BodyComponent(dynamicBody);
        dynamicEntity.add(transformComponent);
        dynamicEntity.add(bodyComponent);

        engine.addEntity(staticEntity);
        engine.addEntity(dynamicEntity);



        /**
         * we apply set linear velocity down to the dynamic body located above the static body
         * the dynamic body should not overlap the static body
         * 1 contact should be counter
         *
         */

        dynamicEntity.getComponent(BodyComponent.class).setLinearVelocity(new Vector2(0,-10));

        /**
         * We let the dynamic body "fall" on the static one
         */
        float deltaTime;
        for(int i = 0; i<30 ; i++){
            deltaTime = 1/50 + r.nextFloat() * (1/75 - 1/50);
            engine.update(deltaTime);
            physicsSystem.update(deltaTime);
            System.out.println("***");
            System.out.println(transformComponent.getPosition());
            System.out.println("***");
        }

        /**
         * We let the dynamic body "fall" on the static one
         */
        for(int i = 0; i<30 ; i++){
            deltaTime = 1/50 + r.nextFloat() * (1/75 - 1/50);
            engine.update(deltaTime);


        }



    }

}