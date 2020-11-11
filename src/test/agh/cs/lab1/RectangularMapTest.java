package agh.cs.lab1;

import org.junit.Assert.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class RectangularMapTest {
    IWorldMap map = new RectangularMap(10, 5);

    @Test
    public void isOccupiedTest(){
        assertFalse(map.isOccupied(new Vector2d(2, 2)));
        map.place(new Animal(map, new Vector2d(2, 2)));
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
    }

    @Test
    public void placeTest(){
        map.place(new Animal(map));
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        map.place(new Animal(map, new Vector2d(3, 4)));
        assertTrue(map.isOccupied(new Vector2d(3, 4)));
    }


    @Test
    public void canMoveToTest(){
        map.place(new Animal(map, new Vector2d(2,3)));
        map.place(new Animal(map, new Vector2d(3, 3)));
        String[] s1 = {"r", "l", "f", "f"};
        MoveDirection[] movedir1=new OptionsParser().parse(s1);
        map.run(movedir1);
        assertEquals(MapDirection.EAST.toString(), map.objectAt(new Vector2d(2, 3)).toString());
        assertEquals(MapDirection.WEST.toString(), map.objectAt(new Vector2d(3, 3)).toString());
        String[] s2={"l", "r", "f", "b", "f", "b", "f", "b", "f", "b"};
        MoveDirection[] movedir2=new OptionsParser().parse(s2);
        map.run(movedir2);
        assertFalse(map.objectAt(new Vector2d(2, 5))==null);
        assertFalse(map.objectAt(new Vector2d(3, 0))==null);
    }

    @Test
    public void objectAtTest(){
        map.place(new Animal(map, new Vector2d(2, 3)));
        assertEquals(null, map.objectAt(new Vector2d(2, 2)));
        assertFalse(map.objectAt(new Vector2d(2, 3))==null);
    }

    @Test
    public void runTest(){
        String[] dirs = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(dirs);
        map.place(new Animal(map));
        map.place(new Animal(map, new Vector2d(3, 4)));
        map.run(directions);
        Object a = map.objectAt(new Vector2d(2, 0));
        Object b = map.objectAt(new Vector2d(3, 5));
        assertEquals(MapDirection.SOUTH.toString(), a.toString());
        assertEquals(MapDirection.NORTH.toString(), b.toString());
    }

}
