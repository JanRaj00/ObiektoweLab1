package agh.cs.lab1.Code;

import java.util.HashMap;
import java.util.Map;

public class BestGenome {   // myląca nazwa
    private Map<Genome, Integer> genomes = new HashMap<>();
    private Genome best = null;
    private int bestSize=0;
    public void addGenome(Genome genome){
        if(this.genomes.get(genome)==null){
            this.genomes.put(genome, 1);
        }
        else{
            int numberOfGenomes=this.genomes.get(genome);
            this.genomes.remove(genome);
            this.genomes.put(genome, numberOfGenomes+1);
        }
        if(bestSize<this.genomes.get(genome)){
            best=genome;
            bestSize=this.genomes.get(genome);
        }
    }

    public Genome getBestGenome(){
        return this.best;
    }

    public boolean isEmpty(){return genomes.isEmpty();}
}
