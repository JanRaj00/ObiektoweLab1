package agh.cs.lab1.Visualisation.src.sample;


import agh.cs.lab1.Code.Animal;
import agh.cs.lab1.Code.Planet;
import agh.cs.lab1.Code.SimulationEngine;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class MapView{
    private final double width;
    private final double height;
    private final double fieldWidth;
    private final double fieldHeight;
    private Planet planet;
    private SimulationEngine simulationEngine;

    public MapView(SimulationEngine simulationEngine, Canvas canvas){
        this.simulationEngine=simulationEngine;
        this.planet=simulationEngine.getPlanet();
        this.width=canvas.getWidth();
        this.height=canvas.getHeight();
        this.fieldWidth=(this.width/this.planet.getUpperRight().x);
        this.fieldHeight=(this.height/this.planet.getUpperRight().y);
    }

    public void drawMap(GraphicsContext gc) {
        int x = planet.getUpperRight().x;
        int y = planet.getUpperRight().y;
        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= y; j++) {
                int typeOfField = planet.getTypeOfElement(i, j);
                if (typeOfField == 0) {
                    gc.setFill(Color.BROWN);
                    gc.fillOval(this.fieldWidth * i, this.fieldHeight * j, this.fieldWidth, this.fieldHeight);
                } else {
                    if (typeOfField == 1) gc.setFill(Color.GREEN);
                    else if (typeOfField == 2) gc.setFill(Color.BLUE);
                    else gc.setFill(Color.YELLOW);
                    gc.fillRect(this.fieldWidth * i, this.fieldHeight * j, this.fieldWidth, this.fieldHeight);
                }
            }
        }
    }

    public void stats(GridPane statsPane){
        String AnimalNum = Long.toString(planet.aliveAnimals());
        String AvEnergy = Long.toString(planet.getAverageEnergy());
        String DeadAnimals = Long.toString(planet.getNumberOfDeadAnimals());
        String AvAgeDead = Long.toString(planet.getAverageAgeOfDeadAnimals());
        String bestGenome = planet.getBestGenome().toString();
        String numberOfPlants = Long.toString(planet.getPlantsNumber());
        String numberOfEpoch = Long.toString(this.simulationEngine.getNumberOFEpoch());
        statsPane.getChildren().clear();
        statsPane.add(new Text("Epoch: "), 0, 0);
        statsPane.add(new Text("Number of Animals: "), 0, 1);
        statsPane.add(new Text("Average Energy: "), 0, 2);
        statsPane.add(new Text("Funerals: "), 0, 3);
        statsPane.add(new Text("Average Age of Dead Animal:"), 0, 4);
        statsPane.add(new Text("Most Popular Genotype: "), 0, 5);
        statsPane.add(new Text("Number of Plants"), 0, 6);
        statsPane.add(new Text(numberOfEpoch), 1, 0);
        statsPane.add(new Text(AnimalNum), 1, 1);
        statsPane.add(new Text(AvEnergy), 1, 2);
        statsPane.add(new Text(DeadAnimals), 1, 3);
        statsPane.add(new Text(AvAgeDead), 1, 4);
        statsPane.add(new Text(bestGenome), 1, 5);
        statsPane.add(new Text(numberOfPlants), 1, 6);
    }

    public void run(GraphicsContext gc, GridPane statsPane){
        drawMap(gc);
        stats(statsPane);
    }


    public Animal getAnimalAtPosition(int x, int y){
        return planet.getBestFromField(x, y);
    }

    public double getFieldWidth(){
        return this.fieldWidth;
    }

    public double getFieldHeight(){
        return this.fieldHeight;
    }
}
