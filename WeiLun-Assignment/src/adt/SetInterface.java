package adt;

public interface SetInterface<T> extends Iterable<T>{

    boolean add(T newElement);
    boolean remove(T anElement);
    boolean checkSubset(SetInterface<T> anotherSet);
    void union(SetInterface<T> anotherSet);
    SetInterface<T> intersection(SetInterface<T> anotherSet);
    boolean isEmpty();

    int size();

    //boolean contains(T item);

    T[] toArray();

    void clear();
}
