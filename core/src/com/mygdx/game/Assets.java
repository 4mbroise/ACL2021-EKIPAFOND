package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

    public Assets() {
    }

    /**
     *
     * @param filename file which we want to load
     * @return Texture
     */
    public static Texture loadTexture(String filename){
        return new Texture(Gdx.files.internal(filename));
    }

    /**
     * Function to load all the textures
     */
    public static void load(){

    }


}
