package com.mygdx.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.components.CollisionComponent;
import com.mygdx.game.components.SteeringComponent;
import com.mygdx.game.components.TransformComponent;
import com.mygdx.game.systems.physics.PhysicsSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class PhysicsSystemTest {

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
    public void collisionStaticDynamicMovingDown() {

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
        dynamicEntity.add(new SteeringComponent(dynamicBody));
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
        staticEntity.add(new SteeringComponent(staticBody));
        staticEntity.add(new CollisionComponent());

        /**
         * We want the dynamic Body Moving Down
         */
        dynamicBody.setLinearVelocity(new Vector2(0, -10));

        engine.addEntity(dynamicEntity);
        engine.addEntity(staticEntity);

        /**
         * Update the engine to let the dynamic move
         */
        float deltaTime;
        for(int i=0; i<200; i++){
            deltaTime = (float) 0.01666666 + r.nextFloat() * ( (float) 0.01333333333 - (float) 0.01666666);
            engine.update(deltaTime);
        }

        /**
         * We should count 2 body in the physics World
         * We should note 1 contact
         * The dynamic Body should be located on the top of the dynamic one
         * (There is a little difference of position, the dynamic body should be a little bit above the static one, a difference < 0.1 )
         * The static body should not have mooved
         */
        assertEquals(2, physicsWorld.getBodyCount());

        assertEquals(1, physicsWorld.getContactCount());

        assertEquals(50, dynamicBody.getPosition().x, 0);
        assertEquals(2+10, dynamicBody.getPosition().y, 0.1);

        assertEquals(0, staticBody.getPosition().x,0);
        assertEquals(0, staticBody.getPosition().y,0);


    }

    @Test
    public void collisionStaticDynamicMovingUp() {

        /**
         * Dynamic Body
         * Width = 10 ; Heigth = 10
         * Position = (20,0)
         */

        Entity dynamicEntity = new Entity();
        Body dynamicBody = physicsSystem.addDynamicBody(20,0,10,10);
        dynamicBody.setUserData(dynamicEntity);
        TransformComponent dynamicEntityPosition = new TransformComponent(new Vector3(0,0,0));
        dynamicEntity.add(dynamicEntityPosition);
        dynamicEntity.add(new SteeringComponent(dynamicBody));
        dynamicEntity.add(new CollisionComponent());

        /**
         * Static Body
         * Width = 100 ; Heigth = 2
         * Position = (0,15)
         */

        Entity staticEntity = new Entity();
        Body staticBody = physicsSystem.addStaticBody(0,15,100,2);
        staticBody.setUserData(staticEntity);
        TransformComponent staticEntityPosition = new TransformComponent(new Vector3(0,0,0));
        staticEntity.add(staticEntityPosition);
        staticEntity.add(new SteeringComponent(staticBody));
        staticEntity.add(new CollisionComponent());

        /**
         * We want the dynamic Body Moving Down
         */
        dynamicBody.setLinearVelocity(new Vector2(0, 10));

        engine.addEntity(dynamicEntity);
        engine.addEntity(staticEntity);

        /**
         * Update the engine to let the dynamic Body move
         */
        float deltaTime;
        for(int i=0; i<200; i++){
            deltaTime = (float) 0.01666666 + r.nextFloat() * ( (float) 0.01333333333 - (float) 0.01666666);
            engine.update(deltaTime);
        }

        /**
         * We should count 2 body in the physics World
         * We should note 1 contact
         * The dynamic Body should be located on the top of the dynamic one
         * (There is a little difference of position, the dynamic body should be a little bit above the static one, a difference < 0.1 )
         * The static body should not have mooved
         */
        assertEquals(2, physicsWorld.getBodyCount());

        assertEquals(1, physicsWorld.getContactCount());

        assertEquals(20, dynamicBody.getPosition().x, 0);
        assertEquals(15-10-2, dynamicBody.getPosition().y, 0.1);

        assertEquals(0, staticBody.getPosition().x,0);
        assertEquals(15, staticBody.getPosition().y,0);


    }

    @Test
    public void collisionStaticDynamicMovingToTheLeft() {
        /**
         * Dynamic Body
         * Width = 10 ; Heigth = 10
         * Position = (20,20)
         */
        Entity dynamicEntity = new Entity();
        Body dynamicBody = physicsSystem.addDynamicBody(20,20,10,10);
        dynamicBody.setUserData(dynamicEntity);
        TransformComponent dynamicEntityPosition = new TransformComponent(new Vector3(0,0,0));
        dynamicEntity.add(dynamicEntityPosition);
        dynamicEntity.add(new SteeringComponent(dynamicBody));
        dynamicEntity.add(new CollisionComponent());


        /**
         * Static Body
         * Width = 2 ; Heigth = 100
         * Position = (0,0)
         */

        Entity staticEntity = new Entity();
        Body staticBody = physicsSystem.addStaticBody(0,0,2,100);
        staticBody.setUserData(staticEntity);
        TransformComponent staticEntityPosition = new TransformComponent(new Vector3(0,0,0));
        staticEntity.add(staticEntityPosition);
        staticEntity.add(new SteeringComponent(staticBody));
        staticEntity.add(new CollisionComponent());

        /**
         * We want the dynamic Body Moving Down
         */
        dynamicBody.setLinearVelocity(new Vector2(-10, 0));

        engine.addEntity(dynamicEntity);
        engine.addEntity(staticEntity);

        /**
         * Update the engine to let the dynamic Body move
         */
        float deltaTime;
        for(int i=0; i<200; i++){
            deltaTime = (float) 0.01666666 + r.nextFloat() * ( (float) 0.01333333333 - (float) 0.01666666);
            engine.update(deltaTime);
        }

        /**
         * We should count 2 body in the physics World
         * We should note 1 contact
         * The dynamic Body should be located on the top of the dynamic one
         * (There is a little difference of position, the dynamic body should be a little bit above the static one, a difference < 0.1 )
         * The static body should not have mooved
         */
        assertEquals(2, physicsWorld.getBodyCount());

        assertEquals(1, physicsWorld.getContactCount());

        assertEquals(10+2, dynamicBody.getPosition().x, 0.1);
        assertEquals(20, dynamicBody.getPosition().y, 0);

        assertEquals(0, staticBody.getPosition().x,0);
        assertEquals(0, staticBody.getPosition().y,0);
    }

    @Test
    public void collisionStaticDynamicMovingToTheRight() {

        /**
         * Dynamic Body
         * Width = 10 ; Heigth = 10
         * Position = (0,0)
         */

        Entity dynamicEntity = new Entity();
        Body dynamicBody = physicsSystem.addDynamicBody(0,0,10,10);
        dynamicBody.setUserData(dynamicEntity);
        TransformComponent dynamicEntityPosition = new TransformComponent(new Vector3(0,0,0));
        dynamicEntity.add(dynamicEntityPosition);
        dynamicEntity.add(new SteeringComponent(dynamicBody));
        dynamicEntity.add(new CollisionComponent());

        /**
         * Static Body
         * Width = 2 ; Heigth = 100
         * Position = (20,0)
         */

        Entity staticEntity = new Entity();
        Body staticBody = physicsSystem.addStaticBody(20,0,2,100);
        staticBody.setUserData(staticEntity);
        TransformComponent staticEntityPosition = new TransformComponent(new Vector3(0,0,0));
        staticEntity.add(staticEntityPosition);
        staticEntity.add(new SteeringComponent(staticBody));
        staticEntity.add(new CollisionComponent());

        /**
         * We want the dynamic Body Moving Down
         */
        dynamicBody.setLinearVelocity(new Vector2(10, 0));

        engine.addEntity(dynamicEntity);
        engine.addEntity(staticEntity);

        /**
         * Update the engine to let the dynamic Body move
         */
        float deltaTime;
        for(int i=0; i<200; i++){
            deltaTime = (float) 0.01666666 + r.nextFloat() * ( (float) 0.01333333333 - (float) 0.01666666);
            engine.update(deltaTime);
        }

        /**
         * We should count 2 body in the physics World
         * We should note 1 contact
         * The dynamic Body should be located on the top of the dynamic one
         * (There is a little difference of position, the dynamic body should be a little bit above the static one, a difference < 0.1 )
         * The static body should not have mooved
         */

        assertEquals(2, physicsWorld.getBodyCount());
        assertEquals(1, physicsWorld.getContactCount());

        assertEquals(20-10-2, dynamicBody.getPosition().x, 0.1);
        assertEquals(0, dynamicBody.getPosition().y, 0);

        assertEquals(20, staticBody.getPosition().x,0);
        assertEquals(0, staticBody.getPosition().y,0);


    }

}