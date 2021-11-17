package com.mygdx.game.systems.physics.collisionhandler;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.World;
import com.mygdx.game.components.HeroComponent;
import com.mygdx.game.components.SteeringComponent;

public class HeroTreasureCollisionHandler implements CollisionHandler{

    ComponentMapper<HeroComponent> hm = ComponentMapper.getFor(HeroComponent.class);
    ComponentMapper<SteeringComponent> bm = ComponentMapper.getFor(SteeringComponent.class);
    private Engine engine;
    private int level;

    public HeroTreasureCollisionHandler(Engine engine) {
        this.engine = engine;
        this.level = 1;
    }

    @Override
    public void handle(Entity colliedA, Entity colliedB) {
        HeroComponent heroComponent = hm.get(colliedA);
        heroComponent.setState(HeroComponent.STATE_STATIC);
        SteeringComponent steeringComponent = bm.get(colliedA);
        System.out.println("Game won - " + "current level : " + level);
        level++;
    }
}
