package agh.cs.lab1;


import java.util.Comparator;

public class AnimalWithEnergy implements Comparable<AnimalWithEnergy> {
    public final Animal animal;
    public final int energy;

    public AnimalWithEnergy(Animal animal, int energy) {
        this.animal = animal;
        this.energy=energy;
    }

    @Override
    public int compareTo(AnimalWithEnergy other) {
        return this.energy == other.energy ? other.animal.number - this.animal.number : other.energy - this.energy;
    }
}
