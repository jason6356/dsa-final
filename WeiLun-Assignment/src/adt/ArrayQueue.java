package adt;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayQueue<T> implements QueueInterface<T>{

    private T[] array;
    private int first, last ;
    private static final int DEFAULT_CAPACITY = 10;
   
    
    public ArrayQueue(){
        first = last = 0;
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }
    
    @Override
    public void enqueue(T newEntry){
        if(isEmpty()){
            expandCapacity();
        }
        array[last++]=newEntry;
        
    }

    @Override
    public T dequeue(){
        T front = array[first];
        for(int i = first;i<last-1;i++){
            array[i] = array[i+1];
        }
        last--;
        return front;
    }

    @Override
    public T getFront(){
        return array[first];
    }

    @Override
    public boolean isEmpty(){
        return (last == 0);
    }

    @Override
    public void clear(){
        array = null;
    }
  
    @Override
    public void display(){
        if(isEmpty()||last==0){
            System.out.println("Queue is empty");
        }else{
            for(int i=0;i<last;i++){
                System.out.println(array[i]);
            }
        }
        
    }
    
    public int getSize(){
        return last;
    }
    
    private void expandCapacity() {
        array = Arrays.copyOf(array, array.length*2);
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayQueueIterator();
    }


    
 private class ArrayQueueIterator implements Iterator<T> {
    private int nextIndex;

    private ArrayQueueIterator() {
      nextIndex = 0;
    }

    @Override
    public boolean hasNext() {
      return nextIndex != last;
    }

    @Override
    public T next() {
      if (hasNext()) {
        T nextEntry = array[nextIndex];
        nextIndex++; // advance iterator
        return nextEntry;
      } else {
        return null;
      }
    }
  }
    
} 