package com.mygdx.game.factory.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Assets;
import com.mygdx.game.World;
import com.mygdx.game.components.*;
import com.mygdx.game.systems.physics.PhysicsSystem;

public class InteligentMonsterBuilder extends PhysicalEntityBuilder{

     public InteligentMonsterBuilder(Assets assets, PhysicsSystem physicsSystem) {
        super(assets, physicsSystem);
    }

    @Override
    public Entity buildEntity(float x, float y) {
        Entity monster = new Entity();

        //Add Movement Component
        monster.add(new MovementComponent(MonsterComponent.MONSTER_VELOCITY));

        //Add Direcetion Component
        monster.add(new DirectionComponent());

        // Add texture
        TextureComponent textureComponent = new TextureComponent();
        textureComponent.setRegion(new TextureRegion(this.assets.getManager().get("sprites/monster.png", Texture.class)));
        monster.add(textureComponent);

        // Add Monster component
        MonsterComponent monsterComponent = new MonsterComponent();
        monster.add(monsterComponent);

        // Add transform
        TransformComponent transformComponent = new TransformComponent(new Vector3(x,y,1));
        monster.add(transformComponent);

        // Add Collision
        monster.add(new CollisionComponent());

        //Add Health Point
        monster.add(new HealthComponent(4));

        Body body = physicsSystem.addDynamicBody(x, y, World.CASE_DIMENSION/2, World.CASE_DIMENSION/2);
        body.setUserData(monster);
        body.setLinearVelocity(new Vector2(0,0));

        // Add steering
        SteeringComponent steeringComponent = new SteeringComponent(body);
        monster.add(steeringComponent);
        //monster.getComponent(SteeringComponent.class).steeringBehavior  = SteeringPresets.getSeek(monster.getComponent(SteeringComponent.class),hero.getComponent(SteeringComponent.class));
        //monster.getComponent(SteeringComponent.class).currentMode = SteeringComponent.SteeringState.SEEK;

        //Add PathFinding
        monster.add(new PathFindingComponent());

        monster.add(new TypeComponent(TypeComponent.TYPE_MONSTER));

        return monster;
    }
}
