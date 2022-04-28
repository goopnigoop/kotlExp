package form.patterns.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.IntStream;

import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CustomStackTest {

    private final CustomStack<Integer> stack = new CustomStack<>();

    @BeforeEach
    void beforeEach() {
        stack.empty();
    }

    @Test
    @DisplayName("should contain 19 elements")
    void testPush19Elements() {
        IntStream.range(0, 19).boxed().forEach(stack::push);
        assertThat(stack.size(), is(19));
        assertThat(stack.toString(), is(Arrays.toString(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18})));
    }

    @Test
    @DisplayName("should contain 20 elements")
    void testPush20Elements() {
        IntStream.range(0, 20).boxed().forEach(stack::push);
        assertThat(stack.size(), is(20));
        assertThat(stack.toString(), is(Arrays.toString(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19})));
    }

    @Test
    @DisplayName("should push 3 elements and them pop them")
    void pop() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertThat(stack.pop(), is(3));
        assertThat(stack.size(), is(2));
        assertThat(stack.pop(), is(2));
        assertThat(stack.pop(), is(1));
        assertThat(stack.size(), is(0));
    }

    @Test
    @DisplayName("should empty stack")
    void empty() {
        pushNElements(30);
        assertThat(stack.size(), is(30));
        stack.empty();
        assertThat(stack.size(), is(0));
        assertThat(stack.toString(), is(emptyList().toString()));
    }

    @Test
    @DisplayName("should show the last element")
    void peek() {
        pushNElements(30);
        assertThat(stack.pop(), is(29));
        assertThat(stack.peek(), is(28));
        assertThat(stack.peek(), is(28));
        assertThat(stack.peek(), is(28));
        assertThat(stack.size(), is(29));
    }

    @Test
    @DisplayName("should find the 15th element")
    void search() {
        pushNElements(30);
        assertThat(stack.search(30), is(-1));
        assertThat(stack.search(29), is(1));
        assertThat(stack.search(1), is(29));
        assertThat(stack.search(0), is(30));
    }

    @Test
    @DisplayName("should iterate through 11 elements")
    void iterateThroughElevenElements() {
        pushNElements(11);
        final ArrayList<Integer> list = new ArrayList<>();
        stack.forEach(list::add);
        assertThat(list, hasSize(11));
        assertThat(list, containsInRelativeOrder(10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0));
    }

    @Test
    @DisplayName("should throw an exception")
    void shouldThrowException() {
        assertThrows(CustomStack.StackIsEmptyException.class, stack::pop);
    }

    private void pushNElements(int nElements) {
        IntStream.range(0, nElements).forEach(stack::push);
    }
}