package datastructures.worklists;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.FixedSizeFIFOWorkList;

import java.util.NoSuchElementException;

/**
 * See cse332/interfaces/worklists/FixedSizeFIFOWorkList.java
 * for method specifications.
 */
public class CircularArrayFIFOQueue<E> extends FixedSizeFIFOWorkList<E> {

    private int capacity;
    private int front;
    private int back;
    private E array[];
    private int size;
    //private E array[] = (E[]) new Object[capacity];

    public CircularArrayFIFOQueue(int capacity) {
        super(capacity);
        array = (E[]) new Object[capacity];
        this.capacity = capacity;
        this.front = 0;
        this.back = 0;
        this.size = 0;
    }

    @Override
    public void add(E work) {
        if (isFull()) {
            throw new IllegalStateException();
        }

        array[back] = work;
        back = (back + 1) % capacity;
        size++;


    }


    @Override
    public E next() {
        if (!hasWork()) {
            throw new NoSuchElementException();
        }
        E value = array[front];
        array[front] = null;

        front = (front + 1) % capacity;
        size--;


        return value;
    }


    @Override
    public void update(int i, E value) {
       // array[i] = value;

       // back = (back + 1) % capacity;
        i = i % capacity;
        array[i] = value;
    }

    @Override
    public E peek() {
        if (!hasWork()) {
            throw new NoSuchElementException();
        }


        return array[front];
    }

    @Override
    public E peek(int i) {
        if (!hasWork()) {
            throw new NoSuchElementException();
        }
        if (i < 0 || size() <= i) {
            throw new IndexOutOfBoundsException();
        }


        return array[front+i];
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array = (E[]) new Object[capacity];

        front = 0;
        back = 0;
        size = 0;
    }

    @Override
    public int compareTo(FixedSizeFIFOWorkList<E> other) {
        // You will implement this method in project 2. Leave this method unchanged for project 1.
        throw new NotYetImplementedException();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        // You will finish implementing this method in project 2. Leave this method unchanged for project 1.
        if (this == obj) {
            return true;
        } else if (!(obj instanceof FixedSizeFIFOWorkList<?>)) {
            return false;
        } else {
            // Uncomment the line below for p2 when you implement equals
            // FixedSizeFIFOWorkList<E> other = (FixedSizeFIFOWorkList<E>) obj;

            // Your code goes here

            throw new NotYetImplementedException();
        }
    }

    @Override
    public int hashCode() {
        // You will implement this method in project 2. Leave this method unchanged for project 1.
        throw new NotYetImplementedException();
    }
}
