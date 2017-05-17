package spbu.sem2.hw4.task2;

/**
 * represents hash table interface
 * @param <Type> hash table elements type
 */
public interface HashTable<Type> {
    /**
     * adds value to table
     * @param value value to be added
     */
    void add(Type value);

    /**
     * removes value from table
     * @param value value to be removed
     * @return true if removed successfully
     */
    boolean remove(Type value);

    /**
     * checks value for existence in table
     * @param value value to be checked
     * @return true if value exists in table
     */
    boolean contains(Type value);

    /**
     * prints hash table statistics
     */
    void  printStats();

    /**
     * changes hash fucntion
     * @param newHashFunction
     */
    void changeHashFunction(HashFunction newHashFunction);
}
