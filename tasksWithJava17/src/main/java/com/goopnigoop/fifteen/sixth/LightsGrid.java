package com.goopnigoop.fifteen.sixth;

import com.goopnigoop.fifteen.sixth.records.Coordinate;

import java.util.Arrays;

public class LightsGrid {
    public static final int ON = 1;
    public static final int OFF = 0;
    private final int[][] grid;

    public LightsGrid(int gridSize) {
        grid = new int[gridSize][gridSize];
    }

    public int getLitLights() {
        return Math.toIntExact(Arrays.stream(grid).flatMapToInt(Arrays::stream).filter(value -> value == ON).count());
    }

    public void lightOn(Coordinate coordinate) {
        grid[coordinate.x()][coordinate.y()] = ON;
    }

    public void lightOff(Coordinate coordinate) {
        grid[coordinate.x()][coordinate.y()] = OFF;
    }

    public void toggle(Coordinate coordinate) {
        grid[coordinate.x()][coordinate.y()] = grid[coordinate.x()][coordinate.y()] == ON ? OFF : ON;
    }
}
