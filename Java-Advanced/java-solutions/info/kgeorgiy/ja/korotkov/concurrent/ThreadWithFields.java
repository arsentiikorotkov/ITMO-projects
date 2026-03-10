package info.kgeorgiy.ja.korotkov.concurrent;

import java.util.function.Supplier;

public class ThreadWithFields<R> {
    private R res = null;
    private final Thread thread;

    public ThreadWithFields(final Supplier<R> supplier) {
        this.thread = new Thread(() -> res = supplier.get());
    }

    public void start() {
        thread.start();
    }

    public Thread getThread() {
        return thread;
    }

    public R getRes() {
        return res;
    }
}
