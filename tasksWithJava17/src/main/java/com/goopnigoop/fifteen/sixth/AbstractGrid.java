package com.goopnigoop.fifteen.sixth;

public abstract class AbstractGrid {
    protected final int[][] grid;

    protected AbstractGrid(int gridSize) {
        grid = new int[gridSize][gridSize];
    }
}
