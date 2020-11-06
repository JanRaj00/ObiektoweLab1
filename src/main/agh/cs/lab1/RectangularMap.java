package agh.cs.lab1;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap extends AbstractWorldMap{
    public final Vector2d upperLimit;   // czemu public?
    public final Vector2d lowerLimit;

    public RectangularMap(int width, int height){
        this.lowerLimit = new Vector2d(0, 0);
        this.upperLimit = new Vector2d(width, height);  // width - 1
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        Vector2d upp = position.upperRight(upperLimit);
        Vector2d low = position.lowerLeft(lowerLimit);
        if(upp.precedes(upperLimit) && low.follows(lowerLimit))
            return(!isOccupied(position));
        else
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

    @Override
    public Vector2d getLowerLeft(){
        return lowerLimit;
    }

    @Override
    public Vector2d getUpperRight(){
        return upperLimit;
    }

}
