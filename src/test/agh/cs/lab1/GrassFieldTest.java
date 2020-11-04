package agh.cs.lab1;
import org.junit.Test;
import org.junit.Assert;
import java.lang.Math;
import javax.swing.*;

import static org.junit.Assert.*;


public class GrassFieldTest {
    IWorldMap grassMap=new GrassField(10);

    @Test
    public void isOccupiedTestGF(){
        Animal a = new Animal(grassMap, new Vector2d(5, 7));
        assertTrue(grassMap.isOccupied(new Vector2d(5, 7)));
    }

    @Test
    public void placeTestGF(){
        grassMap.place(new Animal(grassMap));
        assertTrue(grassMap.isOccupied(new Vector2d(2, 2)));
    }

    @Test
    public void canMoveToTestGF(){
        Animal a = new Animal(grassMap, new Vector2d(2, 3));
        Animal b = new Animal(grassMap, new Vector2d(3, 3));
        MoveDirection[] dir1={MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD};
        MoveDirection[] dir2={MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.FORWARD};
        for(int i=0; i<3; i++){
            a.move(dir1[i]);
            b.move(dir2[i]);
        }
        assertEquals(new Vector2d(2, 3), a.getPosition());
        assertEquals(new Vector2d(3, 3), b.getPosition());
    }

    @Test
    public void objectAtTest(){
        int n=(int) Math.sqrt(10*10);
        int numberofGrassFields=0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(grassMap.objectAt(new Vector2d(i, j))!=null){
                    numberofGrassFields++;
                }
            }
        }

        Animal animal1 = new Animal(grassMap, new Vector2d(2, 3));
        for(int i=0; i<15; i++){
            animal1.move(MoveDirection.FORWARD);
        }

        assertEquals(10, numberofGrassFields);
        assertEquals(animal1, grassMap.objectAt(new Vector2d(2, 18)));
    }

    @Test
    public void runTestGF(){
        String[] dirs = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(dirs);
        grassMap.place(new Animal(grassMap));
        grassMap.place(new Animal(grassMap, new Vector2d(3, 4)));
        grassMap.run(directions);
        Object a = grassMap.objectAt(new Vector2d(2, -1));
        Object b = grassMap.objectAt(new Vector2d(3, 7));
        assertEquals(MapDirection.SOUTH.toString(), a.toString());
        assertEquals(MapDirection.NORTH.toString(), b.toString());
    }
}
