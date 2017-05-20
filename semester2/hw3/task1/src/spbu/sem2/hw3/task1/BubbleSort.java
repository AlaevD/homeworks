package spbu.sem2.hw3.task1;

/** represents a sorter based on bubble sort */
public class BubbleSort implements Sorter {
    @Override
    public void sort(int[] array) {
        bSort(array, array.length);
    }

    /**
     * sorts an array with bubble sort algorithm
     * @param array given array
     * @param n array size
     */
    private void bSort(int[] array, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j + 1] < array[j]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }
}
