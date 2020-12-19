package agh.cs.lab1;

import org.javatuples.Pair;

import java.util.*;

public class Planet implements IEnergyChangeObserver, IPositionChangeObserver{
    private final Map<Vector2d, Field> fields = new HashMap<>();
    private final HashMap<Vector2d, Plant> plants= new HashMap<>();
    private final List<AnimalWithEnergy> animals = new LinkedList<>();
    private final Vector2d lowerLeft;
    private final Vector2d upperRight;
    private final double jungleRatio;
    private final Vector2d jungleLowerLeft;
    private final Vector2d jungleUpperRight;
    private final int plantEnergy;


    public Planet(int width, int height, double jungleRatio, int plantEnergy) {
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width-1, height-1);
        this.jungleRatio = jungleRatio;
        int horizontal = (int) (jungleRatio * width);
        int vertical = (int) (jungleRatio * height);
        int jungleLowerX = (width - horizontal) / 2;
        int jungleLowerY = (height - vertical) / 2;
        this.jungleLowerLeft = new Vector2d(jungleLowerX, jungleLowerY);
        this.jungleUpperRight = new Vector2d(jungleLowerX + horizontal, jungleLowerY + vertical);
        this.plantEnergy = plantEnergy;
        generateMap();
    }

    public void generateMap(){
        for(int i=0; i<this.upperRight.x; i++) {
            for(int j=0; i<this.upperRight.y; j++){
                Vector2d position = new Vector2d(i, j);
                Field field = new Field();
                this.fields.put(position, field);
            }
        }
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
                if(this.fields.get(newPosition).getSize()==0){
                    return newPosition;
                }
            }
        }
        Random rand = new Random();
        while(true){
            int x=rand.nextInt(this.upperRight.x-this.lowerLeft.x);
            int y=rand.nextInt(this.upperRight.y-this.lowerLeft.y);
            Vector2d newPosition = new Vector2d(x, y);
            if(this.fields.get(newPosition).getSize()==0){
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
            if (plants.get(position).getPlantEnergy()==0) {
                plants.get(position).changePlantEnergy(this.plantEnergy);
            } else {
                if (inTheJungle) position = generateRandomVector(true);
                else position = generateRandomVector(false);
            }
        }
    }

    public void placeAnimal(AnimalWithEnergy animalWithEnergy) {
        Vector2d position = animalWithEnergy.animal.getPosition();
        this.fields.get(position).addAnimal(animalWithEnergy);
        animalWithEnergy.animal.addPositionObserver(this);
        animalWithEnergy.animal.addEnergyObserver(this);
        animals.add(animalWithEnergy);
    }


    public void makeFunerals() {
        List<AnimalWithEnergy> deadAnimals=new LinkedList<>();
        for(AnimalWithEnergy animalWithEnergy: animals){
            if(!animalWithEnergy.animal.isAlive()){
                deadAnimals.add(animalWithEnergy);
            }
        }
        int numberOfDeadAnimals=deadAnimals.size();
        for(AnimalWithEnergy animalWithEnergy: deadAnimals){
            animals.remove(animalWithEnergy);
            fields.get(animalWithEnergy).removeAnimal(animalWithEnergy);
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
        for(AnimalWithEnergy animalWithEnergy: animals){
            animalWithEnergy.animal.move();
        }
    }

    public void feedAnimals() {
        Collection<Plant> plantsValues = plants.values();
        for (Plant plant : plantsValues) {
            if (plant.getPlantEnergy() != 0) {
                Vector2d position = plant.position;
                Field field = fields.get(position);
                if (field.getSize() > 0) {
                    List<AnimalWithEnergy> bestAnimals = field.getBestAnimals();
                    int energyPortion = (int) (plant.getPlantEnergy() / bestAnimals.size());
                    for (AnimalWithEnergy animalWithEnergy : bestAnimals) {
                        animalWithEnergy.animal.changeEnergy(energyPortion);
                    }
                    plant.changePlantEnergy(0);
                }
            }
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
        this.fields.get(position).removeAnimal(dog);
        this.fields.get(position).addAnimal(cat);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal){
        AnimalWithEnergy cat = new AnimalWithEnergy(animal, animal.getEnergy());
        this.fields.get(oldPosition).removeAnimal(cat);
        this.fields.get(newPosition).addAnimal(cat);
    }

    //STATYSTYKI
    public int aliveAnimals(){ return this.animals.size();}
    public int getSize(){return this.upperRight.x*this.upperRight.y; }

}