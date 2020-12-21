package agh.cs.lab1.Visualisation.src.sample;

import agh.cs.lab1.Code.Configuration;
import agh.cs.lab1.Code.SimulationEngine;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Main extends Application {
    private static final String parameter = "parameters.json";
    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            final Configuration configuration = Configuration.fromJson(Main.parameter);
            new SimulationView(new SimulationEngine(configuration));
            if(configuration.twoMaps) new SimulationView(new SimulationEngine(configuration));
        } catch (FileNotFoundException exception) {
            System.out.println("File doesn't exist");
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
