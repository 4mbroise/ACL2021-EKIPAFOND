package com.mygdx.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.components.RandomMovementComponent;
import com.mygdx.game.components.SteeringComponent;

public class RandomMovementSystem extends IteratingSystem {


    private ComponentMapper<SteeringComponent> sm;
    private ComponentMapper<RandomMovementComponent> rm;

    public RandomMovementSystem() {
        super(Family.all(SteeringComponent.class, RandomMovementComponent.class).get());
        sm = ComponentMapper.getFor(SteeringComponent.class);
        rm = ComponentMapper.getFor(RandomMovementComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        RandomMovementComponent   rvComp      = rm.get(entity);
        SteeringComponent   sComp      = sm.get(entity);

        float velocity = rvComp.getVelocity();

        int min =1; int max =100;
        int rand = min + (int)(Math.random() * ((max - min) + 1));
        int n = 10000;
        switch(rand){
            case 1:
                for(int i =0;i<n;i++){
                    sComp.setLinearVelocity(new Vector2(0, velocity));
                }
                break;
            case 2:
                for(int i =0;i<n;i++){
                    sComp.setLinearVelocity(new Vector2(0, -velocity));
                }
                break;
            case 3:
                for(int i =0;i<n;i++){
                    sComp.setLinearVelocity(new Vector2(velocity, 0));
                }
                break;
            case 4:
                for(int i =0;i<n;i++){
                    sComp.setLinearVelocity(new Vector2(-velocity, 0));
                }
                break;
        }

    }
}
