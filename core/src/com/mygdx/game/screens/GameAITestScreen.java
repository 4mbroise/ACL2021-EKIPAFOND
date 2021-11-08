package com.mygdx.game.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.game.ACLGame;
import com.mygdx.game.Assets;
import com.mygdx.game.tools.SteeringPresets;
import com.mygdx.game.components.*;
import com.mygdx.game.listeners.ACLGameListener;
import com.mygdx.game.systems.*;
import com.mygdx.game.systems.physics.PhysicsSystem;

public class GameAITestScreen extends GameScreen{
    public ACLGame game;
    private Entity hero;
    private Entity monster;
    public GameAITestScreen(ACLGame game) {
        super(game);
        this.game = game;
        this.engine.addSystem(new RenderSystem(this.game.batcher));
        this.engine.addSystem(new MovementSystem());
        this.engine.addSystem(new HeroSystem());
        //this.engine.addSystem(new AISystem());
        this.engine.addSystem(new PhysicsSystem());
        createHero();
        this.engine.addSystem(new MonsterSystem(hero));
        this.assets.getManager().finishLoading();
        this.engine.addSystem(new DebugRenderSystem(this.game.batcher, this.game.camera));

        createMonster();

        createObstacle();
        createObstacle2();
        createObstacle3();
        createObstacle4();
        createObstacle5();
    }

    private void createObstacle(){

        Entity hero = new Entity();

        //Add Position
        TransformComponent transformComponent = new TransformComponent(new Vector3(10,10,10));
        hero.add(transformComponent);

        hero.add(transformComponent);


        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;
        bd.position.set(8, -32);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(32, 16);

        PhysicsSystem physicsSystem = this.engine.getSystem(PhysicsSystem.class);

        Body body = physicsSystem.addStaticBody(0, -220, 400, 5);
        body.setUserData(hero);
        hero.add(new CollisionComponent());
        hero.add(new TypeComponent(TypeComponent.TYPE_WALL));


        hero.add(new SteeringComponent(body));



        this.engine.addEntity(hero);
    }

    private void createObstacle2(){

        Entity hero = new Entity();

        //Add Position
        TransformComponent transformComponent = new TransformComponent(new Vector3(10,10,10));
        hero.add(transformComponent);

        hero.add(transformComponent);


        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;
        bd.position.set(8, -32);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(32, 32);

        PhysicsSystem physicsSystem = this.engine.getSystem(PhysicsSystem.class);

        Body body = physicsSystem.addStaticBody(0, 220, 400, 5);
        body.setUserData(hero);
        hero.add(new CollisionComponent());
        hero.add(new TypeComponent(TypeComponent.TYPE_WALL));


        hero.add(new SteeringComponent(body));



        this.engine.addEntity(hero);
    }


    private void createObstacle3(){

        Entity hero = new Entity();

        //Add Position
        TransformComponent transformComponent = new TransformComponent(new Vector3(10,10,10));
        hero.add(transformComponent);

        hero.add(transformComponent);


        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;
        bd.position.set(8, -32);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(32, 32);

        PhysicsSystem physicsSystem = this.engine.getSystem(PhysicsSystem.class);

        Body body = physicsSystem.addStaticBody(40, -25, 7, 120);
        body.setUserData(hero);
        hero.add(new CollisionComponent());
        hero.add(new TypeComponent(TypeComponent.TYPE_WALL));


        hero.add(new SteeringComponent(body));



        this.engine.addEntity(hero);
    }

    private void createObstacle4(){

        Entity hero = new Entity();

        //Add Position
        TransformComponent transformComponent = new TransformComponent(new Vector3(10,10,10));
        hero.add(transformComponent);

        hero.add(transformComponent);


        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;
        bd.position.set(8, -32);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(32, 32);

        PhysicsSystem physicsSystem = this.engine.getSystem(PhysicsSystem.class);

        Body body = physicsSystem.addStaticBody(350, 0, 7, 200);
        body.setUserData(hero);
        hero.add(new CollisionComponent());
        hero.add(new TypeComponent(TypeComponent.TYPE_WALL));


        hero.add(new SteeringComponent(body));



        this.engine.addEntity(hero);
    }

