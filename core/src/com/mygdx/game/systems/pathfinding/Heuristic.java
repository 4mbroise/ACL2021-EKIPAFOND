package com.mygdx.game.systems.pathfinding;

public class Heuristic implements com.badlogic.gdx.ai.pfa.Heuristic<Node> {

    @Override
    public float estimate(Node node, Node endNode) {
        return 10;
    }
}
