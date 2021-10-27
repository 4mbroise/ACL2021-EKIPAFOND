package com.mygdx.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.*;
import com.mygdx.game.screens.GameTestScreen;

import java.io.File;


public class DesktopRenderingTestLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "ACL GAME 2021 - EKIPAFOND";
        config.width = 512;
        config.height = 480;

        ACLGame game = new ACLRenderingGameTest();

        LwjglApplication app = new LwjglApplication(game, config);


    }
}
