package com.mygdx.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.components.*;

public class AnimationSystem extends IteratingSystem {
    private ComponentMapper<TextureComponent> tm;
    private ComponentMapper<TransformComponent> trm;
    private ComponentMapper<AnimationComponent> am;
    private ComponentMapper<DirectionComponent> dm;
    private ComponentMapper<HeroComponent> hm;

    private float sumDelta;
    public AnimationSystem() {
        super(Family.all(TextureComponent.class, TransformComponent.class, AnimationComponent.class,HeroComponent.class).get());

        tm = ComponentMapper.getFor(TextureComponent.class);
        trm = ComponentMapper.getFor(TransformComponent.class);
        am = ComponentMapper.getFor(AnimationComponent.class);
        dm = ComponentMapper.getFor(DirectionComponent.class);
        hm = ComponentMapper.getFor(HeroComponent.class);
        sumDelta=0;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TextureComponent txtComp = tm.get(entity);
        TransformComponent trmComp = trm.get(entity);
        AnimationComponent amComp = am.get(entity);
        DirectionComponent dComp = dm.get(entity);
        HeroComponent hComp= hm.get(entity);


        float textureWidth = txtComp.getRegion().getRegionWidth();
        float textureHeigth = txtComp.getRegion().getRegionHeight();

        float x = trmComp.getPosition().x;
        float y = trmComp.getPosition().y;

        float xDraw = x - textureWidth / 2;
        float yDraw = y - textureHeigth / 2;


        switch (dComp.getDirection()) {
            case DirectionComponent.UP:
                if(hComp.getState()==hComp.STATE_ATTACKING||(sumDelta>0&&sumDelta<=0.3)) {
                    txtComp.setRegion(((TextureAtlas.AtlasRegion) amComp.getAnimationAttackUp().getKeyFrame(amComp.animTime)));
                    sumDelta+=deltaTime;
                    if(sumDelta>0.3){
                        hComp.setState(hComp.STATE_WALKING);
                        sumDelta=0;
                    }
                }else {
                    txtComp.setRegion(((TextureAtlas.AtlasRegion) amComp.getAnimationUp().getKeyFrame(amComp.animTime)));
                }
                amComp.animTime += deltaTime;
                break;

            case DirectionComponent.DOWN:
                if(hComp.getState()==hComp.STATE_ATTACKING||(sumDelta>0&&sumDelta<=0.3)) {
                    txtComp.setRegion(((TextureAtlas.AtlasRegion) amComp.getAnimationAttackDown().getKeyFrame(amComp.animTime)));
                    sumDelta+=deltaTime;
                    if(sumDelta>0.3){
                        hComp.setState(hComp.STATE_WALKING);
                        sumDelta=0;
                    }
                }else {
                    txtComp.setRegion(((TextureAtlas.AtlasRegion) amComp.getAnimationDown().getKeyFrame(amComp.animTime)));
                }
                amComp.animTime += deltaTime;
                break;

            case DirectionComponent.RIGHT:
                if(hComp.getState()==hComp.STATE_ATTACKING||(sumDelta>0&&sumDelta<=0.3)) {
                    txtComp.setRegion(((TextureAtlas.AtlasRegion) amComp.getAnimationAttackRight().getKeyFrame(amComp.animTime)));
                    sumDelta+=deltaTime;
                    if(sumDelta>0.3){
                        hComp.setState(hComp.STATE_WALKING);
                        sumDelta=0;
                    }
                } else{
                    txtComp.setRegion(((TextureAtlas.AtlasRegion) amComp.getAnimationRight().getKeyFrame(amComp.animTime)));
                }
                amComp.animTime += deltaTime;
                break;

            case DirectionComponent.LEFT:
                if(hComp.getState()==hComp.STATE_ATTACKING||(sumDelta>0&&sumDelta<=0.3)) {
                    txtComp.setRegion(((TextureAtlas.AtlasRegion) amComp.getAnimationAttackLeft().getKeyFrame(amComp.animTime)));
                    sumDelta+=deltaTime;
                    if(sumDelta>0.3){
                        hComp.setState(hComp.STATE_WALKING);
                        sumDelta=0;
                    }
                }else {
                    txtComp.setRegion(((TextureAtlas.AtlasRegion) amComp.getAnimationLeft().getKeyFrame(amComp.animTime)));
                }
                amComp.animTime += deltaTime;
                //System.out.println("delta:"+deltaTime);
                break;
        }

    }


}
