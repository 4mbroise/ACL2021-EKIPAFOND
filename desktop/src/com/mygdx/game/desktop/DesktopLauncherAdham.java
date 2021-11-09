package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.*;

public class DesktopLauncherAdham {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "ACL GAME 2021 - EKIPAFOND";
        config.width = 800;
        config.height = 480;

        ACLGame game = new ACLRenderingGameTest();

        LwjglApplication app = new LwjglApplication(game, config);
    }
}
