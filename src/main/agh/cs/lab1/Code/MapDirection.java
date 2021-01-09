package agh.cs.lab1.Code;

public enum MapDirection {
    NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST;

    @Override
    public String toString() {
        String result=null;
        switch (this) {
            case NORTH:
                result = "N";
                break;
            case EAST:
                result = "E";
                break;
            case SOUTH:
                result = "S";
                break;
            case WEST:
                result = "W";
                break;
            case NORTHWEST:
                result = "NW";
                break;
            case NORTHEAST:
                result = "NE";
                break;
            case SOUTHWEST:
                result="SW";
                break;
            case SOUTHEAST:
                result="SE";
                break;
        }
        return result;
    }

    public MapDirection next(){
        switch(this){
            case NORTH: return NORTHEAST;
            case NORTHEAST: return EAST;
            case EAST: return SOUTHEAST;
            case SOUTHEAST: return SOUTH;
            case SOUTH: return SOUTHWEST;
            case SOUTHWEST: return WEST;
            case WEST: return NORTHWEST;
            case NORTHWEST: return NORTH;
        }
        return null;
    }


    public Vector2d toUnitVector(){
        switch(this){
            case NORTH: return new Vector2d(0, 1);  // nowy wektor co wywołanie
            case NORTHEAST: return new Vector2d(1, 1);
            case EAST: return new Vector2d(1,0);
            case SOUTHEAST: return new Vector2d(1, -1);
            case SOUTH: return new Vector2d(0,-1);
            case SOUTHWEST: return new Vector2d(-1, -1);
            case WEST: return new Vector2d(-1,0);
            case NORTHWEST: return new Vector2d(-1, 1);
        }
        return null;
    }
}
