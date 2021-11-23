package com.mygdx.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ai.pfa.Connection;
import com.mygdx.game.systems.pathfinding.MapGraph;
import com.mygdx.game.systems.pathfinding.Node;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class WorldTest {

    private Application app;
    World world;
    Engine engine;
    Assets assets;

    @Before
    public void setUp() throws Exception {
        this.engine = new Engine();
        this.assets = new Assets();
        this.world = new World(engine, assets);
    }

    @Test
    public void graphMapTest1() {
        System.out.println(new File("assets/maps/mapTest1.txt").exists());
        this.world.createMapChar(new File("assets/maps/mapTest1.txt"));
        MapGraph graph = this.world.getMapGraph();
        //System.out.println(graph.numberOfNodes());

        for(int i = 1; i<4; i++){
            for(int j = 1; j<4; j++){
                assertTrue(graph.nodeExist(i,j));
            }
        }

        //Node 1,1
        ArrayList<Node> list1 = new ArrayList<>();
        list1.add(graph.getNode(1,2));
        list1.add(graph.getNode(2,1));
        for(Connection<Node> connection : graph.getConnections(graph.getNode(1,1))){
            assertTrue(list1.contains(connection.getToNode()));
            list1.remove(connection.getToNode());
        }
        assertEquals(0, list1.size());

        //Node 1,2
        ArrayList<Node> list2 = new ArrayList<>();
        list2.add(graph.getNode(1,1));
        list2.add(graph.getNode(1,3));
        list2.add(graph.getNode(2,2));
        for(Connection<Node> connection : graph.getConnections(graph.getNode(1,2))){
            assertTrue(list2.contains(connection.getToNode()));
            list2.remove(connection.getToNode());
        }
        assertEquals(0, list2.size());

        //Node 1,3
        ArrayList<Node> list3 = new ArrayList<>();
        list3.add(graph.getNode(1,2));
        list3.add(graph.getNode(2,3));
        for(Connection<Node> connection : graph.getConnections(graph.getNode(1,3))){
            assertTrue(list3.contains(connection.getToNode()));
            list3.remove(connection.getToNode());
        }
        assertEquals(0, list3.size());

        //Node 2,1
        ArrayList<Node> list4 = new ArrayList<>();
        list4.add(graph.getNode(1,1));
        list4.add(graph.getNode(2,2));
        list4.add(graph.getNode(3,1));
        for(Connection<Node> connection : graph.getConnections(graph.getNode(2,1))){
            assertTrue(list4.contains(connection.getToNode()));
            list4.remove(connection.getToNode());
        }
        assertEquals(0, list4.size());

        //Node 2,2
        ArrayList<Node> list5 = new ArrayList<>();
        list5.add(graph.getNode(1,2));
        list5.add(graph.getNode(2,1));
        list5.add(graph.getNode(2,3));
        list5.add(graph.getNode(3,2));
        for(Connection<Node> connection : graph.getConnections(graph.getNode(2,2))){
            assertTrue(list5.contains(connection.getToNode()));
            list5.remove(connection.getToNode());
        }
        assertEquals(0, list5.size());

        //Node 2,3
        ArrayList<Node> list6 = new ArrayList<>();
        list6.add(graph.getNode(1,3));
        list6.add(graph.getNode(2,2));
        list6.add(graph.getNode(3,3));
        for(Connection<Node> connection : graph.getConnections(graph.getNode(2,3))){
            assertTrue(list6.contains(connection.getToNode()));
            list6.remove(connection.getToNode());
        }
        assertEquals(0, list6.size());

        //Node 3,1
        ArrayList<Node> list7 = new ArrayList<>();
        list7.add(graph.getNode(2,1));
        list7.add(graph.getNode(3,2));
        for(Connection<Node> connection : graph.getConnections(graph.getNode(3,1))){
            assertTrue(list7.contains(connection.getToNode()));
            list7.remove(connection.getToNode());
        }
        assertEquals(0, list7.size());

        //Node 3,2
        ArrayList<Node> list8 = new ArrayList<>();
        list8.add(graph.getNode(3,1));
        list8.add(graph.getNode(2,2));
        list8.add(graph.getNode(3,3));
        for(Connection<Node> connection : graph.getConnections(graph.getNode(3,2))){
            assertTrue(list8.contains(connection.getToNode()));
            list8.remove(connection.getToNode());
        }
        assertEquals(0, list8.size());

        //Node 3,1
        ArrayList<Node> list9 = new ArrayList<>();
        list9.add(graph.getNode(2,3));
        list9.add(graph.getNode(3,2));
        for(Connection<Node> connection : graph.getConnections(graph.getNode(3,3))){
            assertTrue(list9.contains(connection.getToNode()));
            list9.remove(connection.getToNode());
        }
        assertEquals(0, list9.size());
    }

    @Test
    public void graphMapTest2() {
        System.out.println(new File("assets/maps/mapTest2.txt").exists());
        this.world.createMapChar(new File("assets/maps/mapTest2.txt"));
        MapGraph graph = this.world.getMapGraph();
        //System.out.println(graph.numberOfNodes());

        assertTrue(graph.nodeExist(3,1));
        assertTrue(graph.nodeExist(3,2));
        assertTrue(graph.nodeExist(2,1));
        assertTrue(graph.nodeExist(2,3));
        assertTrue(graph.nodeExist(1,1));
        assertTrue(graph.nodeExist(1,2));
        assertTrue(graph.nodeExist(1,3));

        assertEquals(7, graph.numberOfNodes());

        //Node 3,1
        ArrayList<Node> list1 = new ArrayList<>();
        list1.add(graph.getNode(3,2));
        list1.add(graph.getNode(2,1));
        for(Connection<Node> connection : graph.getConnections(graph.getNode(3,1))){
            assertTrue(list1.contains(connection.getToNode()));
            list1.remove(connection.getToNode());
        }
        assertEquals(0, list1.size());

        //Node 3,2
        ArrayList<Node> list2 = new ArrayList<>();
        list2.add(graph.getNode(3,1));
        for(Connection<Node> connection : graph.getConnections(graph.getNode(3,2))){
            assertTrue(list2.contains(connection.getToNode()));
            list2.remove(connection.getToNode());
        }
        assertEquals(0, list2.size());

        //Node 2,1
        ArrayList<Node> list3 = new ArrayList<>();
        list3.add(graph.getNode(3,1));
        list3.add(graph.getNode(1,1));
        for(Connection<Node> connection : graph.getConnections(graph.getNode(2,1))){
            assertTrue(list3.contains(connection.getToNode()));
            list3.remove(connection.getToNode());
        }
        assertEquals(0, list3.size());

        //Node 2,3
        ArrayList<Node> list4 = new ArrayList<>();
        list4.add(graph.getNode(1,3));
        for(Connection<Node> connection : graph.getConnections(graph.getNode(2,3))){
            assertTrue(list4.contains(connection.getToNode()));
            list4.remove(connection.getToNode());
        }
        assertEquals(0, list4.size());

        //Node 1,1
        ArrayList<Node> list5 = new ArrayList<>();
        list5.add(graph.getNode(2,1));
        list5.add(graph.getNode(1,2));
        for(Connection<Node> connection : graph.getConnections(graph.getNode(1,1))){
            assertTrue(list5.contains(connection.getToNode()));
            list5.remove(connection.getToNode());
        }
        assertEquals(0, list5.size());

        //Node 1,2
        ArrayList<Node> list6 = new ArrayList<>();
        list6.add(graph.getNode(1,1));
        list6.add(graph.getNode(1,3));
        for(Connection<Node> connection : graph.getConnections(graph.getNode(1,2))){
            assertTrue(list6.contains(connection.getToNode()));
            list6.remove(connection.getToNode());
        }
        assertEquals(0, list6.size());

        //Node 1,3
        ArrayList<Node> list7 = new ArrayList<>();
        list7.add(graph.getNode(2,3));
        list7.add(graph.getNode(1,2));
        for(Connection<Node> connection : graph.getConnections(graph.getNode(1,3))){
            assertTrue(list7.contains(connection.getToNode()));
            list7.remove(connection.getToNode());
        }
        assertEquals(0, list7.size());

    }

    @Test
    public void graphMapTest3() {
        System.out.println(new File("assets/maps/mapTest3.txt").exists());
        this.world.createMapChar(new File("assets/maps/mapTest3.txt"));
        MapGraph graph = this.world.getMapGraph();
        //System.out.println(graph.numberOfNodes());

        for (int j = 1; j < 5; j++) {
            assertTrue(graph.nodeExist(j, 1));
        }

        //Node 1,1
        ArrayList<Node> list1 = new ArrayList<>();
        list1.add(graph.getNode(2, 1));
        for (Connection<Node> connection : graph.getConnections(graph.getNode(1, 1))) {
            assertTrue(list1.contains(connection.getToNode()));
            list1.remove(connection.getToNode());
        }
        assertEquals(0, list1.size());

        //Node 1,2
        ArrayList<Node> list2 = new ArrayList<>();
        list2.add(graph.getNode(1, 1));
        list2.add(graph.getNode(3, 1));
        for (Connection<Node> connection : graph.getConnections(graph.getNode(2, 1))) {
            assertTrue(list2.contains(connection.getToNode()));
            list2.remove(connection.getToNode());
        }
        assertEquals(0, list2.size());

        //Node 1,3
        ArrayList<Node> list3 = new ArrayList<>();
        list3.add(graph.getNode(2, 1));
        list3.add(graph.getNode(4, 1));
        for (Connection<Node> connection : graph.getConnections(graph.getNode(3, 1))) {
            assertTrue(list3.contains(connection.getToNode()));
            list3.remove(connection.getToNode());
        }
        assertEquals(0, list3.size());

        //Node 1,4
        ArrayList<Node> list4 = new ArrayList<>();
        list3.add(graph.getNode(3, 1));
        list3.add(graph.getNode(5, 1));
        for (Connection<Node> connection : graph.getConnections(graph.getNode(4, 1))) {
            assertTrue(list3.contains(connection.getToNode()));
            list3.remove(connection.getToNode());
        }
        assertEquals(0, list3.size());

        //Node 1,5
        ArrayList<Node> list5 = new ArrayList<>();
        list3.add(graph.getNode(4, 1));
        for (Connection<Node> connection : graph.getConnections(graph.getNode(5, 1))) {
            assertTrue(list3.contains(connection.getToNode()));
            list3.remove(connection.getToNode());
        }
        assertEquals(0, list3.size());

    }

}