package com.codurance.marsrover;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RoverShould {

    private Rober rober;

    @BeforeEach
    public void initialise() {
        Grid grid = new Grid();
        rober = new Rober(grid);
    }

    @ParameterizedTest
    @CsvSource({
        "R,0:0:E",
        "RR,0:0:S",
        "RRR,0:0:W",
        "RRRR,0:0:N"
    })
    public void rotate_right(String commands, String position) {
        assertThat(rober.execute(commands), is(position));
    }

    @ParameterizedTest
    @CsvSource({
        "L,0:0:W",
        "LL,0:0:S",
        "LLL,0:0:E",
        "LLLL,0:0:N"
    })
    public void rotate_left(String commands, String position) {
        assertThat(rober.execute(commands), is(position));
    }

    @ParameterizedTest
    @CsvSource({
        "M,0:1:N",
        "MMM,0:3:N",
    })
    public void move(String commands, String position) {
        assertThat(rober.execute(commands), is(position));
    }

    @ParameterizedTest
    @CsvSource({
        "MMMMMMMMMM,0:0:N",
        "MMMMMMMMMMMMMMM,0:5:N"
    })
    public void wrap_from_top_to_botton_when_moving_north(String commands, String position) {
        assertThat(rober.execute(commands), is(position));
    }

    @ParameterizedTest
    @CsvSource({
        "RM,1:0:E",
        "RMMMMM,5:0:E"
    })
    public void move_right(String commands, String position) {
        assertThat(rober.execute(commands), is(position));
    }

    @ParameterizedTest
    @CsvSource({
        "RMMMMMMMMMM,0:0:E",
        "RMMMMMMMMMMMMMMM,5:0:E",
    })
    public void wrap_from_top_to_botton_when_moving_east(String commands, String position) {
        assertThat(rober.execute(commands), is(position));
    }

    @ParameterizedTest
    @CsvSource({
        "LM,9:0:W",
        "LMMMMM,5:0:W"
    })
    public void move_left(String commands, String position) {
        assertThat(rober.execute(commands), is(position));
    }

    @ParameterizedTest
    @CsvSource({
        "LLM,0:9:S",
        "LLMMMMM,0:5:S",
    })
    public void move_south(String commands, String position) {
        assertThat(rober.execute(commands), is(position));
    }

    @ParameterizedTest
    @CsvSource({
        "MMMM,0:0:3:N",
        "RMMMMMM, 0:1:0:E"
    })
    public void stop_at_obstacle(String commands, String position) {
        Coordinate obstacle_0x4 = new Coordinate(0, 4);
        Coordinate obstacle_2x0 = new Coordinate(2, 0);
        Grid grid = new Grid(Arrays.asList(obstacle_0x4, obstacle_2x0));
        Rober rober = new Rober(grid);
        assertThat(rober.execute(commands), is(position));
    }
}
