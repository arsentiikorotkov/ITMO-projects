package info.kgeorgiy.ja.korotkov.arrayset;

import java.util.*;

public class ArraySet<E> extends AbstractSet<E> implements SortedSet<E> {

    private final List<E> list;

    private final Comparator<? super E> comparator;

    public ArraySet() {
        this(Collections.emptyList(), null);
    }

    public ArraySet(Comparator<? super E> comparator) {
        this(Collections.emptyList(), comparator);
    }

    public ArraySet(Collection<E> collection) {
        this(collection, null);
    }

    public ArraySet(Collection<E> collection, Comparator<? super E> comparator) {
        this.comparator = comparator;

        TreeSet<E> sortedList = new TreeSet<>(comparator);
        sortedList.addAll(collection);
        list = List.copyOf(sortedList);
    }

    private ArraySet(List<E> subSet, Comparator<? super E> comparator) {
        this.comparator = comparator;

        list = subSet;
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Comparator<? super E> comparator() {
        return comparator;
    }

    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        if (compare(fromElement, toElement) > 0) throw new IllegalArgumentException("fromKey > toKey");
        return subSet(fromElement, toElement, false);
    }

    private SortedSet<E> subSet(E fromElement, E toElement, boolean toInclusive) {
        if (fromElement == null || toElement == null)
            throw new NullPointerException("No null can be passed into method");
        int l = indexOf(fromElement);
        int r = indexOf(toElement);
        if (toInclusive) r++;
        return new ArraySet<>(list.subList(l, r), comparator);
    }

    private int indexOf(E element) {
        int x = Collections.binarySearch(list, element, comparator);
        return x >= 0 ? x : -x - 1;
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        return list.isEmpty() ? this : subSet(first(), toElement, false);
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        return list.isEmpty() ? this : subSet(fromElement, last(), true);
    }

    @Override
    public E first() {
        if (list.isEmpty()) throw new NoSuchElementException("Empty set");
        return list.get(0);
    }

    @Override
    public E last() {
        if (list.isEmpty()) throw new NoSuchElementException("Empty set");
        return list.get(list.size() - 1);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object o) {
        E elem = (E) o;
        return Collections.binarySearch(list, elem, comparator) >= 0;
    }

    @SuppressWarnings("unchecked")
    protected final int compare(E k1, E k2) {
        return comparator == null ? ((Comparable<? super E>) k1).compareTo(k2) : comparator.compare(k1, k2);
        //return comparator == null ? Comparator.naturalOrder().compare(k1, k2) : comparator.compare(k1, k2);
    }

}
