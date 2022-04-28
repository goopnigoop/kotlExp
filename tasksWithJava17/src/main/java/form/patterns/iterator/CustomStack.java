package form.patterns.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

@SuppressWarnings("unchecked")
public class CustomStack<E extends Comparable<E>> implements Stack<E>, Iterable<E> {

    private Object[] elements = new Object[10];

    private int position = 0;

    @Override
    public String toString() {
        return Arrays.stream(elements).filter(Objects::nonNull).toList().toString();
    }

    @Override
    public void push(E element) {
        final int length = elements.length;
        if (length - position == 1) {
            final Object[] dest = new Object[length * 2];
            System.arraycopy(elements, 0, dest, 0, length);
            elements = dest;
        }
        elements[position++] = element;
    }

    @Override
    public E pop() {
        if (position == 0) {
            throw new StackIsEmptyException("Stack is empty");
        }
        final E elementToReturn = (E) elements[--position];
        elements[position] = null;
        return elementToReturn;
    }

    @Override
    public void empty() {
        elements = new Object[10];
        position = 0;
    }

    @Override
    public E peek() {
        return (E) elements[position - 1];
    }

    @Override
    public int search(E element) {
        if (position == 0) {
            return -1;
        }

        for (int i = 1; i <= position; i++) {
            if (elements[position - i].equals(element)) {
                return i;
            }
        }

        return -1;

    }

    @Override
    public int size() {
        return position;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int start = position;

            @Override
            public boolean hasNext() {
                return start != 0;
            }

            @Override
            public E next() {
                return (E) elements[--start];
            }
        };
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<E> spliterator() {
        return Iterable.super.spliterator();
    }

    public static class StackIsEmptyException extends RuntimeException {
        public StackIsEmptyException(String message) {
            super(message);
        }
    }
}
