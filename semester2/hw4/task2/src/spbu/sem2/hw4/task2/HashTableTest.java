package spbu.sem2.hw4.task2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class HashTableTest {
    private int size = 147;
    private Integer[] numbers = new Integer[size];
    private Random rng = new Random();

    @Before
    public void generateNumbers() {
        for (int i = 0; i < size; i++) {
            numbers[i] = rng.nextInt();
        }
    }

    @Test
    public void testFAQ6() {
        testHashTable(new FAQ6Hash());
    }

    @Test
    public void testRot13() {
        testHashTable(new Rot13Hash());
    }

    @Test
    public void testDefaultHash() {
        testHashTable(new StupidHashFunction());
    }

    private void testHashTable(HashFunction h) {
        HashTable<Integer> testTable = new SeparateChainsHashTable<>(h);
        for (int i = 0; i < size; i++) {
            testTable.add(numbers[i]);
            Assert.assertTrue(numbers[i].toString() + " was added but does not exist in table", testTable.contains(numbers[i]));
        }

        for (int i = 0; i < size; i++) {
            testTable.remove(numbers[i]);
            Assert.assertFalse(numbers[i].toString() + " was removed but still exists in table", testTable.contains(numbers[i]));
        }
    }


}