package com.mygdx.game.systems.pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.Graph;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.systems.pathfinding.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapGraph implements IndexedGraph<Node> {

    private Map<Integer, Map<Integer, Node>> graphMapIndex;
    private List<Node> graphListIndex;

    public MapGraph() {
        this.graphMapIndex = new HashMap<Integer, Map<Integer, Node>>();
        this.graphListIndex = new ArrayList<Node>();
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
        return graphListIndex;
    }

    public int numberOfNodes(){
        return getNodes().size();
    }

    public Node setNode(int x, int y, Node node){
        if(!graphMapIndex.containsKey(y)){
            graphMapIndex.put(y, new HashMap<Integer, Node>());
        }
        if(!graphMapIndex.get(y).containsKey(x)){
            graphMapIndex.get(y).put(x, node);
            graphListIndex.add(node);
        }
        return null;
    }

    public Node getNode(int x, int y){
        if(nodeExist(x,y)){
            return graphMapIndex.get(y).get(x);
        }
        return null;
    }

    public boolean nodeExist(int x, int y){
        if(graphMapIndex.containsKey(y)){
            if(graphMapIndex.get(y).containsKey(x)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int getIndex(Node node) {
        return graphListIndex.indexOf(node);
    }

    @Override
    public int getNodeCount() {
        return graphListIndex.size();
    }
}
