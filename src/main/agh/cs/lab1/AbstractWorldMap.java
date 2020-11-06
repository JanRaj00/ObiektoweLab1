package agh.cs.lab1;

import java.util.ArrayList;
import java.util.List;

//dodanei interfejsu IMapElement mogloby utrudniac implementacje niektorych operacji,
//gdzie musielibysmy sprawdzac, czy element ktory znajduje sie na danym polu to zwierze,
//czyli we wszytskich tych metodach ktore dotyczaca samych zwierzat

abstract class AbstractWorldMap implements IWorldMap {
    protected List<Animal> animals;

    public AbstractWorldMap() {
        animals = new ArrayList<>();
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animals.add(animal);
            return true;
        } else
            return false;
    }

    @Override
    public void run(MoveDirection[] directions) {
        int numberOfAnimals = animals.size();
        for (int i = 0; i < directions.length; i++) {
            animals.get(i % numberOfAnimals).move(directions[i]);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (objectAt(position) != null) return true;    // polecam: return (objectAt(position) != null);
        else return false;
    }

    public abstract Vector2d getLowerLeft();

    public abstract Vector2d getUpperRight();

    @Override
    public String toString() {
        return new MapVisualizer(this).draw(getLowerLeft(), getUpperRight());   // nie ma sensu tworzyć visualizera co wywołanie
    }

}
