package com.mygdx.game.systems.physics.collisionhandler;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.mygdx.game.ACLGame;
import com.mygdx.game.components.HeroComponent;
import com.mygdx.game.components.SteeringComponent;
import com.mygdx.game.screens.EndScreenWin;
import com.mygdx.game.screens.MazeScreen;
import com.mygdx.game.systems.physics.PhysicsSystem;

public class HeroTreasureCollisionHandler implements CollisionHandler{

    ComponentMapper<HeroComponent> hm = ComponentMapper.getFor(HeroComponent.class);
    ComponentMapper<SteeringComponent> bm = ComponentMapper.getFor(SteeringComponent.class);
    private Engine engine;
    private ACLGame game;

    public HeroTreasureCollisionHandler(Engine engine, ACLGame game) {
        this.engine = engine;
        this.game = game;
    }

    @Override
    public void handle(Entity colliedA, Entity colliedB) {
        HeroComponent heroComponent = hm.get(colliedA);
        heroComponent.setState(HeroComponent.STATE_STATIC);
        SteeringComponent steeringComponent = bm.get(colliedB);
        engine.removeEntity(colliedB);
        engine.getSystem(PhysicsSystem.class).getPhysicsWorld().destroyBody(steeringComponent.getBody());
        if (game.getLevel() <= 2){
            game.levelUp();
            MazeScreen MS = new MazeScreen(game);
            game.setScreen(MS);
            if (!game.isSoundon()){
                MS.pause();
            }
        } else {
            //game.resetScore();
            game.setScreen(new EndScreenWin(game));
        }
    }
}
