package com.mygdx.game.systems.pathfinding;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.mygdx.game.World;
import com.mygdx.game.components.*;

public class PathFindingSystem extends IteratingSystem {

    private ComponentMapper<DirectionComponent> dm;
    private ComponentMapper<TransformComponent> tm;
    private Entity target;
    private MapGraph graph;

    public PathFindingSystem() {
        super(Family.all(PathFindingComponent.class).get());
        dm = ComponentMapper.getFor(DirectionComponent.class);
        tm = ComponentMapper.getFor(TransformComponent.class);

    }

    public void setGraph(MapGraph graph){
        this.graph = graph;
    }

    public void setTarget(Entity target){
        this.target = target;
    }

    private Node getNodeFromPosition(float x, float y, int direction){

        if(direction == DirectionComponent.RIGHT){
            x-=World.CASE_DIMENSION*0.75;
        } else if(direction == DirectionComponent.LEFT){
            x+=World.CASE_DIMENSION*0.75;
        } else if(direction == DirectionComponent.UP){
            y-=World.CASE_DIMENSION*0.75;
        } else if(direction == DirectionComponent.DOWN){
            y+=World.CASE_DIMENSION*0.75;
        }

        int caseX = (int) Math.round((x)/(World.CASE_DIMENSION*2));
        int caseY = (int) Math.round((y)/(World.CASE_DIMENSION*2));
        caseY--;
        //System.out.println(caseX+" */*//* "+caseY);
        return graph.getNode(caseX, caseY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        float xTarget = tm.get(target).getPosition().x;
        float yTarget = tm.get(target).getPosition().y;
        Node targetCase = getNodeFromPosition(xTarget, yTarget, dm.get(target).getDirection());

        float xMonster = tm.get(entity).getPosition().x;
        float yMonster = tm.get(entity).getPosition().y ;
        Node monsterCase = getNodeFromPosition(xMonster, yMonster, dm.get(entity).getDirection());

        IndexedAStarPathFinder pathFinder = new IndexedAStarPathFinder<Node>(this.graph);
        GraphPath<Node> nodePath = new DefaultGraphPath<Node>();

        if(monsterCase != null && targetCase != null){
            if(!monsterCase.equals(targetCase)){
                pathFinder.searchNodePath(monsterCase, targetCase, new Heuristic(), nodePath);

                if(nodePath.getCount()>1){
                    Node nextCaseChosen = nodePath.get(1);

                    DirectionComponent directionComponent = dm.get(entity);

                    if (nextCaseChosen == monsterCase.getTopNode()) {
                        directionComponent.setDirection(DirectionComponent.UP);
                    } else if (nextCaseChosen == monsterCase.getBottomNode()) {
                        directionComponent.setDirection(DirectionComponent.DOWN);
                    } else if (nextCaseChosen == monsterCase.getLeftNode()) {
                        directionComponent.setDirection(DirectionComponent.LEFT);
                    } else if (nextCaseChosen == monsterCase.getRightNode()) {
                        directionComponent.setDirection(DirectionComponent.RIGHT);

                    }
                }
            }
        }
    }
}
