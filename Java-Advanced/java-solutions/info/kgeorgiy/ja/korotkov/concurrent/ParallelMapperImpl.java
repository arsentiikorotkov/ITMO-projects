package info.kgeorgiy.ja.korotkov.concurrent;

import info.kgeorgiy.java.advanced.mapper.ParallelMapper;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Implementation of {@link ParallelMapper} interface.
 *
 * @author Arsentiy Korotkov
 */
public class ParallelMapperImpl implements ParallelMapper {
    private final List<Thread> threadList = new ArrayList<>();
    private final Tasks tasks = new Tasks();

    private static final Function<Tasks, Runnable> RUN_TEMP = tasks -> () -> {
        try {
            while (!Thread.interrupted()) {
                tasks.pollTask().get().run();
            }
        } catch (InterruptedException ignored) {
        }
    };

    /**
     * Maps function {@code f} over specified {@code args}.
     * Mapping for each element performed in parallel.
     *
     * @throws InterruptedException if calling thread was interrupted
     */
    @Override
    public <T, R> List<R> map(Function<? super T, ? extends R> f, List<? extends T> args) throws InterruptedException {
        final List<R> results = new ArrayList<>(Collections.nCopies(args.size(), null));
        final ParallelException exception = new ParallelException();
        final ParallelCount count = new ParallelCount(args.size());

        tasks.addTasks(f, args, results, exception, count);

        count.waitMap();

        exception.throwException();

        return results;
    }

    /**
     * Stops all threads. All unfinished mappings are left in undefined state.
     */
    @Override
    public void close() {
        threadList.forEach(Thread::interrupt);
        for (int i = 0; i < threadList.size(); ) {
            try {
                threadList.get(i).join();
                i++;
            } catch (InterruptedException ignored) {
            }
        }
    }

    /**
     * Creates mapper by a given count of threads.
     *
     * @param threads a count of threads
     */
    public ParallelMapperImpl(final int threads) throws IllegalArgumentException {
        if (threads <= 0) {
            throw new IllegalArgumentException("Number of threads cannot be not positive. Calculate map is impossible.");
        }

        final Runnable runTemp = RUN_TEMP.apply(tasks);

        for (int i = 0; i < threads; i++) {
            threadList.add(new Thread(runTemp));
            threadList.get(i).start();
        }
    }

    private static class ParallelCount {
        private int count;

        public ParallelCount(int count) {
            this.count = count;
        }

        public synchronized void decrement() {
            count--;
            this.notify();
        }

        public synchronized void waitMap() throws InterruptedException {
            while (count > 0) {
                this.wait();
            }
        }
    }

    private static class ParallelException {
        private RuntimeException exception = null;

        public void throwException() throws RuntimeException {
            if (exception != null) {
                throw exception;
            }
        }

        public synchronized void update(RuntimeException exception) {
            if (this.exception == null) {
                this.exception = exception;
            }
            this.exception.addSuppressed(exception);
        }
    }

    private static class Tasks {
        private final Queue<Runnable> tasks = new ArrayDeque<>();

        public synchronized <T, R> void addTasks(final Function<? super T, ? extends R> f, final List<? extends T> args,
                                                 final List<R> results, final ParallelException exception,
                                                 final ParallelCount count) {
            for (int index = 0; index < args.size(); index++) {
                final int i = index;

                tasks.add(() -> {
                    try {
                        results.set(i, f.apply(args.get(i)));
                    } catch (RuntimeException e) {
                        exception.update(e);
                    }

                    count.decrement();
                });
            }

            this.notify();
        }

        public synchronized Supplier<Runnable> pollTask() throws InterruptedException {
            while (tasks.isEmpty()) {
                this.wait();
            }
            final Runnable task = tasks.poll();
            this.notify();
            return () -> task;
        }
    }
}
