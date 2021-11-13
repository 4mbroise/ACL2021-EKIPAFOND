package com.mygdx.game.systems.pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.Graph;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.systems.pathfinding.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapGraph implements Graph<Node> {

    private Map<Integer, Map<Integer, Node>> graphIndex;

    public MapGraph() {
        this.graphIndex = new HashMap<Integer, Map<Integer, Node>>();
    }

    @Override
    public Array<Connection<Node>> getConnections(Node fromNode) {

        Array<Connection<Node>> connections = new Array<Connection<Node>>();
        /*
        for(Map<Integer, Node> layerNode : graphIndex.values()){
            for(Node node:layerNode.values()){
                for(Connection<Node> connection : node.getNeighboringNodes()){
                    connections.add(connection);
                }
            }
        }
        */

        for(Connection<Node> connection : fromNode.getNeighboringNodes()){
            connections.add(connection);
        }
        return connections;
    }

    public List<Node> getNodes(){
        ArrayList<Node> list = new ArrayList<Node>();

        for(Map<Integer, Node> layerNode : graphIndex.values()){
            for(Node node:layerNode.values()){
                System.out.println("a");
                list.add(node);
            }
        }

        return list;
    }

    public int numberOfNodes(){
        return getNodes().size();
    }

    public Node setNode(int x, int y, Node node){
        if(!graphIndex.containsKey(y)){
            graphIndex.put(y, new HashMap<Integer, Node>());
        }
        if(!graphIndex.get(y).containsKey(x)){
            graphIndex.get(y).put(x, node);
        }
        return null;
    }

    public Node getNode(int x, int y){
        if(nodeExist(x,y)){
            return graphIndex.get(y).get(x);
        }
        return null;
    }

    public boolean nodeExist(int x, int y){
        if(graphIndex.containsKey(y)){
            if(graphIndex.get(y).containsKey(x)){
                return true;
            }
        }
        return false;
    }
}