    private void createObstacle5(){

        Entity hero = new Entity();

        //Add Position
        TransformComponent transformComponent = new TransformComponent(new Vector3(10,10,10));
        hero.add(transformComponent);

        hero.add(transformComponent);


        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;
        bd.position.set(8, -32);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(32, 32);

        PhysicsSystem physicsSystem = this.engine.getSystem(PhysicsSystem.class);

        Body body = physicsSystem.addStaticBody(-350, 0, 7, 200);
        body.setUserData(hero);
        hero.add(new CollisionComponent());
        hero.add(new TypeComponent(TypeComponent.TYPE_WALL));
        hero.add(new SteeringComponent(body));

        this.engine.addEntity(hero);
    }

    private void createHero(){

        hero = new Entity();

        //Add Texture
        TextureComponent textureComponent = new TextureComponent();
        textureComponent.setRegion(new TextureRegion(this.assets.getManager().get("sprites/cherry.png", Texture.class)));
        hero.add(textureComponent);

        //Add Position
        DirectionComponent directionComponent = new DirectionComponent();
        hero.add(directionComponent);

        //add Movement
        MovementComponent movementComponent = new MovementComponent(HeroComponent.HERO_VELOCITY);
        hero.add(movementComponent);

        //add Hero
        HeroComponent heroComponent = new HeroComponent();
        hero.add(heroComponent);

        //Add Transform
        TransformComponent transformComponent = new TransformComponent(new Vector3(10,20,10));
        hero.add(transformComponent);

        //Add Collisions
        hero.add(new CollisionComponent());

        // Body creation
        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.DynamicBody;
        bd.position.set(8, 8);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(16, 16);

        PhysicsSystem physicsSystem = this.engine.getSystem(PhysicsSystem.class);

        Body body = physicsSystem.addDynamicBody(0, 40, 10, 10);
        body.setUserData(hero);
        body.setLinearVelocity(new Vector2(0,0));

        //Add Body
        hero.add(new SteeringComponent(body));

        //Add Steering
        SteeringComponent steeringComponent = new SteeringComponent(body);
        hero.add(steeringComponent);

        this.engine.addEntity(hero);
    }

    private void createMonster(){

        monster = new Entity();


        // Add texture
        TextureComponent textureComponent = new TextureComponent();
        textureComponent.setRegion(new TextureRegion(this.assets.getManager().get("sprites/spr_orange.png", Texture.class)));
        monster.add(textureComponent);

        //Add Movement
        MovementComponent movementComponent = new MovementComponent(HeroComponent.HERO_VELOCITY);
        monster.add(movementComponent);

        // Add Monster component
        MonsterComponent monsterComponent = new MonsterComponent();
        monster.add(monsterComponent);


        // Add transform
        TransformComponent transformComponent = new TransformComponent(new Vector3(50,40,10));
        monster.add(transformComponent);

        // Add Collision
        monster.add(new CollisionComponent());


        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.DynamicBody;
        bd.position.set(8, 8);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(16, 16);

        PhysicsSystem physicsSystem = this.engine.getSystem(PhysicsSystem.class);

        Body body = physicsSystem.addDynamicBody(50, 40, 10, 10);
        body.setUserData(monster);
        body.setLinearVelocity(new Vector2(0,0));


        // Add steering
        SteeringComponent steeringComponent = new SteeringComponent(body);
        monster.add(steeringComponent);
        //monster.getComponent(SteeringComponent.class).steeringBehavior  = SteeringPresets.getSeek(monster.getComponent(SteeringComponent.class),hero.getComponent(SteeringComponent.class));
        //monster.getComponent(SteeringComponent.class).currentMode = SteeringComponent.SteeringState.SEEK;

        this.engine.addEntity(monster);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.input.setInputProcessor(new ACLGameListener(this));
        engine.update(delta);
    }
}
