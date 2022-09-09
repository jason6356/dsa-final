package adt;

import java.util.Arrays;
import java.util.Iterator;


public class ArraySet<T> implements SetInterface<T> {

    private T[] array;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 5;

    public ArraySet(){
        this(DEFAULT_CAPACITY);
    }

    public ArraySet(int size){
        array = (T[]) new Object[size];
        numberOfEntries = 0;
    }

    @Override
    public boolean add(T newElement) {
        if(contains(newElement))
            return false;

        if(numberOfEntries >= array.length)
            extendArray();

        array[numberOfEntries++] = newElement;

        return true;
    }

    @Override
    public boolean remove(T anElement) {

        if(!contains(anElement))
            return false;

        for(int i = 0; i < numberOfEntries; i++)
            if(array[i].equals(anElement)) {
                shiftArrayToLeft(i);
                break;
            }

        numberOfEntries--;

        return true;
    }

    @Override
    public T[] toArray() {
        return array.clone();
    }

    @Override
    public boolean checkSubset(SetInterface<T> anotherSet) {

        if(anotherSet.size() >= numberOfEntries)
            return false;

        T[] temp = anotherSet.toArray();

        for(int i = 0; i < anotherSet.size(); i++)
            if(!contains(temp[i]))
                return false;

        return true;

    }
    @Override
    public void union(SetInterface<T> anotherSet) {

        if(anotherSet.isEmpty())
            return;

        for(T item: anotherSet.toArray())
            if(item != null)
                add(item);
    }

    @Override
    public SetInterface<T> intersection(SetInterface<T> anotherSet) {

        SetInterface<T> result = new ArraySet<>();

        for(T item : anotherSet.toArray())
            if(contains(item))
                result.add(item);

        return result;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }


    @Override
    public int size() {
        return numberOfEntries;
    }

    private void shiftArrayToLeft(int startingIndex){
        for(int i = startingIndex; i < numberOfEntries - 1; i++)
            array[i] = array[i+1];
    }

    private void extendArray() {

        int newSize = array.length * 2;
        array = Arrays.copyOf(array, newSize);
    }

    public boolean contains(T item) {
        
        for (int i = 0; i < numberOfEntries; i++)
            if(array[i].equals(item))
                return true;


        return false;
    }

    public String toString(){

        if(array.length < 1)
            return  "";

        StringBuilder result = new StringBuilder("[");
        for(int i = 0; i < numberOfEntries; i++)
            result.append(array[i]).append(",");

        result.append("]");

        return result.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new SetIterator();
    }

    @Override
    public void clear() {
        numberOfEntries = 0;
    }

    private class SetIterator implements Iterator<T>{

        int currIndex;

        public SetIterator() {
            currIndex = 0;
        }
        @Override
        public boolean hasNext() {
            return currIndex < numberOfEntries;
        }

        @Override
        public T next() {
            T result = array[currIndex];
            currIndex++;
            return result;
        }
    }
}
