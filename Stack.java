package Stack;

import java.lang.reflect.Array;
import java.util.EmptyStackException;

public class Stack<T> {
    private int top;
    private Object[] stack;
    /*Since it's generic, we can't make an instance  "stack = new T[]"
    * as we don't know T compile time. Making the array Object[] and
     * later casting is pretty safe, because it's private-access and we
     * only put elements of type T there.*/

    public Stack(int maxCapacity) {
        top = -1;
        stack = new Object[maxCapacity];
    }

    private void doubleCapacity() {
        Object[] doubledStack = new Object[stack.length * 2];

        for (int i = 0; i < stack.length; i++) {
            doubledStack[i] = stack[i];
        }
        stack = doubledStack;
    }

    public void push(T element) {
        if (isFull()) {
            doubleCapacity();
        }

        stack[++top] = element;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            T topElement = (T) stack[top];
            stack[top--] = null;
            return topElement;
        }
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            return (T) stack[top];
        }
    }

    public boolean isEmpty() {
        return top < 0;
    }

    private boolean isFull() {
        return top == stack.length - 1;
    }
}
