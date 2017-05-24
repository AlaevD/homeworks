package spbu.sem2.hw3.task1;

/** represents a sorter based on heap sort */
public class HeapSort implements Sorter {
    @Override
    public void sort(int[] array) {
        hSort(array, array.length);
    }

    /**
     * sorts an array with heap sort algorithm
     * @param array array to be sorted
     * @param n array size
     */
    private void hSort(int[] array, int n) {
        for (int i = n / 2 - 1; i > -1; i--) {
            heapify(array, i, n);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i);
            heapify(array, 0, i);
        }
    }

    /**
     * places given element into proper position in the array according to heap rules
     * @param array given array
     * @param currentIndex element index
     * @param size array size
     */
    void heapify(int[] array, int currentIndex, int size) {
        int leftChild = getLeftChild(currentIndex);

        if (leftChild >= size)
        {
            return;
        }

        int rightChild = getRightChild(currentIndex);

        int maxChild = rightChild;
        if (leftChild == size - 1 || array[leftChild] > array[rightChild])
        {
            maxChild = leftChild;
        }

        if (array[currentIndex] < array[maxChild])
        {
            swap(array, currentIndex, maxChild);
            heapify(array, maxChild, size);
        }
    }

    /**
     * returns left child index of given element in the heap
     * @param i index of given element
     */
    private int getLeftChild(int i) {
        return 2 * i + 1;
    }

    /**
     * returns right child index of given element in the heap
     * @param i index of given element
     */
    private int getRightChild(int i) {
        return 2 * i + 2;
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
