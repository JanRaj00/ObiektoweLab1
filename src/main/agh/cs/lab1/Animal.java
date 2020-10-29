package agh.cs.lab1;

public class Animal {
    private Vector2d position;
    private MapDirection orientation;
    private IWorldMap map;

    public Animal() {    // czy ten konstruktor jest poprawny?
        this.position = new Vector2d(2, 2);
        this.orientation = MapDirection.NORTH;
    }

    public Animal(IWorldMap map) {
        this.position = new Vector2d(2, 2);
        this.orientation = MapDirection.NORTH;
        this.map = map;
        map.place(this);
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {    // DRY
        this.position = new Vector2d(initialPosition.x, initialPosition.y); // czy to kopiowanie jest potrzebne?
        this.orientation = MapDirection.NORTH;
        this.map = map;
        map.place(this);
    }

    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    @Override
    public String toString() {
        return orientation.toString();
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT:
                this.orientation = this.orientation.next();
                break;

            case LEFT:
                this.orientation = this.orientation.previous();
                break;

            case FORWARD:
                Vector2d newPosition = this.position.add(this.orientation.toUnitVector());
                if (map.canMoveTo(newPosition))
                    this.position = this.position.add(this.orientation.toUnitVector()); // czemu nie używa Pan newPosition?
                break;

            case BACKWARD:
                newPosition = this.position.subtract(this.orientation.toUnitVector());
                if (map.canMoveTo(newPosition))
                    this.position = this.position.subtract(this.orientation.toUnitVector());
                break;

            default:
                throw new IllegalArgumentException("Illegal direction: " + this);
        }
    }

}
