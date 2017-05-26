import org.junit.Assert;
import org.junit.Test;


/** represents generic bubble sorter test class*/
public class GenericBubbleSortTest {

    /** test with integer values */
    @Test
    public void IntegerBubbleSortTest() {
        Integer[] actual = {5, 6, 2, 0, -4, 8};
        Integer[] expected = {-4, 0, 2, 5, 6, 8};

        actual = GenericBubbleSort.bubbleSort(actual, Integer::compareTo);
        Assert.assertArrayEquals(expected, actual);
    }

    /** test with String values*/
    @Test
    public void StringBubbleSortTest() {
        String[] actual = {"qwe", "asd", "rty", "zxc", "vbn"};
        String[] expected = {"asd", "qwe", "rty", "vbn", "zxc"};

        actual = GenericBubbleSort.bubbleSort(actual, String::compareTo);
        Assert.assertArrayEquals(expected, actual);
    }

    /** test with Person values */
    @Test
    public void PersonBubbleSortTest() {
        Person[] actual = {new Person(12, "asd"), new Person(31, "qwe"),
         new Person(23, "zxc")};
        actual = GenericBubbleSort.bubbleSort(actual, Person::compareTo);
        Assert.assertTrue(sorted(actual));
    }

    /** represents person */
    private class Person implements Comparable<Person> {
        int age;
        String name;
        Person(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public int compareTo(Person o) {
            if (age == o.age && name.equals(o.name)) {
                return 0;
            }
            return (age > o.age ? 1 : -1);
        }
    }

    /**
     * checks that persons array is sorted
     * @param a given array
     * @return true if sorted
     */
    private boolean sorted(Person[] a) {
        final int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            if (a[i].compareTo(a[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }
}