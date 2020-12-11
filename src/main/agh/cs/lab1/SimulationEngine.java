package agh.cs.lab1;

public class SimulationEngine implements IEngine{
    IWorldMap map;
    int plantEnergy;
    int moveEnergy;
    int startEnergy;
    public SimulationEngine(int width, int height, int moveEnergy, int startEnergy, int plantEnergy, double jungleRatio){
        this.map=new Planet(width, height, jungleRatio, plantEnergy);
        this.plantEnergy=plantEnergy;
        this.moveEnergy=moveEnergy;
        this.startEnergy=startEnergy;
    }
    public void run(){

    }
}
