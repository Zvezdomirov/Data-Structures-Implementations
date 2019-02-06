package SinglyLinkedList;

import java.util.Iterator;

public class SinglyLinkedList<T> implements Iterable<T>{
    private Node<T> head;
    private int size;


    //The node class is private because it's an implementation detail
    private static class Node<T> {
        private T data;
        private Node next;

        Node(T data) {
            this.data = data;
            next = null;
        }
    }

    public SinglyLinkedList() {
        head = null;
        size = 0;
    }

    public SinglyLinkedList(T element) {
        head = new Node<T>(element);
        size = 1;
    }

    public void insert(T element) {
        if (this.head == null) {
            this.head = new Node<T>(element);
        }

        else {
            Node prevNode = this.head;
            Node newNode = new Node<T>(element);

            while (prevNode.next != null) {
                prevNode = prevNode.next;
            }

            prevNode.next = newNode;
        }
        size++;
    }

    @SuppressWarnings("unchecked")
    public boolean delete(T element){
        Node<T> currentNode = head;
        Node<T> prevNode = null;

        //check if head contains the element
        if (currentNode != null && currentNode.data == element) {
            this.head = currentNode.next;
            size--;
            return true;
        }

        //linear search for the element
        while (currentNode != null && currentNode.data != element) {
            prevNode = currentNode;
            currentNode = currentNode.next;
        }

        //deleting the element
        if (currentNode != null){
            prevNode.next = currentNode.next;
            size--;
            return true;
        }

        return false;
    }

    public int getSize(){
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {

        private Node<T> currentNode;

        public ListIterator(){
            currentNode = head;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if (!hasNext()) throw new IndexOutOfBoundsException();

            Node<T> temp = currentNode;
            currentNode = currentNode.next;

            return temp.data;
        }
    }
}

