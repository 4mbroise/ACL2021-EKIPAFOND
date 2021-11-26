package com.mygdx.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.components.DirectionComponent;
import com.mygdx.game.components.HeroComponent;
import com.mygdx.game.components.MovementComponent;

public class HeroSystem extends IteratingSystem {

    private ComponentMapper<DirectionComponent> dm;
    private ComponentMapper<MovementComponent> mm;
    private int direction = DirectionComponent.DOWN;
    private ComponentMapper<HeroComponent> hm;
    private float timeInvicibility;
    private float timeSlowed;


    public HeroSystem() {
        super(Family.all(HeroComponent.class).get());
        hm=ComponentMapper.getFor(HeroComponent.class);
        dm =ComponentMapper.getFor(DirectionComponent.class);
        mm = ComponentMapper.getFor(MovementComponent.class);
        timeInvicibility=0;
        timeSlowed = 0;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        DirectionComponent  dirComp     = dm.get(entity);
        HeroComponent heroComponent=hm.get(entity);
        MovementComponent movementComponent = mm.get(entity);
        if(heroComponent.getState()==heroComponent.STATE_DEATH){
            this.direction=dirComp.STATIC;
        } else if(heroComponent.getState()==heroComponent.STATE_INVINCIBILITY) {
            if (timeInvicibility < 2) {
                timeInvicibility += Gdx.graphics.getDeltaTime();
                System.out.println(heroComponent.getState());
            } else {
                timeInvicibility = 0;
                heroComponent.liftInvincibility();
            }
        }
        if(heroComponent.isSlowed()){
            if(timeSlowed < 3){
                timeSlowed += Gdx.graphics.getDeltaTime();
                movementComponent.setVelocity(HeroComponent.HERO_VELOCITY/2);
                System.out.println(timeSlowed);
            } else{
                timeSlowed = 0;
                movementComponent.setVelocity(HeroComponent.HERO_VELOCITY);
                heroComponent.setSlowedStatus(false);
            }
        }
        dirComp.setDirection(this.direction);

    }

    public void setHeroDirection(int directionCode){
        if(direction != DirectionComponent.STATIC) {
            this.direction = directionCode;
        }
    }

}
