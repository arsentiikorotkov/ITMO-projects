package info.kgeorgiy.ja.korotkov.concurrent;

import info.kgeorgiy.java.advanced.concurrent.ListIP;
import info.kgeorgiy.java.advanced.mapper.ParallelMapper;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementation of {@link ListIP} interface.
 *
 * @author Arsentiy Korotkov
 */
public class IterativeParallelism implements ListIP {
    private final ParallelMapper mapper;

    private <T, R> R calc(final int threads, final List<? extends T> values,
                          final Function<Stream<? extends T>, R> procFunc,
                          final Function<Stream<R>, R> redFunc) throws InterruptedException {
        validate(threads, values);

        if (threads == 0 || values.size() <= threads) {
            return processing(values.stream(), procFunc);
        }

        final List<List<? extends T>> microTasks = split(threads, values);
        if (mapper == null) {
            final List<ThreadWithFields<R>> threadList = new ArrayList<>(Collections.nCopies(threads, null));

            for (int index = 0; index < threads; index++) {
                final int i = index;

                ThreadWithFields<R> thread = new ThreadWithFields<>(() -> processing(microTasks.get(i).stream(), procFunc));
                threadList.set(i, thread);
                thread.start();
            }

            join(threadList);

            return reduce(threadList.stream().map(ThreadWithFields::getRes), redFunc);
        } else {
            return reduce(mapper.map(lst -> procFunc.apply(lst.stream()), microTasks).stream(), redFunc);
        }
    }

    private <T> List<List<? extends T>> split(final int threads, final List<? extends T> values) {
        final List<List<? extends T>> splitTasks = new ArrayList<>(Collections.nCopies(threads, null));
        final int sizeSubLists = values.size() / threads;
        final int cntEnlargedLists = values.size() % threads;

        for (int i = 0; i < threads; i++) {
            final int from = (cntEnlargedLists - (i + 1) >= 0) ?
                    (sizeSubLists * i) + i : (sizeSubLists * i) + cntEnlargedLists;
            final int to = (cntEnlargedLists - (i + 1) >= 0) ?
                    from + sizeSubLists + 1 : from + sizeSubLists;
            splitTasks.set(i, values.subList(from, to));
        }

        return splitTasks;
    }

    private <T, R> R processing(final Stream<? extends T> values, Function<Stream<? extends T>, R> procFunc) {
        return procFunc.apply(values);
    }

    private <R> R reduce(final Stream<R> results, final Function<Stream<R>, R> redFunc) {
        return redFunc.apply(results);
    }

    private void validate(final int threads, final List<?> values) throws IllegalArgumentException, NoSuchElementException {
        if (threads < 0) {
            throw new IllegalArgumentException("Number of threads cannot be negative. Calculate maximum is impossible.");
        }
        if (values.isEmpty()) {
            throw new NoSuchElementException("List of values is empty. Maximum does not exist.");
        }
    }

    private <R> void join(final List<ThreadWithFields<R>> threadList) throws InterruptedException {
        InterruptedException exception = null;
        for (int i = 0; i < threadList.size(); ) {
            try {
                threadList.get(i).getThread().join();
                i++;
            } catch (InterruptedException e) {
                if (exception == null) {
                    exception = e;
                } else {
                    exception.addSuppressed(e);
                }
            }
        }

        if (exception != null) {
            throw exception;
        }
    }

    /**
     * Returns maximum value.
     *
     * @param threads    number of concurrent threads.
     * @param values     values to get maximum of.
     * @param comparator value comparator.
     * @param <T>        value type.
     * @return maximum of given values
     * @throws InterruptedException             if executing thread was interrupted.
     * @throws java.util.NoSuchElementException if no values are given.
     */
    @Override
    public <T> T maximum(int threads, List<? extends T> values, Comparator<? super T> comparator) throws InterruptedException {
        return calc(threads, values,
                streamMapValues -> streamMapValues.max(comparator).orElseThrow(),
                streamResults -> streamResults.max(comparator).orElseThrow());
    }

