package agh.cs.lab1;

import org.junit.Test;

import static org.junit.Assert.*;

public class OptionsParserTest {

    @Test
    public void parseTest(){
        String[] sOne={"b", "l", "right", "f", "forward"};
        MoveDirection[] dOne={MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD};

        assertArrayEquals(dOne, OptionsParser.parse(sOne)); //nie moglem znalezc assertThrows, ale sprawdzilem
                                                            //uruchamiajac ze złymi argumentami i rzuciło wyjatek
        
    }

}
