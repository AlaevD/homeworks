package spbu.sem2.hw4.task2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        printHelp();
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        HashTable<String> table = new SeparateChainsHashTable<>();

        while (!exit) {
            String operation = scanner.nextLine();
            try {
                if (!execute(table, Operation.valueOf(operation), scanner)) {
                    exit = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Try again");
            }
        }
    }


    private static void printHelp() {
        System.out.println("Type: ");
        System.out.println("      ADD to add a new value to hash table");
        System.out.println("      REMOVE to remove a value from hash table");
        System.out.println("      CHECK to check if hash table contains a value");
        System.out.println("      STATS to show hash table statistics");
        System.out.println("      CHANGE to select a new hash function");
        System.out.println("      EXIT to exit");
    }

    private enum Operation {
        ADD,
        REMOVE,
        CHECK,
        STATS,
        CHANGE,
        EXIT
    }

    private enum Hash {
        FAQ6,
        ROT13,
        STUPID
    }

    private static boolean execute(HashTable<String> table, Operation operation, Scanner sc) {
        switch (operation) {
            case ADD:
                executeAdd(table, sc);
                break;
            case REMOVE:
                executeRemove(table, sc);
                break;
            case CHECK:
                executeCheck(table, sc);
                break;
            case STATS:
                table.printStats();
                break;
            case CHANGE:
                executeChange(table, sc);
                break;
            case EXIT:
                return false;
        }
        printHelp();
        return true;
    }

    private static void executeChange(HashTable<String> table, Scanner sc) {
        System.out.println("Enter new hash function");
        System.out.println("Available functions: ");
        System.out.println("                     FAQ6");
        System.out.println("                     ROT13");
        System.out.println("                     STUPID");
        try {
            Hash newFunction = Hash.valueOf(sc.nextLine());
            switch (newFunction) {
                case FAQ6:
                    table.changeHashFunction(new FAQ6Hash());
                    break;
                case ROT13:
                    table.changeHashFunction(new Rot13Hash());
                    break;
                case STUPID:
                    table.changeHashFunction(new StupidHashFunction());
                    break;
            }
            System.out.println("Changed successfully");
        } catch (IllegalArgumentException e) {
            System.out.println("Sorry, there is no such hash function yet");
        }
    }

    private static void executeCheck(HashTable<String> table, Scanner sc) {
        System.out.println("Enter a value to check for existence");
        if (table.contains(sc.nextLine())) {
            System.out.println("Exists");
        }
        else {
            System.out.println("Does not exist");
        }
    }

    private static void executeRemove(HashTable<String> table, Scanner sc) {
        System.out.println("Enter a value to remove");
        if (table.remove(sc.nextLine())) {
            System.out.println("Removed successfully");
        }
        else {
            System.out.println("There's no such value in the table");
        }
    }

    private static void executeAdd(HashTable<String> table, Scanner sc) {
        System.out.println("Enter a value to add");
        table.add(sc.nextLine());
        System.out.println("Added successfully(or already exists in the table)");
    }
}
