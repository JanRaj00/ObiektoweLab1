package agh.cs.lab1;

import java.util.SortedSet;
import java.util.TreeSet;
public class MapBoundary implements IPositionChangeObserver{
    SortedSet<IMapElement> setX = new TreeSet<IMapElement>(new elementComparatorX());
    SortedSet<IMapElement> setY = new TreeSet<IMapElement>(new elementComparatorY());

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal){
        if(oldPosition.x!= newPosition.x) {
            setX.remove(animal);
            setX.add(animal);
        }
        if(oldPosition.y!= newPosition.y) {
            setY.remove(animal);
            setY.add(animal);
        }
    }

    public void place(IMapElement element){
        setX.add(element);
        setY.add(element);
        if(element instanceof Animal) {
            ((Animal) element).addObserver(this);
        }
    }

    public Vector2d getBoundaryLeft(){  // mapa ma dwa lewe rogi
            return new Vector2d(setX.first().getPosition().x, setY.first().getPosition().y);
    }

    public Vector2d getBoundaryRight() {
            return new Vector2d(setX.last().getPosition().x, setY.last().getPosition().y);
    }
}
