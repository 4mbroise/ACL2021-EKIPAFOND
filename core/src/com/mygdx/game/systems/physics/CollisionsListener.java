package com.mygdx.game.systems.physics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.components.CollisionComponent;


public class CollisionsListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        Entity entityA = (Entity) fixtureA.getBody().getUserData();
        Entity entityB = (Entity) fixtureB.getBody().getUserData();

        entityA.getComponent(CollisionComponent.class).addEntityCollied(entityB);
        entityB.getComponent(CollisionComponent.class).addEntityCollied(entityA);

    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        Entity entityA = (Entity) fixtureA.getBody().getUserData();
        Entity entityB = (Entity) fixtureB.getBody().getUserData();

        entityA.getComponent(CollisionComponent.class).removeEntityCollied(entityB);
        entityB.getComponent(CollisionComponent.class).removeEntityCollied(entityA);
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
