package agh.cs.lab1;

import org.javatuples.*;

import java.io.PrintStream;
import java.util.*;

public class Planet implements IEnergyChangeObserver, IPositionChangeObserver{
    private final Map<Vector2d, Field> fields = new HashMap<>();
    private final HashMap<Vector2d, Plant> plants= new HashMap<>();
    private final Vector2d lowerLeft;
    private final Vector2d upperRight;
    private final double jungleRatio;
    private final Vector2d jungleLowerLeft;
    private final Vector2d jungleUpperRight;
    private final int plantEnergy;
    public int numberOfAnimals;


    public Planet(int width, int height, double jungleRatio, int plantEnergy) {
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width, height);
        this.jungleRatio = jungleRatio;
        int horizontal = (int) (jungleRatio * width);
        int vertical = (int) (jungleRatio * height);
        int jungleLowerX = (width - horizontal) / 2;
        int jungleLowerY = (height - vertical) / 2;
        this.jungleLowerLeft = new Vector2d(jungleLowerX, jungleLowerY);
        this.jungleUpperRight = new Vector2d(jungleLowerX + horizontal, jungleLowerY + vertical);
        this.plantEnergy = plantEnergy;
        this.numberOfAnimals=0;
    }

    public Vector2d generateRandomVector(boolean inTheJungle) {
        Random rand = new Random();
        if (inTheJungle) {
            int xRange = this.jungleUpperRight.x - this.jungleLowerLeft.x;
            int yRange = this.jungleUpperRight.y - this.jungleLowerLeft.y;
            return new Vector2d(rand.nextInt(xRange) + jungleLowerLeft.x,
                    rand.nextInt(yRange) + jungleLowerLeft.y);
        } else {
            int xRange = this.upperRight.x - this.lowerLeft.x;
            int yRange = this.upperRight.y - this.lowerLeft.y;
            int x = rand.nextInt(xRange);
            if (x < this.jungleLowerLeft.x || x > this.jungleUpperRight.x) {
                int y = rand.nextInt(yRange);
                return new Vector2d(x, y);
            }
            else {
                int p = rand.nextInt(2);
                if (p == 0) {
                    int y = rand.nextInt(this.jungleLowerLeft.y);
                    return new Vector2d(x, y);
                } else {
                    int y = rand.nextInt(this.jungleLowerLeft.y) + this.jungleUpperRight.y;
                    return new Vector2d(x, y);
                }
            }
        }
    }

    public Vector2d generateFieldForChild(Animal animal){
        Vector2d position = animal.getPosition();
        for(int i=-1; i<=1; i++){
            for(int j=-1; j<=1; j++){
                Vector2d newPosition=position.add(new Vector2d(i, j));
                if(this.fields.get(newPosition)==null){
                    return newPosition;
                }
            }
        }
        Random rand = new Random();
        while(true){
            int x=rand.nextInt(this.upperRight.x-this.lowerLeft.x);
            int y=rand.nextInt(this.upperRight.y-this.lowerLeft.y);
            Vector2d newPosition = new Vector2d(x, y);
            if(this.fields.get(newPosition)==null){
                return newPosition;
            }
        }
    }

    public void placePlant(boolean inTheJungle) {
        Vector2d position = new Vector2d(0, 0);
        if (inTheJungle) {
            position = generateRandomVector(true);
        } else {
            position = generateRandomVector(false);
        }
        while (true) {
            if (plants.get(position) == null) {
                Plant plant = new Plant(position, this.plantEnergy);
                plants.put(position, plant);
                break;
            } else {
                if (inTheJungle) position = generateRandomVector(true);
                else position = generateRandomVector(false);
            }
        }
    }

    public void placeAnimal(AnimalWithEnergy animalWithEnergy) {
        addAnimalToField(animalWithEnergy);
        animalWithEnergy.animal.addPositionObserver(this);
        animalWithEnergy.animal.addEnergyObserver(this);
    }

    public void addAnimalToField(AnimalWithEnergy animalWithEnergy){
        Vector2d position = animalWithEnergy.animal.getPosition();
        if (fields.get(position) == null) {
            Field field = new Field(animalWithEnergy);
            fields.put(position, field);
        } else {
            fields.get(position).addAnimal(animalWithEnergy);
        }
        numberOfAnimals++;
    }

    public void removeAnimalFromAField(AnimalWithEnergy animalWithEnergy, Vector2d position){
        Field field = fields.get(position);
        field.removeAnimal(animalWithEnergy);
        if(field.getSize()==0) fields.remove(position, field);
        numberOfAnimals--;
    }

    public void makeFunerals() {
        Collection<Field> fieldsValues = fields.values();
        List<AnimalWithEnergy> allDeadAnimals = new LinkedList<>();
        for(Field field: fieldsValues){
            List<AnimalWithEnergy> deadAnimals = field.deadAnimalsOnField();
            for(AnimalWithEnergy animalWithEnergy: deadAnimals) {
                allDeadAnimals.add(animalWithEnergy);
            }
        }
        for(AnimalWithEnergy animalWithEnergy: allDeadAnimals){
            this.removeAnimalFromAField(animalWithEnergy, animalWithEnergy.animal.getPosition());
        }
    }

    public Vector2d canMoveTo(Vector2d position){
        int x = position.x;
        int y = position.y;
        if(position.x <= this.lowerLeft.x) x=this.upperRight.x;
        if(position.x >= this.upperRight.x) x=this.lowerLeft.x;
        if(position.y <= this.lowerLeft.y) y=this.upperRight.y;
        if(position.y >= this.upperRight.y) y=this.lowerLeft.y;
        return new Vector2d(x, y);
    }

    public void moveAnimals(){
        Collection<Field> fieldsValues = fields.values();
        for (Field field: fieldsValues){
            field.moveAnimals();
        }
    }

    public boolean isOccupied(Vector2d position){
        if(this.fields.get(position)==null) return false;
        else return true;
    }

    public void feedAnimals() {
        Collection<Plant> plantsValues = plants.values();
        List<Plant> plantsToRemoves = new LinkedList<>();
        for (Plant plant : plantsValues) {
            Vector2d position = plant.position;
            Field field = fields.get(position);
            if (field.getSize() > 0) {
                List<AnimalWithEnergy> bestAnimals = field.getBestAnimals();
                int energyPortion = (int) (plant.getPlantEnergy()/bestAnimals.size());
                for(AnimalWithEnergy animalWithEnergy: bestAnimals){
                    animalWithEnergy.animal.changeEnergy(energyPortion);
                }
                plantsToRemoves.add(plant);
            }
        }
        for (Plant plant: plantsToRemoves){
            plants.remove(plant.position, plant);
        }
    }

    public void procreateAnimal(){
        Collection<Field> fieldsValues = fields.values();
        for (Field field: fieldsValues){
            if(field.getSize()>1){
                Pair<AnimalWithEnergy, AnimalWithEnergy> parents=field.getParents();
                parents.getValue0().animal.procreate(parents.getValue1().animal);
            }
        }
    }


    @Override
    public void energyChanged(int oldEnergy, int newEnergy, Animal animal) {
        Vector2d position = animal.getPosition();
        AnimalWithEnergy dog = new AnimalWithEnergy(animal, oldEnergy);
        AnimalWithEnergy cat = new AnimalWithEnergy(animal, newEnergy);
        this.removeAnimalFromAField(dog, position);
        addAnimalToField(cat);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal){
        AnimalWithEnergy cat = new AnimalWithEnergy(animal, animal.getEnergy());
        this.removeAnimalFromAField(cat, oldPosition);
        this.addAnimalToField(cat);
    }

    //STATYSTYKI
    public int aliveAnimals(){ return this.numberOfAnimals;}
    public int getSize(){return this.upperRight.x*this.upperRight.y; }

}