package agh.cs.lab1;

import java.util.*;
import java.util.List;


public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected List<Animal> animals;
    protected Map<Vector2d, Animal> elements = new HashMap<>();
    public AbstractWorldMap() {
        animals = new ArrayList<>();
    }

    public boolean canMoveTo(Vector2d position){
        return !this.isOccupied(position);
    }

    public boolean place(Animal animal) throws IllegalArgumentException{
        if(this.canMoveTo(animal.getPosition())){
            this.animals.add(animal);
            this.elements.put(animal.getPosition(), animal);
            animal.addObserver(this);
            return true;
        }
        else
            throw new IllegalArgumentException(animal.getPosition() + " is illegal position to place.");
    }

    public boolean isOccupied(Vector2d position) {
        return this.objectAt(position)!=null;
    }

    public Object objectAt(Vector2d position){
        return elements.get(position);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal) {
        Animal a = elements.get(oldPosition);
        elements.remove(oldPosition);
        elements.put(newPosition, a);
    }

    public void run(MoveDirection[] directions) { //*według mnie nie jest zasadnym użycie values(), ponieważ
        int numberOfAnimals=animals.size(); // mamy trzymaną liste zwierząt osobno i to korzystając z niej
        for(int i=0; i<directions.length; i++){ // możemy poruszać sie zwierzetami po mapie
            int index = i%numberOfAnimals;
            Animal dog = animals.get(index);
            dog.move(directions[i]);
        }
    }


    public abstract Vector2d getLowerLeft();
    public abstract Vector2d getUpperRight();

    public String toString(){
        MapVisualizer visualize=new MapVisualizer(this);    // czy to trzeba tworzyć co wywołanie?
        return visualize.draw(getLowerLeft(), getUpperRight());
    }

}
