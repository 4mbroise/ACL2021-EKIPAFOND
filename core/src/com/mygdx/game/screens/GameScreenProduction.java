package com.mygdx.game.screens;

import com.mygdx.game.ACLGame;
import com.mygdx.game.Assets;

public class GameScreenProduction extends GameScreen{

    public GameScreenProduction(ACLGame game) {
        super(game, game.getAssets());



        this.assets.getManager().finishLoading();

    }

}
