package com.mygdx.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.systems.physics.PhysicsSystem;


public class DebugRenderSystem extends EntitySystem {

    private Box2DDebugRenderer renderer;
    private World world;
    private SpriteBatch batcher;
    private OrthographicCamera camera;

    public DebugRenderSystem(SpriteBatch batcher, OrthographicCamera camera) {
        this.renderer = new Box2DDebugRenderer();
        this.batcher = batcher;
        this.camera = camera;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        this.world =this.getEngine().getSystem(PhysicsSystem.class).getPhysicsWorld();

    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        renderer.render(world, camera.combined);
    }
}
