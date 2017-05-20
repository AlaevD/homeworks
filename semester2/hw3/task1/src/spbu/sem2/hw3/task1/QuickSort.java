package spbu.sem2.hw3.task1;

/** represents a sorter based on quick sort */
public class QuickSort implements Sorter {
    @Override
    public void sort(int[] array) {
        qSort(array, 0, array.length - 1);
    }

    /**
     * sorts array interval with quick sort algorithm
     * @param array array to be sorted
     * @param left left interval bound
     * @param right right interval bound
     */
    private void qSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivot = partition(array, left, right);
        qSort(array, left, pivot);
        qSort(array, pivot + 1, right);
    }

    /**
     * reorganizes elements in the array interval. Elements which values are lower or equal than
     * value of pivot(value at leftmost index) are placed before elements with values greater than value of pivot
     * @param array given array
     * @param left left interval bound
     * @param right right interval bound
     * @return pivot's index after reorganization
     */
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

    /**
     * swaps two elements in the array
     * @param arr given array
     * @param i first element index
     * @param j second element index
     */
    private static void swap(final int[] arr, final int i, final int j){
        final int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
