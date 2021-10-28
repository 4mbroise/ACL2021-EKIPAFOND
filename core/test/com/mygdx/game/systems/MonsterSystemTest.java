package com.mygdx.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.game.components.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonsterSystemTest {


    Engine engine;
    MonsterSystem monsterSystem;
    MonsterComponent monsterComponent, monsterComponent1;
    Entity monster, hero;

    @Before
    public void setUp() throws Exception {
        engine = new PooledEngine();
        hero= new Entity();
        monsterSystem = new MonsterSystem(hero);
        monsterComponent = new MonsterComponent();
        monsterComponent1 = new MonsterComponent(1);
        monster = new Entity();
        monster.add(monsterComponent);
        engine.addSystem(monsterSystem);
        engine.addEntity(monster);
    }


    @Test
    public void typeTest(){
        assertEquals("equals", monsterComponent.getMonsterType().toString(), MonsterComponent.Type.MONSTER.toString());
        assertEquals("equals", monsterComponent1.getMonsterType().toString(), MonsterComponent.Type.GHOST.toString());
        assertNotEquals("equals", monsterComponent1.getMonsterType().toString(), MonsterComponent.Type.MONSTER.toString());
    }

    @Test
    public void checkSystem(){
        assertEquals(1, engine.getSystems().size());
        assertNotEquals(2, engine.getEntities().size());
    }
    @Test
    public void update() {
    }
}