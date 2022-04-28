package form.patterns.queue;

import java.util.Collection;

public interface Queue<E> extends Collection<E> {
    E poll();

    void offer(E element);

    void empty();

}
