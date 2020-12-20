package agh.cs.lab1.Visualisation.src.sample;

import agh.cs.lab1.Code.Animal;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AnimalInfo extends Stage {
    AnimalInfo(Animal animal){
        VBox animalBox = new VBox(new Text("Genome"), new Text(animal.getGenome().toString()));
        Scene scene = new Scene(animalBox);
        this.setTitle("Zwierzak i jego historia");
        this.setScene(scene);
        this.show();
    }
}
