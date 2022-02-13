package com.goopnigoop.fifteen.sixth.records;

import com.goopnigoop.fifteen.sixth.GridCommand;

import java.util.Map;
import java.util.function.BiConsumer;

public interface GridHandler<T> {
    default void applyInstruction(Instruction instruction) {
        final Map<GridCommand, BiConsumer<T, Coordinate>> actionsMap = getActionsMap();
        final BiConsumer<T, Coordinate> action = actionsMap.get(instruction.action());
        final Coordinate firstCoordinate = instruction.first();
        final Coordinate secondCoordinate = instruction.second();
        final T grid = getGrid();
        for (int i = firstCoordinate.x(); i <= secondCoordinate.x(); i++) {
            for (int j = firstCoordinate.y(); j <= secondCoordinate.y(); j++) {
                action.accept(grid, new Coordinate(i, j));
            }
        }
    }

    Map<GridCommand, BiConsumer<T, Coordinate>> getActionsMap();
    T getGrid();
}
