package com.mygdx.game.components;

import com.badlogic.gdx.physics.box2d.Body;

public class Box2dComponent {

    private Body body;
    public Box2dComponent(Body body) {
        this.body = body;
    }

    public Body getBody() {
        return body;
    }
}
