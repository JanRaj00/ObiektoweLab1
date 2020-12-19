package agh.cs.lab1.Visualisation.src.sample;

import agh.cs.lab1.Code.Configuration;
import agh.cs.lab1.Code.SimulationEngine;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SimulationView extends Stage {
    SimulationEngine simulationEngine;
    public SimulationView(Configuration configuration){
        this.simulationEngine = new SimulationEngine(configuration);
        MapView map = new MapView(simulationEngine.getPlanet());
        GridPane mapToDraw = map.prepMap();
        Scene scene = new Scene(mapToDraw);
        this.setTitle("costam");
        this.setScene(scene);
        this.show();
    }
}
