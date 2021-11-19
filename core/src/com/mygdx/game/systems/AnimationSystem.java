package com.mygdx.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
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

    private boolean attack=false;
    private float sumDelta;
    private float stateTime;

    public AnimationSystem() {
        super(Family.all(TextureComponent.class, TransformComponent.class, AnimationComponent.class,HeroComponent.class).get());

        tm = ComponentMapper.getFor(TextureComponent.class);
        trm = ComponentMapper.getFor(TransformComponent.class);
        am = ComponentMapper.getFor(AnimationComponent.class);
        dm = ComponentMapper.getFor(DirectionComponent.class);
        hm = ComponentMapper.getFor(HeroComponent.class);
        sumDelta=0;
        float stateTime=0f;

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
        stateTime += deltaTime;
        switch (dComp.getDirection()) {
            case DirectionComponent.UP:
                if(attack||(sumDelta>0&&sumDelta<=0.4)) {
                    txtComp.setRegion(((TextureAtlas.AtlasRegion) amComp.getAnimationAttackUp().getKeyFrame(sumDelta)));
                    sumDelta+=deltaTime;
                    if(sumDelta>amComp.getAnimationAttackUp().getFrameDuration()*4){
                        attack=false;
                        hComp.setState(hComp.STATE_WALKING);
                        sumDelta=0;
                    }
                }else {
                    txtComp.setRegion(((TextureAtlas.AtlasRegion) amComp.getAnimationUp().getKeyFrame(stateTime)));
                }
                break;

            case DirectionComponent.DOWN:
                if(attack||(sumDelta>0&&sumDelta<=0.4)) {
                    txtComp.setRegion(((TextureAtlas.AtlasRegion) amComp.getAnimationAttackDown().getKeyFrame(sumDelta)));
                    sumDelta+=deltaTime;
                    if(sumDelta>amComp.getAnimationAttackUp().getFrameDuration()*4){
                        attack=false;
                        hComp.setState(hComp.STATE_WALKING);
                        sumDelta=0;
                    }
                }else {
                    txtComp.setRegion(((TextureAtlas.AtlasRegion) amComp.getAnimationDown().getKeyFrame(stateTime)));
                }
                break;

            case DirectionComponent.RIGHT:
                if(attack||(sumDelta>0&&sumDelta<=0.4)) {
                    txtComp.setRegion(((TextureAtlas.AtlasRegion) amComp.getAnimationAttackRight().getKeyFrame(sumDelta)));
                    sumDelta+=deltaTime;
                    if(sumDelta>amComp.getAnimationAttackUp().getFrameDuration()*4){
                        attack=false;
                        hComp.setState(hComp.STATE_WALKING);
                        sumDelta=0;
                    }
                } else{
                    txtComp.setRegion(((TextureAtlas.AtlasRegion) amComp.getAnimationRight().getKeyFrame(stateTime)));
                }
                break;

            case DirectionComponent.LEFT:
                if(attack||(sumDelta>0&&sumDelta<=0.4)) {
                    txtComp.setRegion(((TextureAtlas.AtlasRegion) amComp.getAnimationAttackLeft().getKeyFrame(sumDelta)));
                    sumDelta+=deltaTime;
                    if(sumDelta>amComp.getAnimationAttackUp().getFrameDuration()*4){
                        attack=false;
                        hComp.setState(hComp.STATE_WALKING);
                        sumDelta=0;
                    }
                }else {
                    txtComp.setRegion(((TextureAtlas.AtlasRegion) amComp.getAnimationLeft().getKeyFrame(stateTime)));
                }
                //System.out.println("delta:"+deltaTime);
                break;
        }

    }

    public void attack(){
        attack = true;
    }


}
