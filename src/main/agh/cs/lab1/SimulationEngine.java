package agh.cs.lab1;

public class SimulationEngine{
    private Planet map;
    private Configuration configuration;

    public SimulationEngine(Configuration config){
        this.configuration=config;
        this.map = new Planet(config.width, config.height, config.jungleRatio, config.plantEnergy);
    }
    public void prepAMap(){
        int numberOfStartAnimals = configuration.startAnimals;
        while(numberOfStartAnimals>0) {
            int startEne = configuration.startEnergy;
            int moveEne = configuration.moveEnergy;
            if(numberOfStartAnimals%6==0) {
                Animal animal = new Animal(map, map.generateRandomVector(true), startEne, moveEne, startEne);
            }
            else{
                Animal animal = new Animal(map, map.generateRandomVector(false), startEne, moveEne, startEne);
            }
            numberOfStartAnimals--;
        }
    }
    public void nextDay(){
        this.map.makeFunerals();
        this.map.moveAnimals();
        this.map.feedAnimals();
        this.map.procreateAnimal();
        this.map.placePlant(true);
        this.map.placePlant(false);
    }
}
