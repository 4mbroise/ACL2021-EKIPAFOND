package com.mygdx.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Font {
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    private FreeTypeFontGenerator.FreeTypeBitmapFontData fontData;
    private BitmapFont font;
    private int size;
    private Color color;

    public Font(int size,Color color){
        generator=new FreeTypeFontGenerator(Gdx.files.internal("fonts/Retro_Gaming.ttf"));
        fontParameter =new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.characters=generator.DEFAULT_CHARS+"";
        fontParameter.size=size;
        fontParameter.color= color;
        fontData=generator.generateData(fontParameter);
        font=new BitmapFont(fontData,fontData.regions,false);
    }

    public BitmapFont getFont(){
        return font;
    }

    public void setSize(int size){
        this.size=size;
    }

    public int getSize(){
        return this.size;
    }

    public void setColor(Color color){
        this.color=color;
    }

    public Color getColor(){
        return this.color;
    }
}