    /**
     * Returns whether all values satisfy predicate.
     *
     * @param threads   number of concurrent threads.
     * @param values    values to test.
     * @param predicate test predicate.
     * @param <T>       value type.
     * @return whether all values satisfy predicate or {@code true}, if no values are given.
     * @throws InterruptedException if executing thread was interrupted.
     */
    @Override
    public <T> boolean all(int threads, List<? extends T> values, Predicate<? super T> predicate) throws InterruptedException {
        return calc(threads, values,
                streamMapValues -> streamMapValues.allMatch(predicate),
                streamResults -> streamResults.allMatch(Boolean::booleanValue));
    }

    /**
     * Returns number of values satisfying predicate.
     *
     * @param threads   number of concurrent threads.
     * @param values    values to test.
     * @param predicate test predicate.
     * @param <T>       value type.
     * @return number of values satisfying predicate.
     * @throws InterruptedException if executing thread was interrupted.
     */
    @Override
    public <T> int count(int threads, List<? extends T> values, Predicate<? super T> predicate) throws InterruptedException {
        return calc(threads, values,
                streamMapValues -> streamMapValues.filter(predicate).mapToInt(e -> 1).sum(),
                streamResults -> streamResults.reduce(0, Integer::sum));
    }

    /**
     * Returns minimum value.
     *
     * @param threads    number of concurrent threads.
     * @param values     values to get minimum of.
     * @param comparator value comparator.
     * @param <T>        value type.
     * @return minimum of given values
     * @throws InterruptedException             if executing thread was interrupted.
     * @throws java.util.NoSuchElementException if no values are given.
     */
    @Override
    public <T> T minimum(int threads, List<? extends T> values, Comparator<? super T> comparator) throws InterruptedException {
        return maximum(threads, values, comparator.reversed());
    }

    /**
     * Returns whether any of values satisfies predicate.
     *
     * @param threads   number of concurrent threads.
     * @param values    values to test.
     * @param predicate test predicate.
     * @param <T>       value type.
     * @return whether any value satisfies predicate or {@code false}, if no values are given.
     * @throws InterruptedException if executing thread was interrupted.
     */
    @Override
    public <T> boolean any(int threads, List<? extends T> values, Predicate<? super T> predicate) throws InterruptedException {
        return !all(threads, values, predicate.negate());
    }

    /**
     * Filters values by predicate.
     *
     * @param threads   number of concurrent threads.
     * @param values    values to filter.
     * @param predicate filter predicate.
     * @return list of values satisfying given predicate. Order of values is preserved.
     * @throws InterruptedException if executing thread was interrupted.
     */
    @Override
    public <T> List<T> filter(int threads, List<? extends T> values, Predicate<? super T> predicate) throws InterruptedException {
        return calc(threads, values,
                streamMapValues -> streamMapValues.filter(predicate).map(e -> (T) e).toList(),
                streamResults -> streamResults.flatMap(List::stream).toList());
    }

    /**
     * Maps values.
     *
     * @param threads number of concurrent threads.
     * @param values  values to map.
     * @param f       mapper function.
     * @return list of values mapped by given function.
     * @throws InterruptedException if executing thread was interrupted.
     */
    @Override
    public <T, U> List<U> map(int threads, List<? extends T> values, Function<? super T, ? extends U> f) throws InterruptedException {
        return calc(threads, values,
                streamMapValues -> streamMapValues.map(e -> (U) f.apply(e)).toList(),
                streamResults -> streamResults.flatMap(List::stream).toList());
    }

    /**
     * Join values to string.
     *
     * @param threads number of concurrent threads.
     * @param values  values to join.
     * @return list of joined results of {@link #toString()} call on each value.
     * @throws InterruptedException if executing thread was interrupted.
     */
    @Override
    public String join(int threads, List<?> values) throws InterruptedException {
        return calc(threads, values,
                streamMapValues -> streamMapValues.map(Object::toString).collect(Collectors.joining("")),
                streamMapValues -> streamMapValues.collect(Collectors.joining("")));
    }

    /**
     * The constructor from the mapper. Calculations will be performed using a mapper.
     *
     * @param mapper a current mapper
     */
    public IterativeParallelism(ParallelMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Default constructor. Calculations will be performed not using a mapper.
     */
    public IterativeParallelism() {
        this.mapper = null;
    }
}
