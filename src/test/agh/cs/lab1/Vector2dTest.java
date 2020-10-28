package agh.cs.lab1;

import org.junit.Assert;
import org.junit.Test;

public class Vector2dTest {
    @Test public void equalsTest(){
        Vector2d v1= new Vector2d(3, 1);
        Vector2d v2= new Vector2d(6, 5);
        Vector2d v3= new Vector2d(3, 1);
        Assert.assertFalse(v1.equals(v2));
        Assert.assertTrue(v1.equals(v3));
    }

    @Test public void precedesTest(){
        Vector2d v1= new Vector2d(3, 1);
        Vector2d v2= new Vector2d(6, 5);
        Vector2d v3= new Vector2d(6, 7);
        Assert.assertTrue(v1.precedes(v2));
        Assert.assertTrue(v2.precedes(v3));
        Assert.assertFalse(v3.precedes(v1));
    }

    @Test public void toStringTest(){
        Vector2d v1=new Vector2d(1, 2);
        String s1 = "(1,2)";
        Assert.assertEquals(s1, v1.toString());
    }

    @Test public void followsTest() {
        Vector2d v1= new Vector2d(3, 1);
        Vector2d v2= new Vector2d(6, 5);
        Vector2d v3= new Vector2d(6, 7);
        Assert.assertFalse(v1.follows(v2));
        Assert.assertFalse(v2.follows(v3));
        Assert.assertTrue(v3.follows(v1));
    }

    @Test public void upperRightTest() {
        Vector2d v1 = new Vector2d(8, 2);
        Vector2d v2 = new Vector2d(4, 6);
        Vector2d upperRightTrue = new Vector2d(8, 6);
        Vector2d upperRightFalse = new Vector2d(8, 2);
        Assert.assertEquals(upperRightTrue, v1.upperRight(v2));
        Assert.assertFalse(upperRightFalse == v1.upperRight(v2));
    }

    @Test public void lowerLeftTest() {
        Vector2d v1 = new Vector2d(8, 2);
        Vector2d v2 = new Vector2d(4, 6);
        Vector2d lowerLeftTrue = new Vector2d(4, 2);
        Vector2d loweLeftFalse = new Vector2d(8, 6);
        Assert.assertEquals(lowerLeftTrue, v1.lowerLeft(v2));
        Assert.assertFalse(loweLeftFalse == v1.lowerLeft(v2));
    }

    @Test public void addTest() {
        Vector2d v1 = new Vector2d(8, 2);
        Vector2d v2 = new Vector2d(4, 6);
        Vector2d addTrue = new Vector2d(12, 8);
        Vector2d addFalse = new Vector2d(5, 7);
        Assert.assertEquals(addTrue, v1.add(v2));
        Assert.assertFalse(addFalse==v1.add(v2));
    }

    @Test public void subtractTest() {
        Vector2d v1 = new Vector2d(8, 2);
        Vector2d v2 = new Vector2d(4, 6);
        Vector2d subtractTrue = new Vector2d(4, -4);
        Vector2d subtractFalse = new Vector2d(12, 8);
        Assert.assertEquals(subtractTrue, v1.subtract(v2));
        Assert.assertFalse(subtractFalse==v1.subtract(v2));
    }

    @Test public void oppositeTest(){
        Vector2d v1 = new Vector2d(100, -12);
        Vector2d oppositeTrue= new Vector2d(-100, 12);
        Vector2d oppositeFalse = new Vector2d(100, 12);
        Assert.assertEquals(oppositeTrue, v1.opposite());
        Assert.assertFalse(oppositeFalse==v1.opposite());
    }
}
