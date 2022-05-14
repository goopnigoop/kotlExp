package com.goopniggop.live.tasks.text;

public class TextProcessor {
    String sort(String input) {
        int[] res = new int[200];
        final char[] chars = input.toCharArray();
        for (char aChar : chars) {
            res[aChar]++;
        }
        char[] output = new char[input.length()];
        int counter = 0;
        for (int i = 0; i < res.length; i++) {
            if (res[i] == 0) {
                continue;
            }
            for (int j = 0; j < res[i]; j++) {
                output[counter++] = (char) i;
            }
        }
        return new String(output);
    }
}
