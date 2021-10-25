package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureComponent implements Component {
    private TextureRegion region = null;

    public TextureRegion getRegion() {
        return new TextureRegion(region);
    }

    public void setRegion(TextureRegion region) {
        this.region = region;
    }
}
