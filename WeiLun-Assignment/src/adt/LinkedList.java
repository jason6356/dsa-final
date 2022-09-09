package adt;

import java.util.Iterator;

public class LinkedList<T> implements ListInterface<T>{
     private Node firstNode;             // reference to first node
    private int numberOfEntries;  	// number of entries in list
    
    
    public LinkedList(Node firstNode) {
        this.firstNode = firstNode;
        numberOfEntries++;
    }

    public LinkedList() {
    }

    @Override
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);

        if (contains(newEntry)) { //Added this check contains or not (Different with sample code)
            return false;
        } else if (isEmpty()) {
            firstNode = newNode;

            numberOfEntries++;
            return true;
        } else {
            Node currentNode = firstNode;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;

            numberOfEntries++;
            return true;
        }
    }

    //Recursive Search (Different with sample code)
    @Override
    public boolean contains(T anEntry) {
        return search(firstNode, anEntry);
    }

    private boolean search(Node currentNode, T anEntry) {
        boolean found;

        if (currentNode == null) {
            found = false;
        } else if (currentNode.data.equals(anEntry)) {
            found = true;
        } else {
            found = search(currentNode.next, anEntry);
        }

        return found;
    }

    @Override
    public boolean isEmpty() {
        boolean answer = false;

        if (numberOfEntries == 0) {
            answer = true;
        }

        return answer;
    }

    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    @Override
    public T getEntry(int givenPosition) {
        T result = null;

        if (givenPosition > numberOfEntries) {
            return null;
        }

        if (!(isEmpty()) && (givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            result = getNodeAt(givenPosition).data; //Changed this (Different with sample code)
        }
        return result;
    }

    @Override
    public T remove(int givenPosition) {
        T result = null;                 // return value

        if ((!isEmpty()) && (givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            if (givenPosition == 1) {      // case 1: remove first entry
                result = firstNode.data;     // save entry to be removed
                firstNode = firstNode.next;
            } else {                         // case 2: givenPosition > 1
                Node nodeBefore = getNodeAt(givenPosition - 1); //Changed from here (Different with sample code)
                Node nodeToRemove = nodeBefore.next;
                Node nodeAfter = nodeToRemove.next;
                nodeBefore.next = nodeAfter;
                result = nodeToRemove.data;
            }
            numberOfEntries--;
        } else {
            result = null;
        }

        return result; // return removed entry, or null if operation fails
    }

    //Get Node Position
    private Node getNodeAt(int givenPosition) { //Added this as private to get node position (Different with sample code)
        Node currentNode = firstNode;

        //Traverse the chain to get the entry
        for (int i = 1; i < givenPosition; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    @Override
    public boolean replace(int position, T newEntry) {
        boolean isSuccessful = true;

        if (newEntry == null || position < 1 || position > numberOfEntries) { //Added this to check position is null or greater and smaller
            isSuccessful = false;
        }

        if ((position >= 1) && (position <= numberOfEntries)) {
            Node currentNode = firstNode;
            for (int i = 0; i < position - 1; ++i) {
                currentNode = currentNode.next;		// advance currentNode to next node
            }
            currentNode.data = newEntry;	// currentNode is pointing to the node at givenPosition
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    @Override
    public void clear() {
        if (isEmpty()) {
            return;
        }

        firstNode = null;
        numberOfEntries = 0;
    }


    public Iterator<T> getIterator() {
        return new ListIterator();
    }
    
    @Override 
    public Iterator<T> iterator(){
        return new ListIterator();
    }

    @Override
    public boolean add(int newPosition, T newEntry) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isFull() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private class ListIterator implements Iterator<T> {

        private Node currentNode = firstNode;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            T currentData = null;
            if (hasNext()) {
                currentData = currentNode.data;
                currentNode = currentNode.next;
            }
            return currentData;
        }
    }
    
    public String toString() {
            int count = 1;
            String output = "";
            Node currentNode = firstNode;
            
            while (currentNode != null){
                output += "" + count + "." + " " +currentNode.data + "\n";
                //output += currentNode.data + "\n";
                currentNode = currentNode.next;
                count++;    
            }
            return output;
        }

    //Chapter 5 Node inner class
    private class Node {

        private T data;
        private Node next;

        private Node(T data) {
            this.data = data;
            this.next = null;
        }

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
