package agh.cs.lab1;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap{
    public final Vector2d upperLimit;   // proponuję upperRight
    public final Vector2d lowerLimit;
    List<Animal> animals = new ArrayList<>();

    public RectangularMap(int width, int height){
        this.lowerLimit = new Vector2d(0, 0);
        this.upperLimit = new Vector2d(width, height);  // width - 1
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        Vector2d upp = position.upperRight(upperLimit); // trochę pokrętne mi się wydaje to obliczenie
        Vector2d low = position.lowerLeft(lowerLimit);
        if(upp.precedes(upperLimit) && low.follows(lowerLimit))
            if(isOccupied(position))    // return !isOccupied
                return false;
            else
                return true;
        else
            return false;
    }

    @Override
    public boolean place(Animal animal) {
        if(isOccupied(animal.getPosition()))    // czy isOccupied to właściwa metoda tutaj?
            return false;
        else{
            animals.add(animal);
            return true;
        }
    }

    @Override
    public void run(MoveDirection[] directions) {
        int numberOfAnimals=animals.size();
        for(int i=0; i<directions.length; i++){
            animals.get(i%numberOfAnimals).move(directions[i]);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for(Animal pet: animals){
            if (pet.getPosition().equals(position)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for(Animal pet: animals){
            if (position.equals(pet.getPosition())){
                return pet;
            }
        }
        return null;
    }

    public String toString() {
        return new MapVisualizer(this).draw(lowerLimit, upperLimit);
    }
}
