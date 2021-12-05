package com.mygdx.game.screens;

import com.mygdx.game.ACLGame;

public class GameScreenProduction extends GameScreen{

    public GameScreenProduction(ACLGame game) {
        super(game);



        this.assets.getManager().finishLoading();

    }

}
