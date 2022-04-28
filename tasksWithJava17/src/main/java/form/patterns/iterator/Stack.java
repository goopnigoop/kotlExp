package form.patterns.iterator;

public interface Stack<E> {
    void push(E element);
    E pop();
    void empty();
    E peek();
    int search(E element);
    int size();
}
