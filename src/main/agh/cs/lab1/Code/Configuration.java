package agh.cs.lab1.Code;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
public class Configuration {
    public final int width;
    public final int height;
    public final int startEnergy;
    public final  int moveEnergy;
    public final int startAnimals;
    public final double jungleRatio;
    public final int plantEnergy;
    public final boolean twoMaps;


    Configuration(){
        this.width = this.height = this.startEnergy = this.moveEnergy = this.startAnimals = this.plantEnergy = 0;
        this.jungleRatio = 0;
        twoMaps=false;
    }

    public static Configuration fromJson(final String parameters) throws FileNotFoundException {
        Gson gson = new Gson();
        try {
            return gson.fromJson(new FileReader(parameters), Configuration.class);
        }catch (FileNotFoundException ex){
            System.out.println("Configuration file not found!\n Path: "+ parameters+"\n"+ex.toString());
            throw ex;
        }
    }

}
