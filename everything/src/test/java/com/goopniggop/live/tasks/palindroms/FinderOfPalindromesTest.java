package com.goopniggop.live.tasks.palindroms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class FinderOfPalindromesTest {

    FinderOfPalindroms finder = new FinderOfPalindroms();

    private static Stream<Arguments> generator() {

        return Stream.of(
                Arguments.of(4, 1, 1001L),
                Arguments.of(5, 1, 10001L),
                Arguments.of(6, 1, 100001L),
                Arguments.of(6, 3, 102201L),
                Arguments.of(3, 90, 999L),
                Arguments.of(5, 3, 10201L));
    }

    @MethodSource("generator")
    @ParameterizedTest
    @DisplayName("should throw an exception if input string is not a math expression")
    void testKthPalindrome(int size, int number, long expected) {
        long result = finder.findKthElement(size, number);
        assertThat(result, is(expected));
    }
}
