package Heap;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class Heap<T extends Comparable<T>> {
    private T[] heapArr;
    private int maxCapacity;
    private int size;

    @SuppressWarnings("unchecked")
    public Heap(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        size = 0;
        this.heapArr =(T[]) new Comparable[maxCapacity];
    }

    @SuppressWarnings("unchecked")
    public void insertKey(T key) {
        ensureEnoughCapacity();
        int i = size;
        size++;
        heapArr[i] = key;
        while (i > 0 && key.compareTo(heapArr[parent(i)]) < 0) {
            swap(parent(i), i);
            i = parent(i);
        }
    }

    @SuppressWarnings("unchecked")
    private void decreaseKey(int keyInd, T newVal) {
        if (newVal.compareTo(heapArr[keyInd]) > 0) {
            throw new IllegalArgumentException(
                    "The argument key is bigger than the value of the key at keyInd"
            );
        } else {
            heapArr[keyInd] = newVal;
            while(keyInd != 0 && heapArr[parent(keyInd)].compareTo(heapArr[keyInd]) > 0) {
                swap(parent(keyInd), keyInd);
                keyInd = parent(keyInd);
            }
        }
    }

    public T extractMin() {
        if (size <= 0) {
            throw new NoSuchElementException("The heap is empty!");
        } else if (size == 1) {
            size--;
            return heapArr[0];
        } else {
            T root = heapArr[0];
            heapArr[0] = heapArr[size - 1];
            size--;
            MinHeapify(0);
            return root;
        }
    }

    public boolean deleteKey(T value) {
        for (int i = 0; i < heapArr.length; i++)
        {
            if (value.equals(heapArr[i]))
            {
                swap(i, size - 1);
                heapArr[size - 1] = null;
                size--;
                MinHeapify(i);
                return true;
            }
        }
        return false;
    }

    private void MinHeapify(int index) {
        int lChild = leftChildInd(index);
        int rChild = rightChildInd(index);
        int smallest = index;

        if (lChild < size && heapArr[lChild].compareTo(heapArr[smallest]) < 0) {
            smallest = lChild;
        }
        if (rChild < size && heapArr[rChild].compareTo(heapArr[smallest]) < 0) {
            smallest = rChild;
        }
        if (smallest != index) {
            swap(smallest, index);
            MinHeapify(smallest);
        }
    }

    public int getSize() {
        return this.size;
    }

    private void ensureEnoughCapacity() {
        if (size == maxCapacity - 1) {
            heapArr = Arrays.copyOf(heapArr, maxCapacity * 2);
            maxCapacity *= 2;
        }
    }

    @SuppressWarnings("unchecked")
    private void swap(int firstInd, int secondInd) {
        T tempElement = (T) heapArr[firstInd];
        heapArr[firstInd] = heapArr[secondInd];
        heapArr[secondInd] = tempElement;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChildInd(int index) {
        return 2 * index + 1;
    }

    private int rightChildInd(int index) {
        return 2 * index + 2;
    }
}
