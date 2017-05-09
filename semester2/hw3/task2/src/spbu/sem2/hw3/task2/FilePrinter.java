package spbu.sem2.hw3.task2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FilePrinter implements Printer {
    @Override
    public void print(int[] array) throws IOException {
        int n = array.length;
        FileWriter writer = new FileWriter("output.txt");
        for (int i = 0; i < n; i++) {
            writer.write(array[i] + " ");
        }
        writer.close();
    }
}
