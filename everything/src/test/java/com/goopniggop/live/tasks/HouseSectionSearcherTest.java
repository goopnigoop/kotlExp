package com.goopniggop.live.tasks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class HouseSectionSearcherTest {
    private final HouseSectionSearcher houseSectionSearcher = new HouseSectionSearcher();

    public static Stream<Arguments> arraysToCheck() {
        return Stream.of(Arguments.of(new int[]{1, 3, 0, 4, 7}, new int[]{2, 1, 0, 1, 2}),
                         Arguments.of(new int[]{0, 1, 4, 9, 0}, new int[]{0, 1, 2, 1, 0}),
                         Arguments.of(new int[]{0, 7, 9, 4, 8, 20}, new int[]{0, 1, 2, 3, 4, 5}),
                         Arguments.of(new int[]{0, 0, 100, 0, 7, 0}, new int[]{0, 0, 1, 0, 1, 0}),
                         Arguments.of(new int[]{0, 0, 100, 0, 7, 0}, new int[]{0, 0, 1, 0, 1, 0}));
    }

    @ParameterizedTest(name = "{index}:should return {1} for the following array {0}")
    @MethodSource
    void arraysToCheck(int[] input, int[] expected) {
        final int[] result = houseSectionSearcher.countDistance(input);
        assertThat(result, is(expected));
    }
}