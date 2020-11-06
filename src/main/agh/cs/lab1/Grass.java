package agh.cs.lab1;

public class Grass {
    public final Vector2d position; // to powinno być prywatne, dla zachowania spójności interfejsu ze zwierzęciem - skoro mamy getter, to z niego korzystamy
    public Grass(Vector2d pos){
        this.position=pos;
    }

    public Vector2d getPosition(){ return this.position; }

    @Override
    public String toString() { return "*"; }

}
