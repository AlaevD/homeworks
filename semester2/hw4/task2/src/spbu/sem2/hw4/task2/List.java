package spbu.sem2.hw4.task2;

/**
 * Generic list interface
 * @param <Type> list elements value type
 */
public interface List<Type> {
    /**
     * adds new value to list
     * @param value value to be added
     */
    void add(Type value);

    /**
     * removes value from list
     * @param value value to be removed
     * @return true if value was removed successfully
     */
    boolean remove(Type value);

    /**
     * checks whether list is empty or not
     * @return true if list is empty
     */
    boolean isEmpty();

    /**
     * prints list elements to the console
     */
    void print();

    /**
     * returns the number of elements in the list
     * @return number of elements in the list
     */
    int size();
}
