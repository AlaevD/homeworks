/** represents generic linked list */
public interface List<Type> {
    /**
     * adds value to list
     * @param value value to be added
     */
    void add(Type value);

    /**
     * removes value from list
     * @param value value to be removed
     * @return true if removed successfully
     */
    boolean remove(Type value);

    /**
     * gets value on specified position in list
     * @param index position of value in list starting from 0
     * @return value at index+1-th position in list
     * @throws IndexOutOfRangeException in case index is more or equal to list size
     */
    Type getValue(int index) throws IndexOutOfRangeException;

    /**
     * checks if list is empty
     * @return true if list is empty
     */
    boolean isEmpty();

    /**
     * returns list size
     * @return list size
     */
    int size();

    /** exception thrown when calling a getValue method with index exceeding size of list*/
    class IndexOutOfRangeException extends Exception {
        public IndexOutOfRangeException() {
            super("Index is out of range.");
        }

    }
}
