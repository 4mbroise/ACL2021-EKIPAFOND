package com.mygdx.game.systems.pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultConnection;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Node topNode;
    private Node bottomNode;
    private Node leftNode;
    private Node rightNode;
    private List<Connection<Node>> neighboringNodes;


    public Node() {
        topNode         = null;
        bottomNode      = null;
        leftNode        = null;
        rightNode       = null;
        neighboringNodes = new ArrayList<Connection<Node>>();
    }

    public void setRightNode(Node n){
        this.rightNode = n;
        addNeighboringNodes(n);
    }

    public void setTopNode(Node n){
        this.topNode = n;
        addNeighboringNodes(n);
    }

    public void setBottomNode(Node n){
        this.bottomNode = n;
        addNeighboringNodes(n);
    }

    public void setLeftNode(Node n){
        this.leftNode = n;
        addNeighboringNodes(n);
    }

    public void addNeighboringNodes(Node n){
        neighboringNodes.add(new DefaultConnection<Node>(this, n));
    }

    public List<Connection<Node>> getNeighboringNodes() {
        return neighboringNodes;
    }
}
