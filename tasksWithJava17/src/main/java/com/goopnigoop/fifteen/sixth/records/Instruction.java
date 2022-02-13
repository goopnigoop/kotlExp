package com.goopnigoop.fifteen.sixth.records;

import com.goopnigoop.fifteen.sixth.GridCommand;

public record Instruction(GridCommand action, Coordinate first, Coordinate second) {
}
