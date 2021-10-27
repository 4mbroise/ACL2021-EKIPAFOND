package com.mygdx.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.components.DirectionComponent;
import com.mygdx.game.components.MovementComponent;
import com.mygdx.game.components.SteeringComponent;
import com.mygdx.game.components.TransformComponent;
import com.mygdx.game.systems.physics.PhysicsSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class MovementSystemTest {

    Engine engine;
    MovementSystem movementSystem = new MovementSystem();
    TransformComponent transformComponent;
    DirectionComponent directionComponent;
    MovementComponent movementComponent;
    PhysicsSystem physicsSystem = new PhysicsSystem();
    Random r = new Random();

    @Before
    public void setUp() throws Exception {
        engine = new PooledEngine();
        physicsSystem.priority=2;
        movementSystem.priority=1;
        engine.addSystem(physicsSystem);
        engine.addSystem(movementSystem);
        transformComponent = new TransformComponent(new Vector3(0,0,0));
    }

    @Test
    public void upMovement() {

        //random speed between 0 and 10
        float velocity = 10;

        directionComponent = new DirectionComponent();
        directionComponent.setDirection(DirectionComponent.UP);
        movementComponent = new MovementComponent(velocity);

        Entity e = new Entity();
        e.add(new SteeringComponent(physicsSystem.addDynamicBody(0,0,10,10)));
        e.add(directionComponent);
        e.add(transformComponent);
        e.add(movementComponent);

        engine.addEntity(e);

        float deltaTime;

        float y;
        for(int i = 0; i<30 ; i++){

            y = transformComponent.getPosition().y;

            //random float between 1/50 and 1/75 (framerate)
            deltaTime = (float) 0.01666666 + r.nextFloat() * ( (float) 0.01333333333 - (float) 0.01666666);
            engine.update(deltaTime);


            assertEquals(0, transformComponent.getPosition().x, 0);
            assertEquals(y+(movementComponent.getVelocity()*deltaTime), transformComponent.getPosition().y, 0);

        }

    }

    @Test
    public void downMovement() {

        //random speed between 0 and 10
        float velocity = r.nextFloat() * 10;

        directionComponent = new DirectionComponent();
        directionComponent.setDirection(DirectionComponent.DOWN);
        movementComponent = new MovementComponent(velocity);

        Entity e = new Entity();
        e.add(new SteeringComponent(physicsSystem.addDynamicBody(0,0,10,10)));
        e.add(directionComponent);
        e.add(transformComponent);
        e.add(movementComponent);

        engine.addEntity(e);

        float deltaTime;
        float y;
        for(int i = 0; i<30 ; i++){

            y = transformComponent.getPosition().y;

            //random float between 1/50 and 1/75 (framerate)
            deltaTime = (float) 0.01666666 + r.nextFloat() * ( (float) 0.01333333333 - (float) 0.01666666);
            engine.update(deltaTime);


            assertEquals(0, transformComponent.getPosition().x, 0);
            assertEquals(y-(movementComponent.getVelocity()*deltaTime), transformComponent.getPosition().y, 0);

        }
    }

    @Test
    public void leftMovement() {

        //random speed between 0 and 10
        float velocity = r.nextFloat() * 10;

        directionComponent = new DirectionComponent();
        directionComponent.setDirection(DirectionComponent.LEFT);
        movementComponent = new MovementComponent(velocity);

        Entity e = new Entity();
        e.add(new SteeringComponent(physicsSystem.addDynamicBody(0,0,10,10)));
        e.add(directionComponent);
        e.add(transformComponent);
        e.add(movementComponent);

        engine.addEntity(e);

        float deltaTime;
        float x;
        for(int i = 0; i<30 ; i++){

            x = transformComponent.getPosition().x;

            //random float between 1/50 and 1/75 (framerate)
            deltaTime = (float) 0.01666666 + r.nextFloat() * ( (float) 0.01333333333 - (float) 0.01666666);
            engine.update(deltaTime);

            assertEquals(0, transformComponent.getPosition().y, 0);
            assertEquals(x-(movementComponent.getVelocity()*deltaTime), transformComponent.getPosition().x, 0);

        }
    }

    @Test
    public void rightMovement() {

        //random speed between 0 and 10
        float velocity = r.nextFloat() * 10;

        directionComponent = new DirectionComponent();
        directionComponent.setDirection(DirectionComponent.RIGHT);
        movementComponent = new MovementComponent(velocity);

        Entity e = new Entity();
        e.add(new SteeringComponent(physicsSystem.addDynamicBody(0,0,10,10)));
        e.add(directionComponent);
        e.add(transformComponent);
        e.add(movementComponent);

        engine.addEntity(e);

        float deltaTime;
        float x;
        for(int i = 0; i<30 ; i++){

            x = transformComponent.getPosition().x;

            //random float between 1/50 and 1/75 (framerate)
            deltaTime = (float) 0.01666666 + r.nextFloat() * ( (float) 0.01333333333 - (float) 0.01666666);
            engine.update(deltaTime);

            assertEquals(0, transformComponent.getPosition().y, 0);
            assertEquals(x+(movementComponent.getVelocity()*deltaTime), transformComponent.getPosition().x, 0);

        }
    }

}