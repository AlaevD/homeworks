/** represents singly linked generic list */
public class SinglyLinkedList<Type> implements List<Type> {
    /** represents element of the list */
    private class Node {
        /** element's value */
        private Type value;
        /** pointer to the next element */
        private Node next;

        public Node(Type value, Node next) {
            this.next = next;
            this.value = value;
        }
    }

    /** pointer to the first element in list */
    private Node head = null;
    /** pointer to the last element in list */
    private Node tail = null;
    /** size of the list */
    private int size = 0;

    @Override
    public void add(Type value) {
        if (isEmpty()) {
            head = new Node(value, null);
            tail = head;
            size++;
            return;
        }

        tail.next = new Node(value, null);
        tail = tail.next;
        size++;
    }

    @Override
    public boolean remove(Type value) {
        if (isEmpty()) {
            return false;
        }

        if (head.value == value) {
            head = head.next;
            return true;
        }

        Node temp = head;
        while (temp.next != null && temp.next.value != value) {
            temp = temp.next;
        }

        if (temp.next == null) {
            return false;
        }

        temp.next = temp.next.next;
        return true;
    }

    @Override
    public Type getValue(int index) throws IndexOutOfRangeException {
        if (index >= size || index < 0) {
            throw new IndexOutOfRangeException();
        }

        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.value;
    }

    @Override
    public boolean isEmpty() {
        return (head == null);
    }

    @Override
    public int size() {
        return size;
    }
}
