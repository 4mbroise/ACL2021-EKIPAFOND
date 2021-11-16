package com.mygdx.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.Assets;
import com.mygdx.game.components.HealthComponent;
import com.mygdx.game.components.HeroComponent;

/**
 * HealthRenderSystem
 * SYstem which displays hero's currrent life to inform the player
 */
public class HealthRenderSystem extends IteratingSystem {


    private ComponentMapper<HeroComponent> heroMapper;
    private ComponentMapper<HealthComponent> healthMapper;
    private Assets assets;

    private BitmapFont health; //bitmapfont for the health
    private Batch batch;

    private final float fontScale = 0.75f; //static font scale

    /**
     * Constructor
     * @param batch game's batch
     * @param assets game's assets
     */
    public HealthRenderSystem(Batch batch, Assets assets) {
        super(Family.all(HeroComponent.class, HealthComponent.class).get());
        this.heroMapper = ComponentMapper.getFor(HeroComponent.class);
        this.healthMapper = ComponentMapper.getFor(HealthComponent.class);
        this.assets = assets;
        this.health =assets.getManager().get("fonts/Minecraft.ttf");
        this.batch = batch;
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        HealthComponent hc = healthMapper.get(entity);

        String currentHealth = String.valueOf(hc.healthPoint);
        String printHealth = "Health: " + currentHealth+ "/"+String.valueOf(HeroComponent.START_HEALTH);
        health.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        health.getData().setScale(fontScale);
        batch.begin();
        health.draw(batch, printHealth,-150,400);
        batch.end();
    }
}
