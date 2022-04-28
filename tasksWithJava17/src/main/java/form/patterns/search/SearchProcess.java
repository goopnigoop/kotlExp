package form.patterns.search;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SearchProcess {
    public static void main(String[] args) throws InterruptedException {

        final int[] arr = generateArray(100);
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        final Set<Number> objects = ConcurrentHashMap.newKeySet(100);

        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        final ConcurrentHashMap<Number, Boolean> concurrentHashMap = new ConcurrentHashMap<>();
//        IntStream.range(0, 100).boxed().map(i -> new Task(arr, concurrentHashMap, allFounds)).forEach(executorService::submit);

        executorService.shutdown();
        System.out.println("Was executor terminated: " + executorService.awaitTermination(3, TimeUnit.DAYS));

        System.out.println("All results" + concurrentHashMap);
        final Set<Number> results = concurrentHashMap.entrySet().stream().filter(Map.Entry::getValue)
                                                     .map(Map.Entry::getKey).collect(Collectors.toSet());
        System.out.println("True results: " + results);
        concurrentHashMap.entrySet().removeIf(entry -> !entry.getValue());
        System.out.println("All results after filtering" + concurrentHashMap);


        Executors.newCachedThreadPool();
  //      IntStream.range(0, 10000).boxed().
//                 map(i -> new Task(arr, concurrentHashMap, allFounds)).forEach(executorService::submit);

    }

    private static class Task implements Runnable {

        private final int[] arr;
        private final Map<Number, Boolean> mapWithResults;
        private final Set<Number> allFounds;

        private Task(int[] arr, ConcurrentHashMap<Number, Boolean> concurrentHashMap, Set<Number> allFounds) {
            this.arr = arr;
            mapWithResults = concurrentHashMap;
            this.allFounds = allFounds;
        }

        @Override
        public void run() {
            final int current = ThreadLocalRandom.current().nextInt(100);
            System.out.println("Thread with name " + Thread.currentThread().getName() + " is looking for " + current);
            final boolean doesArrayHasNumber = doesArrayHasNumber(current, arr);
            System.out.println("Did it found? - answer:" + doesArrayHasNumber);
            mapWithResults.merge(current, doesArrayHasNumber, (first, second) -> second);
        }
    }

    private static class TaskWithResult implements Callable<Boolean> {

        private final int[] arr;

        private TaskWithResult(int[] arr) {
            this.arr = arr;
        }

        @Override
        public Boolean call() {
            final int current = ThreadLocalRandom.current().nextInt(100);
            System.out.println("Thread with name " + Thread.currentThread().getName() + " is looking for " + current);
            final boolean doesContainNumber = doesArrayHasNumber(current, arr);
            System.out.println("Did it found? - answer:" + doesContainNumber);
            return doesContainNumber;
        }
    }

    private static boolean doesArrayHasNumber(int i, int[] arr) {
        final boolean isSorted = ArrayUtils.isSorted(arr);
        if (!isSorted) {
            Arrays.sort(arr);
        }
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int m = start - (start - end) / 2;
            final int current = arr[m];
            if (current == i) {
                return true;
            }
            if (i > current) {
                start = m + 1;
            } else {
                end = m - 1;
            }
        }
        return false;
    }

    private static int[] generateArray(int size) {
        final ThreadLocalRandom current = ThreadLocalRandom.current();
        final int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = current.nextInt(100);
        }
        return arr;
    }
}
