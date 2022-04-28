package form.patterns.genarator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInRelativeOrder;
import static org.hamcrest.Matchers.hasSize;

class FibonacciGeneratorTest {
    private final Generator<Integer> generator = new FibonacciGenerator();

    @BeforeEach
    void clear() {
        generator.clear();
    }

    @Test
    void shouldReturnFirstTenFibonacciNumbers() {
        final List<Integer> limit = IntStream.generate(generator::next).boxed().limit(10).toList();
        assertThat(limit, hasSize(10));
        assertThat(limit, containsInRelativeOrder(0, 1, 1, 2, 3, 5, 8, 13, 21, 34));
    }

    @DisplayName("should return 12 fibonacci numbers in 4 threads")
    @RepeatedTest(1000)
    void shouldReturnFirstTwentyFibonacciNumbers() throws ExecutionException, InterruptedException {

        final Set<CompletableFuture<List<Integer>>> collect = IntStream.range(0, 4).boxed()
                                                                       .map(i -> CompletableFuture.supplyAsync(() -> IntStream.generate(generator::next)
                                                                                                                              .boxed()
                                                                                                                              .limit(3)
                                                                                                                              .collect(Collectors.toList())))
                                                                       .collect(Collectors.toSet());

        final CompletableFuture<List<Integer>> setCompletableFuture = CompletableFuture.allOf(collect.toArray(CompletableFuture[]::new))
                                                                                       .thenApply(list -> collect.stream()
                                                                                                                 .map(CompletableFuture::join)
                                                                                                                 .flatMap(List::stream)
                                                                                                                 .sorted()
                                                                                                                 .collect(Collectors.toList()));

        final List<Integer> integers = setCompletableFuture.get();

        assertThat(integers, hasSize(12));
        assertThat(integers, containsInRelativeOrder(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89));
    }
}