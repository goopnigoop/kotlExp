package com.goopnigoop.fifteen.sixth.impl;

import com.goopnigoop.fifteen.sixth.GridCommand;
import com.goopnigoop.fifteen.sixth.LightsGrid;
import com.goopnigoop.fifteen.sixth.records.Coordinate;
import com.goopnigoop.fifteen.sixth.records.GridHandler;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.BiConsumer;

public record LightsGridHandler(LightsGrid grid) implements GridHandler<LightsGrid> {
    @Override
    public Map<GridCommand, BiConsumer<LightsGrid, Coordinate>> getActionsMap() {
        return new EnumMap<>(
                Map.of(
                        GridCommand.TOGGLE, LightsGrid::toggle,
                        GridCommand.TURN_OFF, LightsGrid::lightOff,
                        GridCommand.TURN_ON, LightsGrid::lightOn));
    }

    @Override
    public LightsGrid getGrid() {
        return this.grid;
    }
}
