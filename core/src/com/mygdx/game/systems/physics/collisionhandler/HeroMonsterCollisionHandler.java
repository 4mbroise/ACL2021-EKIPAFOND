package com.mygdx.game.systems.physics.collisionhandler;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ACLGame;
import com.mygdx.game.components.*;
import com.mygdx.game.screens.EndScreen;
import com.mygdx.game.systems.HeroSystem;
import com.mygdx.game.systems.physics.PhysicsSystem;

public class HeroMonsterCollisionHandler implements CollisionHandler{

    ComponentMapper<SteeringComponent> sm = ComponentMapper.getFor(SteeringComponent.class);
    ComponentMapper<MovementComponent> movem = ComponentMapper.getFor(MovementComponent.class);
    ComponentMapper<DirectionComponent> dm = ComponentMapper.getFor(DirectionComponent.class);
    ComponentMapper<HealthComponent> healm= ComponentMapper.getFor(HealthComponent.class);
    ComponentMapper<TransformComponent> tm= ComponentMapper.getFor(TransformComponent.class);
    ComponentMapper<HeroComponent> hm=ComponentMapper.getFor(HeroComponent.class);
    Engine engine;
    ACLGame game;

    public HeroMonsterCollisionHandler(Engine engine, ACLGame game) {
        this.engine = engine;
        this.game = game;
    }

    @Override
    public void handle(Entity colliedA, Entity colliedB) {
        HealthComponent healthComponent = healm.get(colliedA);
        HeroComponent herc= hm.get(colliedA);
        MovementComponent   mc = movem.get(colliedA);
        SteeringComponent   sc  = sm.get(colliedA);
        DirectionComponent  dc  = dm.get(colliedA);
        TransformComponent tc=tm.get(colliedA);
        TransformComponent tc2=tm.get(colliedB);

        //System.out.println(tc.getPosition().x-tc2.getPosition().x);
        float velocity = mc.getVelocity();

        /*switch(dc.getDirection()){
            case DirectionComponent.UP:
                engine.getSystem(HeroSystem.class).setHeroDirection(DirectionComponent.DOWN);
                //sc.getBody().applyForceToCenter(new Vector2(0,-100),true);
                break;
            case DirectionComponent.DOWN:
                engine.getSystem(HeroSystem.class).setHeroDirection(DirectionComponent.UP);
                //sc.getBody().applyForceToCenter(new Vector2(0,100),true);
                break;
            case DirectionComponent.RIGHT:
                engine.getSystem(HeroSystem.class).setHeroDirection(DirectionComponent.LEFT);
                //sc.getBody().applyForceToCenter(new Vector2(-100,0),true);
                break;
            case DirectionComponent.LEFT:
                engine.getSystem(HeroSystem.class).setHeroDirection(DirectionComponent.RIGHT);
                //sc.getBody().applyForceToCenter(new Vector2(100,0),true);
                break;
        }*/

        // System.out.println(healthComponent.getHealthPoint());
        System.out.println(healthComponent.getHealthPoint());
        //healthComponent.reduceHealthPoint(1);


        if (healthComponent.getHealthPoint() <= 0) {
            game.setScreen(new EndScreen(game));
        }

        System.out.println(healthComponent.getHealthPoint());
    }
}
