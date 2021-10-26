package com.mygdx.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.components.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class World {

    private char[][] grid;
    private int width, height, ctr;
    private Scanner SizeReader,TileReader;
    private File mapFile;
    private String MapDir;
    private List<File> Maps;
    private Engine engine;
    private Assets assets;


    public World(Engine engine, Assets assets) {
        this.Maps = new ArrayList<File>();
        this.MapDir = "core/assets/maps/map1.txt" ;
        this.ctr = 0;
        this.engine = engine;
        this.assets = assets;
    }

    public World() {
    }

    public void createMap(){
        mapFile = new File(this.MapDir);
        try{
            this.SizeReader = new Scanner(mapFile);
            } catch (FileNotFoundException e){
                System.out.println("File not found");
        }
        /* Get the width and the height of the map */
        while (SizeReader.hasNext()) {
            String data = SizeReader.nextLine();
            if (data.length() > this.width) {
                this.width = data.length();
            }
            this.height++;
        }

        /* In case the file does not contain a single column and/or row */

        if (this.height == 0){
            this.height = 1;
        }
        if (this.width == 0){
            this.width = 1;
        }

        SizeReader.close();

        try{
            this.TileReader = new Scanner(mapFile);
            } catch(FileNotFoundException e){
                System.out.println("File not found");
        }
        this.grid = new char[this.height][this.width];
        while (TileReader.hasNext()) {
            String data = TileReader.nextLine();
                for (int j = 0; j < this.width ; j++){
                    grid[ctr][j] = data.charAt(j);
                    switch (data.charAt(j)){
                        case '-':
                            Entity wall = new Entity();
                            this.engine.addEntity(wall);
                            TextureComponent textureComponent = new TextureComponent();
                            textureComponent.setRegion(new TextureRegion(this.assets.getManager().get("sprites/damage_up.png", Texture.class)));
                            wall.add(textureComponent);
                            TransformComponent transformComponent = new TransformComponent(new Vector3((float)(j+ 0.5) * 16 * 2 , (float)(ctr+0.5) * 16 * 2,0));
                            wall.add(transformComponent);

                            System.out.print("  Wall  ");

                            break;
                        case '+':
                            Entity ground = new Entity();
                            this.engine.addEntity(ground);
                            System.out.print(" Ground ");
                            break;
                        case '1':
                            createHero((float)(ctr+0.5) * 16 * 2, (float)(j+0.5) * 16 * 2);
                            System.out.print("  Hero  ");
                            break;
                        default :
                            System.out.print("Ground-d");
                    }
                }
            ctr++;
            System.out.println("");
        }
        this.TileReader.close();
    }

    public void createHero(float posx, float posy){

        Entity hero = new Entity();

        //Add Texture
        TextureComponent textureComponent = new TextureComponent();
        textureComponent.setRegion(new TextureRegion(this.assets.getManager().get("sprites/cherry.png", Texture.class)));
        hero.add(textureComponent);


        //Add Position
        TransformComponent transformComponent = new TransformComponent(new Vector3(32,32,0));
        hero.add(transformComponent);

        //Add Position
        DirectionComponent directionComponent = new DirectionComponent();
        hero.add(directionComponent);

        MovementComponent movementComponent = new MovementComponent(HeroComponent.HERO_VELOCITY);
        hero.add(movementComponent);

        HeroComponent heroComponent = new HeroComponent();
        hero.add(heroComponent);

        //hero.add(transformComponent);

        this.engine.addEntity(hero);
    }

    /*public Monster? createMonster(){

    }*/

    public char[][] getGrid() {
        return grid;
    }

    public void setGrid(char[][] grid) {
        this.grid = grid;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}