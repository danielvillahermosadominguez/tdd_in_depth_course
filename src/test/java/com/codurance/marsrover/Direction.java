package com.codurance.marsrover;

enum Direction {
    NORTH("N", "W", "E"),
    EAST("E", "N", "S"),
    SOUTH("S", "E", "W"),
    WEST("W", "S", "N");

    private final String value;
    private final String left;
    private final String right;

    Direction(String value, String left, String right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Direction right() {
        return move(this.right);
    }

    public Direction left() {
        return move(this.left);
    }

    public String value() {
        return this.value;
    }

    private Direction move(String left) {
        for (Direction direction : values()) {
            if (direction.value == left) {
                return direction;
            }
        }
        return null;
    }
}
