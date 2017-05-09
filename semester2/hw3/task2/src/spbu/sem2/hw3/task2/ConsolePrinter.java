package spbu.sem2.hw3.task2;

public class ConsolePrinter implements Printer {
    @Override
    public void print(int[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.print("\n");
    }
}
