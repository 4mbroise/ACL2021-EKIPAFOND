package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.*;


public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//World world = new World();
		//world.createMap();
		config.title = "ACL GAME 2021 - EKIPAFOND";
		config.width = 800;//world.getWidth() * 16 * 2;
		config.height = 480;//world.getHeight() * 16 * 2;
		new LwjglApplication(new ACLGameMazeTest(), config);
	}
}
