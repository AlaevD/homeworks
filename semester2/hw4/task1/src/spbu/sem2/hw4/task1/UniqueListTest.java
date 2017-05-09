package spbu.sem2.hw4.task1;

import org.junit.Test;

import static org.junit.Assert.*;

public class UniqueListTest {
    @Test(expected = UniqueList.ElementAlreadyExistsException.class)
    public void addingExistingElementTest() {
        List<Integer> list = new UniqueList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        list.add(1);
    }

    @Test(expected = UniqueList.NoSuchElementException.class)
    public void removingUnexistingElementTest() {
        List<Integer> list = new UniqueList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        boolean removed = list.remove(11);
    }
}