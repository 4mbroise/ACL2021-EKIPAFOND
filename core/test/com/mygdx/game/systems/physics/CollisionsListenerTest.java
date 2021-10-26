package com.mygdx.game.systems.physics;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.components.*;
import com.mygdx.game.systems.MovementSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class CollisionsListenerTest {

    Engine engine;
    PhysicsSystem physicsSystem;
    World physicsWorld;
    Random r = new Random();

    @Before
    public void setUp() throws Exception {
        physicsSystem = new PhysicsSystem();
        engine = new PooledEngine();
        engine.addSystem(physicsSystem);
        physicsWorld = engine.getSystem(PhysicsSystem.class).getPhysicsWorld();
    }

    @Test
    public void beginContact() {

        CollisionsListener collisionsListener = new CollisionsListener();

        physicsWorld.setContactListener(collisionsListener);

        /**
         * Dynamic Body
         * Width = 10 ; Heigth = 10
         * Position = (50,20)
         */

        Entity dynamicEntity = new Entity();
        Body dynamicBody = physicsSystem.addDynamicBody(50,20,10,10);
        dynamicBody.setUserData(dynamicEntity);
        TransformComponent dynamicEntityPosition = new TransformComponent(new Vector3(0,0,0));
        dynamicEntity.add(dynamicEntityPosition);
        dynamicEntity.add(new BodyComponent(dynamicBody));
        dynamicEntity.add(new CollisionComponent());

        /**
         * Static Body
         * Width = 100 ; Heigth = 2
         * Position = (0,0)
         */

        Entity staticEntity = new Entity();
        Body staticBody = physicsSystem.addStaticBody(0,0,100,2);
        staticBody.setUserData(staticEntity);
        TransformComponent staticEntityPosition = new TransformComponent(new Vector3(0,0,0));
        staticEntity.add(staticEntityPosition);
        staticEntity.add(new BodyComponent(staticBody));
        staticEntity.add(new CollisionComponent());

        /**
         * We want the dynamic Body Moving Down
         */
        dynamicBody.setLinearVelocity(new Vector2(0, -10));

        engine.addEntity(dynamicEntity);
        engine.addEntity(staticEntity);

        float deltaTime;
        for(int i=0; i<200; i++){
            deltaTime = (float) 0.01666666 + r.nextFloat() * ( (float) 0.01333333333 - (float) 0.01666666);
            engine.update(deltaTime);
        }

        /**
         * The 2 bodies are in contact
         * staticEntity1 should have staticEntity2 in its CollisionsComponent
         * staticEntity2 should have staticEntity1 in its CollisionsComponent
         */

        assertEquals(staticEntity, dynamicEntity.getComponent(CollisionComponent.class).getEntityCollied());
        assertEquals(dynamicEntity, staticEntity.getComponent(CollisionComponent.class).getEntityCollied());

    }

    @Test
    public void endContact() {

        CollisionsListener collisionsListener = new CollisionsListener();

        physicsWorld.setContactListener(collisionsListener);

        /**
         * Dynamic Body
         * Width = 10 ; Heigth = 10
         * Position = (50,20)
         */

        Entity dynamicEntity = new Entity();
        Body dynamicBody = physicsSystem.addDynamicBody(50,20,10,10);
        dynamicBody.setUserData(dynamicEntity);
        TransformComponent dynamicEntityPosition = new TransformComponent(new Vector3(0,0,0));
        dynamicEntity.add(dynamicEntityPosition);
        dynamicEntity.add(new BodyComponent(dynamicBody));
        dynamicEntity.add(new CollisionComponent());

        /**
         * Static Body
         * Width = 100 ; Heigth = 2
         * Position = (0,0)
         */

        Entity staticEntity = new Entity();
        Body staticBody = physicsSystem.addStaticBody(0,0,100,2);
        staticBody.setUserData(staticEntity);
        TransformComponent staticEntityPosition = new TransformComponent(new Vector3(0,0,0));
        staticEntity.add(staticEntityPosition);
        staticEntity.add(new BodyComponent(staticBody));
        staticEntity.add(new CollisionComponent());

        /**
         * We want the dynamic Body Moving Down
         */
        dynamicBody.setLinearVelocity(new Vector2(0, -10));

        engine.addEntity(dynamicEntity);
        engine.addEntity(staticEntity);

        float deltaTime;
        for(int i=0; i<200; i++){
            deltaTime = (float) 0.01666666 + r.nextFloat() * ( (float) 0.01333333333 - (float) 0.01666666);
            engine.update(deltaTime);
        }

        /**
         * We want the dynamic Body Moving up
         */

        dynamicBody.setLinearVelocity(new Vector2(0, 10));

        for(int i=0; i<200; i++){
            deltaTime = (float) 0.01666666 + r.nextFloat() * ( (float) 0.01333333333 - (float) 0.01666666);
            engine.update(deltaTime);
        }

        /**
         * The 2 bodies are in contact
         * staticEntity1 should have staticEntity2 in its CollisionsComponent
         * staticEntity2 should have staticEntity1 in its CollisionsComponent
         */

        assertEquals(null, dynamicEntity.getComponent(CollisionComponent.class).getEntityCollied());
        assertEquals(null, staticEntity.getComponent(CollisionComponent.class).getEntityCollied());

    }

}