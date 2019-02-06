package BinarySearchTree;

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

    //TODO: Implement delete method
    public void delete() {

    }


    public static void main(String[] args) {
        BinarySearchTree<Integer> myTree = new BinarySearchTree<>(90);
        myTree.add(50);
        myTree.add(150);
        myTree.add(75);
        myTree.add(20);
        myTree.add(95);
        myTree.add(175);
        System.out.println(myTree.contains(75));
        System.out.println(myTree.contains(175));
        System.out.println(myTree.contains(8));
    }
}
