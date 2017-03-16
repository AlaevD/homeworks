package semester2.hw1.task1;

public class Stack<Type> {

    public void push(Type value) {
        head = new Node(value, head);
    }

    public void pop() {
        if (!isEmpty()) {
            head = head.next;
        }
    }

    public Type top() {
        if (isEmpty()) {
            return null;
        }
        Type result = head.value;
        head = head.next;
        return result;
    }

    public boolean isEmpty() {
        return head == null;
    }

    private class Node {
        public Type value;
        public Node next;

        public Node(Type value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node head;
}
