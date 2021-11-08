package com.mygdx.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.game.components.*;
import com.mygdx.game.systems.physics.PhysicsSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class World {

    private char[][] grid;
    private int width, height, ctr, nbportal;
    private boolean won;
    private Scanner SizeReader,TileReader;
    private File mapFile;
    private String MapDir;
    private List<File> Maps;
    private List<Vector3> portals;
    private Engine engine;
    private Assets assets;
    private com.mygdx.game.systems.physics.PhysicsSystem physicsSystem;
    private Entity hero;
    private TransformComponent transformComponent;
    private Body heroBody;
    private Vector3 treasureVector;

    public World(Engine engine, Assets assets) {
        this.Maps = new ArrayList<File>();
        this.MapDir = "core/assets/maps/map1.txt" ;
        this.ctr = 0;
        this.nbportal = 0;
        this.won =  false;
        this.engine = engine;
        this.assets = assets;
        this.physicsSystem = this.engine.getSystem(com.mygdx.game.systems.physics.PhysicsSystem.class);
        this.portals = new ArrayList<Vector3>();


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
                            TransformComponent transformComponent = new TransformComponent(new Vector3((float)(j+ 0.5) * 16 * 2 , 480-(float)(ctr+0.5) * 16 * 2,10));
                            wall.add(transformComponent);
                           com.mygdx.game.systems.physics.PhysicsSystem physicsSystem = this.engine.getSystem(com.mygdx.game.systems.physics.PhysicsSystem.class);
                            Body body = physicsSystem.addStaticBody((float)(j+ 0.5) * 16 * 2 , 480 - (float)(ctr+0.5) * 16 * 2,16,16);
                            //System.out.print("  Wall  ");
                            wall.add(new BodyComponent(body));
                            break;
                        case '+':
                            Entity ground = new Entity();
                            this.engine.addEntity(ground);
                            //System.out.print(" Ground ");
                            break;
                        case '1':
                            createHero((float)(j + 0.5) * 16 * 2, 480 - (float)(ctr + 0.5) * 16 * 2);

                            //System.out.print("  Hero  ");
                            break;
                        case 'm':
                            createMonster();
                            break;
                        case 'p':
                            nbportal++;
                            Entity portal = new Entity();
                            this.engine.addEntity(portal);
                            TextureComponent textureComponentP = new TextureComponent();
                            textureComponentP.setRegion(new TextureRegion(this.assets.getManager().get("tiles/dungeonDecoration_portal.png", Texture.class)));
                            portal.add(textureComponentP);
                            //System.out.print(new Vector3((float)(j+ 0.5) * 16 * 2 , 480-(float)(ctr+0.5) * 16 * 2,0));
                            TransformComponent transformComponentP = new TransformComponent(new Vector3((float)(j+ 0.5) * 16 * 2 , 480-(float)(ctr+0.5) * 16 * 2,10));
                            portal.add(transformComponentP);
                            portals.add(new Vector3((float)(j+ 0.5) * 16 * 2 , 480-(float)(ctr+0.5) * 16 * 2,0));
                            break;
                        case 'k':
                            Entity treasure = new Entity();
                            this.engine.addEntity(treasure);
                            TextureComponent textureComponentT = new TextureComponent();
                            textureComponentT.setRegion(new TextureRegion(this.assets.getManager().get("tiles/treasure32x32.png", Texture.class)));
                            treasure.add(textureComponentT);
                            //System.out.print(new Vector3((float)(j+ 0.5) * 16 * 2 , 480-(float)(ctr+0.5) * 16 * 2,0));
                            TransformComponent transformComponentT = new TransformComponent(new Vector3((float)(j+ 0.5) * 16 * 2 , 480-(float)(ctr+0.5) * 16 * 2,10));
                            treasure.add(transformComponentT);
                            treasureVector = new Vector3(new Vector3((float)(j+ 0.5), height - (float)(ctr+0.5),0));

                        default :
                            //System.out.print("Ground-d");
                    }
                }
            ctr++;
            //System.out.println("");
        }

        this.TileReader.close();
    }

    public void updateMap(){
        if (nbportal != 0 && nbportal != 2){
            try{
                throw new Exception("Error creating map; only one portal exists");
            } catch (Exception e){
                e.printStackTrace();
                System.exit(222);
            }
        } else {
            if( (int) transformComponent.getPosition().x/32 == (int)portals.get(0).x /32
                && (int) transformComponent.getPosition().y/32 == (int)portals.get(0).y/32){
                transformComponent.setAbscissa((portals.get(1).x + 32));
                transformComponent.setOrdinate((int)portals.get(1).y);
                heroBody.setTransform((int)portals.get(1).x + 32,portals.get(1).y,0);
            }
             else if( (int) transformComponent.getPosition().x/32 == (int)portals.get(1).x /32
                    && (int) transformComponent.getPosition().y/32 == (int)portals.get(1).y/32) {
                transformComponent.setAbscissa((portals.get(0).x - 32));
                transformComponent.setOrdinate((int) portals.get(0).y );
                heroBody.setTransform((int) portals.get(0).x - 32 , portals.get(0).y, 0);
            }
             checkVictory();
        }
    }

    public void checkVictory(){
        if( (int) transformComponent.getPosition().x/32 == (int)treasureVector.x
                && (int) transformComponent.getPosition().y/32 == (int)treasureVector.y ){
            System.out.println((int)transformComponent.getPosition().x/32 +" - "+ (int)treasureVector.x);
            won = true;
        }
    }

    public void createHero(float posx, float posy){

        hero = new Entity();

        //Add Texture
        TextureComponent textureComponent = new TextureComponent();
        textureComponent.setRegion(new TextureRegion(this.assets.getManager().get("sprites/cherry.png", Texture.class)));
        hero.add(textureComponent);

        //Add Position
        DirectionComponent directionComponent = new DirectionComponent();
        hero.add(directionComponent);

        //add Movement
        MovementComponent movementComponent = new MovementComponent(HeroComponent.HERO_VELOCITY);
        hero.add(movementComponent);

        //add Hero
        HeroComponent heroComponent = new HeroComponent();
        hero.add(heroComponent);

        //Add Transform
        transformComponent = new TransformComponent(new Vector3(10,20,10));
        hero.add(transformComponent);

        //Add Collisions
        hero.add(new CollisionComponent());

        // Body creation
        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.DynamicBody;
        bd.position.set(8, 8);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(16, 16);

        PhysicsSystem physicsSystem = this.engine.getSystem(PhysicsSystem.class);

        Body body = physicsSystem.addDynamicBody(posx, posy, 10, 10);
        body.setUserData(hero);
        body.setLinearVelocity(new Vector2(0,0));

        //Add steering
        hero.add(new SteeringComponent(body));

        this.engine.addEntity(hero);
    }

    public void createMonster(){

    }

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

    public boolean isWon() {
        return won;
    }
}