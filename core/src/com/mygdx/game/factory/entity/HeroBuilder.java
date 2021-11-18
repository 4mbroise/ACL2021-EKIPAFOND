package com.mygdx.game.factory.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Assets;
import com.mygdx.game.World;
import com.mygdx.game.components.*;
import com.mygdx.game.systems.MonsterSystem;
import com.mygdx.game.systems.pathfinding.PathFindingSystem;
import com.mygdx.game.systems.physics.PhysicsSystem;

public class HeroBuilder implements EntityBuilder{

    private Assets assets;
    private PhysicsSystem physicsSystem;
    private GroundBuilder groundBuilder;
    private Body heroBody;
    Entity hero;

    public HeroBuilder(Assets assets, PhysicsSystem physicsSystem) {
        this.assets = assets;
        this.physicsSystem = physicsSystem;
    }

    @Override
    public Entity buildEntity(float x, float y) {
        hero = new Entity();
        //Add Texture
        TextureComponent textureComponent = new TextureComponent();
        textureComponent.setRegion(new TextureRegion(assets.getManager().get("sprites/HeroPack.png", Texture.class)));
        hero.add(textureComponent);

        //Add Position
        TransformComponent transformComponent = new TransformComponent(new Vector3(x,y,10));
        hero.add(transformComponent);

        AnimationComponent animationComponent = new AnimationComponent();
        hero.add(animationComponent);

        //Add Position
        DirectionComponent directionComponent = new DirectionComponent();
        hero.add(directionComponent);

        MovementComponent movementComponent = new MovementComponent(HeroComponent.HERO_VELOCITY);
        hero.add(movementComponent);

        HeroComponent heroComponent = new HeroComponent();
        hero.add(heroComponent);

        heroBody = physicsSystem.addDynamicBody(x, y, World.CASE_DIMENSION - 1 , World.CASE_DIMENSION - 1);
        heroBody.setUserData(hero);
        hero.add(new SteeringComponent(heroBody));

        hero.add(new TypeComponent(TypeComponent.TYPE_HERO));

        hero.add(new CollisionComponent());

        hero.add(new HealthComponent(HeroComponent.START_HEALTH));

        hero.add(new AttackerComponent(1));

        this.physicsSystem.getEngine().getSystem(PathFindingSystem.class).setTarget(hero);
        this.physicsSystem.getEngine().getSystem(MonsterSystem.class).setTarget(hero);

        return  hero;
    }

    public void relocateHero(int x, int y){
        heroBody = physicsSystem.addDynamicBody(x, y, World.CASE_DIMENSION - 1 , World.CASE_DIMENSION - 1);
        heroBody.setUserData(hero);
    }
}
