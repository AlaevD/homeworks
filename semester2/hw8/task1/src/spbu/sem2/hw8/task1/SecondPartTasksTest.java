package spbu.sem2.hw8.task1;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class SecondPartTasksTest {

    @Test
    public void testFindQuotes() throws IOException {
        File tmp1 = File.createTempFile("tmp1", ".txt");
        tmp1.deleteOnExit();
        FileWriter w = new FileWriter(tmp1);
        w.write("qwertyASD\nASDfgh\nzxc");
        w.flush();

        File tmp2 = File.createTempFile("tmp2", ".txt");
        tmp2.deleteOnExit();
        w = new FileWriter(tmp2);
        w.write("vbn\nfgh\nabrakadabraASD");
        w.flush();

        Assert.assertEquals(Arrays.asList("qwertyASD", "ASDfgh", "abrakadabraASD"),
            SecondPartTasks.findQuotes(Arrays.asList(tmp1.getAbsolutePath(), tmp2.getAbsolutePath()), "ASD"));
    }

    @Test
    public void testPiDividedBy4() {
        double expected = Math.PI / 4;
        double eps = 1e-3;
        Assert.assertTrue(Math.abs(expected - SecondPartTasks.piDividedBy4()) < eps);
    }

    @Test
    public void testFindPrinter() {
        Map <String, List<String>> compositions = new HashMap<>();
        compositions.put("Random author1", Arrays.asList("asd", "qwe"));
        compositions.put("Random author2", Arrays.asList("zxc", "vbn"));
        compositions.put("Winner", Collections.singletonList("the longest composition title"));
        Assert.assertEquals("Winner", SecondPartTasks.findPrinter(compositions));
    }

    @Test
    public void testCalculateGlobalOrder() {
        Map <String, Integer> order1 = new HashMap<>();
        order1.put("A", 5);
        order1.put("B", 3);
        order1.put("C", 2);

        Map <String, Integer> order2 = new HashMap<>();
        order2.put("A", 1);
        order2.put("B", 5);
        order2.put("D", 8);

        Map <String, Integer> order3 = new HashMap<>();
        order3.put("A", 5);
        order3.put("E", 1);
        order3.put("C", 8);

        List<Map<String, Integer>> orders = Arrays.asList(order1, order2, order3);

        Map <String, Integer> expected = new HashMap<>();
        expected.put("A", 11);
        expected.put("B", 8);
        expected.put("C", 10);
        expected.put("D", 8);
        expected.put("E", 1);

        Assert.assertEquals(expected, SecondPartTasks.calculateGlobalOrder(orders));
    }
}