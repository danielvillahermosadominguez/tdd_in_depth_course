package com.codurance.marsrover;

import java.util.Optional;

import static com.codurance.marsrover.Direction.NORTH;

public class Rober {
    Direction direction = NORTH;
    Grid grid;
    Coordinate coordinate = new Coordinate(0, 0);
    String obstacleString = "";

    public Rober(Grid grid) {
        this.grid = grid;
    }

    public String execute(String commands) {
        obstacleString = "";
        for (char c : commands.toCharArray()) {
            if (c == 'R') {
                direction = direction.right();
            } else if (c == 'L') {
                direction = direction.left();
            } else if (c == 'M') {
                move();
            }
        }

        return obstacleString + coordinate.x() + ":" + coordinate.y() + ":" + direction.value();
    }

    private void move() {
        Optional<Coordinate> nextCoordinate;
        nextCoordinate = grid.nextCoordinateFor(coordinate, direction);
        nextCoordinate.ifPresent(co -> this.coordinate = co);
        obstacleString = nextCoordinate.isPresent() ? "" : "0:";
    }
}
