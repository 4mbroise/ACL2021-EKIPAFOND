package com.mygdx.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.TextureComponent;
import com.mygdx.game.components.TransformComponent;

import java.util.Comparator;

public class RenderSystem extends SortedIteratingSystem {

    private ComponentMapper<TransformComponent> trm;
    private ComponentMapper<TextureComponent> txm;
    private SpriteBatch batcher;

    public RenderSystem(SpriteBatch batcher){
        super(Family.all(TextureComponent.class, TransformComponent.class).get(), new ZComparator());

        trm = ComponentMapper.getFor(TransformComponent.class);
        txm = ComponentMapper.getFor(TextureComponent.class);

        this.batcher = batcher;

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TextureComponent txComp = txm.get(entity);
        TransformComponent trComp = trm.get(entity);

        float textureWidth = txComp.getRegion().getRegionWidth();
        float textureHeigth = txComp.getRegion().getRegionHeight();

        float x = trComp.getPosition().x;
        float y = trComp.getPosition().y;

        float xDraw = x - textureWidth/2;
        float yDraw = y - textureHeigth/2;

        batcher.begin();
        batcher.draw(txComp.getRegion(), xDraw, yDraw);
        batcher.end();

    }

    private static class ZComparator implements Comparator<Entity> {
        private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);

        @Override
        public int compare(Entity e1, Entity e2) {
            return (int)Math.signum(tm.get(e1).getPosition().z - tm.get(e2).getPosition().z);
        }
    }
}
