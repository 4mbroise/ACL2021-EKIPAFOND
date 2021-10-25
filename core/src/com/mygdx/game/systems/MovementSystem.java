package com.mygdx.game.systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.components.DirectionComponent;
import com.mygdx.game.components.MovementComponent;
import com.mygdx.game.components.TransformComponent;


public class MovementSystem extends IteratingSystem {

    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<MovementComponent> mm;
    private ComponentMapper<DirectionComponent> dm;

    public MovementSystem(){
        super(Family.all(TransformComponent.class, MovementComponent.class, DirectionComponent.class).get());

        tm = ComponentMapper.getFor(TransformComponent.class);
        mm = ComponentMapper.getFor(MovementComponent.class);
        dm = ComponentMapper.getFor(DirectionComponent.class);

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {


        MovementComponent   mvComp      = mm.get(entity);
        TransformComponent  trComp      = tm.get(entity);
        DirectionComponent  dirComp     = dm.get(entity);

        float velocity = mvComp.getVelocity();
        Vector3 position = trComp.getPosition();

        switch(dirComp.getDirection()){
            case DirectionComponent.UP:
                trComp.applyPositionTranslation(new Vector3(0, velocity,0), deltaTime);
                break;
            case DirectionComponent.DOWN:
                trComp.applyPositionTranslation(new Vector3(0, -velocity,0), deltaTime);
                break;
            case DirectionComponent.RIGHT:
                trComp.applyPositionTranslation(new Vector3(velocity, 0,0), deltaTime);
                break;
            case DirectionComponent.LEFT:
                trComp.applyPositionTranslation(new Vector3(-velocity,0,0), deltaTime);
                break;
        }
    }
}
