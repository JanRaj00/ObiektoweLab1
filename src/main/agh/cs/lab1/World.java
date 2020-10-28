package agh.cs.lab1;

public class World {
    public static void main(String[] args){
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        map.place(new Animal((map)));
        map.place(new Animal(map,new Vector2d(3,4)));
        map.run(directions);
        System.out.println(map.objectAt(new Vector2d(2, 0)));
        System.out.println(map.objectAt(new Vector2d(3, 5)));
    }
}
