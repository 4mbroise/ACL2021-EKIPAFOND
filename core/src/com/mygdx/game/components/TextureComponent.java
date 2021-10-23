package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureComponent implements Component {

    private TextureRegion region;

    public TextureComponent(TextureRegion region) {
        this.region=region;
    }

    public TextureRegion getRegion() {
        return region;
    }
}
