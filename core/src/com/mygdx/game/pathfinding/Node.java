package com.mygdx.game.pathfinding;

public class Node implements Comparable {
    public Node parent;
    public int x, y;
    public double g;
    public double h;

    Node(Node parent, int xpos, int ypos, double g, double h) {
        this.parent = parent;
        this.x = xpos;
        this.y = ypos;
        this.g = g;
        this.h = h;
    }

    @Override
    public int compareTo(Object o) {
        Node that = (Node) o;
        return (int)((this.g + this.h) - (that.g + that.h));
    }
}
