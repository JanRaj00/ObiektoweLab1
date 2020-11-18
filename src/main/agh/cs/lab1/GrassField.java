package agh.cs.lab1;

import java.util.*;
import java.lang.Math;

public class GrassField extends AbstractWorldMap{
    public final int fieldsNumber;
    List<Grass> grasses;
    Map<Vector2d, Grass> grassElements = new HashMap<>();
    MapBoundary mapElements = new MapBoundary();

    public GrassField(int n){
        grasses = new ArrayList<>();
        this.fieldsNumber=n;
        generateFields();
    }


    public void generateFields(){
        int i=0;
        Random rnd = new Random();
        int maxDraw = (int) Math.sqrt(this.fieldsNumber*10);
        //System.out.println("xd");
        while(i<this.fieldsNumber){
            Vector2d newPosition = new Vector2d(rnd.nextInt(maxDraw), rnd.nextInt(maxDraw));
            if(this.grassElements.get(newPosition)==null){
                Grass g = new Grass(newPosition);
                this.grasses.add(g);
                this.grassElements.put(newPosition, g);
                i++; //jezeli pole nie jest zajete to idziemy dalej
                mapElements.place(g);
            }
        }

    }

    @Override
    public boolean canMoveTo(Vector2d position){
        if(this.elements.get(position)==null) return true;
        else return false;
    }

    @Override
    public boolean place(Animal animal) throws IllegalArgumentException{
        super.place(animal);
        mapElements.place(animal);
        return true;
    }

    public Object objectAt(Vector2d position) {
        Object object = super.objectAt(position);
        if(object!=null){
            return object;
        }
        else{
            return grassElements.get(position);
        }
    }


    public Vector2d getLowerLeft(){
        return mapElements.getBoundaryLeft();
    }

    public Vector2d getUpperRight(){
        return mapElements.getBoundaryRight();
    }
}
