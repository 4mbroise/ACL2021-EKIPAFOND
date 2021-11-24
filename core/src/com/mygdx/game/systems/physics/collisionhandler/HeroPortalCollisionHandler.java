package com.mygdx.game.systems.physics.collisionhandler;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.World;
import com.mygdx.game.components.HeroComponent;
import com.mygdx.game.components.SteeringComponent;
import com.mygdx.game.components.TransformComponent;
import com.mygdx.game.factory.entity.HeroBuilder;
import com.mygdx.game.systems.physics.PhysicsSystem;
import org.mockito.internal.util.collections.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class HeroPortalCollisionHandler implements CollisionHandler{

    ComponentMapper<HeroComponent> hm = ComponentMapper.getFor(HeroComponent.class);
    ComponentMapper<SteeringComponent> bm = ComponentMapper.getFor(SteeringComponent.class);
    ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);
    Engine engine;
    World world;

    public HeroPortalCollisionHandler(Engine engine, World world) {
        this.engine = engine;
        this.world = world;
    }

    @Override
    public void handle(Entity colliedA, Entity colliedB) {
        HeroComponent heroComponent = hm.get(colliedA);
        heroComponent.setState(HeroComponent.STATE_STATIC);
        SteeringComponent steeringComponent = bm.get(colliedA);
        TransformComponent transformComponent = tm.get(colliedA);

        if (Distance(world.getPortals().get(0),transformComponent.getPosition().x, transformComponent.getPosition().y) <
                Distance(world.getPortals().get(1),transformComponent.getPosition().x, transformComponent.getPosition().y)) {
                    teleport(steeringComponent, world.getPortals().get(1).x, world.getPortals().get(1).y);
        }
        if (Distance(world.getPortals().get(0),transformComponent.getPosition().x, transformComponent.getPosition().y) >
                Distance(world.getPortals().get(1),transformComponent.getPosition().x, transformComponent.getPosition().y)) {
                    teleport(steeringComponent, world.getPortals().get(0).x, world.getPortals().get(0).y);
        }
    }


    public void teleport(SteeringComponent hero, float x, float y){
        engine.getSystem(PhysicsSystem.class).getPhysicsWorld().destroyBody(hero.getBody());
        HeroBuilder hb = new HeroBuilder(this.world.getAssets(), this.world.getPhysicsSystem());

        if (world.getMap()[((int)x/32)][((int)y/32) + 1] == '+'){
            engine.addEntity(hb.buildEntity(x + 32, y));
        } else {
            engine.addEntity(hb.buildEntity(x - 32, y));
        }

        System.out.println(Arrays.deepToString(world.getMap()).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

        //System.out.println(((int)x/32) + " - "+ (((int)y/32) - 2) );
        System.out.println(world.getMap()[3][2]);


    }

    public double Distance(Vector2 p, double x1, double y1){
        return Math.sqrt((p.y - y1) * (p.y - y1) + (p.x - x1) * (p.x - x1));
    }
}
