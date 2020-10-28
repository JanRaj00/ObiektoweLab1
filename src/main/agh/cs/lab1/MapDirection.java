package agh.cs.lab1;

public enum MapDirection {
    NORTH,
    SOUTH,
    EAST,
    WEST;

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
        }
        return result;
    }

    public MapDirection next(){
        switch(this){
            case NORTH: return EAST;
            case EAST: return SOUTH;
            case SOUTH: return WEST;
            case WEST: return NORTH;
        }
        return null;
    }

    public MapDirection previous(){
        switch(this){
            case NORTH: return WEST;
            case EAST: return NORTH;
            case SOUTH: return EAST;
            case WEST: return SOUTH;
        }
        return null;
    }

    public Vector2d toUnitVector(){
        switch(this){
            case NORTH: return new Vector2d(0, 1);
            case EAST: return new Vector2d(1,0);
            case SOUTH: return new Vector2d(0,-1);
            case WEST: return new Vector2d(-1,0);
        }
        return null;
    }
}
