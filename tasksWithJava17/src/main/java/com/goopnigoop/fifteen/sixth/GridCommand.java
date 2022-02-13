package com.goopnigoop.fifteen.sixth;

import java.util.Arrays;
import java.util.Objects;

public enum GridCommand {
    TOGGLE("toggle"), TURN_ON("turn on"), TURN_OFF("turn off");

    private final String commandAlias;

    GridCommand(String commandAlias) {
        this.commandAlias = commandAlias;
    }

    public static GridCommand getCommandByAlias(String alias) {
        return Arrays.stream(GridCommand.values()).filter(command -> Objects.equals(alias, command.commandAlias)).findAny()
                     .orElseThrow(() -> new IllegalArgumentException("commandAlias is unknown"));
    }
}