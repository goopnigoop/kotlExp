package com.goopnigoop.fifteen.sixth;

import com.goopnigoop.fifteen.sixth.impl.LightsGridHandler;
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

class LightsGridTest {

    private static final String resourceName = "sixth";

    LightsGrid grid = new LightsGrid(1000);
    Function<String, Instruction> toInstructionMapper = new ToInstructionMapper();
    private static File file;

    @BeforeAll
    static void beforeAll() {
        ClassLoader classLoader = LightsGridTest.class.getClassLoader();
        file = new File(Objects.requireNonNull(classLoader.getResource(resourceName)).getFile());
    }

    @Test
    void shouldReturnProperValueAfterInstructionsApplied() throws IOException {
        final Collection<Instruction> lines = Files.lines(file.toPath())
                                                   .map(toInstructionMapper)
                                                   .toList();
        LightsGridHandler lightsGridHandler = new LightsGridHandler(grid);
        lines.forEach(lightsGridHandler::applyInstruction);
        assertThat(grid.getLitLights(), is(equalTo(543903)));
    }

    @ParameterizedTest
    @CsvSource(value = {"turn on 0,0 through 999,999:1000000",
            "turn on 0,0 through 2,2:9"}, delimiter = ':')
    void shouldLight1MioOfLights(String instruction, String expected) {
        final Collection<Instruction> lines = List.of(toInstructionMapper.apply(instruction));
        LightsGridHandler lightsGridHandler = new LightsGridHandler(grid);
        lines.forEach(lightsGridHandler::applyInstruction);
        assertThat(grid.getLitLights(), is(equalTo(Integer.parseInt(expected))));
    }
}