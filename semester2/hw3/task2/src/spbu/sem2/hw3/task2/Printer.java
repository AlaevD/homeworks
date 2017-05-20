package spbu.sem2.hw3.task2;

import java.io.IOException;

/** represents class that prints 2d array by spiral starting from the middle */
public abstract class Printer {
    /**
     * prints 2d array by spiral
     * @param array array to be printed
     */
    public void print(int[][] array) throws IOException {
        Transformer t = new Transformer();
        int[] result = new int[array.length * array.length];
        t.transform(array, result);
        printResult(result);
    }

    /**
     * prints given array
     * @param array array to be printed
     */
    public abstract void printResult(int[] array) throws IOException;
}
