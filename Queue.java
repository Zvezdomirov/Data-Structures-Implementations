package Queue;

import java.util.NoSuchElementException;

public class Queue<T> {
    private int head, tail;
    private int maxCapacity, size;
    private Object[] queue;

    public Queue(int maxCapacity) {
        queue = new Object[maxCapacity];
        this.maxCapacity = maxCapacity;
        head = 0;
        tail = maxCapacity - 1;
    }

    public void add(T element) {
        if (isFull()) {
            throw new StackOverflowError();
        } else {
            /*This way of calculating the index prevents
            * IndexOutOfBounds Exception, because it resets
            * to 0 after reaching the end of the array*/
            tail = (tail + 1) % maxCapacity;
            queue[tail] = element;
            size++;
        }
    }

    @SuppressWarnings("unchecked")
    public T remove() {
        T element;
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            element = (T) queue[head];
            queue[head] = null;
            size--;
            head = (head + 1) % maxCapacity;
            return element;
        }
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return (T) queue[head];
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == maxCapacity;
    }
}