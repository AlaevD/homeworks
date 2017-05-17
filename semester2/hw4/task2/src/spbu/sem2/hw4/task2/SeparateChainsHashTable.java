package spbu.sem2.hw4.task2;

import static java.lang.Math.max;

public class SeparateChainsHashTable<Type> implements HashTable<Type> {
    private static final int SIZE = 1342;
    private List<Type>[] table;
    private HashFunction hashFunction = new StupidHashFunction();

    private int totalCellsOccupied = 0;
    private int totalElementsAdded = 0;

    public SeparateChainsHashTable(HashFunction newHashFunction) {
        createTable();
        this.hashFunction = newHashFunction;
    }

    public SeparateChainsHashTable() {
        createTable();
        this.hashFunction = new StupidHashFunction();
    }

    /**
     * creates empty hash table
     */
    private void createTable() {
        table = (UniqueList<Type>[]) new UniqueList[SIZE];
        for (int i = 0; i < SIZE; i++) {
            table[i] = new UniqueList<>();
        }
    }

    @Override
    public void add(Type value) {
        int hash = hashFunction.getHash(value.toString(), SIZE);
        try {
            table[hash].add(value);
            if (table[hash].size() == 1) {
                totalCellsOccupied++;
            }
            totalElementsAdded++;
        } catch (UniqueList.ElementAlreadyExistsException ignored) {}
    }

    @Override
    public boolean remove(Type value) {
        int hash = hashFunction.getHash(value.toString(), SIZE);
        try {
            table[hash].remove(value);
            if (table[hash].size() == 0) {
                totalCellsOccupied--;
            }
            totalElementsAdded--;
            return true;
        } catch (UniqueList.NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public boolean contains(Type value) {
        if (!this.remove(value)) {
            return false;
        }
        else {
            this.add(value);
            return true;
        }
    }

    @Override
    public void printStats() {
        System.out.println("Added " + totalElementsAdded + " elements");
        System.out.println("Occupied " + totalCellsOccupied + " out of " + SIZE + " cells");
        System.out.println("Load factor: " + getLoadFactor());
        System.out.println("Max chain size: " + getMaxChainSize());

    }

    @Override
    public void changeHashFunction(HashFunction newHashFunction) {
        this.hashFunction = newHashFunction;
    }

    /**
     * @return max size of chain in the table
     */
    private int getMaxChainSize() {
        int result = 0;
        for (int i = 0; i < SIZE; i++) {
            result = max(result, table[i].size());
        }
        return result;
    }

    /**
     * @return table load factor
     */
    private double getLoadFactor() {
        return (double)totalElementsAdded / SIZE;
    }
}
