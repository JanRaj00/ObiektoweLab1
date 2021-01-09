package agh.cs.lab1.Code;

public class SimulationEngine{
    private final Planet map;
    private final Configuration configuration;
    private long numberOFEpoch; // wielkie F powoduje, że to wygląda jak skrót

    public SimulationEngine(Configuration config){
        this.configuration=config;
        this.map = new Planet(config.width, config.height, config.jungleRatio, config.plantEnergy);
        numberOFEpoch=0;
        this.run();
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
    }
    public void nextDay(){
        this.map.makeFunerals();
        this.map.moveAnimals();
        this.map.feedAnimals();
        this.map.procreateAnimal();
        this.map.placePlant(false);
        this.map.placePlant(true);
        numberOFEpoch++;
    }
    public Planet getPlanet() {return this.map;}
    public long getNumberOFEpoch(){return this.numberOFEpoch;};
}
