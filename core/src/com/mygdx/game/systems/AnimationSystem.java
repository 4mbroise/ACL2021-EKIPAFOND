package com.mygdx.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ACLGame;
import com.mygdx.game.components.*;
import com.mygdx.game.screens.EndScreenLoose;
import com.mygdx.game.systems.physics.PhysicsSystem;
import org.lwjgl.Sys;

public class AnimationSystem extends IteratingSystem {
    private ComponentMapper<TextureComponent> tm;
    private ComponentMapper<TransformComponent> trm;
    private ComponentMapper<AnimationComponent> am;
    private ComponentMapper<DirectionComponent> dm;
    private ComponentMapper<HeroComponent> hm;
    private ComponentMapper<HealthComponent> heam;
    private ComponentMapper<SteeringComponent> sm;
    private boolean attack=false;
    private float sumDelta;
    private float stateTime;
    private float deathDelta;
    private ACLGame game;
    public AnimationSystem(ACLGame game) {
        super(Family.all(TextureComponent.class, TransformComponent.class, AnimationComponent.class,HeroComponent.class,HealthComponent.class,SteeringComponent.class).get());
        this.game=game;
        tm = ComponentMapper.getFor(TextureComponent.class);
        trm = ComponentMapper.getFor(TransformComponent.class);
        am = ComponentMapper.getFor(AnimationComponent.class);
        dm = ComponentMapper.getFor(DirectionComponent.class);
        hm = ComponentMapper.getFor(HeroComponent.class);
        heam=ComponentMapper.getFor(HealthComponent.class);
        sm=ComponentMapper.getFor(SteeringComponent.class);
        sumDelta=0;
        deathDelta=0;
        float stateTime=0f;

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TextureComponent txtComp = tm.get(entity);
        TransformComponent trmComp = trm.get(entity);
        AnimationComponent amComp = am.get(entity);
        DirectionComponent dComp = dm.get(entity);
        HeroComponent hComp= hm.get(entity);
        HealthComponent healthComponent=heam.get(entity);
        SteeringComponent sc=sm.get(entity);

        float textureWidth = txtComp.getRegion().getRegionWidth();
        float textureHeigth = txtComp.getRegion().getRegionHeight();

        float x = trmComp.getPosition().x;
        float y = trmComp.getPosition().y;

        float xDraw = x - textureWidth / 2;
        float yDraw = y - textureHeigth / 2;
        stateTime += deltaTime;
        //System.out.println("heroHP:"+healthComponent.getHealthPoint());
        if(healthComponent.getHealthPoint()>0) {
            switch (dComp.getDirection()) {
                case DirectionComponent.UP:
                    if (attack || (sumDelta > 0 && sumDelta <= 0.4)) {
                        txtComp.setRegion(((TextureAtlas.AtlasRegion) amComp.getAnimationAttackUp().getKeyFrame(sumDelta)));
                        sumDelta += deltaTime;
                        if (sumDelta > amComp.getAnimationAttackUp().getFrameDuration() * 4) {
                            attack = false;
                            hComp.setState(hComp.STATE_WALKING);
                            sumDelta = 0;
                        }
                    } else {
                        txtComp.setRegion(((TextureAtlas.AtlasRegion) amComp.getAnimationUp().getKeyFrame(stateTime)));
                    }
                    break;

                case DirectionComponent.DOWN:
                    if (attack || (sumDelta > 0 && sumDelta <= 0.4)) {
                        txtComp.setRegion(((TextureAtlas.AtlasRegion) amComp.getAnimationAttackDown().getKeyFrame(sumDelta)));
                        sumDelta += deltaTime;
                        if (sumDelta > amComp.getAnimationAttackUp().getFrameDuration() * 4) {
                            attack = false;
                            hComp.setState(hComp.STATE_WALKING);
                            sumDelta = 0;
                        }
                    } else {
                        txtComp.setRegion(((TextureAtlas.AtlasRegion) amComp.getAnimationDown().getKeyFrame(stateTime)));
                    }
                    break;

                case DirectionComponent.RIGHT:
                    if (attack || (sumDelta > 0 && sumDelta <= 0.4)) {
                        txtComp.setRegion(((TextureAtlas.AtlasRegion) amComp.getAnimationAttackRight().getKeyFrame(sumDelta)));
                        sumDelta += deltaTime;
                        if (sumDelta > amComp.getAnimationAttackUp().getFrameDuration() * 4) {
                            attack = false;
                            hComp.setState(hComp.STATE_WALKING);
                            sumDelta = 0;
                        }
                    } else {
                        txtComp.setRegion(((TextureAtlas.AtlasRegion) amComp.getAnimationRight().getKeyFrame(stateTime)));
                    }
                    break;

                case DirectionComponent.LEFT:
                    if (attack || (sumDelta > 0 && sumDelta <= 0.4)) {
                        txtComp.setRegion(((TextureAtlas.AtlasRegion) amComp.getAnimationAttackLeft().getKeyFrame(sumDelta)));
                        sumDelta += deltaTime;
                        if (sumDelta > amComp.getAnimationAttackUp().getFrameDuration() * 4) {
                            attack = false;
                            hComp.setState(hComp.STATE_WALKING);
                            sumDelta = 0;
                        }
                    } else {
                        txtComp.setRegion(((TextureAtlas.AtlasRegion) amComp.getAnimationLeft().getKeyFrame(stateTime)));
                    }
                    break;
            }
        } else {
            txtComp.setRegion(((TextureAtlas.AtlasRegion) amComp.getAnimationDeath().getKeyFrame(deathDelta)));
            deathDelta += deltaTime;
            hComp.death();
            if (deathDelta > amComp.getAnimationDeath().getFrameDuration() * 8) {
                getEngine().removeEntity(entity);
                getEngine().getSystem(PhysicsSystem.class).getPhysicsWorld().destroyBody(sc.getBody());
                game.setScreen(new EndScreenLoose(game));
            }
        }
    }


    public void attack(){
        attack = true;
    }


}
