package spbu.sem2.hw6.task1;

/** represents all nodes in tree except leafs */
public abstract class Operator implements Node {
    @Override
    public void print() {
        System.out.print("(" + getOperation() + " ");
        leftChild.print();
        System.out.print(" ");
        rightChild.print();
        System.out.print(")");
    }

    /** left subtree */
    public Node leftChild;
    /** right subtree */
    public Node rightChild;

    /** returns operation sign of current node */
    public abstract String getOperation();

    /** sets current node right child to the given */
    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    /** sets current node left child to the given */
    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }
}
