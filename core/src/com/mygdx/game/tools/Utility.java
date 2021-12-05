package com.mygdx.game.tools;

import com.badlogic.gdx.math.Vector2;


/**
 *  Utility class for all minor operations
 */
public class Utility {

    public Utility() {
    }

    /**
     * Converts a vector2 to an angle
     * @param vector Vector2 that we want to convert to an angle
     * @return angle value (float)
     */
    public static float vectorToAngle (Vector2 vector) {
        return (float)Math.atan2(-vector.x, vector.y);
    }

    /**
     * Converts an angle to a vector
     * @param outVector new Vector2
     * @param angle angle which we want to transform to a Vector2
     * @return new Vector2
     */
    public static Vector2 angleToVector (Vector2 outVector, float angle) {
        outVector.x = -(float)Math.sin(angle);
        outVector.y = (float)Math.cos(angle);
        return outVector;
    }

}
