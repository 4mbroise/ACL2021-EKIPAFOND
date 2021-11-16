package com.mygdx.game.systems.physics;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.components.SteeringComponent;
import com.mygdx.game.components.TransformComponent;

public class PhysicsSystem extends IteratingSystem {

    private World physicsWorld;
    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<SteeringComponent> sm;
    private final FixtureDef fixturePrototype = new FixtureDef();
    private  PolygonShape shapePrototype;
    private final BodyDef BodyDefPrototype = new BodyDef();


    public PhysicsSystem() {
        super(Family.all(TransformComponent.class, SteeringComponent.class).get());
        this.physicsWorld = new World(new Vector2(0,0), false);
        this.physicsWorld.setContactListener(new CollisionsListener());
        tm = ComponentMapper.getFor(TransformComponent.class);
        sm = ComponentMapper.getFor(SteeringComponent.class);
        this.shapePrototype =  new PolygonShape();
    }

    private Body addOctogonBody(float abscissa , float ordinate, float width, float heigth){
        BodyDefPrototype.position.set(abscissa, ordinate);
        float[] vertices = {0-width,2-heigth,
                            0-width,heigth-2,
                            2-width,heigth,
                            width-2,heigth,
                            width,heigth-2,
                            width,2-heigth,
                            width-2,0-heigth,
                            2-width,0-heigth};
        shapePrototype.set(vertices);
        fixturePrototype.shape = shapePrototype;
        return this.physicsWorld.createBody(BodyDefPrototype);
    }

    private Body addBoxBody(float abscissa , float ordinate, float width, float height){
        BodyDefPrototype.position.set(abscissa, ordinate);
        shapePrototype.setAsBox(width, height);
        fixturePrototype.shape = shapePrototype;
        return this.physicsWorld.createBody(BodyDefPrototype);
    }

    public Body addDynamicBody(float abscissa , float ordinate, float width, float height ){
        BodyDefPrototype.type = BodyDef.BodyType.DynamicBody;
        fixturePrototype.isSensor = false;
        Body body = addOctogonBody(abscissa , ordinate, width, height);
        body.createFixture(fixturePrototype);
        return body;
    }

    public Body addStaticBody(float abscissa , float ordinate, float width, float height ){
        BodyDefPrototype.type = BodyDef.BodyType.StaticBody;
        fixturePrototype.isSensor = false;
        Body body = addOctogonBody(abscissa , ordinate, width, height);
        body.createFixture(fixturePrototype);
        return body;
    }

    public Body addSensorBody(float abscissa , float ordinate, float width, float height){
        BodyDefPrototype.type = BodyDef.BodyType.StaticBody;
        fixturePrototype.isSensor = true;
        Body body = addOctogonBody(abscissa , ordinate, width, height);
        body.createFixture(fixturePrototype);
        return body;
    }

    public World getPhysicsWorld() {
        return physicsWorld;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        //this.physicsWorld.step(deltaTime, 8, 3);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        this.physicsWorld.step(deltaTime, 8, 3);

        TransformComponent  tComp = tm.get(entity);
        SteeringComponent   sComp = sm.get(entity);
        tComp.setAbscissa(sComp.getBody().getPosition().x);
        tComp.setOrdinate(sComp.getBody().getPosition().y);

    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
        shapePrototype.dispose();
    }
}
