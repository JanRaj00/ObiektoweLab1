package agh.cs.lab1.Code;

import org.javatuples.Pair;

import java.util.*;

public class Field {
    private final SortedSet <AnimalWithEnergy> animals;
    private int numberOfAnimals;
    public Field() {
        this.animals = new TreeSet<AnimalWithEnergy>();
        numberOfAnimals = 0;
    }

    public void addAnimal(AnimalWithEnergy animalWithEnergy){
        this.animals.add(animalWithEnergy);
        numberOfAnimals++;
    }

    public void removeAnimal(AnimalWithEnergy animalWithEnergy){
        this.animals.remove(animalWithEnergy);
        numberOfAnimals--;
    }

    public int getSize(){ return numberOfAnimals;}

    public List<AnimalWithEnergy> getBestAnimals(){
        int bestEnergy = this.animals.first().animal.getEnergy();
        List<AnimalWithEnergy> bestAnimals = new ArrayList<AnimalWithEnergy>();
        for(AnimalWithEnergy animal: animals){
            if(animal.animal.getEnergy()!=bestEnergy) break;
            bestAnimals.add(animal);
        }
        return bestAnimals;
    }

    public Pair<AnimalWithEnergy, AnimalWithEnergy> getParents(){
        Random rand = new Random();
        List<AnimalWithEnergy> bests=getBestAnimals();
        if(bests.size()==1){
            AnimalWithEnergy parent1 = bests.get(0);
            animals.remove(parent1);
            bests=getBestAnimals();
            int p= rand.nextInt(bests.size());
            AnimalWithEnergy parent2 = bests.get(p);
            animals.add(parent1);
            return new Pair<>(parent1, parent2);
        }
        else{
            int p=rand.nextInt(bests.size());
            AnimalWithEnergy parent1 = bests.get(p);
            bests.remove(parent1);
            int q= rand.nextInt(bests.size());
            AnimalWithEnergy parent2 = bests.get(q);
            return new Pair<>(parent1, parent2);
        }
    }

    public Animal getBestAnimal(){
        return this.animals.first().animal;
    }
}
