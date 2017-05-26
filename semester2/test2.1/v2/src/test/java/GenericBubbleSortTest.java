import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;


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
        actual = GenericBubbleSort.bubbleSort(actual, new PersonComparator());
        Assert.assertTrue(sorted(actual));
    }

    /** represents person */
    private class Person{
        int age;
        String name;
        Person(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }

    /** represents Persons comparator */
    private class PersonComparator implements Comparator<Person>{
        @Override
        public int compare(Person o1, Person o2) {
            if (o1.age == o2.age) {
                return 0;
            }
            return (o1.age > o2.age ? 1 : -1);
        }
    }

    /**
     * checks that persons array is sorted
     * @param a given array
     * @return true if sorted
     */
    private boolean sorted(Person[] a) {
        final int n = a.length;
        PersonComparator pc = new PersonComparator();
        for (int i = 0; i < n - 1; i++) {
            if (pc.compare(a[i], a[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }
}