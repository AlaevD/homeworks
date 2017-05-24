package spbu.sem2.hw3.task2;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter array size");
        int n = scanner.nextInt();

        int[][] a = new int[n][n];
        System.out.println("Enter array elements");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = scanner.nextInt();
            }
        }

        int flag = 2;
        System.out.println("Enter 1 if you want to print array to file \"output.txt\" in current directory");
        System.out.println("Otherwise it will be printed to console");


        if (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                flag = scanner.nextInt();
            }
        }

        Printer printer;
        if (flag == 1) {
            printer = new FilePrinter();
        }
        else {
            printer = new ConsolePrinter();
        }

        try {
            printer.print(a);
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }
}
