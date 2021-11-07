package com.mygdx.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.ai.steer.behaviors.*;
import com.badlogic.gdx.ai.steer.utils.rays.ParallelSideRayConfiguration;
import com.badlogic.gdx.ai.steer.utils.rays.SingleRayConfiguration;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.components.MonsterComponent;
import com.mygdx.game.components.SteeringComponent;
import com.mygdx.game.systems.physics.PhysicsSystem;
import com.mygdx.game.tools.Box2dRaycastCollisionDetector;


/**
 * MonsterSystem which implements partially the AI of the monster (other part is implemented by AISYstem)
 */
public class MonsterSystem extends IteratingSystem {

    private Entity hero;
    private ComponentMapper<SteeringComponent> steerMap;

    /**
     * Constructor of the MonsterSystem
     * @param entity we need hero entity to apply Seek Behaviour to the monster
     */
    public MonsterSystem(Entity entity) {
        super(Family.all(MonsterComponent.class, SteeringComponent.class).get());
        this.hero=entity;
        this.steerMap = ComponentMapper.getFor(SteeringComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        SteeringComponent bHero = steerMap.get(hero);
        SteeringComponent bMonster = steerMap.get(entity); //get monster's entity
        this.applySeek(bMonster,bHero);
        bMonster.update(deltaTime); //update SteeringComponent
    }

    /**
     * Function which applies seek behavior to the monster
     * @param s Monster's SteeringComponent
     * @param t Hero's SteeringComponent
     */
    public void applySeek(SteeringComponent s, SteeringComponent t) {
        World w = this.getEngine().getSystem(PhysicsSystem.class).getPhysicsWorld();

        // Set PrioritySteering
        PrioritySteering<Vector2> prioritySteering = new PrioritySteering(s, 0.0001f);

        // Creation of the collision detector
        Box2dRaycastCollisionDetector m = new Box2dRaycastCollisionDetector(w);
        RaycastObstacleAvoidance<Vector2> raycastObstacleAvoidanceSB = new RaycastObstacleAvoidance<>(s,
                new SingleRayConfiguration<Vector2>(s, 1.0f), m, 0.1f);
        prioritySteering.add(new Seek<Vector2>(s, t).setEnabled(true));
        s.steeringBehavior=prioritySteering;
        s.currentMode= SteeringComponent.SteeringState.SEEK;
        prioritySteering.add(raycastObstacleAvoidanceSB);

    }

}
