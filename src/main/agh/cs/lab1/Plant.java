package agh.cs.lab1;

public class Plant implements IMapElement{
    public final Vector2d position;
    private int plantEnergy;
    public Plant(Vector2d pos, int value){
        this.position=pos;
        this.plantEnergy=value;
    }
    public void changePlantEnergy(int value){
        this.plantEnergy=value;
    }
    public Vector2d getPosition(){ return this.position; }
    public int getPlantEnergy() {return this.plantEnergy; }
}
