package com.goopniggop.live.tasks.palindroms;

public class FinderOfPalindroms {
    public long findKthElement(int size, int number) {
        int half = (size + 1) / 2;
        long startHalf = (long) Math.pow(10, half - 1d);
        long val = number - 1 + startHalf;
        long temp = val;
        if ((size & 1) == 1) {
            temp /= 10;
        }
        while (val > 0) {
            temp = temp * 10 + val % 10;
            val /= 10;
        }
        return temp;
    }
}
