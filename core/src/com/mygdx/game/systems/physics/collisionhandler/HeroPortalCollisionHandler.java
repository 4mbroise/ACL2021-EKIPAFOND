package com.mygdx.game.systems.physics.collisionhandler;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.World;
import com.mygdx.game.components.HeroComponent;
import com.mygdx.game.components.SteeringComponent;
import com.mygdx.game.factory.entity.HeroBuilder;
import com.mygdx.game.systems.physics.PhysicsSystem;

public class HeroPortalCollisionHandler implements CollisionHandler{

    ComponentMapper<HeroComponent> hm = ComponentMapper.getFor(HeroComponent.class);
    ComponentMapper<SteeringComponent> bm = ComponentMapper.getFor(SteeringComponent.class);
    Engine engine;
    World world;
    Vector2 portal1;
    Vector2 portal2;

    public HeroPortalCollisionHandler(Engine engine, World world) {
        this.engine = engine;
        this.world = world;
        this.portal1 = world.getPortal1();
        this.portal2 = world.getPortal2();
    }

    @Override
    public void handle(Entity colliedA, Entity colliedB) {
        HeroComponent heroComponent = hm.get(colliedA);
        heroComponent.setState(HeroComponent.STATE_STATIC);
        SteeringComponent steeringComponent = bm.get(colliedA);
        /*engine.getSystem(PhysicsSystem.class).getPhysicsWorld().destroyBody(steeringComponent.getBody());
        HeroBuilder hb = new HeroBuilder(this.world.getAssets(), this.world.getPhysicsSystem());
        engine.addEntity(hb.buildEntity(world.getPortal1().x - 32, world.getPortal1().y));*/

        System.out.println(world.getPortal1());
        System.out.println(world.getPortal2());

    }
}
