package com.goopniggop.live.tasks;

import org.apache.commons.lang3.ArrayUtils;

import java.util.BitSet;

public class HouseSectionSearchAdditionalAlgorithm implements HouseSectionSearchable {
    @Override
    public int[] countDistance(int[] inputArray) {
        final BitSet bitSet = ArrayUtils.indexesOf(inputArray, 0);

        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i] == 0) {
                continue;
            }
            int min = Integer.MAX_VALUE;
            for (int d : bitSet.stream().toArray()) {
                min = Math.min(min, Math.abs(d - i));
            }
            inputArray[i] = min;
        }

        return inputArray;
    }
}
