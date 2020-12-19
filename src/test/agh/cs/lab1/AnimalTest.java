package agh.cs.lab1;

import org.junit.Assert;
import org.junit.Test;

public class AnimalTest {
    @Test public void isAliveTest(){
        Planet map = new Planet(10, 10, 0.2, 1);
        Animal animal = new Animal(map, new Vector2d(5, 5), 8, 4, 8);
        animal.move();
        Assert.assertTrue(animal.isAlive());
        animal.move();
        Assert.assertFalse(animal.isAlive());
    }


    @Test public void changeEnergyTest(){
        Planet map =  new Planet(10, 10, 0.2, 1);
        Animal animal = new Animal(map, new Vector2d(5, 5), 8, 4, 8);
        animal.changeEnergy(-2);
        Assert.assertEquals(6, animal.getEnergy());
    }
}
