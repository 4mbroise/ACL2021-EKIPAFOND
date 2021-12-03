package com.mygdx.game.systems.physics.collisionhandler;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.World;
import com.mygdx.game.components.DirectionComponent;
import com.mygdx.game.components.SteeringComponent;
import com.mygdx.game.components.TransformComponent;

public class HeroPortalCollisionHandler implements CollisionHandler{

    private ComponentMapper<SteeringComponent> bm = ComponentMapper.getFor(SteeringComponent.class);
    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<DirectionComponent> dm = ComponentMapper.getFor(DirectionComponent.class);
    private World world;

    public HeroPortalCollisionHandler(World world) {
        this.world = world;
    }

    @Override
    public void handle(Entity colliedA, Entity colliedB) {
        SteeringComponent  steeringComponent  = bm.get(colliedA);
        TransformComponent transformComponent = tm.get(colliedA);
        DirectionComponent directionComponent = dm.get(colliedA);

            if (Distance(world.getPortals().get(0),transformComponent.getPosition().x, transformComponent.getPosition().y) <
                    Distance(world.getPortals().get(1),transformComponent.getPosition().x, transformComponent.getPosition().y)) {
                teleport(steeringComponent, directionComponent, world.getPortals().get(1).x, world.getPortals().get(1).y);
            }
            if (Distance(world.getPortals().get(0),transformComponent.getPosition().x, transformComponent.getPosition().y) >
                    Distance(world.getPortals().get(1),transformComponent.getPosition().x, transformComponent.getPosition().y)) {
                teleport(steeringComponent, directionComponent, world.getPortals().get(0).x, world.getPortals().get(0).y);
            }
        }

    public void teleport(SteeringComponent hero, DirectionComponent direction, float x, float y){
        if (world.getMap()[((int)x/32)][((int)y/32) + 1] == '+'){
            hero.getBody().setTransform(x+32,y,0);
            direction.setDirection(DirectionComponent.RIGHT);

        } else {
            hero.getBody().setTransform(x-32,y,0);
            direction.setDirection(DirectionComponent.LEFT);
        }
    }

    public double Distance(Vector2 p, double x1, double y1){
        return Math.sqrt((p.y - y1) * (p.y - y1) + (p.x - x1) * (p.x - x1));
    }
}