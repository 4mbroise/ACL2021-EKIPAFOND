package com.mygdx.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.mygdx.game.factory.EntityFactory;
import com.mygdx.game.factory.entity.HeroBuilder;
import com.mygdx.game.factory.entity.InteligentMonsterBuilder;
import com.mygdx.game.factory.entity.MonsterBuilder;
import com.mygdx.game.factory.entity.WallBuilder;
import com.mygdx.game.systems.pathfinding.MapGraph;
import com.mygdx.game.systems.pathfinding.Node;
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
    private int maxWidth;
    private int maxHeight;
    private char[][] map;

    public World(Engine engine, Assets assets) {
        this.engine = engine;
        this.assets = assets;
        this.physicsSystem = this.engine.getSystem(PhysicsSystem.class);

        this.entityFactory = new EntityFactory();
        this.entityFactory.addEntityBuilder("-", new WallBuilder(assets, physicsSystem));
        this.entityFactory.addEntityBuilder("1", new HeroBuilder(assets, physicsSystem));
        this.entityFactory.addEntityBuilder("m", new MonsterBuilder(assets, physicsSystem));
        this.entityFactory.addEntityBuilder("M", new InteligentMonsterBuilder(assets, physicsSystem));

    }

    public World() {
    }

    public void createMapChar(File mapFile){
        int[] mapMaxDimensions = getMapMaxDimension(mapFile);
        int x = 0;
        int y = mapMaxDimensions[1];
        this.map = new char[y][mapMaxDimensions[0]];
        Scanner mapReader = null;
        try {
            mapReader = new Scanner(mapFile);

            while (mapReader.hasNext()) {
                String data = mapReader.nextLine();
                for (int j = 0; j < data.length(); j++) {
                    map[y-1][x] = data.charAt(j);
                    x++;
                }
                x = 0;
                y--;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
            this.maxWidth = maxWidth;
            this.maxHeight = maxHeight;
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
        int y = CASE_DIMENSION * mapHeight * 2;

        this.map = new char[mapHeight][mapWidth];

        Scanner mapReader = null;
        try {
            mapReader = new Scanner(mapFile);

            while (mapReader.hasNext()) {
                String data = mapReader.nextLine();
                for (int j = 0; j < data.length(); j++) {
                    //System.out.println("("+x+";"+y+")");
                    Entity entity = entityFactory.createEntity(Character.toString(data.charAt(j)), x, y);
                    map[y/(CASE_DIMENSION*2)-1][x/(CASE_DIMENSION*2)] = data.charAt(j);
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

    //Look for the first case's coords that isn't a wall, it will be our first Node
    private int[] getFirstCase(){
        int y = 0;
        while(true){
            for(int i=0; i<maxWidth;i++){
                //System.out.println(this.map[y][i]);
                //System.out.println(this.map[y][i] != '-' && this.map[y][i] != ' ');
                if(this.map[y][i] != '-' && this.map[y][i] != ' ')
                {
                    return new int[]{i, y};
                }
            }
            y++;
        }
    }

    public MapGraph getMapGraph(){

        MapGraph graph = new MapGraph();
        int[] firstCaseCoords = getFirstCase();

        graph.setNode(firstCaseCoords[0], firstCaseCoords[1], getNode(firstCaseCoords[0], firstCaseCoords[1],graph));

        return graph;
    }

    public Node getNode(int x, int y, MapGraph graph){
        //If this node has not been has already been processed
        if(!graph.nodeExist(x, y) && this.map[y][x]!='-' && this.map[y][x]!=' '){
             Node node = new Node();
             graph.setNode(x, y, node);
             if(y+1<=maxHeight){
                 Node topNode = getNode(x, y+1, graph);
                 if(topNode != null){
                     node.setTopNode(topNode);
                 }
             }
             if(y-1>=0){
                 Node bottomNode = getNode(x, y-1, graph);
                 if(bottomNode != null){
                     node.setBottomNode(bottomNode);
                 }
             }
             if(x-1>=0){
                 Node leftNode = getNode(x-1, y, graph);
                 if(leftNode != null){
                     node.setLeftNode(leftNode);
                 }
             }
             if(x+1<=maxWidth){
                 Node rightNode = getNode(x+1, y, graph);
                 if(rightNode != null){
                     node.setRightNode(rightNode);
                 }
             }
             return node;
        } else{
            return graph.getNode(x, y);
        }
    }

}