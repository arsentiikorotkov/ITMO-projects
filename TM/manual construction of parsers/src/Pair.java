public class Pair<T, V> {
    private final T first;
    private final V second;

    public Pair(final T first, final V second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }
}
