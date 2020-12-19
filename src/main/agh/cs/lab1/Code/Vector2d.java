package agh.cs.lab1.Code;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        String s_x = String.valueOf(this.x);
        String s_y = String.valueOf(this.y);
        return "(" + s_x + "," + s_y + ")";
    }

    public boolean precedes(Vector2d other) {
        return this.x <= other.x && this.y <= other.y;
    }

    public boolean follows(Vector2d other) {
        return this.x >= other.x && this.y >= other.y;
    }

    public Vector2d upperRight(Vector2d other) {
        int upper_x;
        int upper_y;
        upper_x = Math.max(this.x, other.x);
        upper_y = Math.max(this.y, other.y);
        return new Vector2d(upper_x, upper_y);
    }

    public Vector2d lowerLeft(Vector2d other) {
        int lower_x;
        int lower_y;
        lower_x = Math.min(this.x, other.x);
        lower_y = Math.min(this.y, other.y);
        return new Vector2d(lower_x, lower_y);
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    public Vector2d subtract(Vector2d other) {
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;
        return this.x == that.x && this.y == that.y;
    }

    public Vector2d opposite() {
        return new Vector2d((-1) * this.x, (-1) * this.y);
    }

    @Override
    public int hashCode() {
        int hash = 13;
        hash += this.x * 31;
        hash += this.y * 17;
        return hash;
    }

}