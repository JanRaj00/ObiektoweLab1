package agh.cs.lab1.Code;

public class SimulationEngine{
    private final Planet map;
    private final Configuration configuration;

    public SimulationEngine(Configuration config){
        this.configuration=config;
        this.map = new Planet(config.width, config.height, config.jungleRatio, config.plantEnergy);
        run();
    }
    public void run(){
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
        int p=200;
        while(p>0) {
            nextDay();
            p--;
        }
        System.out.println(map.aliveAnimals());
        System.out.println(map.getAverageEnergy());
        System.out.println(map.getNumberOfDeadAnimals());
        System.out.println(map.getAverageAgeOfDeadAnimals());
        System.out.println(map.getBestGenome().toString());
    }
    public void nextDay(){
        this.map.makeFunerals();
        this.map.moveAnimals();
        this.map.feedAnimals();
        this.map.procreateAnimal();
        this.map.placePlant(false);
        this.map.placePlant(true);
    }
    public Planet getPlanet(){
        return this.map;
    }
}
