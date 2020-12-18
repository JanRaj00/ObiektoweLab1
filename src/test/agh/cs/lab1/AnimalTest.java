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

    @Test public void procreateTest(){
        Planet map = new Planet(10, 10, 0.2, 1);
        Animal parent1 = new Animal(map, new Vector2d(5, 5), 8, 4, 8);
        Animal parent2 = new Animal(map, new Vector2d(5, 5), 8, 4, 8);
        parent1.procreate(parent2);
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
        Assert.assertEquals(6, parent1.getEnergy());
        Assert.assertEquals(6, parent2.getEnergy());
    }

    @Test public void changeEnergyTest(){
        Planet map =  new Planet(10, 10, 0.2, 1);
        Animal animal = new Animal(map, new Vector2d(5, 5), 8, 4, 8);
        animal.changeEnergy(-2);
        Assert.assertEquals(6, animal.getEnergy());
    }
}
