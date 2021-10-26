package com.mygdx.game.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.components.*;

public class AttackSystem extends IteratingSystem {
    private ComponentMapper<HealthComponent> heam;
    private ComponentMapper<HeroComponent> herm;
    private ComponentMapper<DirectionComponent> dm;
    private ComponentMapper<TransformComponent> tm;

    private boolean attack;
    public AttackSystem() {
        super(Family.all(HealthComponent.class, HeroComponent.class,DirectionComponent.class,TransformComponent.class).get());
        heam = ComponentMapper.getFor(HealthComponent.class);
        herm=ComponentMapper.getFor(HeroComponent.class);
        dm=ComponentMapper.getFor(DirectionComponent.class);
        tm=ComponentMapper.getFor(TransformComponent.class);
        attack=false;

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        HeroComponent herc=herm.get(entity);
        DirectionComponent dc=dm.get(entity);
        TransformComponent tc=tm.get(entity);

        int damage=herc.STATE_ATTACKING;
        int direction=dc.getDirection();
        Vector3 position=tc.getPosition();

        Family family = Family.all(TransformComponent.class)
                .one(MonsterComponent.class)
                .get();
        ImmutableArray<Entity> entities = this.getEngine().getEntitiesFor(family);
        Vector3 positionTem=new Vector3();
        if(attack) {
            switch (direction) {
                case DirectionComponent.UP:
                    positionTem.set(position.x, position.y + 1, position.z);
                    for (Entity e : entities) {
                        TransformComponent monsterPosition = tm.get(e);
                        if (monsterPosition.getPosition() == positionTem) {
                            HealthComponent monsterHealth = heam.get(e);
                            monsterHealth.setHealthPoint(monsterHealth.getHealthPoint() - damage);
                        }
                    }
                    break;
                case DirectionComponent.DOWN:
                    positionTem.set(position.x, position.y - 1, position.z);
                    for (Entity e : entities) {
                        TransformComponent monsterPosition = tm.get(e);
                        if (monsterPosition.getPosition() == positionTem) {
                            HealthComponent monsterHealth = heam.get(e);
                            monsterHealth.setHealthPoint(monsterHealth.getHealthPoint() - damage);
                        }
                    }
                    break;
                case DirectionComponent.RIGHT:
                    positionTem.set(position.x + 1, position.y, position.z);
                    for (Entity e : entities) {
                        TransformComponent monsterPosition = tm.get(e);
                        if (monsterPosition.getPosition() == positionTem) {
                            HealthComponent monsterHealth = heam.get(e);
                            monsterHealth.setHealthPoint(monsterHealth.getHealthPoint() - damage);
                        }
                    }
                    break;
                case DirectionComponent.LEFT:
                    positionTem.set(position.x - 1, position.y, position.z);
                    for (Entity e : entities) {
                        TransformComponent monsterPosition = tm.get(e);
                        if (monsterPosition.getPosition() == positionTem) {
                            HealthComponent monsterHealth = heam.get(e);
                            monsterHealth.setHealthPoint(monsterHealth.getHealthPoint() - damage);
                        }
                    }
                    break;
            }
        }
        attack=false;
    }

    public void attack(){
        attack=true;
    }
}
