package agh.cs.lab1;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.Math;

public class GrassField extends AbstractWorldMap {
    public final int fieldsNumber;  // proszę przemyśleć, czy to jest część interfejsu
    List<Grass> grasses;

    public GrassField(int n){
        grasses = new ArrayList<>();
        this.fieldsNumber=n;
        generateFields();
    }


    public void generateFields(){
        int i=0;
        while(i<this.fieldsNumber){
            Random rnd = new Random();  // dlaczego tworzy Pan nowy generator co obrót pętli?
            int maxDraw = (int) Math.sqrt(this.fieldsNumber*10);    // j.w.
            Vector2d newPosition = new Vector2d(rnd.nextInt(maxDraw), rnd.nextInt(maxDraw));
            boolean isPlaced = false; //znacznik mowiacy czy pole jest zajete
            for (Grass g: grasses) {
                if(g.getPosition().equals(newPosition)) {
                    isPlaced = true;
                    break;
                }
            }
            if(!isPlaced){
                grasses.add(new Grass(newPosition));
                i++; //jezeli pole nie jest zajete to idziemy dalej
            }
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        for (Animal pet: animals){
            if(pet.getPosition().equals(position)){
                return false;
            }
        }
        return true;
    }


    @Override
    public Object objectAt(Vector2d position) {
        for(Animal pet: animals) {
            if (position.equals(pet.getPosition())) {
                return pet;
            }
        }
        for(Grass g: grasses){
            if(position.equals(g.getPosition())){
                return g;
            }
        }
        return null;
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
