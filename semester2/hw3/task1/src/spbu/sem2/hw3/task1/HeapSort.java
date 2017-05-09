package spbu.sem2.hw3.task1;

public class HeapSort implements Sorter {
    @Override
    public void sort(int[] array) {
        hSort(array, array.length);
    }

    private void hSort(int[] array, int n) {
        for (int i = n / 2 - 1; i > -1; i--) {
            heapify(array, i, n);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i);
            heapify(array, 0, i);
        }
    }

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

    private int getLeftChild(int i) {
        return 2 * i + 1;
    }

    private int getRightChild(int i) {
        return 2 * i + 2;
    }

    private static void swap(final int[] arr, final int i, final int j){
        final int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
