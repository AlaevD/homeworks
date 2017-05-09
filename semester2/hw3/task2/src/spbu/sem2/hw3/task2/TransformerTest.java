package spbu.sem2.hw3.task2;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TransformerTest {
    @Test
    public void transformTest1() {
        int[][] test = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[] expected = {5, 8, 7, 4, 1, 2, 3, 6, 9};
        int[] actual = new int[9];
        new Transformer().transform(test, actual);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void transformTest2() {
        int[][] test = {{1}};
        int[] expected = {1};
        int[] actual = new int[1];
        new Transformer().transform(test, actual);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void transformTest3() {
        int[][] test = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}};
        int[] expected = {13, 18, 17, 12, 7, 8, 9, 14, 19, 24, 23, 22, 21, 16,
                11, 6, 1, 2, 3, 4, 5, 10, 15, 20, 25};
        int[] actual = new int[25];
        new Transformer().transform(test, actual);
        Assert.assertArrayEquals(expected, actual);
    }
}