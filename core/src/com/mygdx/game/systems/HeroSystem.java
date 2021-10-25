package com.mygdx.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.game.components.DirectionComponent;
import com.mygdx.game.components.HeroComponent;

public class HeroSystem extends IteratingSystem {

    private ComponentMapper<DirectionComponent> dm;
    private int direction = DirectionComponent.DOWN;

    public HeroSystem() {
        super(Family.all(HeroComponent.class).get());

        dm = ComponentMapper.getFor(DirectionComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        DirectionComponent  dirComp     = dm.get(entity);

        dirComp.setDirection(this.direction);
    }

    public void setHeroDirection(int directionCode){
        this.direction = directionCode;
    }
}
