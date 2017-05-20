package spbu.sem2.hw4.task1;

/**
 * Singly-linked generic list class which can not contain two elements with same value
 * @param <Type> list elements value type
 */
public class UniqueList<Type> implements List<Type> {
    /** stores UniqueList elements */
    LinkedList<Type> data = new LinkedList<>();

    /**
     * add value to unique list
     * @param value value to be added
     * @throws ElementAlreadyExistsException in case value already exists in list
     */
    @Override
    public void add(Type value) throws ElementAlreadyExistsException {
        if (data.contains(value)) {
            throw new ElementAlreadyExistsException();
        }
        else {
            data.add(value);
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
        if (!data.contains(value)) {
            throw new NoSuchElementException();
        }
        else {
            return data.remove(value);
        }
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void print() {
        data.print();
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
