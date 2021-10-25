package com.mygdx.game.screens;

import com.mygdx.game.ACLGame;
import com.mygdx.game.Assets;

public class GameScreenProduction extends GameScreen{

    public GameScreenProduction(ACLGame game, Assets assets) {
        super(game, assets);



        this.assets.getManager().finishLoading();

    }

}
