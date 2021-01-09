package agh.cs.lab1.Visualisation.src.sample;   // src w nazwie pakietu? Pakiety powinny zawierać tylko źródła

import agh.cs.lab1.Code.Animal;
import agh.cs.lab1.Code.SimulationEngine;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AnimalInfo extends Stage {
    AnimalInfo(Animal animal, SimulationEngine simulationEngine){
        while(animal.isAlive()) {
            simulationEngine.nextDay();
        }
        GridPane animalPane = new GridPane();
        animalPane.add(new Label("Genotype: "),0,0);
        animalPane.add(new Label(animal.getGenome().toString()), 1,0);
        animalPane.add(new Label("Epoch of Dead: "),0,1);
        animalPane.add(new Label(Long.toString(simulationEngine.getNumberOFEpoch())), 1,1);
        animalPane.add(new Label("Number of children: "),0,2);
        animalPane.add(new Label(Integer.toString(animal.getChildrenNumber())), 1,2);
        Scene scene = new Scene(animalPane);
        this.setTitle("ANIMAL INFO");
        this.setScene(scene);
        this.show();
    }
}
