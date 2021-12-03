package com.mygdx.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Assets;
import com.mygdx.game.components.HealthComponent;
import com.mygdx.game.components.HeroComponent;
import com.mygdx.game.components.ScoreComponent;


public class ScoreRenderSystem extends IteratingSystem {


    private  Stage stage;
    private ComponentMapper<ScoreComponent> srm;
    private Assets assets;
    private Batch batch;
    private BitmapFont score;
    /**
     * Constructor
     * @param batch game's batch
     * @param assets game's assets
     * @param stage game's stage
     */
    public ScoreRenderSystem(Batch batch, Assets assets, Stage stage) {
        super(Family.all(ScoreComponent.class).get()); // we want to collect hero's health point
        this.srm = ComponentMapper.getFor(ScoreComponent.class);
        this.assets = assets;
        this.score = this.assets.getManager().get("fonts/Retro_Gaming2.ttf");
        this.batch = batch;
        this.stage=stage;
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ScoreComponent sc=srm.get(entity);
        System.out.println(sc.getScore());

        GlyphLayout scoreG = new GlyphLayout();
        scoreG.setText(score,sc.getScore()+"0");
        float scoreW = scoreG.width;
        stage.draw();
        batch.begin();
        score.draw(batch, scoreG,50, stage.getHeight()-100);
        batch.end();
    }
}
