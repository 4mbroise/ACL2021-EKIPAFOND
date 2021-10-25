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

public class MonsterSystem extends IteratingSystem {

    private ComponentMapper<MonsterComponent> monsCom;
    private ComponentMapper<Box2dComponent> boxCom;

    public MonsterSystem() {
        super(Family.all(MonsterComponent.class).get());
        monsCom = ComponentMapper.getFor(MonsterComponent.class);
        boxCom = ComponentMapper.getFor(Box2dComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        MonsterComponent monsterCom = monsCom.get(entity);
        Box2dComponent box2dCom = boxCom.get(entity);
        ComponentMapper<SteeringComponent> steerMap = ComponentMapper.getFor(SteeringComponent.class);
        SteeringComponent steerComp = steerMap.get(entity);
        ComponentMapper<HeroComponent> heroMap = ComponentMapper.getFor(HeroComponent.class);
        steerComp.steeringBehavior = SteeringPresets.getSeek(steerMap.get(entity), heroMap.get(entity));

    }
}
