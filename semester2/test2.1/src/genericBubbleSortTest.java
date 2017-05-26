import org.junit.Assert;
import org.junit.Test;


/** represents generic bubble sorter test class*/
public class genericBubbleSortTest {
    /** test with integer values */
    @Test
    public void IntegerBubbleSortTest() {
        Integer[] actual = {5, 6, 2, 0, -4, 8};
        Integer[] expected = {-4, 0, 2, 5, 6, 8};
        genericBubbleSort<Integer> s = new genericBubbleSort<>();
        actual = s.bubbleSort(actual, Integer::compareTo);
        Assert.assertArrayEquals(expected, actual);
    }

    /** test with String values*/
    @Test
    public void StringBubbleSortTest() {
        String[] actual = {"qwe", "asd", "rty", "zxc", "vbn"};
        String[] expected = {"asd", "qwe", "rty", "vbn", "zxc"};
        genericBubbleSort<String> s = new genericBubbleSort<>();
        actual = s.bubbleSort(actual, String::compareTo);
        Assert.assertArrayEquals(expected, actual);
    }
}