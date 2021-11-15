package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class AnimationComponent implements Component {

    private TextureAtlas atlas;
    private Array<TextureAtlas.AtlasRegion> atlasRegions, atlasRegionsL,atlasRegionsR, atlasRegionsU, atlasRegionsD;
    public float animTime = 0.3f;
    private Animation animationRight, animationLeft, animationUp, animationDown;
    private Array<TextureAtlas.AtlasRegion>atlasRegionsAL,atlasRegionsAR, atlasRegionsAU, atlasRegionsAD;
    private Animation animationAttackRight, animationAttackLeft, animationAttackUp, animationAttackDown;

    public AnimationComponent() {
        atlas = new TextureAtlas(Gdx.files.internal("sprites/HeroPack.atlas"));
        atlasRegions= atlas.getRegions();
        //walk
        atlasRegionsD = new Array<>();
        atlasRegionsU = new Array<>();
        atlasRegionsL = new Array<>();
        atlasRegionsR = new Array<>();
        setDownAnim();
        setUpAnim();
        setLeftAnim();
        setRightAnim();
        //attack
        atlasRegionsAD = new Array<>();
        atlasRegionsAU = new Array<>();
        atlasRegionsAL = new Array<>();
        atlasRegionsAR = new Array<>();
        setAttackDownAnim();
        setAttackUpAnim();
        setAttackLeftAnim();
        setAttackRightAnim();
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

    public Animation getAnimationAttackRight() {
        return animationAttackRight;
    }

    public Animation getAnimationAttackLeft() {
        return animationAttackLeft;
    }

    public Animation getAnimationAttackUp() {
        return animationAttackUp;
    }

    public Animation getAnimationAttackDown() {
        return animationAttackDown;
    }

    private void setRightAnim(){
        atlasRegionsR.add(atlas.findRegion("Character without weapon/walk/walk right1.png"));
        atlasRegionsR.add(atlas.findRegion("Character without weapon/walk/walk right2.png"));
        atlasRegionsR.add(atlas.findRegion("Character without weapon/walk/walk right3.png"));
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

    private void setAttackRightAnim(){
        atlasRegionsAR.add(atlas.findRegion("Character with sword and shield/attack/attack right1.png"));
        atlasRegionsAR.add(atlas.findRegion("Character with sword and shield/attack/attack right2.png"));
        atlasRegionsAR.add(atlas.findRegion("Character with sword and shield/attack/attack right3.png"));
        atlasRegionsAR.add(atlas.findRegion("Character with sword and shield/attack/attack right4.png"));
        animationAttackRight = new Animation(animTime, atlasRegionsAR);
        animationAttackRight.setPlayMode(Animation.PlayMode.LOOP);
    }

    private void setAttackLeftAnim(){
        atlasRegionsAL.add(atlas.findRegion("Character with sword and shield/attack/attack left1.png"));
        atlasRegionsAL.add(atlas.findRegion("Character with sword and shield/attack/attack left2.png"));
        atlasRegionsAL.add(atlas.findRegion("Character with sword and shield/attack/attack left3.png"));
        atlasRegionsAL.add(atlas.findRegion("Character with sword and shield/attack/attack left4.png"));
        animationAttackLeft = new Animation(animTime, atlasRegionsAL);
        animationAttackLeft.setPlayMode(Animation.PlayMode.LOOP);
    }
    private void setAttackUpAnim(){
        atlasRegionsAU.add(atlas.findRegion("Character with sword and shield/attack/attack up1.png"));
        atlasRegionsAU.add(atlas.findRegion("Character with sword and shield/attack/attack up2.png"));
        atlasRegionsAU.add(atlas.findRegion("Character with sword and shield/attack/attack up3.png"));
        atlasRegionsAU.add(atlas.findRegion("Character with sword and shield/attack/attack up4.png"));
        animationAttackUp = new Animation(animTime, atlasRegionsAU);
        animationAttackUp.setPlayMode(Animation.PlayMode.LOOP);
    }
    private void setAttackDownAnim(){
        atlasRegionsAD.add(atlas.findRegion("Character with sword and shield/attack/attack down1.png"));
        atlasRegionsAD.add(atlas.findRegion("Character with sword and shield/attack/attack down2.png"));
        atlasRegionsAD.add(atlas.findRegion("Character with sword and shield/attack/attack down3.png"));
        atlasRegionsAD.add(atlas.findRegion("Character with sword and shield/attack/attack down4.png"));
        animationAttackDown = new Animation(animTime, atlasRegionsAD);
        animationAttackDown.setPlayMode(Animation.PlayMode.LOOP);
    }
}
