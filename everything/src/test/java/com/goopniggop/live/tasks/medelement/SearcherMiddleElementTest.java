package com.goopniggop.live.tasks.medelement;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


class SearcherMiddleElementTest {
    SearcherMiddleElement searcherMiddleElement = new SearcherMiddleElement();

    public static Stream<Arguments> findIn() {
        return Stream.of(Arguments.of(new LinkedList<>(List.of(1, 4, 6, 10, 100)), 6),
                         Arguments.of(new LinkedList<>(List.of(1, 4, 65, 1, 10, 100)), 65));
    }

    @ParameterizedTest(name = "{index}:should return {1} for the following linked list {0}")
    @MethodSource
    @DisplayName("should find middle element in the list")
    void findIn(LinkedList<Integer> list, Integer integer) {
        final Integer result = searcherMiddleElement.findIn(list);
        assertThat(result, is(integer));
    }
}