package com.mygdx.game.components;


import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.mygdx.game.tools.Box2dLocation;
import com.mygdx.game.tools.Utility;


/**
 * Class for the Steering Component
 */
public class SteeringComponent implements Steerable<Vector2>, Component, Poolable{

    public static enum SteeringState {WANDER,SEEK,FLEE,ARRIVE,NONE} //Enum for the behaviors
    public SteeringState currentMode = SteeringState.SEEK; // current mode (Wander, Seek, Flee, Arrive)
    private Body body; // Body linked to the component

    // Steering data
    private float maxLinearSpeed = 15f;
    private float maxLinearAcceleration = 30f;
    private float maxAngularSpeed =30f;
    private float maxAngularAcceleration = 10f;
    private float zeroThreshold = 0.1f;
    public SteeringBehavior<Vector2> steeringBehavior; // Behavior applied to our comoonent
    private static final SteeringAcceleration<Vector2> steeringOutput = new SteeringAcceleration<Vector2>(new Vector2()); // accelaration vector of our component
    private float boundingRadius = 1f;
    private boolean tagged = true;
    private boolean independentFacing = false;


    public SteeringComponent(Body body) {
        this.body=body;
    }


    @Override
    public void reset() {
        currentMode = SteeringState.NONE;
        body = null;
        steeringBehavior = null;
    }

    /** Call this to update the steering behaviour (per frame)
     * @param delta delta time between frames
     */
    public void update (float delta) {
        if (steeringBehavior != null) {
            steeringBehavior.calculateSteering(steeringOutput);
            applySteering(steeringOutput, delta);
        }
    }

    /** apply steering to the Box2d body
     * @param steering the steering vector
     * @param deltaTime teh delta time
     */
    protected void applySteering (SteeringAcceleration<Vector2> steering, float deltaTime) {
        boolean anyAccelerations = false;

        // Update position and linear velocity.
        if (!steering.linear.isZero()) {
            // this method internally scales the force by deltaTime
            body.applyForceToCenter(steering.linear, true);
            anyAccelerations = true;
        }

        // Update orientation and angular velocity
        if (isIndependentFacing()) {
            if (steering.angular != 0) {
                // this method internally scales the torque by deltaTime
                body.applyTorque(steering.angular, true);
                anyAccelerations = true;
            }
        } else {
            // If we haven't got any velocity, then we can do nothing.
            Vector2 linVel = getLinearVelocity();
            if (!linVel.isZero(getZeroLinearSpeedThreshold())) {
                float newOrientation = vectorToAngle(linVel);
                body.setAngularVelocity((newOrientation - getAngularVelocity()) * deltaTime); // this is superfluous if independentFacing is always true
                body.setTransform(body.getPosition(), newOrientation);
            }
        }

        if (anyAccelerations) {
            // Cap the linear speed
            Vector2 velocity = body.getLinearVelocity();
            float currentSpeedSquare = velocity.len2();
            float maxLinearSpeed = getMaxLinearSpeed();
            if (currentSpeedSquare > (maxLinearSpeed * maxLinearSpeed)) {
                body.setLinearVelocity(velocity.scl(maxLinearSpeed / (float)Math.sqrt(currentSpeedSquare)));
            }
            // Cap the angular speed
            float maxAngVelocity = getMaxAngularSpeed();
            if (body.getAngularVelocity() > maxAngVelocity) {
                body.setAngularVelocity(maxAngVelocity);
            }
        }
    }

    public SteeringState getCurrentMode() {
        return currentMode;
    }

    public Body getBody() {
        return body;
    }

    public float getZeroThreshold() {
        return zeroThreshold;
    }

    public SteeringBehavior<Vector2> getSteeringBehavior() {
        return steeringBehavior;
    }

    public boolean isIndependentFacing () {
        return independentFacing;
    }

    public void setIndependentFacing (boolean independentFacing) {
        this.independentFacing = independentFacing;
    }



    @Override
    public Vector2 getPosition() {
        return body.getPosition();
    }

    @Override
    public float getOrientation() {
        return body.getAngle();
    }

    @Override
    public void setOrientation(float orientation) {
        body.setTransform(getPosition(), orientation);
    }
    @Override
    public float vectorToAngle(Vector2 vector) {
        return Utility.vectorToAngle(vector);
    }

    /**
     * Function which converts an angle to a Vector2
     * @param outVector new Vector2
     * @param angle angle which xe xant to convert
     * @return new Vector2 from the input angle
     */
    @Override
    public Vector2 angleToVector(Vector2 outVector, float angle) {
        return Utility.angleToVector(outVector, angle);
    }

    /**
     * Function that returns new Box2dLocation
     * @return Box2dLpcation
     */
    @Override
    public Location<Vector2> newLocation() {
        return new Box2dLocation();
    }
    @Override
    public float getZeroLinearSpeedThreshold() {
        return zeroThreshold;
    }
    @Override
    public void setZeroLinearSpeedThreshold(float value) {
        zeroThreshold = value;
    }
    @Override
    public float getMaxLinearSpeed() {
        return this.maxLinearSpeed;
    }
    @Override
    public void setMaxLinearSpeed(float maxLinearSpeed) {
        this.maxLinearSpeed = maxLinearSpeed;
    }
    @Override
    public float getMaxLinearAcceleration() {
        return this.maxLinearAcceleration;
    }
    @Override
    public void setMaxLinearAcceleration(float maxLinearAcceleration) {
        this.maxLinearAcceleration = maxLinearAcceleration;
    }
    @Override
    public float getMaxAngularSpeed() {
        return this.maxAngularSpeed;
    }
    @Override
    public void setMaxAngularSpeed(float maxAngularSpeed) {
        this.maxAngularSpeed = maxAngularSpeed;
    }
    @Override
    public float getMaxAngularAcceleration() {
        return this.maxAngularAcceleration;
    }
    @Override
    public void setMaxAngularAcceleration(float maxAngularAcceleration) {
        this.maxAngularAcceleration = maxAngularAcceleration;
    }
    @Override
    public Vector2 getLinearVelocity() {
        return body.getLinearVelocity();
    }

    public void setLinearVelocity(Vector2 newLinearVelocity){
        this.body.setLinearVelocity(newLinearVelocity);
    }
    @Override
    public float getAngularVelocity() {
        return body.getAngularVelocity();
    }
    @Override
    public float getBoundingRadius() {
        return this.boundingRadius;
    }
    @Override
    public boolean isTagged() {
        return this.tagged;
    }
    @Override
    public void setTagged(boolean tagged) {
        this.tagged = tagged;
    }



}