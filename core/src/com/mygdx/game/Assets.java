package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
        assetManager.load("UI/dawnbackground.png",Texture.class);
        assetManager.load("UI/fajrbackground.png",Texture.class);

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

        parms2 = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms2.fontFileName = "fonts/Retro_Gaming.ttf";
        parms2.fontParameters.size = 24;
        parms2.fontParameters.color=Color.BLUE;
        parms2.fontParameters.borderColor=Color.BLACK;
        assetManager.load( "fonts/Minecraft.ttf", BitmapFont.class, parms2);
        //sprites
        assetManager.load("sprites/HeroPack.png", Texture.class);
        assetManager.load("sprites/cherry.png", Texture.class);
        assetManager.load("tiles/treasure32x32.png", Texture.class);
        assetManager.load("tiles/wall.png", Texture.class);
        assetManager.load("tiles/ground.png", Texture.class);
        assetManager.load("tiles/ground2.png", Texture.class);
        assetManager.load("tiles/fire.png", Texture.class);
        assetManager.load("tiles/portal.png", Texture.class);
        assetManager.load("sprites/cherry.png", Texture.class);
        assetManager.load("sprites/ghost.png", Texture.class);
        assetManager.load("sprites/monster.png", Texture.class);
        assetManager.load("tiles/magic.png", Texture.class);
        assetManager.load("tiles/gold.png", Texture.class);
        assetManager.load(("tiles/vie.png"), Texture.class);
        assetManager.load("tiles/spiderWeb.png", Texture.class);
        //load
        //System.out.println(assetManager.getProgress());
        //sound
        //attack
        assetManager.load(("audio/attack/Attack.ogg"), Sound.class);
        //damage
        assetManager.load(("audio/attack/Damage.ogg"), Sound.class);
        //game
        assetManager.load(("audio/game/Fire.ogg"), Sound.class);
        assetManager.load(("audio/system/button.ogg"), Sound.class);
        assetManager.load(("audio/game/Heal.ogg"), Sound.class);
        assetManager.load("audio/game/web_effect.mp3", Sound.class);
        //BGM
        assetManager.load(("audio/BGM/MusMus-BGM-125.mp3"), Music.class);
        assetManager.load(("audio/BGM/MusMus-BGM-115.mp3"), Music.class);
        assetManager.load(("audio/BGM/end_music.mp3"), Music.class);
        assetManager.load(("audio/BGM/winning_music.mp3"), Music.class);
        assetManager.load(("audio/game/coin_effect.mp3"), Sound.class);

    }

    public AssetManager getManager() {
        return assetManager;
    }
}
