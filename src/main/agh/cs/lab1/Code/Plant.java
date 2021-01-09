package agh.cs.lab1.Code;

public class Plant implements IMapElement {
    private final Vector2d position;
    private int plantEnergy;
    public Plant(Vector2d pos, int value){  // value mało mówi
        this.position=pos;
        this.plantEnergy=value;
    }
    public void changePlantEnergy(int value){   // niepotrzebna metoda
        this.plantEnergy=value;
    }
    public Vector2d getPosition(){ return this.position; }
    public int getPlantEnergy() {return this.plantEnergy; }
}
