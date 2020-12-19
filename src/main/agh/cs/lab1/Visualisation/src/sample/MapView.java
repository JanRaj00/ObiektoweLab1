package agh.cs.lab1.Visualisation.src.sample;

import agh.cs.lab1.Code.Planet;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MapView {
    private int width=690;
    private int height=690;
    private int fieldWidth;
    private int fieldHeight;
    private GridPane mapViewPane = new GridPane();
    private Planet planet;

    public MapView(Planet planet){
        this.fieldWidth=this.width/(planet.getUpperRight().x+1);
        this.fieldHeight=this.height/(planet.getUpperRight().y+1);
        this.planet=planet;
    }

    public GridPane prepMap(){
        int x = this.planet.getUpperRight().x;
        int y = this.planet.getUpperRight().y;
        for(int i=0; i<=x; i++){
            for(int j=0; j<=y; j++){
                Rectangle rec = new Rectangle();
                rec.setHeight(fieldHeight);
                rec.setWidth(fieldWidth);
                int p = planet.getTypeOfElement(i, j);
                if(p==0) rec.setFill(Color.AQUAMARINE);
                else if(p==1) rec.setFill(Color.GREEN);
                else if(p==2) rec.setFill(Color.GOLD);
                else rec.setFill(Color.BROWN);
                mapViewPane.add(rec, i, j);
            }
        }
        return mapViewPane;
    }
}
