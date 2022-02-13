package com.goopnigoop.fifteen.sixth;

import com.goopnigoop.fifteen.sixth.records.Coordinate;
import com.goopnigoop.fifteen.sixth.records.Instruction;

import java.util.function.Function;

import static com.goopnigoop.fifteen.sixth.GridCommand.getCommandByAlias;

public class ToInstructionMapper implements Function<String, Instruction> {

    public static final String COORDINATES_DELIMITER = ",";
    public static final String BLANK_DELIMITER = " ";

    @Override
    public Instruction apply(String input) {
        final String[] arguments = input.split(BLANK_DELIMITER);

        if (arguments.length == 5) {
            final GridCommand commandByAlias = getCommandByAlias(arguments[0] + BLANK_DELIMITER + arguments[1]);
            return new Instruction(commandByAlias, getCoordinate(arguments[2]), getCoordinate(arguments[4]));
        }

        return new Instruction(getCommandByAlias(arguments[0]), getCoordinate(arguments[1]), getCoordinate(arguments[3]));
    }

    private Coordinate getCoordinate(String coordinates) {
        final String[] coordinate = coordinates.split(COORDINATES_DELIMITER);
        return new Coordinate(Integer.parseInt(coordinate[0]), Integer.parseInt(coordinate[1]));
    }

}
