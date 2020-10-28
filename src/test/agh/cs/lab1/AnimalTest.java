package agh.cs.lab1;

import org.junit.Test;
import org.junit.Assert;

import static org.junit.Assert.*;

public class AnimalTest {
    @Test
    public void moveTest(){
        Animal a1=new Animal();

        //orientation test

        a1.move(MoveDirection.LEFT);
        assertEquals(MapDirection.WEST, a1.getOrientation());

        a1.move(MoveDirection.LEFT);
        assertEquals(MapDirection.SOUTH, a1.getOrientation());

        a1.move(MoveDirection.RIGHT); a1.move(MoveDirection.RIGHT); a1.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.EAST, a1.getOrientation());

        //position test

        a1=new Animal();

        a1.move(MoveDirection.LEFT); a1.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(1, 2), a1.getPosition());

        a1.move(MoveDirection.RIGHT); a1.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(1, 3), a1.getPosition());

        a1.move(MoveDirection.RIGHT); a1.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(0, 3), a1.getPosition());

        //in-map test

        a1=new Animal();
        a1.move(MoveDirection.FORWARD); a1.move(MoveDirection.FORWARD); a1.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(2,4), a1.getPosition());

        a1=new Animal();
        a1.move(MoveDirection.LEFT); a1.move(MoveDirection.FORWARD); a1.move(MoveDirection.FORWARD); a1.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(0, 2), a1.getPosition());

        a1=new Animal();
        a1.move(MoveDirection.BACKWARD); a1.move(MoveDirection.BACKWARD); a1.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(2, 0), a1.getPosition());

        a1=new Animal();
        a1.move(MoveDirection.RIGHT); a1.move(MoveDirection.FORWARD); a1.move(MoveDirection.FORWARD); a1.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(4, 2), a1.getPosition());

    }
}
