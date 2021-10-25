package com.mygdx.game.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.game.ACLGame;
import com.mygdx.game.Assets;
import com.mygdx.game.components.*;
import com.mygdx.game.listeners.ACLGameListener;
import com.mygdx.game.systems.*;

public class GameAITestScreen extends GameScreen{
    public ACLGame game;
    public GameAITestScreen(ACLGame game, Assets assets) {
        super(game, assets);
        this.game = game;
        this.engine.addSystem(new RenderSystem(this.game.batcher));
        this.engine.addSystem(new MovementSystem());
        this.engine.addSystem(new HeroSystem());
        this.engine.addSystem(new AISystem());
        this.engine.addSystem(new MonsterSystem());
        this.assets.getManager().finishLoading();

        createHero();
        createMonster();
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

        //Add Direction
        DirectionComponent directionComponent = new DirectionComponent();
        hero.add(directionComponent);

        //AddBody
        // to do

        MovementComponent movementComponent = new MovementComponent(HeroComponent.HERO_VELOCITY);
        hero.add(movementComponent);

        HeroComponent heroComponent = new HeroComponent();
        hero.add(heroComponent);

        hero.add(transformComponent);

        //Add SteeringComponent
        SteeringComponent steeringComponent = new SteeringComponent();
        hero.add(steeringComponent);

        this.engine.addEntity(hero);
    }

    private void createMonster(){

        Entity monster = new Entity();

        //Add Texture
        TextureComponent textureComponent = new TextureComponent();
        textureComponent.setRegion(new TextureRegion(this.assets.getManager().get("sprites/spr_orange.png", Texture.class)));
        monster.add(textureComponent);

        //Add Position
        TransformComponent transformComponent = new TransformComponent(new Vector3(50,50,10));
        monster.add(transformComponent);

        MovementComponent movementComponent = new MovementComponent(HeroComponent.HERO_VELOCITY);
        monster.add(movementComponent);

        MonsterComponent monsterComponent = new MonsterComponent();
        monster.add(monsterComponent);

        //Add SteeringComponent
        SteeringComponent steeringComponent = new SteeringComponent();
        monster.add(steeringComponent);

        this.engine.addEntity(monster);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        Gdx.input.setInputProcessor(new ACLGameListener(this));

    }
}
