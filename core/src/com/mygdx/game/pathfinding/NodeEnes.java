package com.mygdx.game.pathfinding;

public class NodeEnes implements Comparable {
    public NodeEnes parent;
    public int x, y;
    public double g;
    public double h;

    NodeEnes(NodeEnes parent, int xpos, int ypos, double g, double h) {
        this.parent = parent;
        this.x = xpos;
        this.y = ypos;
        this.g = g;
        this.h = h;
    }

    @Override
    public int compareTo(Object o) {
        NodeEnes that = (NodeEnes) o;
        return (int)((this.g + this.h) - (that.g + that.h));
    }
}
