import org.junit.Assert;
import org.junit.Test;

public class LinkedListsTest {
    @Test
    public void addAndGetValueTest() throws List.IndexOutOfRangeException {
        List <Integer> dlList = new DoublyLinkedList<>();
        List <Integer> slList = new SinglyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            slList.add(i);
            dlList.add(i);
        }

        for (int i = 0; i < 10; i++) {
            Assert.assertTrue(i == slList.getValue(i));
            Assert.assertTrue(i == dlList.getValue(i));
        }
    }

    @Test
    public void removeTest() {
        List <Integer> dlList = new DoublyLinkedList<>();
        List <Integer> slList = new SinglyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            slList.add(i);
            dlList.add(i);
        }

        for (int i = 0; i < 10; i++) {
            Assert.assertTrue(slList.remove(i));
            Assert.assertTrue(dlList.remove(i));
        }
    }

    @Test
    public void isEmptyTest() {
        List <Integer> slList = new SinglyLinkedList<>();
        List <Integer> dlList = new DoublyLinkedList<>();

        Assert.assertTrue(slList.isEmpty());
        Assert.assertTrue(dlList.isEmpty());

        slList.add(1);
        dlList.add(1);

        Assert.assertFalse(slList.isEmpty());
        Assert.assertFalse(dlList.isEmpty());
    }
}