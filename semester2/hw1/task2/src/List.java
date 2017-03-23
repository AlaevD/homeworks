public class List <Type> {
    private class Node {
        public Type value = null;
        public Node next = null;

        public Node(Type value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node head = null;

    public boolean isEmpty() {
        return head == null;
    }

    public void add(Type value) {
        if (isEmpty()) {
            head = new Node(value, null);
        }
        else {
            Node newNode = new Node(value, head);
            head = newNode;
        }
    }

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

        if (temp.next != null) {
            temp.next = temp.next.next;
            return true;
        }
        else {
            return false;
        }
    }

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
}
