package com.codurance.marsrover;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.codurance.marsrover.Direction.*;

public class Grid {
    private static final int MAX_WIDTH = 10;
    private static final int MAX_HEIGHT = MAX_WIDTH;
    private List<Coordinate> obstacles;

    public Grid() {
        this.obstacles = new ArrayList<>();
    }

    public Grid(List<Coordinate> obstacles) {
        this.obstacles = obstacles;
    }

    public Optional<Coordinate> nextCoordinateFor(Coordinate coordinate, Direction direction) {
        int x = coordinate.x();
        int y = coordinate.y();
        if (direction == NORTH) {
            y = (y + 1) % MAX_HEIGHT;
        }
        if (direction == EAST) {
            x = (x + 1) % MAX_WIDTH;
        }
        if (direction == SOUTH) {
            y = (y > 0) ? y - 1 : MAX_HEIGHT - 1;
        }
        if (direction == WEST) {
            x = (x > 0) ? x - 1 : MAX_WIDTH - 1;
        }
        Coordinate newCoordinate = new Coordinate(x, y);
        return obstacles.contains(newCoordinate)
            ? Optional.empty()
            : Optional.of(newCoordinate);
    }
}
