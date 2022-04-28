package form.patterns.queue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

@SuppressWarnings("unchecked")
public class CircleQueue<E> implements Queue<E> {
    private final int initialCapacity;
    private final Object[] arr;

    private int tail = -1;
    private int head=-1;

    public CircleQueue() {
        this(10);
    }

    public CircleQueue(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        arr = new Object[this.initialCapacity];
    }

    @Override
    public E poll() {
        if (head == tail) {
            return null;
        }
        return (E) arr[++head % initialCapacity];
    }

    @Override
    public void offer(E element) {
        tail = ++tail % initialCapacity;
        arr[tail] = element;
    }

    @Override
    public void empty() {
        tail = head = -1;
        Arrays.fill(arr, null);
    }

    @Override
    public int size() {
        return tail+1;
    }

    @Override
    public boolean isEmpty() {
        return tail == head;
    }

    @Override
    public boolean contains(Object o) {
        return Arrays.asList(arr).contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int current = head;
            @Override
            public boolean hasNext() {
                return current != tail;
            }

            @Override
            public E next() {
                return (E) arr[++current];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.stream(arr).toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        this.empty();
    }
}
