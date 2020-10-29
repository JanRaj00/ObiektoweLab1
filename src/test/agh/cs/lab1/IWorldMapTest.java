package agh.cs.lab1;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class IWorldMapTest {    // czy da się przetestować interfejs?
    IWorldMap map = new RectangularMap(10, 5);

    @Test
    public void isOccupiedTest(){
        assertFalse(map.isOccupied(new Vector2d(2, 2)));
        assertFalse(map.isOccupied(new Vector2d(3, 4)));
    }

    @Test
    public void placeTest(){
        map.place(new Animal(map));
        assertTrue(map.isOccupied(new Vector2d(2,2)));
        map.place(new Animal(map, new Vector2d(3, 4)));
        assertTrue(map.isOccupied(new Vector2d(3, 4)));
    }

    @Test
    public void canMoveToTest(){
        Animal animal1 = new Animal(map, new Vector2d(2, 3));
        Animal animal2 = new Animal(map, new Vector2d(3, 3));
        Animal animal3 = new Animal(map, new Vector2d(10, 5));
        Animal animal4 = new Animal(map, new Vector2d(0, 0));
        MoveDirection[] dir1={MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD};
        MoveDirection[] dir2={MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.FORWARD};
        MoveDirection[] dir3={MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD};
        MoveDirection[] dir4={MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.FORWARD};
        for (int i=0; i<3; i++) {
            animal1.move(dir1[i]);
            animal2.move(dir2[i]);
            animal3.move(dir3[i]);
            animal4.move(dir4[i]);
        }

        assertEquals(animal1.getPosition(), new Vector2d(2, 3));
        assertEquals(animal2.getPosition(), new Vector2d(3, 3));
        assertEquals(animal3.getPosition(), new Vector2d(10, 5));
        assertEquals(animal4.getPosition(), new Vector2d(0, 0));
    }

    @Test
    public void objectAtTest(){
        Animal animal1 = new Animal(map, new Vector2d(2, 3));
        animal1.move(MoveDirection.FORWARD);
        assertEquals(null, map.objectAt(new Vector2d(2, 3)));
        assertEquals(animal1, map.objectAt(new Vector2d(2, 4)));
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
