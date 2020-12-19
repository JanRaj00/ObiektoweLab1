package agh.cs.lab1.Code;

import java.io.FileNotFoundException;

public class World {
    private static final String parameter = "parameters.json";
    public static void main(String []args) throws FileNotFoundException {
        try {
            final Configuration configuration = Configuration.fromJson(World.parameter);
            SimulationEngine engine = new SimulationEngine(configuration);
        } catch (FileNotFoundException exception) {
            System.out.println("File doesnt exist");
        }
    }
}
