package agh.cs.lab1;

import agh.cs.lab1.Code.Animal;
import agh.cs.lab1.Code.Planet;
import agh.cs.lab1.Code.Vector2d;
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

    @Test public void makeFuneralsTest(){
        Planet map =  new Planet(10, 10, 0.2, 1);
        for(int i=0; i<10; i++){
            Vector2d position = map.generateRandomVector(false);
            Animal animal = new Animal(map, position, 8, 4, 2);
        }
        map.moveAnimals();
        map.makeFunerals();
        Assert.assertEquals(0, map.aliveAnimals());
    }
}
