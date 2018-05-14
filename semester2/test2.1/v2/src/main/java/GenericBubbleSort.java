import java.util.Comparator;

/** represents generic array sorter with bubble sort as base algorithm*/
public class GenericBubbleSort {
    /**
     * sorts an array with bubble sort
     * @param array given array
     * @param comparator T comparator
     * @return sorted array
     */
    public static <T> T[] bubbleSort(T[] array, Comparator<T> comparator) {
        final int N = array.length;
        T[] result = array.clone();
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N - i - 1; j++) {
                if (comparator.compare(result[j + 1], result[j]) < 0) {
                    T tmp = result[j + 1];
                    result[j + 1] = result[j];
                    result[j] = tmp;
                }
            }
        }
        return result;
    }
}
