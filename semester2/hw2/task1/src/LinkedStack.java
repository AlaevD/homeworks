public class LinkedStack<Type> implements Stack<Type> {
    private class Node {
        public Type value;
        public Node next;

        public Node(Type value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node head;

    @Override
    public void push(Type value) {
        head = new Node(value, head);
    }

    @Override
    public void pop() {
        if (isEmpty()) {
            return null;
        }

        Type result = head.value;
        head = head.next;
        return result;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }
}
