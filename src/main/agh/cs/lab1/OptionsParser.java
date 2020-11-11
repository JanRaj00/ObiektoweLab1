package agh.cs.lab1;

public class OptionsParser {

    public static MoveDirection[] parse(String[] directions) {
        MoveDirection[] result = new MoveDirection[directions.length];
        int i = 0;
        for (String s : directions) {
            switch (s) {
                case "f": case "forward":
                    result[i] = MoveDirection.FORWARD;
                    i++; break;
                case "b": case "backward":
                    result[i] = MoveDirection.BACKWARD;
                    i++; break;
                case "r": case "right":
                    result[i] = MoveDirection.RIGHT;
                    i++; break;
                case "l": case "left":
                    result[i] = MoveDirection.LEFT;
                    i++; break;
                default:
                    throw new IllegalArgumentException(s + " is not legal move specification");
            }
        }
        return result;
    }
}
