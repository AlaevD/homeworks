/** list based priority queue */
public class ListBasedPQueue<Type> implements PriorityQueue<Type> {
    /** queue values */
    private SortedList data = new SortedList();

    @Override
    public void enqueue(Type value, int priority) {
        data.add(value, priority);
    }

    @Override
    public Type dequeue() throws QueueIsEmptyException {
        if (data.isEmpty()) {
            throw new QueueIsEmptyException();
        }

        Type result = data.getMax();
        data.removeMax();
        return result;
    }

    /** list with values sorted by priorities */
    private class SortedList {
        /** list's head */
        private Node head = null;

        /**
         * checks whether list is empty
         * @return true if list is empty, false otherwise
         */
        public boolean isEmpty() {
            return (head == null);
        }

        /**
         * adds value with its priority to list
         * @param value value to be added to list
         * @param priority value's priority
         */
        public void add(Type value, int priority) {
            if (this.isEmpty()) {
                head = new Node(value, priority, head);
                return;
            }

            if (head.next == null) {
                if (priority > head.priority) {
                    head = new Node(value, priority, head);
                }
                else {
                    head.next = new Node(value, priority, null);
                }
                return;
            }

            Node temp = head;
            while (temp.next.priority > priority) {
                temp = temp.next;
            }

            temp = new Node(value, priority, temp.next);
        }

        /**
         * return value of element with highest priority
         * @return value of element with highest priority
         */
        public Type getMax() {
            return head.value;
        }

        /**
         * removes element with highest priority
         */
        public void removeMax() {
            head = head.next;
        }

        /** list element */
        private class Node {
            public Type value;
            public int priority;
            public Node next;

            public Node(Type value, int priority, Node next) {
                this.next = next;
                this.value = value;
                this.priority = priority;
            }
        }
    }
}
