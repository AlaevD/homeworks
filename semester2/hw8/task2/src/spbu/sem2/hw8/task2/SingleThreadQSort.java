package spbu.sem2.hw8.task2;

/** represents single-thread quick sorter */
public class SingleThreadQSort extends QuickSort {
    @Override
    public void sort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivot = partition(array, left, right);
        sort(array, left, pivot);
        sort(array, pivot + 1, right);
    }
}
