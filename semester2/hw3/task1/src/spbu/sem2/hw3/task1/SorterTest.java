package spbu.sem2.hw3.task1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;
import static org.junit.Assert.*;

/** represents sorter test class */
public class SorterTest {
    /** test array size */
    private static final int n = 1000;
    private static int[] a = new int[n];

    /** generates testing array */
    @Before
    public void initialize() {
        Random rng = new Random();
        for (int i = 0; i < n; i++) {
            a[i] = rng.nextInt();
        }
    }

    /** bubble sort test */
    @Test
    public void BubbleSortTest() {
        Sorter bSort = new BubbleSort();
        bSort.sort(a);
        Assert.assertTrue(sorted(a));
    }

    /* heap sort test */
    @Test
    public void HeapSortTest() {
        Sorter hSort = new HeapSort();
        hSort.sort(a);
        Assert.assertTrue(sorted(a));
    }

    /* quick sort test */
    @Test
    public void QuickSortTest() {
        Sorter qSort = new QuickSort();
        qSort.sort(a);
        Assert.assertTrue(sorted(a));
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