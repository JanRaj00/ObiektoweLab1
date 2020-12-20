package agh.cs.lab1.Visualisation.src.sample;

import agh.cs.lab1.Code.Configuration;
import agh.cs.lab1.Code.SimulationEngine;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SimulationView extends Stage {
    SimulationEngine simulationEngine;

    public SimulationView(Configuration configuration) {
        simulationEngine = new SimulationEngine(configuration);
        run();
    }

    public void run(){
        Stage theStage = new Stage();
        theStage.setTitle("Evolution");
        SplitPane root = new SplitPane();
        Canvas canvas = new Canvas(800, 650);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        GridPane stats = new GridPane();
        root.getItems().addAll(canvas, stats);
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);
        MapView mapView = new MapView(simulationEngine, canvas);
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                mapView.run(gc, stats);
                simulationEngine.nextDay();
            }
        }.start();
        theStage.show();
    }
}
