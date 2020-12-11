package agh.cs.lab1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.LinkedList;

import static java.util.Arrays.*;

public class Genome {
    public int[] genome = new int[32];
    public Genome(){
        for (int i=0; i<8; i++) this.genome[i]=i;

        Random rand=new Random();
        for (int i = 8; i < 32; i++) {
            this.genome[i]=rand.nextInt(8);
        }
        sort(this.genome);
    }

    public int getRand(){
        Random rand=new Random();
        return this.genome[rand.nextInt(32)]; //zwroc wartosc z wylosowanej pozycji
    }

    public Genome mixGenes(Genome firstParent, Genome secondParent){
        Genome childGenome = new Genome();
        Random rand = new Random();
        int firstCut = rand.nextInt(30);
        firstCut=firstCut+1;
        int secondCut = rand.nextInt(31-firstCut);
        secondCut = secondCut + firstCut + 1;

        for(int i=0; i<firstCut; i++) childGenome.genome[i]=firstParent.genome[i];
        for(int i=firstCut; i<secondCut; i++) childGenome.genome[i]=secondParent.genome[i];
        for(int i=secondCut; i<32; i++) childGenome.genome[i]=firstParent.genome[i];

        LinkedList<Integer> missingGenomes = new LinkedList<>();
        int[] numberOfGenes={0,0,0,0,0,0,0,0};
        for(int i=0; i<32; i++){
            numberOfGenes[childGenome.genome[i]]++;
        }
        for(int i=0; i<8; i++) if(numberOfGenes[i]==0) missingGenomes.add(i);

        int i=0; //licznik do petli while
        while(!missingGenomes.isEmpty()){
            if(numberOfGenes[i]>1){
                childGenome.genome[i]=missingGenomes.pop();
                numberOfGenes[i]--;
            }
            i++;
        }
        sort(childGenome.genome);
        return childGenome;
    }
}
