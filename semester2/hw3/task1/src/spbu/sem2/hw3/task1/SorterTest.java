package spbu.sem2.hw3.task1;

import org.junit.Assert;
import org.junit.Test;
import java.util.Random;
import static org.junit.Assert.*;

public class SorterTest {
    private int n = 1000;
    private int[] a = new int[n];

    private void initialize(int[] array) {
        Random rng = new Random();
        for (int i = 0; i < n; i++) {
            array[i] = rng.nextInt();
        }
    }

    @Test
    public void BubbleSortTest() {
        initialize(a);
        Sorter bSort = new BubbleSort();
        bSort.sort(a);
        Assert.assertTrue(sorted(a));
    }

    @Test
    public void HeapSortTest() {
        initialize(a);
        Sorter hSort = new HeapSort();
        hSort.sort(a);
        Assert.assertTrue(sorted(a));
    }

    @Test
    public void QuickSortTest() {
        initialize(a);
        Sorter qSort = new QuickSort();
        qSort.sort(a);
        Assert.assertTrue(sorted(a));
    }

    private boolean sorted(int[] a) {
        for (int i = 0; i < n - 1; i++) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
    }
}