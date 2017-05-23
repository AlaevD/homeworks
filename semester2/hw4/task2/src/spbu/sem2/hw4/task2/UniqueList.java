package spbu.sem2.hw4.task2;

/**
 * Singly-linked generic list class which can not contain two elements with same value
 * @param <Type> list elements value type
 */
public class UniqueList<Type> extends LinkedList<Type> {
    /** pointer to the head of the list */
    private Node head = null;
    /** list size */
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    /**
     * checks whether value exists in list
     * @param value value to be checked
     * @return true if value exists in list
     */
    private boolean exists(Type value) {
        Node temp = head;
        while (temp != null) {
            if (temp.value.equals(value)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    /**
     * add value to unique list
     * @param value value to be added
     * @throws ElementAlreadyExistsException in case value already exists in list
     */
    @Override
    public void add(Type value) throws ElementAlreadyExistsException {
        if (isEmpty()) {
            head = new Node(value, null);
            size++;
        }
        else {
            if (exists(value)) {
                throw new ElementAlreadyExistsException();
            }
            else {
                head = new Node(value, head);
                size++;
            }
        }
    }

    /**
     * removes value from list
     * @param value value to be removed
     * @return true if removed successfully
     * @throws NoSuchElementException in case there is no such value in list
     */
    @Override
    public boolean remove(Type value) throws NoSuchElementException {
        if (!exists(value)) {
            throw new NoSuchElementException();
        }

        if (head.value.equals(value)) {
            head = head.next;
            size--;
            return true;
        }

        Node temp = head;
        while (temp.next != null && temp.next.value != value) {
            temp = temp.next;
        }
        size--;

        temp.next = temp.next.next;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * exception thrown when adding value which already exists in list
     */
    public static class ElementAlreadyExistsException extends RuntimeException {
        public ElementAlreadyExistsException() {
            super("Element already exists in the list");
        }
    }

    /**
     * exception thrown when removing unexisting value from list
     */
    public static class NoSuchElementException extends RuntimeException {
        public NoSuchElementException() {
            super("There is no such element in the list");
        }
    }
}
