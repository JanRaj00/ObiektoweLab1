package agh.cs.lab1.Visualisation.src.sample;

import agh.cs.lab1.Code.Configuration;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Main extends Application {
    private static final String parameter = "parameters.json";
    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            final Configuration configuration = Configuration.fromJson(Main.parameter);
            new SimulationView(configuration);
        } catch (FileNotFoundException exception) {
            System.out.println("File doesnt exist");
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
