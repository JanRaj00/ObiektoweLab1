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
        grassMap.place(new Animal(grassMap));
        assertTrue(grassMap.isOccupied(new Vector2d(2, 2)));
    }

    @Test
    public void placeTestGF(){
        grassMap.place(new Animal(grassMap));
        assertFalse(grassMap.objectAt(new Vector2d(2, 2))==null);
    }

    @Test
    public void canMoveToTestGF(){
        grassMap.place(new Animal(grassMap, new Vector2d(2, 3)));
        grassMap.place(new Animal(grassMap, new Vector2d(3, 3)));
        MoveDirection[] d={MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.FORWARD};
        grassMap.run(d);
        assertEquals(MapDirection.EAST.toString(), grassMap.objectAt(new Vector2d(2, 3)).toString());
        assertEquals(MapDirection.WEST.toString(), grassMap.objectAt(new Vector2d(3, 3)).toString());
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

        grassMap.place(new Animal(grassMap, new Vector2d(2, 3)));
        String[] dir = {"f", "f", "f", "f", "f"};
        MoveDirection[] dirr = new OptionsParser().parse(dir);
        grassMap.run(dirr);
        assertEquals(10, numberofGrassFields);
        assertFalse(grassMap.objectAt(new Vector2d(2, 8))==null);
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
