package BinarySearchTree;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BinarySearchTree<T extends Comparable<T>> {
    private TreeNode<T> root;

    public BinarySearchTree() {
        this.root = null;
    }

    public BinarySearchTree(T element) {
        this.root = new TreeNode<>(element);
    }

    public void add(T element) {
        if (root == null) {
            root = new TreeNode<>(element);
        } else {
            root.insert(element);
        }
    }

    public boolean contains(T element) {
        TreeNode<T> currentNode = root;

        while (currentNode != null) {
            if (currentNode.data.compareTo(element) == 0) {
                return true;
            } else if (currentNode.data.compareTo(element) < 0) {
                currentNode = currentNode.rightChild;
            } else if (currentNode.data.compareTo(element) > 0) {
                currentNode = currentNode.leftChild;
            }
        }
        return false;
    }

    //this class is private, because it's an implementation detail
    private static class TreeNode<T extends Comparable<T>> {
        private T data;
        private TreeNode<T> leftChild;
        private TreeNode<T> rightChild;

        public TreeNode() {
            data = null;
            leftChild = null;
            rightChild = null;
        }

        public TreeNode(T data) {
            this.data = data;
            leftChild = null;
            rightChild = null;
        }

        public void insert(T element) {
            if (this.data.compareTo(element) < 0) {
                if (this.rightChild == null) {
                    this.rightChild = new TreeNode<>(element);
                } else {
                    this.rightChild.insert(element);
                }
            } else if (this.data.compareTo(element) > 0) {
                if (this.leftChild == null) {
                    this.leftChild = new TreeNode<>(element);
                } else {
                    this.leftChild.insert(element);
                }
            }
        }
    }

    private TreeNode<T> deleteRec(TreeNode<T> root, T element) {
        if (root == null) {
            return null;
        }

            /*Traverse the tree recursively to find the node,
            * containing the element for deletion*/
        if (root.data.compareTo(element) < 0) {
            root.rightChild = deleteRec(root.rightChild, element);
        } else if (root.data.compareTo(element) > 0) {
            root.leftChild = deleteRec(root.leftChild, element);
        } else {

            /*We have now found the node we were looking for
            * and now we have to delete it, considering
            * the following cases:
            *
            * 1) The node has one child or no child*/
            if (root.leftChild == null) {
                return root.rightChild;
            } else if (root.rightChild == null) {
                return root.leftChild;
            }

            /*2) The node has two children*/
            root.data = getTreeMin(root.rightChild);
            root.rightChild = deleteRec(root.rightChild, root.data);
        }
        return root;
    }

    public T getTreeMin(TreeNode<T> root) {
        if (root.leftChild == null) {
            return root.data;
        } else {
            return getTreeMin(root.leftChild);
        }
    }

    //TODO: Implement delete method
    public void deleteKey(T element) {
        root = deleteRec(root, element);
    }

    public void printPostOrder(TreeNode<T> root, BufferedWriter bufferedWriter,
                               String sep) throws IOException {
        if (root == null) {
            return;
        }
        printPostOrder(root.leftChild, bufferedWriter, sep);
        printPostOrder(root.rightChild, bufferedWriter, sep);

        bufferedWriter.write(root.data.toString());
        bufferedWriter.write(sep);
    }
}
