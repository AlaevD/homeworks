package spbu.sem2.hw6.task1;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String fileName = "input.txt";
        Tree tree = new Tree();
        try {
            tree.read(fileName);
            tree.print();
            System.out.println("\n " + tree.calculate());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
