package agh.cs.lab1;

public class SimulationEngine implements IEngine{
    Planet map;
    int plantEnergy;
    int moveEnergy;
    int startEnergy;
    public SimulationEngine(int width, int height, int moveEnergy, int startEnergy, int plantEnergy, double jungleRatio){
        this.map=new Planet(width, height, jungleRatio, plantEnergy);
        this.moveEnergy=moveEnergy;
        this.startEnergy=startEnergy;
    }
    public void run(){
        int numberOfStartAnimals = (int)(0.05)*map.getSize();
        while(numberOfStartAnimals>0) {
            numberOfStartAnimals--;
        }
        while(true){
            this.map.placePlant(false);
            this.map.placePlant(true);

        }
    }
}
