package agh.cs.lab1;


import java.util.*;

public class Animal implements IMapElement{
    private Vector2d position;
    private MapDirection orientation=MapDirection.NORTH;
    private Planet map;
    private List<IPositionChangeObserver> positionObservers;
    private List<IEnergyChangeObserver> energyObservers;
    private int startEnergy;
    private int energy;
    private int moveEnergy;
    private Genome genome;
    private int childrenNumber=0;
    private int age; //w dniach
    public static int number=0;

    public Animal(Planet map, Vector2d initialPosition, int startEnergy, int moveEnergy){
        this.position = initialPosition;
        this.map=map;
        this.positionObservers=new LinkedList<>();
        this.energyObservers=new LinkedList<>();
        this.genome=new Genome();
        this.startEnergy=startEnergy;
        this.energy=startEnergy;
        this.moveEnergy=moveEnergy;
        this.number=number++;
    }

    public void addPositionObserver(IPositionChangeObserver observer){
        positionObservers.add(observer);
    }

    public void removePositionObserver(IPositionChangeObserver observer){ positionObservers.remove(observer); }

    public void addEnergyObserver(IEnergyChangeObserver observer){energyObservers.add(observer);}

    public void removeEnergyObserver(IEnergyChangeObserver observer){energyObservers.remove(observer);}

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for(IPositionChangeObserver observer: positionObservers){
            observer.positionChanged(oldPosition, newPosition, this);
        }
    }

    private void energyChanged(int oldEnergy, int newEnergy) {
        for(IEnergyChangeObserver observer: energyObservers){
            observer.energyChanged(oldEnergy, newEnergy, this);
        }
    }

    public Vector2d getPosition(){ return position; }

    public MapDirection getOrientation(){ return orientation; }


    @Override
    public String toString() { return orientation.toString(); }

    public void move(){
        int rotation=this.genome.getRand();
        for(int i=0; i<rotation; i++){
            this.orientation=this.orientation.next();
        }
        Vector2d oldPosition = this.getPosition();
        Vector2d newPosition=this.position.add(this.orientation.toUnitVector());
        if (map.canMoveTo(newPosition)) {
            this.position.add(this.orientation.toUnitVector());
            this.positionChanged(oldPosition, newPosition);
        }
        this.changeEnergy(-this.moveEnergy);
        this.age+=1;
    }

    public void changeEnergy(int value){
        int oldEnergy=this.energy;
        this.energy+=value;
        this.energyChanged(oldEnergy, this.energy);
    }


    public void procreate(Animal secondParent){
        if(this.energy>(int) (0.5*this.startEnergy) && secondParent.getEnergy()>(int) (0.5*this.startEnergy)){
            Vector2d childField=this.map.generateFieldForChild(this);
            Genome childGenome = new Genome();
            childGenome.mixGenes(this.genome, secondParent.genome);
            int firstEnergy=(int)(0.25)*this.energy;
            int secondEnergy=(int)(0.25)*secondParent.getEnergy();
            int childEnergy=firstEnergy+secondEnergy;
            this.changeEnergy(-firstEnergy);
            secondParent.changeEnergy(-secondEnergy);
            Animal child = new Animal(map, childField, childEnergy, moveEnergy);
        }
    }

    //STATYSTYKI
    public boolean isAlive() {return this.energy>0;}
    public int getChildrenNumber() { return this.childrenNumber; }
    public int getAge() { return this.age; }
    public int getEnergy() {return this.energy; }



}
