package agh.cs.lab1;

import org.junit.Assert;
import org.junit.Test;


public class MapDirectionTest {
    @Test public void nextTest(){
        MapDirection N = MapDirection.NORTH;
        MapDirection E = MapDirection.EAST;
        MapDirection S = MapDirection.SOUTH;
        MapDirection W = MapDirection.WEST;
        MapDirection NE = MapDirection.NORTHEAST;
        MapDirection SE = MapDirection.SOUTHEAST;
        MapDirection SW = MapDirection.SOUTHWEST;
        MapDirection NW = MapDirection.NORTHWEST;

        Assert.assertEquals(N.next(),MapDirection.NORTHEAST);
        Assert.assertEquals(NE.next(), MapDirection.EAST);
        Assert.assertEquals(E.next(), MapDirection.SOUTHEAST);
        Assert.assertEquals(SE.next(), MapDirection.SOUTH);
        Assert.assertEquals(S.next(),MapDirection.SOUTHWEST);
        Assert.assertEquals(SW.next(), MapDirection.WEST);
        Assert.assertEquals(W.next(), MapDirection.NORTHWEST);
        Assert.assertEquals(NW.next(), MapDirection.NORTH);
    }
}
