package com.mygdx.game.systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.components.SteeringComponent;
import com.mygdx.game.components.DirectionComponent;
import com.mygdx.game.components.MovementComponent;
import com.mygdx.game.components.TransformComponent;


public class MovementSystem extends IteratingSystem {

    private ComponentMapper<SteeringComponent> sm;
    private ComponentMapper<MovementComponent> mm;
    private ComponentMapper<DirectionComponent> dm;

    public MovementSystem(){
        super(Family.all(SteeringComponent.class, MovementComponent.class, DirectionComponent.class).get());

        sm = ComponentMapper.getFor(SteeringComponent.class);
        mm = ComponentMapper.getFor(MovementComponent.class);
        dm = ComponentMapper.getFor(DirectionComponent.class);

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        MovementComponent   mvComp      = mm.get(entity);
        SteeringComponent   sComp      = sm.get(entity);
        DirectionComponent  dirComp     = dm.get(entity);

        float velocity = mvComp.getVelocity();


        switch(dirComp.getDirection()){
            case DirectionComponent.UP:
                sComp.setLinearVelocity(new Vector2(0, velocity));
                break;
            case DirectionComponent.DOWN:
                sComp.setLinearVelocity(new Vector2(0, -velocity));
                break;
            case DirectionComponent.RIGHT:
                sComp.setLinearVelocity(new Vector2(velocity, 0));
                break;
            case DirectionComponent.LEFT:
                sComp.setLinearVelocity(new Vector2(-velocity, 0));
                break;
            case DirectionComponent.STATIC:
                sComp.setLinearVelocity(new Vector2(0, 0));
                break;
        }
    }
}