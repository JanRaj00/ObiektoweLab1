package agh.cs.lab1;

import agh.cs.lab1.Code.Genome;
import org.junit.Assert;
import org.junit.Test;

public class GenomeTest {
    @Test public void mixGenesTest(){
        Genome firstGen = new Genome();
        Genome secondGen = new Genome();
        Genome childGen = new Genome();
        childGen.mixGenes(firstGen, secondGen);
        int differentGenes=-1;
        for(int i=0; i<32; i++){
            if(differentGenes!=childGen.genome[i]) differentGenes++;
        }
        Assert.assertEquals(7, differentGenes);
    }
}
