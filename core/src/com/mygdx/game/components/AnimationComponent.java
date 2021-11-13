package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class AnimationComponent implements Component {

    private TextureAtlas atlas;
    private Array<TextureAtlas.AtlasRegion> atlasRegions, atlasRegionsL,atlasRegionsR, atlasRegionsU, atlasRegionsD;
    public float animTime = 0.2f;
    private Animation animationRight, animationLeft, animationUp, animationDown;

    public AnimationComponent() {
        atlas = new TextureAtlas(Gdx.files.internal("sprites/HeroPack.atlas"));
        atlasRegions= atlas.getRegions();
        atlasRegionsD = new Array<>();
        atlasRegionsU = new Array<>();
        atlasRegionsL = new Array<>();
        atlasRegionsR = new Array<>();
        setDownAnim();
        setUpAnim();
        setLeftAnim();
        setRightAnim();
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public Array<TextureAtlas.AtlasRegion> getAtlasRegions() {
        return atlasRegions;
    }


    public Array<TextureAtlas.AtlasRegion> getAtlasRegionsL() {
        return atlasRegionsL;
    }

    public Array<TextureAtlas.AtlasRegion> getAtlasRegionsR() {
        return atlasRegionsR;
    }

    public Array<TextureAtlas.AtlasRegion> getAtlasRegionsU() {
        return atlasRegionsU;
    }

    public Array<TextureAtlas.AtlasRegion> getAtlasRegionsD() {
        return atlasRegionsD;
    }

    public Animation getAnimationRight() {
        return animationRight;
    }

    public Animation getAnimationLeft() {
        return animationLeft;
    }

    public Animation getAnimationUp() {
        return animationUp;
    }

    public Animation getAnimationDown() {
        return animationDown;
    }

    private void setRightAnim(){
        atlasRegionsR.add(atlas.findRegion("Character without weapon/walk/walk right1.png"));
        atlasRegionsR.add(atlas.findRegion("Character without weapon/walk/walk right2.png"));
        atlasRegionsR.add(atlas.findRegion("Character without weapon/walk/walk right2.png"));
        atlasRegionsR.add(atlas.findRegion("Character without weapon/walk/walk right4.png"));
        animationRight = new Animation(animTime, atlasRegionsR);
        animationRight.setPlayMode(Animation.PlayMode.LOOP);
    }

    private void setLeftAnim(){
        atlasRegionsL.add(atlas.findRegion("Character without weapon/walk/walk left1.png"));
        atlasRegionsL.add(atlas.findRegion("Character without weapon/walk/walk left2.png"));
        atlasRegionsL.add(atlas.findRegion("Character without weapon/walk/walk left3.png"));
        atlasRegionsL.add(atlas.findRegion("Character without weapon/walk/walk left4.png"));
        animationLeft = new Animation(animTime, atlasRegionsL);
        animationLeft.setPlayMode(Animation.PlayMode.LOOP);
    }
    private void setUpAnim(){
        atlasRegionsU.add(atlas.findRegion("Character without weapon/walk/walk up1.png"));
        atlasRegionsU.add(atlas.findRegion("Character without weapon/walk/walk up2.png"));
        atlasRegionsU.add(atlas.findRegion("Character without weapon/walk/walk up3.png"));
        atlasRegionsU.add(atlas.findRegion("Character without weapon/walk/walk up4.png"));
        animationUp = new Animation(animTime, atlasRegionsU);
        animationUp.setPlayMode(Animation.PlayMode.LOOP);
    }
    private void setDownAnim(){
        atlasRegionsD.add(atlas.findRegion("Character without weapon/walk/walk down1.png"));
        atlasRegionsD.add(atlas.findRegion("Character without weapon/walk/walk down2.png"));
        atlasRegionsD.add(atlas.findRegion("Character without weapon/walk/walk down3.png"));
        atlasRegionsD.add(atlas.findRegion("Character without weapon/walk/walk down4.png"));
        animationDown = new Animation(animTime, atlasRegionsD);
        animationDown.setPlayMode(Animation.PlayMode.LOOP);
    }
}
