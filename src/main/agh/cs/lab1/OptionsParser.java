package agh.cs.lab1;

public class OptionsParser {

    public static MoveDirection[] parse(String[] directions){
        int wrongDirections=0;
        for(String s: directions){
            switch (s){
                case "f": case"forward": case "b": case "backward": case "r": case "right": case "l": case"left":
                    break;
                default:
                    wrongDirections++;
            }
        }
        MoveDirection[] result = new MoveDirection[directions.length-wrongDirections];
        int i=0;
        for (String s: directions){
            switch(s){
                case "f": case "forward":
                    result[i]=MoveDirection.FORWARD;
                    i++;
                    break;
                case "b": case "backward":
                    result[i]=MoveDirection.BACKWARD;
                    i++;
                    break;
                case "r": case "right":
                    result[i]=MoveDirection.RIGHT;
                    i++;
                    break;
                case "l": case "left":
                    result[i]=MoveDirection.LEFT;
                    i++;
                    break;
                default:
                    break;
            }
        }
        return result;
    }
}
