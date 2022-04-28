package form.patterns.genarator;

public interface Generator<E> {
    E next();

    void clear();
}
