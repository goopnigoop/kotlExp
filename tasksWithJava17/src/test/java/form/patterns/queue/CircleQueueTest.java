package form.patterns.queue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

class CircleQueueTest {

    private final Queue<Integer> queue = new CircleQueue<>(5);

    @Test
    @DisplayName("should return null")
    void pollFromEmptyQueue() {
        assertThat(queue.poll(), nullValue());
    }

    @Test
    @DisplayName("should return 3 elements from the queue")
    void pollFromQueue() {
        final int first = 1;
        queue.offer(first);
        final int second = 10;
        queue.offer(second);
        final int third = 2;
        queue.offer(third);
        assertThat(queue.poll(), is(first));
        assertThat(queue.poll(), is(second));
        assertThat(queue.poll(), is(third));
        assertThat(queue.poll(), nullValue());
    }

    @Test
    @DisplayName("should return 5 elements from the queue")
    void pollFromQueue5Elements() {
        IntStream.range(0, 10).forEach(queue::offer);
        assertThat(queue.poll(), is(5));
        assertThat(queue.poll(), is(6));
        assertThat(queue.poll(), is(7));
        assertThat(queue.poll(), is(8));
        assertThat(queue.poll(), is(9));
        assertThat(queue.poll(), nullValue());
    }

    @Test
    void shouldShowAllElements() {
        IntStream.range(0, 10).forEach(queue::offer);
        int count = 5;
        for (Integer integer : queue) {
            assertThat(integer, is(count++));
        }
    }

    @Test
    void empty() {
        queue.empty();
        assertThat(queue.size(), is(0));
    }
}