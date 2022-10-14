package datastructures.worklists;


import cse332.interfaces.worklists.LIFOWorkList;

import java.util.NoSuchElementException;

/**
 * See cse332/interfaces/worklists/LIFOWorkList.java
 * for method specifications.
 */
public class ArrayStack<E> extends LIFOWorkList<E> {
    private int top;
    private int size;
    private int capacity;
    private E[] array;

    public ArrayStack() {
        this.top = -1;
        this.capacity = 10;
        this.array = (E[]) new Object[capacity];
    }

    @Override
    public void add(E work) {
        if (top == capacity - 1) {
            capacity = capacity * 2;
            E[] anotherArray = (E[]) new Object[capacity];
            for (int i = 0; i < capacity / 2; i++) {
                anotherArray[i] = array[i];
            }
            array = anotherArray;
            anotherArray = null;


        }
        array[top+1] = work;

        top++;
        size++;


    }

    @Override
    public E peek() {
        if (!hasWork()) {
            throw new NoSuchElementException();
        }
        return array[top];
    }

    @Override
    public E next() {
        if (!hasWork()) {
            throw new NoSuchElementException();
        }
        E return_value = peek();
        array[top] = null;

        top--;
        size--;
        return return_value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        capacity = 10;
        array = (E[]) new Object[capacity];
        top = 0;
    }
}
