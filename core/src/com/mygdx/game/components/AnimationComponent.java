package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;



public class AnimationComponent implements Component {

    private TextureAtlas atlas; // Texture atlas
    private Array<TextureAtlas.AtlasRegion>  atlasRegionsL,atlasRegionsR, atlasRegionsU, atlasRegionsD; // AtlasRegion array for each walking direction (UP, DOWN, LEFT, RIGHT)
    public static float animTime = 0.3f; // animation time
    private Animation animationRight, animationLeft, animationUp, animationDown; // animation for each walking direction
    private Array<TextureAtlas.AtlasRegion>atlasRegionsAL,atlasRegionsAR, atlasRegionsAU, atlasRegionsAD;  // AtlasRegion array for each attacking direction (UP, DOWN, LEFT, RIGHT)
    private Animation animationAttackRight, animationAttackLeft, animationAttackUp, animationAttackDown; // animation for each attacking direction


    /**
     * Constructor
     */
    public AnimationComponent() {
        atlas = new TextureAtlas(Gdx.files.internal("sprites/HeroPack.atlas"));
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

    /**
     * Right walking animation getter
     * @return right walking animation
     */
    public Animation getAnimationRight() {
        return animationRight;
    }

    /**
     * Left walking animation getter
     * @return left walking animation
     */
    public Animation getAnimationLeft() {
        return animationLeft;
    }

    /**
     * Up walking animation getter
     * @return up walking animation
     */
    public Animation getAnimationUp() {
        return animationUp;
    }

    /**
     * Down walking animation getter
     * @return down walking animation
     */
    public Animation getAnimationDown() {
        return animationDown;
    }

    /**
     * Right attacking animation getter
     * @return right attacking animation
     */
    public Animation getAnimationAttackRight() {
        return animationAttackRight;
    }

    /**
     * Left attacking animation getter
     * @return left attacking animation
     */
    public Animation getAnimationAttackLeft() {
        return animationAttackLeft;
    }

    /**
     * Up attacking animation getter
     * @return up attacking animation
     */
    public Animation getAnimationAttackUp() {
        return animationAttackUp;
    }

    /**
     * Down attacking animation getter
     * @return down attacking animation
     */
    public Animation getAnimationAttackDown() {
        return animationAttackDown;
    }

    /**
     * Right walking animation setter
     */
    private void setRightAnim(){
        atlasRegionsR.add(atlas.findRegion("Character without weapon/walk/walk right1.png"));
        atlasRegionsR.add(atlas.findRegion("Character without weapon/walk/walk right2.png"));
        atlasRegionsR.add(atlas.findRegion("Character without weapon/walk/walk right3.png"));
        atlasRegionsR.add(atlas.findRegion("Character without weapon/walk/walk right4.png"));
        animationRight = new Animation(animTime, atlasRegionsR);
        animationRight.setPlayMode(Animation.PlayMode.LOOP);
    }

    /**
     * Left walking animation setter
     */
    private void setLeftAnim(){
        atlasRegionsL.add(atlas.findRegion("Character without weapon/walk/walk left1.png"));
        atlasRegionsL.add(atlas.findRegion("Character without weapon/walk/walk left2.png"));
        atlasRegionsL.add(atlas.findRegion("Character without weapon/walk/walk left3.png"));
        atlasRegionsL.add(atlas.findRegion("Character without weapon/walk/walk left4.png"));
        animationLeft = new Animation(animTime, atlasRegionsL);
        animationLeft.setPlayMode(Animation.PlayMode.LOOP);
    }

    /**
     * Up walking animation setter
     */
    private void setUpAnim(){
        atlasRegionsU.add(atlas.findRegion("Character without weapon/walk/walk up1.png"));
        atlasRegionsU.add(atlas.findRegion("Character without weapon/walk/walk up2.png"));
        atlasRegionsU.add(atlas.findRegion("Character without weapon/walk/walk up3.png"));
        atlasRegionsU.add(atlas.findRegion("Character without weapon/walk/walk up4.png"));
        animationUp = new Animation(animTime, atlasRegionsU);
        animationUp.setPlayMode(Animation.PlayMode.LOOP);
    }

    /**
     * Down walking animation setter
     */
    private void setDownAnim(){
        atlasRegionsD.add(atlas.findRegion("Character without weapon/walk/walk down1.png"));
        atlasRegionsD.add(atlas.findRegion("Character without weapon/walk/walk down2.png"));
        atlasRegionsD.add(atlas.findRegion("Character without weapon/walk/walk down3.png"));
        atlasRegionsD.add(atlas.findRegion("Character without weapon/walk/walk down4.png"));
        animationDown = new Animation(animTime, atlasRegionsD);
        animationDown.setPlayMode(Animation.PlayMode.LOOP);
    }

    /**
     * Right attacking animation setter
     */
    private void setAttackRightAnim(){
        atlasRegionsAR.add(atlas.findRegion("Character with sword and shield/attack/attack right1.png"));
        atlasRegionsAR.add(atlas.findRegion("Character with sword and shield/attack/attack right2.png"));
        atlasRegionsAR.add(atlas.findRegion("Character with sword and shield/attack/attack right3.png"));
        atlasRegionsAR.add(atlas.findRegion("Character with sword and shield/attack/attack right4.png"));
        animationAttackRight = new Animation(animTime, atlasRegionsAR);
        animationAttackRight.setPlayMode(Animation.PlayMode.LOOP);
    }

    /**
     * Left attacking animation setter
     */
    private void setAttackLeftAnim(){
        atlasRegionsAL.add(atlas.findRegion("Character with sword and shield/attack/attack left1.png"));
        atlasRegionsAL.add(atlas.findRegion("Character with sword and shield/attack/attack left2.png"));
        atlasRegionsAL.add(atlas.findRegion("Character with sword and shield/attack/attack left3.png"));
        atlasRegionsAL.add(atlas.findRegion("Character with sword and shield/attack/attack left4.png"));
        animationAttackLeft = new Animation(animTime, atlasRegionsAL);
        animationAttackLeft.setPlayMode(Animation.PlayMode.LOOP);
    }

    /**
     * Up attacking animation setter
     */
    private void setAttackUpAnim(){
        atlasRegionsAU.add(atlas.findRegion("Character with sword and shield/attack/attack up1.png"));
        atlasRegionsAU.add(atlas.findRegion("Character with sword and shield/attack/attack up2.png"));
        atlasRegionsAU.add(atlas.findRegion("Character with sword and shield/attack/attack up3.png"));
        atlasRegionsAU.add(atlas.findRegion("Character with sword and shield/attack/attack up4.png"));
        animationAttackUp = new Animation(animTime, atlasRegionsAU);
        animationAttackUp.setPlayMode(Animation.PlayMode.LOOP);
    }

    /**
     * Down attacking animation setter
     */
    private void setAttackDownAnim(){
        atlasRegionsAD.add(atlas.findRegion("Character with sword and shield/attack/attack down1.png"));
        atlasRegionsAD.add(atlas.findRegion("Character with sword and shield/attack/attack down2.png"));
        atlasRegionsAD.add(atlas.findRegion("Character with sword and shield/attack/attack down3.png"));
        atlasRegionsAD.add(atlas.findRegion("Character with sword and shield/attack/attack down4.png"));
        animationAttackDown = new Animation(animTime, atlasRegionsAD);
        animationAttackDown.setPlayMode(Animation.PlayMode.LOOP);
    }
}
