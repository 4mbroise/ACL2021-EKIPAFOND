package com.mygdx.game.listeners;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.*;
import com.mygdx.game.ACLGame;
import com.mygdx.game.components.AnimationComponent;
import com.mygdx.game.components.AttackerComponent;
import com.mygdx.game.components.DirectionComponent;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.MenuScreen;
import com.mygdx.game.systems.AnimationSystem;
import com.mygdx.game.systems.AttackSystem;
import com.mygdx.game.systems.HeroSystem;

public class ACLGameListener extends InputAdapter  {

    private Engine engine;
    private ACLGame game;
    public ACLGameListener(GameScreen gameScreen){
        this.engine = gameScreen.engine;
        this.game=gameScreen.game;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.Z:
            case Input.Keys.W:
            case Input.Keys.UP:
                engine.getSystem(HeroSystem.class).setHeroDirection(DirectionComponent.UP);
                return true;
            case Input.Keys.S:
            case Input.Keys.DOWN:
                engine.getSystem(HeroSystem.class).setHeroDirection(DirectionComponent.DOWN);
                return true;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                engine.getSystem(HeroSystem.class).setHeroDirection(DirectionComponent.RIGHT);
                return true;
            case Input.Keys.Q:
            case Input.Keys.A:
            case Input.Keys.LEFT:
                engine.getSystem(HeroSystem.class).setHeroDirection(DirectionComponent.LEFT);
                return true;
            case Input.Keys.J:
            case Input.Keys.SPACE:
                engine.getSystem(AttackSystem.class).attack();
                engine.getSystem(AnimationSystem.class).attack();

        }
        return false;
    }


}
