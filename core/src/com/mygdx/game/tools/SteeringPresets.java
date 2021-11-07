package com.mygdx.game.tools;

import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.ai.steer.behaviors.Flee;
import com.badlogic.gdx.ai.steer.behaviors.Seek;
import com.badlogic.gdx.ai.steer.behaviors.Wander;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.components.HeroComponent;
import com.mygdx.game.components.SteeringComponent;


/**
 * Class which implements steering behaviours
 * Wander, Seek, Flee and
 */
public class SteeringPresets {

    private static final float wanderOffset = 20f;
    private static final float wanderOrientation = 1f;
    private static final float wanderRadius = 10f;
    private static final float timeToTarget = 0.1f;
    private static final float arrivalTolerance = 10f;
    private static final float decelerationRadius = 20f;


    /**
     * Default constructor
     */
    public SteeringPresets() {
    }

    /**
     * Function which creates Wander behaviour for the monster
     * @param scom SteeringComponent linked to the monster
     * @return Wander behaviour applied to the monster
     */
    public static Wander<Vector2> getWander(SteeringComponent scom){
        Wander<Vector2> wander = new Wander<Vector2>(scom)
                .setFaceEnabled(false) // let wander behaviour manage facing
                .setWanderOffset(wanderOffset) // distance away from entity to set target
                .setWanderOrientation(wanderOrientation) // the initial orientation
                .setWanderRadius(wanderRadius) // size of target
                .setWanderRate(MathUtils.PI2 * 2); // higher values = more spinning
        return wander;
    }


    /**
     * Function which permits monster to seek the hero
     * @param seeker SteeringComponent linked to the monster
     * @param target SteeringComponent linked to the hero
     * @return Seek behaviour applied to the monster
     */
    public static Seek<Vector2> getSeek(SteeringComponent seeker, SteeringComponent target){
        Seek<Vector2> seek = new Seek<Vector2>(seeker,target);
        return seek;
    }

    /**
     * Function which permits the monster to flee from hero
     * @param runner SteeringComponent linked to the monster
     * @param fleeingFrom SteeringComponent linked to the hero
     * @return Flee behaviour applied to the monster
     */
    public static Flee<Vector2> getFlee(SteeringComponent runner, SteeringComponent fleeingFrom){
        Flee<Vector2> flee = new Flee<Vector2>(runner,fleeingFrom);
        return flee;
    }

    /**
     * Function which permits monster to pursue the hero (not the same way than seek)
     * @param runner SteeringComponent linked to the monster
     * @param target SteeringComponent linked to the hero
     * @return Arrive behaviour linked to the monster
     */
    public static Arrive<Vector2> getArrive(SteeringComponent runner, SteeringComponent target){
        Arrive<Vector2> arrive = new Arrive<Vector2>(runner, target)
                .setTimeToTarget(timeToTarget) // default 0.1f
                .setArrivalTolerance(arrivalTolerance) //
                .setDecelerationRadius(decelerationRadius);
        return arrive;
    }
}
