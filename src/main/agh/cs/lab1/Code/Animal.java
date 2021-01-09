package agh.cs.lab1.Code;   // a inne pakiety nie zawierają kodu? + pakiety małymi literami


import java.util.*;

public class Animal implements IMapElement {
    private Vector2d position;
    private MapDirection orientation = MapDirection.NORTH;
    private Planet map;
    private List<IPositionChangeObserver> positionObservers;
    private List<IEnergyChangeObserver> energyObservers;
    private int startEnergy;
    private int energy;
    private int moveEnergy;
    private Genome genome;  // nie może być finalne?
    private int childrenNumber = 0;
    private int age; //w dniach
    public long number = 0;   // public?
    public static long nextNumber = 0;    // j.w.

    public Animal(Planet map, Vector2d initialPosition, int startEnergy, int moveEnergy, int initialEnergy) {
        this.position = initialPosition;
        this.map = map;
        this.positionObservers = new LinkedList<>();
        this.energyObservers = new LinkedList<>();
        this.genome = new Genome();
        this.startEnergy = startEnergy;
        this.energy = initialEnergy;
        this.moveEnergy = moveEnergy;
        this.number = nextNumber;
        nextNumber++;
        this.age = 0;
        this.map.placeAnimal(new AnimalWithEnergy(this, this.energy));
    }

    public void addPositionObserver(IPositionChangeObserver observer) {
        positionObservers.add(observer);
    }

    public void removePositionObserver(IPositionChangeObserver observer) {
        positionObservers.remove(observer);
    }

    public void addEnergyObserver(IEnergyChangeObserver observer) {
        energyObservers.add(observer);
    }

    public void removeEnergyObserver(IEnergyChangeObserver observer) {
        energyObservers.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for (IPositionChangeObserver observer : positionObservers) {
            observer.positionChanged(oldPosition, newPosition, this);
        }
    }

    private void energyChanged(int oldEnergy, int newEnergy) {
        for (IEnergyChangeObserver observer : energyObservers) {
            observer.energyChanged(oldEnergy, newEnergy, this);
        }
    }

    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getOrientation() {
        return orientation;
    }


    @Override
    public String toString() {
        return orientation.toString();
    }

    public void move() {
        int rotation = this.genome.getRand();
        for (int i = 0; i < rotation; i++) {
            this.orientation = this.orientation.next();
        }
        Vector2d oldPosition = this.getPosition();
        Vector2d newPosition = this.position.add(Objects.requireNonNull(this.orientation.toUnitVector()));
        newPosition = map.canMoveTo(newPosition);
        this.position = newPosition;
        this.positionChanged(oldPosition, newPosition);
        this.changeEnergy(-this.moveEnergy);
        this.age += 1;
    }

    public void changeEnergy(int value) {
        int oldEnergy = this.getEnergy();
        this.energy += value;
        this.energyChanged(oldEnergy, this.energy);
    }


    public void procreate(Animal secondParent) {
        if (this.getEnergy() > (int) (0.5 * this.startEnergy) && secondParent.getEnergy() > (int) (0.5 * this.startEnergy)) {
            Vector2d childField = this.map.generateFieldForChild(this);
            Genome childGenome = new Genome();
            childGenome.mixGenes(this.genome, secondParent.genome); // nie wygodniejszy byłby konstruktor z dwoma parametrami?
            int firstEnergy = (int) (this.getEnergy() / 4);
            int secondEnergy = (int) (secondParent.getEnergy() / 4);
            int childEnergy = firstEnergy + secondEnergy;
            this.changeEnergy((-1) * firstEnergy);
            secondParent.changeEnergy((-1) * secondEnergy);
            Animal child = new Animal(map, childField, startEnergy, moveEnergy, childEnergy);
            this.addChildren();
            secondParent.addChildren();
        }
    }

    public void addChildren() {
        this.childrenNumber++;
    }

    public Genome getGenome() {
        return this.genome;
    }

    //STATYSTYKI
    public boolean isAlive() {
        return this.getEnergy() > 0;
    }

    public int getChildrenNumber() {
        return this.childrenNumber;
    }

    public int getAge() {
        return this.age;
    }

    public int getEnergy() {
        return this.energy;
    }


}
