package com.mygdx.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.mygdx.game.factory.EntityFactory;
import com.mygdx.game.factory.entity.HeroBuilder;
import com.mygdx.game.factory.entity.MonsterBuilder;
import com.mygdx.game.factory.entity.WallBuilder;
import com.mygdx.game.systems.physics.PhysicsSystem;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class World {

    public static final int CASE_DIMENSION = 16;
    private Engine engine;
    private Assets assets;
    private PhysicsSystem physicsSystem;
    private EntityFactory entityFactory;

    public World(Engine engine, Assets assets) {
        this.engine = engine;
        this.assets = assets;
        this.physicsSystem = this.engine.getSystem(PhysicsSystem.class);

        this.entityFactory = new EntityFactory();
        this.entityFactory.addEntityBuilder("-", new WallBuilder(assets, physicsSystem));
        this.entityFactory.addEntityBuilder("1", new HeroBuilder(assets, physicsSystem));
        this.entityFactory.addEntityBuilder("m", new MonsterBuilder(assets, physicsSystem));

    }

    public World() {
    }

    private int[] getMapMaxDimension(File mapFile) {
        int maxWidth = 0;
        int maxHeight = 0;
        try{
            Scanner scanner = new Scanner(mapFile);
            while (scanner.hasNext()) {
                String data = scanner.nextLine();
                if (data.length() > maxWidth) {
                    maxWidth = data.length();
                }
                maxHeight++;
            }
            int result[] = new int[2];
            result[0] = maxWidth;
            result[1] = maxHeight;
            scanner.close();

            return result;
        } catch (FileNotFoundException e){
            System.out.println("Map "+mapFile.getPath()+" not found");
        }
        return null;
    }

    public int[] createMap(File mapFile) {
        int[] mapMaxDimensions = getMapMaxDimension(mapFile);
        int mapWidth = mapMaxDimensions[0];
        int mapHeight = mapMaxDimensions[1];
        int x = 0;
        int y = CASE_DIMENSION * mapHeight;

        Scanner mapReader = null;
        try {
            mapReader = new Scanner(mapFile);

            while (mapReader.hasNext()) {
                String data = mapReader.nextLine();
                for (int j = 0; j < data.length(); j++) {
                    Entity entity = entityFactory.createEntity(Character.toString(data.charAt(j)), x, y);
                    //System.out.println("("+x+";"+y+")");
                    if(entity != null){
                        this.engine.addEntity(entity);
                    }
                    x += CASE_DIMENSION*2;
                }
                x = 0;
                y -= CASE_DIMENSION*2;
            }
            return mapMaxDimensions;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}