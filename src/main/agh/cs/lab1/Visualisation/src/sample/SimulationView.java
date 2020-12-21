package agh.cs.lab1.Visualisation.src.sample;

import agh.cs.lab1.Code.Animal;
import agh.cs.lab1.Code.SimulationEngine;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SimulationView extends Stage {
    public SimulationView(SimulationEngine simulationEngine) {
        Stage theStage = new Stage();
        theStage.setTitle("Evolution");
        SplitPane root = new SplitPane();
        Canvas canvas = new Canvas(800, 700);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Pane right = new Pane();
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
        Button saveToFileButton = new Button("Save to file");
        runButton.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
        stopButton.setBackground(new Background(new BackgroundFill(Color.DARKSALMON, CornerRadii.EMPTY, Insets.EMPTY)));
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
        HBox buttons = new HBox(runButton, stopButton);
        buttons.setPadding(new Insets(200, 200, 200, 200));
        stats.setGridLinesVisible(true);
        stats.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
        right.getChildren().addAll(stats, buttons);
        right.setBorder(Border.EMPTY);
        right.setBackground(new Background(new BackgroundFill(Color.MISTYROSE, CornerRadii.EMPTY, Insets.EMPTY)));
        root.getItems().add(right);
        theStage.show();
    }
}
