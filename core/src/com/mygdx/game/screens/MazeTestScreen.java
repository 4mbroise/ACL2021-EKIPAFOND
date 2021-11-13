package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.ACLGame;
import com.mygdx.game.World;
import com.mygdx.game.components.TypeComponent;
import com.mygdx.game.listeners.ACLGameListener;
import com.mygdx.game.systems.*;
import com.mygdx.game.systems.physics.CollisionsSystem;
import com.mygdx.game.systems.physics.PhysicsSystem;
import com.mygdx.game.systems.physics.collisionhandler.HeroWallCollisionHandler;

public class MazeTestScreen extends GameScreen {


    public MazeTestScreen(ACLGame game) {
        super(game);

        this.assets.getManager().finishLoading();
        int[] mapDim = this.world.createMap(Gdx.files.internal("maps/map1.txt").file());
        this.world.getMapGraph();
        float x = (float) ((mapDim[1])*World.CASE_DIMENSION);
        float y = (mapDim[0])*World.CASE_DIMENSION;
        this.game.camera.position.set(x,y,0);
        this.game.camera.update();
        this.game.batcher.setProjectionMatrix(this.game.camera.combined);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.act();
        stage.draw();
        //this.world.updateMap();
    }

}

