package agh.cs.lab1.Code;


public class AnimalWithEnergy implements Comparable<AnimalWithEnergy> { // nie lepiej dodać energię do animala? Albo przynajmniej dziedziczyć?
    public final Animal animal;
    public final int energy;

    public AnimalWithEnergy(Animal animal, int energy) {
        this.animal = animal;
        this.energy=energy;
    }

    @Override
    public int compareTo(AnimalWithEnergy other) {
        return this.energy == other.energy ? Long.compare(other.animal.number, this.animal.number) : other.energy - this.energy;
    }
}
