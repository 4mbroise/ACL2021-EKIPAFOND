package com.mygdx.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.components.BodyComponent;
import com.mygdx.game.components.TransformComponent;

public class PhysicsSystem extends IteratingSystem {

    private World physicsWorld;
    private ComponentMapper<BodyComponent> bm;
    private ComponentMapper<TransformComponent> tm;
    private final FixtureDef fixturePrototype = new FixtureDef();
    private  PolygonShape shapePrototype;
    private final BodyDef BodyDefPrototype = new BodyDef();


    public PhysicsSystem() {
        super(Family.all(TransformComponent.class, BodyComponent.class).get());
        this.physicsWorld = new World(new Vector2(0,0), false);
        bm = ComponentMapper.getFor(BodyComponent.class);
        tm = ComponentMapper.getFor(TransformComponent.class);
        this.shapePrototype =  new PolygonShape();
    }

    public Body addDynamicBody(float abscissa , float ordinate, float width, float heigth ){
        BodyDefPrototype.type = BodyDef.BodyType.DynamicBody;
        BodyDefPrototype.position.set(abscissa, ordinate);
        Body body = this.physicsWorld.createBody(BodyDefPrototype);
        shapePrototype.setAsBox(width, heigth);
        fixturePrototype.shape = shapePrototype;
        fixturePrototype.isSensor = false;
        body.createFixture(fixturePrototype);
        return body;
    }

    public Body addStaticBody(float abscissa , float ordinate, float width, float heigth ){
        BodyDefPrototype.type = BodyDef.BodyType.StaticBody;
        BodyDefPrototype.position.set(abscissa, ordinate);
        Body body = this.physicsWorld.createBody(BodyDefPrototype);
        shapePrototype.setAsBox(width, heigth);
        fixturePrototype.shape = shapePrototype;
        fixturePrototype.isSensor = false;
        body.createFixture(fixturePrototype);
        return body;
    }

    public Body addSensorBody(float abscissa , float ordinate, float width, float heigth){
        BodyDefPrototype.type = BodyDef.BodyType.StaticBody;
        BodyDefPrototype.position.set(abscissa, ordinate);
        Body body = this.physicsWorld.createBody(BodyDefPrototype);
        shapePrototype.setAsBox(width, heigth);
        fixturePrototype.shape = shapePrototype;
        fixturePrototype.isSensor = true;
        body.createFixture(fixturePrototype);
        return body;
    }

    public World getPhysicsWorld() {
        return physicsWorld;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        this.physicsWorld.step(deltaTime, 8, 3);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent  tComp = tm.get(entity);
        BodyComponent       bComp = bm.get(entity);

        tComp.setAbscissa(bComp.getBody().getPosition().x);
        tComp.setOrdinate(bComp.getBody().getPosition().y);

    }
}
