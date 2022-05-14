package com.goopniggop.live.tasks.text;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class TextProcessorTest {
    TextProcessor textProcessor = new TextProcessor();

    @Test
    @DisplayName("should sort incoming String")
    void sortTest() {
        final String s = generateString(100000);
        final String sort = textProcessor.sort(s);
        assertThat(ArrayUtils.isEmpty(sort.toCharArray()), is(false));
        assertThat(ArrayUtils.isSorted(sort.toCharArray()), is(true));
    }

    private String generateString(int size) {
        char[] res = new char[size];
        for (int i = 0; i < size; i++) {
            res[i] = (char) (ThreadLocalRandom.current().nextInt(26) + 'a');
        }
        return new String(res);
    }
}