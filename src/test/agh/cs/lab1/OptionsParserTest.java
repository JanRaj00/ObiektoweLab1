package agh.cs.lab1;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class OptionsParserTest {
    // ten test przechodzi?
    @Test
    public void parseTest(){
        String[] sOne={"b", "l", "right", "f", "forward"};
        MoveDirection[] dOne={MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD};

        String[] sTwo={"b", "backwrd", "right", "left", "122", "LEFT"};
        MoveDirection[] dTwo={MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT};

        String[] sThree={"b", "backwad", "right", "l", "\n", "f"};
        MoveDirection[] dThree={MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD};

        assertArrayEquals(dOne, OptionsParser.parse(sOne));
        assertArrayEquals(dTwo, OptionsParser.parse(sTwo));
        assertArrayEquals(dThree, OptionsParser.parse(sThree));
    }


}
