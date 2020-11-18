package agh.cs.lab1;

import java.util.*;

public class Animal implements IMapElement{
    private Vector2d position;
    private MapDirection orientation=MapDirection.NORTH;
    private IWorldMap map;
    private List<IPositionChangeObserver> observers=new ArrayList<>();

    public Animal(IWorldMap map){
        this.position=new Vector2d(2,2);
        this.map=map;
        this.observers=new LinkedList<>();
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.position = initialPosition;
        this.map=map;
        this.observers=new LinkedList<>();
    }

    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for(IPositionChangeObserver observer: observers){
            observer.positionChanged(oldPosition, newPosition, this);
        }
    }

    public Vector2d getPosition(){
        return position;
    }

    public MapDirection getOrientation(){
        return orientation;
    }

    @Override
    public String toString() {
        return orientation.toString();
    }

    public void move(MoveDirection direction){
        switch(direction){
            case RIGHT:
                this.orientation=this.orientation.next();
                break;

            case LEFT:
                this.orientation=this.orientation.previous();
                break;

            case FORWARD:
                Vector2d newPosition = this.position.add(this.orientation.toUnitVector());
                if(map.canMoveTo(newPosition)) {
                    Vector2d oldPosition = this.getPosition();
                    this.position = newPosition;
                    this.positionChanged(oldPosition, newPosition);
                }
                break;

            case BACKWARD:
                newPosition = this.position.subtract(this.orientation.toUnitVector());
                if(map.canMoveTo(newPosition)) {
                    Vector2d oldPosition = this.getPosition();
                    this.position = newPosition;
                    this.positionChanged(oldPosition, newPosition);
                }
                break;

            default:
                throw new IllegalArgumentException("Illegal direction: " + this);
        }
    }

}
