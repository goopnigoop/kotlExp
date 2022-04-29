package com.goopniggop.live.tasks;

import org.apache.commons.lang3.ArrayUtils;

import static org.apache.commons.lang3.math.NumberUtils.min;

/**
 * Michael wants to build a house on a street that consists of N equal sections. Each section either has a house built in it or it is empty. Michael searches for a section to build his house in. He is very friendly and desires to live closer to other people. To optimize a selection of a section to build his house Michael decides to determine a distance from every house to the closest empty section. You should help him doing this.
 * <p>
 * Input data:
 * <p>
 * Integer number N which determines the number of sections on a street.
 * Array of N integer numbers where every number is a number of a house. Houses are built in random order. Number 0 represents empty section (with no house built in it). At least one value of 0 exists in the array.
 * <p>
 * Design an algorithm that finds a distance from every house to the closest empty section.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * 5
 * 0 1 4 9 0
 * Output:
 * 0 1 2 1 0
 * <p>
 * Example 2:
 * <p>
 * Input:
 * 7
 * 0 7 9 4 8 20
 * Output:
 * 0 1 2 3 4 5
 */
public class HouseSectionSearcher {
    public int[] countDistance(int[] inputArray) {
        final int[] clone = ArrayUtils.clone(inputArray);
        processFirst(clone);
        processSecond(clone);
        return clone;
    }

    private void processFirst(int[] clone) {
        int d = clone.length;
        for (int i = 0; i < clone.length; i++) {
            if (clone[i] == 0) {
                d = 0;
                continue;
            }
            clone[i] = ++d;
        }
    }

    private void processSecond(int[] clone) {
        int d = clone.length;
        for (int i = clone.length-1; i >= 0; i--) {
            if (clone[i] == 0) {
                d = 0;
                continue;
            }
            clone[i] = min(clone[i],++d);
        }
    }
}
