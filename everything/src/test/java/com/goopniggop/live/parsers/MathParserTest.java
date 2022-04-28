package com.goopniggop.live.parsers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MathParserTest {

    private final MathParser parser = new MathParser();

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    @DisplayName("should throw an exception if input is an empty string")
    void parseEmptyString(String emptyStrings) {
        assertThrows(IllegalArgumentException.class, () -> parser.parse(emptyStrings), "The input is empty string");
    }

    @ParameterizedTest
    @ValueSource(strings = {"12 d 34", " 12 + w", "f/12   "})
    @DisplayName("should throw an exception if input string is not a math expression")
    void parseWrongInput(String wrongInput) {
        assertThrows(IllegalArgumentException.class, () -> parser.parse(wrongInput), "Wrong input string");
    }

    @ParameterizedTest
    @ValueSource(strings = {"12 & 5", "4     %5"})
    @DisplayName("should throw an exception if input doesnt have proper action ")
    void parseWrongAction(String wrongInput) {
        assertThrows(IllegalArgumentException.class, () -> parser.parse(wrongInput), "Action from the input is wrong");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1+2", "4-1", "1*3", "9   /   3"})
    @DisplayName("should parse string and show the result 3")
    void parse(String input) {
        assertThat(parser.parse(input), is(3L));
    }
}