import java.util.Comparator;

/** represents generic array sorter with bubble sort as base algorithm*/
public class genericBubbleSort<Type> {
    /**
     * sorts an array with bubble sort
     * @param array given array
     * @param comparator Type comparator
     * @return sorted array
     */
    public Type[] bubbleSort(Type[] array, Comparator<Type> comparator) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(array[j + 1], array[j]) < 0) {
                    Type tmp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = tmp;
                }
            }
        }
        return array;
    }
}
