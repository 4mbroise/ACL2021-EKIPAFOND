package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class BodyComponent implements Component {
    private Body body;

    public BodyComponent(Body body) {
        this.body = body;
    }

    public Body getBody() {
        return body;
    }

    public void setPosition(Vector2 newPosition){
        this.body.setTransform(newPosition, 0);
    }

}
