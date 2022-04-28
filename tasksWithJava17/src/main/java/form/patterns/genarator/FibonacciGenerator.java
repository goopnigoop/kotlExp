package form.patterns.genarator;

public class FibonacciGenerator<E> implements Generator<Integer> {

    private int first = 0;
    private int second = 1;

    @Override
    public synchronized Integer next() {
        int toReturn = first;
        first = second;
        second = toReturn + second;
        return toReturn;
    }

    @Override
    public void clear() {
        first = 0;
        second = 1;
    }
}
