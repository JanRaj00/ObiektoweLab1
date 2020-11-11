package agh.cs.lab1;

import java.util.*;
import java.lang.Math;

public class GrassField extends AbstractWorldMap {
    public final int fieldsNumber;
    List<Grass> grasses;
    Map<Vector2d, Grass> grassElements = new HashMap<>();

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
            }
        }

    }

    @Override
    public boolean canMoveTo(Vector2d position){
        if(this.elements.get(position)==null) return true;
        else return false;
    }


    @Override
    public Object objectAt(Vector2d position) {
        if(this.elements.get(position)!=null){
            return this.elements.get(position);
        }
        else
            return this.grassElements.get(position);
    }


   @Override
    public Vector2d getLowerLeft(){
        int n = (int) Math.sqrt(this.fieldsNumber);
        Vector2d lower = new Vector2d(n, n);
        for(Animal pet: animals) {
            Vector2d newPosition = pet.getPosition();
            lower = lower.lowerLeft(newPosition);
        }
        for(Grass g: grasses) {
            Vector2d newPosition = g.getPosition();
            lower = lower.lowerLeft(newPosition);
        }
        return lower;
    }

    @Override
    public Vector2d getUpperRight(){
        Vector2d upper=new Vector2d(0, 0);
        for(Animal pet: animals){
            Vector2d newPosition = pet.getPosition();
            upper=upper.upperRight(newPosition);
        }
        for(Grass g: grasses) {
            Vector2d newPosition = g.getPosition();
            upper=upper.upperRight(newPosition);
        }
        return upper;
    }

}
