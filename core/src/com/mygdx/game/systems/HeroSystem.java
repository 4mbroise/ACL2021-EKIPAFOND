package com.mygdx.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.components.DirectionComponent;
import com.mygdx.game.components.HeroComponent;

public class HeroSystem extends IteratingSystem {

    private ComponentMapper<DirectionComponent> dm;
    private int direction = DirectionComponent.DOWN;
    private ComponentMapper<HeroComponent> hm;
    private float stateTime;

    public HeroSystem() {
        super(Family.all(HeroComponent.class).get());
        hm=ComponentMapper.getFor(HeroComponent.class);
        dm =ComponentMapper.getFor(DirectionComponent.class);
        stateTime=0;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        DirectionComponent  dirComp     = dm.get(entity);
        dirComp.setDirection(this.direction);
        HeroComponent herc=hm.get(entity);
        if(stateTime < 2) {
            stateTime += Gdx.graphics.getDeltaTime();
        } else {
            stateTime = 0;
            herc.liftInvincibility();
        }
    }

    public void setHeroDirection(int directionCode){
        this.direction = directionCode;
    }

}
