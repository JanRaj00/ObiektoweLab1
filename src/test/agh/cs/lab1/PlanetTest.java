package agh.cs.lab1;

import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Test;
public class PlanetTest {
    @Test public void placeTest(){
        Planet map = new Planet(10, 10, 0.2, 1);
        Animal animal = new Animal(map, new Vector2d(5, 5), 8, 4, 8);
        Animal animal2 = new Animal(map, new Vector2d(6, 6), 8, 4, 8);
        Animal animal3 = new Animal(map, new Vector2d(7, 7), 8, 4, 8);
        Animal animal4 = new Animal(map, new Vector2d(5, 5), 8, 4, 8);
        Assert.assertTrue(map.isOccupied(animal.getPosition()));
        Assert.assertEquals(4, map.aliveAnimals());
    }

    @Test public void procreateAnimalTest(){
        Planet map = new Planet(10, 10, 0.2, 1);
        Animal animal = new Animal(map, new Vector2d(5, 5), 8, 4, 8);
        Animal animal2 = new Animal(map, new Vector2d(5, 5), 8, 4, 8);
        map.procreateAnimal();
        boolean isChild=false;
        for(int i=4; i<=6; i++) {
            for (int j = 4; j <= 6; j++) {
                if (i != 5 || j != 5) {
                    Vector2d pos = new Vector2d(i, j);
                    if(map.isOccupied(pos)){
                        isChild = true;
                        break;
                    }
                }
            }
        }
        Assert.assertTrue(isChild);
        Assert.assertEquals(3,map.aliveAnimals());
    }

    @Test public void funeralsTest(){
        Planet map = new Planet(10, 10, 0.2, 1);
        Animal animal = new Animal(map, new Vector2d(5, 5), 8, 4, 8);
        Animal animal2 = new Animal(map, new Vector2d(3, 3), 8, 4, 8);
        animal.move();
        animal.move();
        animal2.move();
        animal2.move();
        map.makeFunerals();
        Assert.assertFalse(map.isOccupied(animal.getPosition()));
        Assert.assertEquals(0, map.aliveAnimals());
    }

}
