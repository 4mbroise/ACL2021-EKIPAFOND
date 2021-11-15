package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

public class Assets {
    private InternalFileHandleResolver resolver=new  InternalFileHandleResolver();
    private final AssetManager assetManager = new AssetManager(resolver);

    public Assets() {
        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
        //UIs
        assetManager.load("UI/startUp.png",Texture.class);
        assetManager.load("UI/startDown.png",Texture.class);
        assetManager.load("UI/homeUp.png",Texture.class);
        assetManager.load("UI/homeDown.png",Texture.class);
        assetManager.load("UI/regleUp.png",Texture.class);
        assetManager.load("UI/regleDown.png",Texture.class);
        //backGround
        assetManager.load("UI/sunsetbackground.png",Texture.class);
        assetManager.load("UI/noonbackground.png",Texture.class);
        assetManager.load("UI/nightbackgroundwithmoon.png",Texture.class);
        //fonts
        FreetypeFontLoader.FreeTypeFontLoaderParameter parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = "fonts/Retro_Gaming.ttf";
        parms.fontParameters.size = 60;
        parms.fontParameters.color=Color.RED;
        parms.fontParameters.borderColor=Color.BLACK;
        assetManager.load( "fonts/Retro_Gaming.ttf", BitmapFont.class, parms);

        FreetypeFontLoader.FreeTypeFontLoaderParameter parms2 = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms2.fontFileName = "fonts/Retro_Gaming.ttf";
        parms2.fontParameters.size = 24;
        parms2.fontParameters.color=Color.RED;
        parms2.fontParameters.borderColor=Color.BLACK;
        assetManager.load( "fonts/Retro_Gaming2.ttf", BitmapFont.class, parms2);
        //sprites
        assetManager.load("sprites/cherry.png", Texture.class);
        assetManager.load("tiles/treasure32x32.png", Texture.class);
        assetManager.load("tiles/wall2.png", Texture.class);
        assetManager.load("tiles/fire.png", Texture.class);
        assetManager.load("tiles/portal.png", Texture.class);
        assetManager.load("sprites/spr_orange.png", Texture.class);
        assetManager.load("tiles/magic.png", Texture.class);
        //load
        //System.out.println(assetManager.getProgress());

    }

    public AssetManager getManager() {
        return assetManager;
    }
}
