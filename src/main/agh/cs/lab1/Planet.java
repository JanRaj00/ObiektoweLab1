package agh.cs.lab1;

import org.javatuples.*;
import java.util.*;

public class Planet implements IWorldMap{
    private List<AnimalWithEnergy> animals = new ArrayList<>();
    private Map<Vector2d, Field> fields = new HashMap<>();
    private final Vector2d lowerLeft;
    private final Vector2d upperRight;
    private final double jungleRatio;
    private final Vector2d jungleLowerLeft;
    private final Vector2d jungleUpperRight;
    private int plantEnergy;


    public Planet(int width, int height, double jungleRatio, int plantEnergy) {
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width, height);
        this.jungleRatio = jungleRatio;
        int horizontal = (int) jungleRatio * width;
        int vertical = (int) jungleRatio * height;
        int jungleLowerX = (width - horizontal) / 2;
        int jungleLowerY = (height - vertical) / 2;
        this.jungleLowerLeft = new Vector2d(jungleLowerX, jungleLowerY);
        this.jungleUpperRight = new Vector2d(jungleLowerX + horizontal, jungleLowerY + vertical);
        this.plantEnergy = plantEnergy;
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
            } else {
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
                if(this.fields.get(newPosition)!=null){
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
            if (fields.get(position) == null) {
                Field field = new Field();
                Plant plant = new Plant(position, this.plantEnergy);
                field.addPlant(plant);
                fields.put(position, field);
                break;
            } else {
                if (inTheJungle) position = generateRandomVector(true);
                else position = generateRandomVector(false);
            }
        }
    }

    public void placeAnimal(Animal animal) {
        Vector2d position = animal.getPosition();
        AnimalWithEnergy dog = new AnimalWithEnergy(animal, animal.getEnergy());
        if (fields.get(position) == null) {
            Field field = new Field();
            field.addAnimal(dog);
            fields.put(position, field);
        } else {
            fields.get(position).addAnimal(dog);
        }
        animals.add(dog);
    }

    public void makeFunerals() {
        for (AnimalWithEnergy dog : animals) {
            if (!dog.animal.isAlive()) {
                animals.remove(dog);
                Field field = fields.get(dog.animal.getPosition());
                field.removeAnimal(dog);
                if (field.getSize() == 0 && field.getPlant() == null) {
                    fields.remove(field);
                }
            }
        }
    }

    public boolean canMoveTo(Vector2d position) {
        return position.follows(lowerLeft) && position.precedes(upperRight);
    }

    public void moveAnimals(){
        for(AnimalWithEnergy dog: animals){
            dog.animal.move();
        }
    }

    public void feedAnimals() {
        Collection<Field> fieldsValues = fields.values();
        for (Field field : fieldsValues) {
            Plant plant=field.getPlant();
            if (plant != null) {
                if (field.getSize() > 0) {
                    List<AnimalWithEnergy> bestAnimals = field.getBestAnimals();
                    int energyPortion = plant.getPlantEnergy()/bestAnimals.size();
                    for(AnimalWithEnergy animal: bestAnimals){
                        animal.animal.changeEnergy(energyPortion);
                    }
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

    public int aliveAnimals(){
        return this.animals.size();
    }

}