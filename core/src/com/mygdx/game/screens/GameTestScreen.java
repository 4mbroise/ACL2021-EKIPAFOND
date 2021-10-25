package com.mygdx.game.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.ACLGame;
import com.mygdx.game.components.*;
import com.mygdx.game.systems.MovementSystem;
import com.mygdx.game.systems.RenderSystem;

public class GameTestScreen extends GameScreen {

    public GameTestScreen(ACLGame game) {
        super(game, game.getAssets());
        this.engine.addSystem(new RenderSystem(this.game.batcher));
        this.engine.addSystem(new MovementSystem());

        this.assets.getManager().finishLoading();

        createHero();
    }

    private void createHero(){

        Entity hero = new Entity();

        //Add Texture
        TextureComponent textureComponent = new TextureComponent();
        textureComponent.setRegion(new TextureRegion(this.assets.getManager().get("sprites/cherry.png", Texture.class)));
        hero.add(textureComponent);


        //Add Position
        TransformComponent transformComponent = new TransformComponent(new Vector3(10,10,10));
        hero.add(transformComponent);

        //Add Position
        DirectionComponent directionComponent = new DirectionComponent();
        hero.add(directionComponent);

        MovementComponent movementComponent = new MovementComponent(HeroComponent.HERO_VELOCITY);
        hero.add(movementComponent);

        HeroComponent heroComponent = new HeroComponent();
        hero.add(heroComponent);

        hero.add(transformComponent);

        this.engine.addEntity(hero);
    }
}

