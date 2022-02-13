package com.goopnigoop.fifteen.sixth;

import com.goopnigoop.fifteen.sixth.impl.BrightnessGridHandler;
import com.goopnigoop.fifteen.sixth.records.Instruction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class BrightLightsGridTest {

    private static final String resourceName = "sixth";

    BrightLightsGrid grid = new BrightLightsGrid(1000);
    Function<String, Instruction> toInstructionMapper = new ToInstructionMapper();
    private static File file;

    @BeforeAll
    static void beforeAll() {
        ClassLoader classLoader = BrightLightsGridTest.class.getClassLoader();
        file = new File(Objects.requireNonNull(classLoader.getResource(resourceName)).getFile());
    }

    @Test
    void shouldReturnProperValueAfterInstructionsApplied() throws IOException {
        final Collection<Instruction> lines = Files.lines(file.toPath())
                                                   .map(toInstructionMapper)
                                                   .toList();
        BrightnessGridHandler lightsGridHandler = new BrightnessGridHandler(grid);
        lines.forEach(lightsGridHandler::applyInstruction);
        assertThat(grid.getBrightness(), is(equalTo(14687245)));
    }

    @ParameterizedTest
    @CsvSource(value = {"turn on 0,0 through 999,999:1000000",
            "toggle 0,0 through 999,999:2000000"}, delimiter = ':')
    void shouldLight1MioOfLights(String instruction, String expected) {
        final Collection<Instruction> lines = List.of(toInstructionMapper.apply(instruction));
        BrightnessGridHandler lightsGridHandler = new BrightnessGridHandler(grid);
        lines.forEach(lightsGridHandler::applyInstruction);
        assertThat(grid.getBrightness(), is(equalTo(Integer.parseInt(expected))));
    }
}