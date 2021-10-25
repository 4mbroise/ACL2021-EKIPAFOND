package com.mygdx.game;

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


    public World() {
        Maps = new ArrayList<File>();
        this.MapDir = "core/assets/maps/map1.txt" ;
    }

    public void createMap(){
        mapFile = new File(this.MapDir);
        try{
        this.SizeReader = new Scanner(mapFile);
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        /*
         * Get the width and the height of the map
         */
        while (SizeReader.hasNext()) {
            String data = SizeReader.nextLine();
            if (data.length() > this.width) {
                this.width = data.length();
            }
            this.height++;
        }

        /*
         *  In case the file does not contain a single column and/or row
         */
        if (this.height == 0){
            this.height = 1;
        }
        if (this.width == 0){
            this.width = 1;
        }

        SizeReader.close();

        try{
        this.TileReader = new Scanner(mapFile);
        }
        catch(Exception e){
            System.out.println("File not found");
        }
        this.grid = new char[this.height][this.width];
        ctr = 0;
        while (TileReader.hasNext()) {
            String data = TileReader.nextLine();
                for (int j = 0; j < this.width ; j++){
                    grid[ctr][j] = data.charAt(j);
                }
            ctr++;
        }
        this.TileReader.close();
    }

    public void LoadMap(){

    }
    public void createPlayer(){

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



