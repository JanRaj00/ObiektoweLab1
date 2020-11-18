package agh.cs.lab1;

public class World {
    public static void main(String[] args) {
        try {
            MoveDirection[] directions = new OptionsParser().parse(args);
            IWorldMap map = new GrassField(10);
            map.place(new Animal((map)));
            map.place(new Animal(map, new Vector2d(3, 4)));
            map.run(directions);
            System.out.println(map.objectAt(new Vector2d(2, -1)));
            System.out.println(map.toString());
        } catch (IllegalArgumentException except) {
            System.out.println(except.toString());
        }
    }
}
