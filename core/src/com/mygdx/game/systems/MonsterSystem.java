package com.mygdx.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.game.SteeringPresets;
import com.mygdx.game.components.Box2dComponent;
import com.mygdx.game.components.HeroComponent;
import com.mygdx.game.components.MonsterComponent;
import com.mygdx.game.components.SteeringComponent;
import com.mygdx.game.screens.GameAITestScreen;

public class MonsterSystem extends IteratingSystem {

    private Entity hero;
    public MonsterSystem(Entity entity) {
        super(Family.all(MonsterComponent.class).get());
        this.hero= entity;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ComponentMapper<SteeringComponent> steerMap = ComponentMapper.getFor(SteeringComponent.class);
        SteeringComponent steerComp = steerMap.get(entity);
        steerComp.steeringBehavior = SteeringPresets.getArrive(steerMap.get(entity), hero.getComponent(SteeringComponent.class));
        steerComp.currentMode= SteeringComponent.SteeringState.ARRIVE;
    }
}
