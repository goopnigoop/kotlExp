package com.goopnigoop.fifteen.sixth.impl;

import com.goopnigoop.fifteen.sixth.BrightLightsGrid;
import com.goopnigoop.fifteen.sixth.GridCommand;
import com.goopnigoop.fifteen.sixth.records.Coordinate;
import com.goopnigoop.fifteen.sixth.records.GridHandler;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.BiConsumer;

public record BrightnessGridHandler(BrightLightsGrid grid) implements GridHandler<BrightLightsGrid> {
    @Override
    public Map<GridCommand, BiConsumer<BrightLightsGrid, Coordinate>> getActionsMap() {
        return new EnumMap<>(Map.of(GridCommand.TOGGLE, BrightLightsGrid::toggle,
                                    GridCommand.TURN_OFF, BrightLightsGrid::decrease,
                                    GridCommand.TURN_ON, BrightLightsGrid::increase));
    }

    @Override
    public BrightLightsGrid getGrid() {
        return this.grid;
    }
}
