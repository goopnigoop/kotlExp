package form.patterns.task;

import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

public class Fibonacci {
    public static void main(String[] args) throws InterruptedException {
        final Thread thread = new Thread(() -> {
            System.out.println(getNFibonacci(1000));
            System.out.println(Thread.currentThread().getName());
        });
        final Thread second = new Thread(() -> {
            System.out.println(getNFibonacciRecursion(1000));
            System.out.println(Thread.currentThread().getName());
        });
        thread.start();
        thread.join();
        second.start();
        second.join();
        System.out.println("done");
    }

    private static List<Integer> getNFibonacci(int i) {
        return IntStream.range(0, i).map(Fibonacci::getNNumber).boxed().toList();
    }

    private static List<Integer> getNFibonacciRecursion(int i) {
        final HashMap<Integer, Integer> cache = new HashMap<>();
        return IntStream.range(0, i).map(index -> getNNumberRecursion(index, cache)).boxed().toList();
    }

    private static Integer getNNumberRecursion(int index, HashMap<Integer, Integer> cache) {
        if (cache.containsKey(index)) {
            return cache.get(index);
        }
        if (index == 0) {
            return 0;
        }
        if (index == 1) {
            return 1;
        }
        final int current = getNNumberRecursion(index - 1, cache) + getNNumberRecursion(index - 2, cache);
        cache.put(index, current);
        return current;
    }

    private static Integer getNNumber(int index) {
        if (index == 0) {
            return 0;
        }
        if (index == 1) {
            return 1;
        }
        int a = 0;
        int b = 1;
        int next = 0;
        for (int i = 2; i <= index; i++) {
            next = a + b;
            a = b;
            b = next;
        }
        return next;
    }

}
