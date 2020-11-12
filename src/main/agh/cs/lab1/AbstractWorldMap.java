package agh.cs.lab1;

import java.util.*;
import java.util.List;

//dodanie interfejsu IMapElement mogloby utrudniac implementacje niektorych operacji,
//gdzie musielibysmy sprawdzac, czy element ktory znajduje sie na danym polu to zwierze,
//czyli we wszytskich tych metodach ktore dotyczaca samych zwierzat

abstract class AbstractWorldMap implements IWorldMap{
    protected List<Animal> animals;
    protected Map<Vector2d, Animal> elements = new HashMap<>();
    public AbstractWorldMap() {
        animals = new ArrayList<>();
    }

    @Override
    public boolean place(Animal animal){
        if(this.canMoveTo(animal.getPosition())){
            this.animals.add(animal);
            this.elements.put(animal.getPosition(), animal);
            return true;
        }
        else
            throw new IllegalArgumentException(animal.getPosition() + " is illegal position to place.");
    }

    @Override
    public void run(MoveDirection[] directions) { //*według mnie nie jest zasadnym użycie values(), ponieważ
        int numberOfAnimals=animals.size(); // mamy trzymaną liste zwierząt osobno i to korzystając z niej
        for(int i=0; i<directions.length; i++){ // możemy poruszać sie zwierzetami po mapie
            int index = i%numberOfAnimals;
            Animal dog = animals.get(index);
            Vector2d oldPosition = dog.getPosition();
            dog.move(directions[i]);
            Vector2d newPosition = dog.getPosition();
            if(!oldPosition.equals(newPosition)){
                elements.remove(oldPosition);
                elements.put(newPosition, dog);
            }
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.objectAt(position)!=null;
    }

    public abstract Vector2d getLowerLeft();
    public abstract Vector2d getUpperRight();

  @Override
    public String toString(){
        MapVisualizer visualize=new MapVisualizer(this);    // nie warto tego tworzyć co wywołanie
        return visualize.draw(getLowerLeft(), getUpperRight());
    }

}
