import org.junit.Assert;
import org.junit.Test;

public class SinglyLinkedListTest {
    @Test
    public void ListTest() throws List.IndexOutOfRangeException {
        List <Integer> list = new SinglyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        for (int i = 0; i < 10; i++) {
            Assert.assertTrue(i == list.getValue(i));
        }
    }

    @Test
    public void removeTest() {
        List <Integer> list = new SinglyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        for (int i = 0; i < 10; i++) {
            Assert.assertTrue(list.remove(i));
        }
    }

    @Test
    public void isEmptyTest() {
        List <Integer> list = new SinglyLinkedList<>();
        Assert.assertTrue(list.isEmpty());
        list.add(1);
        Assert.assertFalse(list.isEmpty());
    }
}