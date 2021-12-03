package com.mygdx.game.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.ACLGame;
import com.mygdx.game.components.*;

public class AttackSystem extends IteratingSystem {
    private ComponentMapper<HealthComponent> heam;
    private ComponentMapper<HeroComponent> herm;
    private ComponentMapper<DirectionComponent> dm;
    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<AttackerComponent> am;
    private ComponentMapper<AnimationComponent> anm;
    private ComponentMapper<TextureComponent> txtm;
    private boolean attack;
    private Sound attackSound;
    private Sound damageSound;
    private ACLGame game;
    public AttackSystem(ACLGame game) {
        super(Family.all(HealthComponent.class, HeroComponent.class,DirectionComponent.class,TransformComponent.class,AttackerComponent.class,AnimationComponent.class,TextureComponent.class).get());
        am = ComponentMapper.getFor(AttackerComponent.class);
        heam = ComponentMapper.getFor(HealthComponent.class);
        herm=ComponentMapper.getFor(HeroComponent.class);
        dm=ComponentMapper.getFor(DirectionComponent.class);
        tm=ComponentMapper.getFor(TransformComponent.class);
        anm=ComponentMapper.getFor(AnimationComponent.class);
        txtm=ComponentMapper.getFor(TextureComponent.class);
        attackSound=game.getAssets().getManager().get("audio/attack/Attack.ogg");
        damageSound=game.getAssets().getManager().get("audio/attack/Damage.ogg");
        this.game = game;
        attack=false;

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        HeroComponent herc=herm.get(entity);
        DirectionComponent dc=dm.get(entity);
        TransformComponent tc=tm.get(entity);
        AttackerComponent ac=am.get(entity);

        int damage=ac.getDamage();
        int direction=dc.getDirection();
        Vector3 position=tc.getPosition();

        Family family = Family.all(TransformComponent.class)
                .one(MonsterComponent.class)
                .get();
        ImmutableArray<Entity> entities = this.getEngine().getEntitiesFor(family);
        if(attack) {
            attackSound.play();
            herc.setState(herc.STATE_ATTACKING);
            switch (direction) {
                case DirectionComponent.UP:
                    for (Entity e : entities) {
                        TransformComponent monsterPosition = tm.get(e);
                        Vector3 MP=monsterPosition.getPosition();
                        if( MP.x>=position.x-30&&MP.x<=position.x+30&&MP.y>=position.y+15&&MP.y<=position.y+60) {
                            damageSound.play();
                            HealthComponent monsterHealth = heam.get(e);
                            monsterHealth.reduceHealthPoint(damage);
                            if(monsterHealth.getHealthPoint()==0){
                                this.game.increaseScore();
                            }
                        }
                    }
                    break;
                case DirectionComponent.DOWN:
                    for (Entity e : entities) {
                        TransformComponent monsterPosition = tm.get(e);
                        Vector3 MP=monsterPosition.getPosition();
                        if (MP.x>=position.x-30&&MP.x<=position.x+30&&MP.y<=position.y-15&&MP.y>=position.y-60) {
                            damageSound.play();
                            HealthComponent monsterHealth = heam.get(e);
                            monsterHealth.reduceHealthPoint(damage);
                            if(monsterHealth.getHealthPoint()==0){
                                this.game.increaseScore();
                            }
                        }
                    }
                    break;
                case DirectionComponent.RIGHT:
                    for (Entity e : entities) {
                        TransformComponent monsterPosition = tm.get(e);
                        Vector3 MP=monsterPosition.getPosition();
                        if (MP.x>=position.x+15&&MP.x<=position.x+60&&MP.y>=position.y-30&&MP.y<=position.y+30) {
                            damageSound.play();
                            HealthComponent monsterHealth = heam.get(e);
                            monsterHealth.reduceHealthPoint(damage);
                            if(monsterHealth.getHealthPoint()==0){
                                this.game.increaseScore();
                            }
                        }
                    }
                    break;
                case DirectionComponent.LEFT:
                    for (Entity e : entities) {
                        TransformComponent monsterPosition = tm.get(e);
                        Vector3 MP=monsterPosition.getPosition();
                        if (MP.x<=position.x-15&&MP.x>=position.x-60&&MP.y>=position.y-30&&MP.y<=position.y+30) {
                            damageSound.play();
                            HealthComponent monsterHealth = heam.get(e);
                            monsterHealth.reduceHealthPoint(damage);
                            if(monsterHealth.getHealthPoint()==0){
                                this.game.increaseScore();
                            }
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
