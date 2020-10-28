package agh.cs.lab1;

import agh.cs.lab1.MapDirection;
import org.junit.Assert;
import org.junit.Test;


public class MapDirectionTest {
    @Test public void nextTest(){
        MapDirection N = MapDirection.NORTH;
        MapDirection E = MapDirection.EAST;
        MapDirection S = MapDirection.SOUTH;
        MapDirection W = MapDirection.WEST;

        Assert.assertTrue(N.next()==MapDirection.EAST);
        Assert.assertTrue(E.next()==MapDirection.SOUTH);
        Assert.assertTrue(S.next()==MapDirection.WEST);
        Assert.assertTrue(W.next()==MapDirection.NORTH);
    }

    @Test public void previousTest(){
        MapDirection N = MapDirection.NORTH;
        MapDirection E = MapDirection.EAST;
        MapDirection S = MapDirection.SOUTH;
        MapDirection W = MapDirection.WEST;

        Assert.assertTrue(N.previous()==MapDirection.WEST);
        Assert.assertTrue(E.previous()==MapDirection.NORTH);
        Assert.assertTrue(S.previous()==MapDirection.EAST);
        Assert.assertTrue(W.previous()==MapDirection.SOUTH);
    }
}
