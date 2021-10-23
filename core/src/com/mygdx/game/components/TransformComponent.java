package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;

public class TransformComponent implements Component {
    private Vector3 position;

    public TransformComponent(Vector3 position) {
        this.position = position;
    }

    public Vector3 getPosition(){
        return new Vector3(position);
    }

    public void applyPositionTranslation(Vector3 newPosition, float scalar){
        this.position.mulAdd(newPosition, scalar);
    }

}
