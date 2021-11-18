package com.mygdx.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.HeroComponent;
import com.mygdx.game.components.TransformComponent;

public class CameraSystem extends IteratingSystem {

    private OrthographicCamera camera;
    private SpriteBatch batcher;

    public CameraSystem(OrthographicCamera camera, SpriteBatch batcher) {
        super(Family.all(HeroComponent.class, TransformComponent.class).get());
        this.camera = camera;
        this.batcher = batcher;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transformComponent = entity.getComponent(TransformComponent.class);
        camera.position.set(transformComponent.getPosition().x, transformComponent.getPosition().y, 0);
        camera.update();
        batcher.setProjectionMatrix(camera.combined);
    }
}
