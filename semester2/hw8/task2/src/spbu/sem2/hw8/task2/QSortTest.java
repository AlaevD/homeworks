package spbu.sem2.hw8.task2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/** represents test class for both single- and multi-thread quick sorters */
public class QSortTest {
    private Random rng = new Random();
    private static final int n = 12345678;
    private static int[] a = new int[n];
    private static final int numberOfTests = 10;

    /** generate test array */
    @Before
    public void initialize() {
        for (int i = 0; i < n; i++) {
            a[i] = rng.nextInt();
        }
    }

    /** single-thread quick sorter correctness test */
    @Test
    public void singleQSortTest() {
        SingleThreadQSort qSorter = new SingleThreadQSort();
        qSorter.sort(a, 0, n - 1);
        Assert.assertTrue(sorted(a));
    }

    /** multi-thread quick sorter correctness test */
    @Test
    public void multiQSortTest() {
        MultiThreadQSort qSorter = new MultiThreadQSort();
        qSorter.sort(a, 0, n - 1);
        Assert.assertTrue(sorted(a));
    }

    /** comparing speed */
    @Test
    public void speedTest() {
        double averageSingle = 0;
        SingleThreadQSort qSorter1 = new SingleThreadQSort();
        for (int i = 0; i < numberOfTests; i++) {
            long start = System.currentTimeMillis();
            qSorter1.sort(a, 0, n - 1);
            averageSingle += System.currentTimeMillis() - start;
            initialize();
        }
        averageSingle /= numberOfTests;

        double averageMulti = 0;
        MultiThreadQSort qSorter2 = new MultiThreadQSort();
        for (int i = 0; i < numberOfTests; i++) {
            long start = System.currentTimeMillis();
            qSorter2.sort(a, 0, n - 1);
            averageMulti += System.currentTimeMillis() - start;
            initialize();
        }
        averageMulti /= numberOfTests;
        System.out.println("single = " + averageSingle);
        System.out.println("multi = " + averageMulti);
        Assert.assertTrue(averageMulti < averageSingle);
    }

    /**
     * checks if array is sorted in ascending order
     * @param a given array
     * @return true if array is sorted in ascending order
     */
    private boolean sorted(int[] a) {
        for (int i = 0; i < n - 1; i++) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
    }

}