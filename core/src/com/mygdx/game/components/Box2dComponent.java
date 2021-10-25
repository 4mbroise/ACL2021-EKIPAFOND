package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;

public class Box2dComponent implements Component {

    public final Body body;
    public Box2dComponent() {
        this.body = null;
    }

    public Body getBody() {
        return body;
    }
}
