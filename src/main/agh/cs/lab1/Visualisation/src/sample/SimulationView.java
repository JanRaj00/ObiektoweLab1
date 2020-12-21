package agh.cs.lab1.Visualisation.src.sample;

import agh.cs.lab1.Code.Animal;
import agh.cs.lab1.Code.SimulationEngine;
import com.google.gson.stream.JsonWriter;
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

import java.io.FileWriter;
import java.io.IOException;

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

        saveToFileButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String[] statistics = mapView.statisticsInString();
                try {
                    JsonWriter writer = new JsonWriter(new FileWriter("output.json"));
                    writer.beginObject();
                    writer.name("Statistics");
                    writer.beginArray();
                    writer.beginObject(); writer.name("Epoch: ").value(statistics[0]); writer.endObject();
                    writer.beginObject(); writer.name("Number of Animals: ").value(statistics[1]); writer.endObject();
                    writer.beginObject(); writer.name("Average Energy: ").value(statistics[2]); writer.endObject();
                    writer.beginObject(); writer.name("Dead Animals: ").value(statistics[3]); writer.endObject();
                    writer.beginObject(); writer.name("Average Age of Dead: ").value(statistics[4]); writer.endObject();
                    writer.beginObject(); writer.name("Most Popular Genotype: ").value(statistics[5]); writer.endObject();
                    writer.beginObject(); writer.name("Number of Plants: ").value(statistics[6]); writer.endObject();
                    writer.endArray(); writer.endObject(); writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
        HBox buttons = new HBox(runButton, stopButton, saveToFileButton);
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
