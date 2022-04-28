package form.patterns.sort;

import java.util.Arrays;

public class First {
    private final static int[] arr = new int[]{1, 5, 123, 34413, 9, 4, 6, 2, 1, 6, 10, 143, 3, 4};

    public static int[] sort() {
        final int[] toSort = Arrays.copyOf(arr, arr.length);
        for (int i = 0; i < toSort.length; i++) {
            boolean wasReplaced = false;
            for (int j = 0; j < toSort.length - 1; j++) {
                if (toSort[j] > toSort[j + 1]) {
                    int t = toSort[j];
                    toSort[j] = toSort[j + 1];
                    toSort[j + 1] = t;
                    wasReplaced = true;
                }
            }
            if (!wasReplaced) {
                break;
            }
        }
        return toSort;
    }

    public static int[] sortN() {
        final int[] toSort = Arrays.copyOf(arr, arr.length);
        for (int i = 0; i < toSort.length; i++) {
            for (int j = i + 1; j < toSort.length; j++) {
                if (toSort[i] > toSort[j]) {
                    int t = toSort[i];
                    toSort[i] = toSort[j];
                    toSort[j] = t;
                }
            }
        }
        return toSort;
    }

    public static int[] sortInsert() {
        final int[] toSort = Arrays.copyOf(arr, arr.length);
        for (int i = 1; i < toSort.length; i++) {
            for (int j = i; j > 0; j--) {
                if (toSort[j] < toSort[j-1]) {
                    int t = toSort[j];
                    toSort[j] = toSort[j-1];
                    toSort[j-1] = t;
                }
            }
        }
        return toSort;
    }

    public static int[] quicksort() {
        final int[] toSort = Arrays.copyOf(arr, arr.length);
        int first = 0;
        int last = toSort.length - 1;
        qsort(first, last, toSort);
        return toSort;
    }


    private static void qsort(int first, int last, int[] arr) {
         int h = first;
         int l = last;
        int m = l + (h - l) / 2;
        final int middle = arr[m];
        while (h <= l) {
            while (arr[h] < middle) {
                h++;
            }
            while (arr[l] > middle) {
                l--;
            }
            if (h <= l) {
                int t = arr[h];
                arr[h] = arr[l];
                arr[l] = t;
                h++;
                l--;
            }
            if (h < last) {
                qsort(h,last,arr);
            }
            if (first < l) {
                qsort(first, l, arr);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(sort()));
        System.out.println(Arrays.toString(sortN()));
        System.out.println(Arrays.toString(sortInsert()));
        System.out.println(Arrays.toString(quicksort()));
    }
}
