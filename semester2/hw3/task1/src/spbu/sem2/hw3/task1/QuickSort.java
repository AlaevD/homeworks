package spbu.sem2.hw3.task1;

public class QuickSort implements Sorter {
    @Override
    public void sort(int[] array) {
        qSort(array, 0, array.length - 1);
    }

    private void qSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivot = partition(array, left, right);
        qSort(array, left, pivot);
        qSort(array, pivot + 1, right);
    }

    private int partition(int[] array, int left, int right) {
        int i = left;
        for (int j = left + 1; j <= right; j++) {
            if (array[j] < array[left]) {
                swap(array, i + 1, j);
                i++;
            }
        }
        swap(array, left, i);
        return i;
    }

    private static void swap(final int[] arr, final int i, final int j){
        final int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
