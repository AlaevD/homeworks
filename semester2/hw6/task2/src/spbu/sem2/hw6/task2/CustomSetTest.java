package spbu.sem2.hw6.task2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class CustomSetTest {
    private Set<Integer> expected = new TreeSet<>();
    private Set<Integer> actual = new CustomSet<>();
    Random rng = new Random();

    private Collection<Integer> generateCollection() {
        Collection<Integer> result = new ArrayList<>();
        int size = rng.nextInt() % 101;
        for (int i = 0; i < size; i++) {
            result.add(rng.nextInt());
        }
        return result;
    }

    @Before
    public void generateSets() {
        int size = rng.nextInt() % 101;
        for (int i = 0; i < size; i++) {
            int a = rng.nextInt();
            expected.add(a);
            actual.add(a);
        }
    }

    @Test
    public void size() {
        Assert.assertTrue(actual.size() == expected.size());
    }

    @Test
    public void isEmpty() {
        Assert.assertEquals(actual.isEmpty(), expected.isEmpty());
    }

    @Test
    public void contains() {
        for (Integer i: expected) {
            Assert.assertTrue(actual.contains(i));
        }
    }

    @Test
    public void iterator() {
        for (Integer i: actual) {
            Assert.assertTrue(expected.contains(i));
        }
    }

    @Test
    public void toArray() {
        Object[] expectedArray = expected.toArray();
        Object[] actualArray = actual.toArray();
        Arrays.sort(expectedArray);
        Arrays.sort(actualArray);
        Assert.assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void add() {
        Set<Integer> addTestSet = new CustomSet<>();
        Assert.assertTrue(addTestSet.add(1));
        Assert.assertTrue(addTestSet.contains(1));
        Assert.assertFalse(addTestSet.add(1));
    }

    @Test
    public void remove() {
        Set<Integer> removeTestSet = new CustomSet<>();
        Assert.assertFalse(removeTestSet.remove(1));
        removeTestSet.add(1);
        Assert.assertTrue(removeTestSet.remove(1));
        Assert.assertTrue(removeTestSet.isEmpty());
    }

    @Test
    public void containsAll() {
        Collection<Integer> customCollection = new ArrayList<>();
        customCollection.add(1000);
        Assert.assertFalse(actual.containsAll(customCollection));
        Assert.assertTrue(actual.containsAll(expected));
    }

    @Test
    public void addAll() {
        Collection<Integer> addAllTestCollection = generateCollection();
        expected.addAll(addAllTestCollection);
        actual.addAll(addAllTestCollection);
        Assert.assertTrue(expected.equals(actual));
    }

    @Test
    public void retainAll() {
        Collection<Integer> retainAllTestCollection = generateCollection();
        expected.retainAll((retainAllTestCollection));
        actual.retainAll(retainAllTestCollection);
        Assert.assertTrue(expected.equals(actual));
    }

    @Test
    public void removeAll() {
        Collection<Integer> removeAllTestCollection = generateCollection();
        expected.removeAll(removeAllTestCollection);
        actual.removeAll(removeAllTestCollection);
        Assert.assertTrue(expected.equals(actual));
    }

    @Test
    public void clear() {
        actual.clear();
        Assert.assertTrue(actual.isEmpty());
    }

}