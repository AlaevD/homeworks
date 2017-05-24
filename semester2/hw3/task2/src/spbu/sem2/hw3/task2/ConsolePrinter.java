package spbu.sem2.hw3.task2;

/** represents Printer that prints array result into console */
public class ConsolePrinter extends Printer {
    @Override
    public void printResult(int[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.print("\n");
    }
}
