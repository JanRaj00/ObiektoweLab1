package agh.cs.lab1.Visualisation.src.sample;

import agh.cs.lab1.Code.Animal;
import agh.cs.lab1.Code.Configuration;
import agh.cs.lab1.Code.SimulationEngine;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SimulationView extends Stage {
    public SimulationView(Configuration configuration) {
        SimulationEngine simulationEngine = new SimulationEngine(configuration);
        Stage theStage = new Stage();
        theStage.setTitle("Evolution");
        SplitPane root = new SplitPane();
        Canvas canvas = new Canvas(800, 650);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        GridPane stats = new GridPane();
        root.getItems().add(canvas);
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);
        MapView mapView = new MapView(simulationEngine, canvas);
        AnimationTimer myTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                mapView.run(gc, stats);
                simulationEngine.nextDay();
            }
        };

        Button runButton = new Button("Run");
        Button stopButton = new Button("Stop");
        runButton.setPrefWidth(120);
        runButton.setPrefHeight(300);
        stopButton.setPrefWidth(120);
        stopButton.setPrefHeight(300);
        runButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                myTimer.start();
            }
        });

        stopButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                myTimer.stop();
            }
        });
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int x = (int) (mouseEvent.getX()/mapView.getFieldWidth());
                int y = (int) (mouseEvent.getY()/mapView.getFieldHeight());
                Animal animal = mapView.getAnimalAtPosition(x, y);
                new AnimalInfo(animal);
            }
        });
        VBox buttons = new VBox(runButton, stopButton);
        buttons.setPrefWidth(120);
        root.getItems().addAll(buttons, stats);
        theStage.show();
    }
}
