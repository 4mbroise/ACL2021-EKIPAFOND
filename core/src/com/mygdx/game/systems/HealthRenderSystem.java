package com.mygdx.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Assets;
import com.mygdx.game.components.HealthComponent;
import com.mygdx.game.components.HeroComponent;
import com.mygdx.game.screens.MenuScreen;

import javax.xml.soap.Text;

/**
 * HealthRenderSystem
 * SYstem which displays hero's currrent life to inform the player
 */
public class HealthRenderSystem extends IteratingSystem {


    private ComponentMapper<HeroComponent> heroMapper;
    private ComponentMapper<HealthComponent> healthMapper;
    private Assets assets;

    private Batch batch;
    private Texture heartTexture;
    private final float fontScale = 0.75f; //static font scale
    private Stage stage;
    /**
     * Constructor
     * @param batch game's batch
     * @param assets game's assets
     * @param stage game's stage
     */
    public HealthRenderSystem(Batch batch, Assets assets, Stage stage) {
        super(Family.all(HeroComponent.class, HealthComponent.class).get()); // we want to collect hero's health point
        this.heroMapper = ComponentMapper.getFor(HeroComponent.class);
        this.healthMapper = ComponentMapper.getFor(HealthComponent.class);
        this.assets = assets;
        this.heartTexture = assets.getManager().get("tiles/vie.png");
        this.batch = batch;
        this.stage=stage;
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        // we should remove all heart textures from stage before refresh
        for(Actor actor : stage.getActors()) {
            System.out.println(actor.getName());

            if(actor.getName().equals("heartbutton")){
                actor.addAction(Actions.removeActor());
            }
        }
        HealthComponent hc = healthMapper.get(entity);

        String currentHealth = String.valueOf(hc.healthPoint);
        float resizing =0;
        batch.begin();

        // on heart by health point is drawn
        for(int i = 0; i<hc.healthPoint;i++){
            Button.ButtonStyle healthStyle=new Button.ButtonStyle();
            healthStyle.up=new TextureRegionDrawable(new TextureRegion(heartTexture));
            Button heartButton=new Button(healthStyle);
            heartButton.setName("heartbutton");
            heartButton.setPosition(100,100+resizing);
            stage.addActor(heartButton);
            resizing+=heartTexture.getWidth();
        }

        batch.end();
    }
}
