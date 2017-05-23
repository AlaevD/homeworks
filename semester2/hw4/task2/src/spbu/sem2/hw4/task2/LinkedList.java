package spbu.sem2.hw4.task2;

/**
 * Singly-linked generic list class
 * @param <Type> list elements value type
 */
public class LinkedList<Type> implements List<Type> {
    @Override
    public void add(Type value) {
        if (isEmpty()) {
            head = new Node(value, null);
        }
        else {
            head = new Node(value, head);
        }
        size++;
    }

    @Override
    public boolean remove(Type value) {
        if (isEmpty()) {
            return false;
        }

        if (head.value == value) {
            head = head.next;
            size--;
            return true;
        }

        Node temp = head;
        while (temp.next != null && temp.next.value != value) {
            temp = temp.next;
        }

        if (temp.next != null) {
            temp.next = temp.next.next;
            size--;
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void print() {
        if (isEmpty()) {
            System.out.println("List is empty");
            return;
        }

        System.out.println("List contains:");
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * List node
     */
    protected class Node {
        /** node value */
        public Type value = null;
        /** pointer to the next node */
        public Node next = null;

        /**
         * sets both fields of node to the given
         * @param value node value
         * @param next pointer to the next node
         */
        public Node(Type value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    /** pointer to the head of the list */
    private Node head = null;
    /** list size */
    private int size = 0;
}
