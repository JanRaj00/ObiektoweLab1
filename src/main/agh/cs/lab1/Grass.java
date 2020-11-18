package agh.cs.lab1;

public class Grass implements IMapElement{
    public final Vector2d position;
    public Grass(Vector2d pos){
        this.position=pos;
    }

    public Vector2d getPosition(){ return this.position; }

    @Override
    public String toString() { return "*"; }
}
