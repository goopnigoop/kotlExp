package com.goopnigoop.fifteen.sixth;

import com.goopnigoop.fifteen.sixth.records.Coordinate;

import java.util.Arrays;

public class BrightLightsGrid extends AbstractGrid {
    public static final int ON = 1;
    public static final int OFF = 0;

    public BrightLightsGrid(int gridSize) {
        super(gridSize);
    }

    public int getBrightness() {
        return Math.toIntExact(Arrays.stream(grid).flatMapToInt(Arrays::stream).reduce(0, Integer::sum));
    }

    public void increase(Coordinate coordinate) {
        grid[coordinate.x()][coordinate.y()] += 1;
    }

    public void decrease(Coordinate coordinate) {
        if (grid[coordinate.x()][coordinate.y()] < 1) {
            return;
        }
        grid[coordinate.x()][coordinate.y()] -= 1;
    }

    public void toggle(Coordinate coordinate) {
        grid[coordinate.x()][coordinate.y()] = grid[coordinate.x()][coordinate.y()] + 2;
    }
}
