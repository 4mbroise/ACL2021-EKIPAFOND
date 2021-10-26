package com.mygdx.game.systems;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.game.components.AttackerComponent;
import com.mygdx.game.components.HealthComponent;
import com.mygdx.game.components.HeroComponent;

public class AttackSystem extends IteratingSystem {
    private ComponentMapper<HealthComponent> heam;
    private ComponentMapper<AttackerComponent> am;
    private ComponentMapper<HeroComponent> herm;

    public AttackSystem() {
        super(Family.all(HealthComponent.class, AttackerComponent.class, HeroComponent.class).get());
        heam = ComponentMapper.getFor(HealthComponent.class);
        am = ComponentMapper.getFor(AttackerComponent.class);
        herm=ComponentMapper.getFor(HeroComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AttackerComponent ac=am.get(entity);
    }
}
