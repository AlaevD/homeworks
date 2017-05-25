package spbu.sem2.hw8.task2;

/** represents array sorter with quick sort as base algorithm */
public abstract class QuickSort {
    /**
     * sorts given interval of array in ascending order
     * @param array given array
     * @param left left interval bound
     * @param right right interval bound
     */
    public abstract void sort(int[] array, int left, int right);

    /**
     * reorganizes elements in the array interval. Elements which values are lower or equal than
     * value of pivot(value at leftmost index) are placed before elements with values greater than value of pivot
     * @param array given array
     * @param left left interval bound
     * @param right right interval bound
     * @return pivot's index after reorganization
     */
    public static int partition(int[] array, int left, int right) {
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
