package spbu.sem2.hw6.task1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/** represents expression tree */
public class Tree {
    Node root;

    /**
     * reads tree from file
     * @param fileName name of the file
     */
    public void read(String fileName) throws IOException {
        FileInputStream in = new FileInputStream(new File(fileName));
        root = readNode(in);
    }

    /**
     * reads tree node
     * @param in input stream
     * @return read node
     */
    private Node readNode(FileInputStream in) throws IOException {
        Node result;
        char c = (char)in.read();
        if (isDigit(c)) {
            result = readNumber(in, c);
        }
        else {
            result = readSubTree(in);
        }
        return result;
    }

    /**
     * reads subtree(node with its children)
     * @param in input stream
     * @return read subtree
     */
    private Node readSubTree(FileInputStream in) throws IOException {
        Operator result = null;
        char operation = (char)in.read();
        switch (operation) {
            case '+':
                result = new Addition();
                break;
            case '-':
                result = new Subtraction();
                break;
            case '*':
                result = new Multiplication();
                break;
            case '/':
                result = new Division();
        }

        in.skip(1);
        result.setLeftChild(readNode(in));
        result.setRightChild(readNode(in));
        in.skip(1);
        return result;
    }

    /**
     * reads tree leaf, i.e. one separate number
     * @param in input stream
     * @param firstDigit first digit of number
     * @return read number
     */
    private Node readNumber(FileInputStream in, char firstDigit) throws IOException {
        int result = firstDigit - '0';
        while (in.available() > 0) {
            char c = (char)in.read();
            if (!isDigit(c)) {
                break;
            }
            result *= 10;
            result += (c - '0');
        }
        return new Number(result);
    }

    /**
     * checks if character is digit
     * @param c given character
     * @return true if c is digit
     */
    private boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }

    /**
     * calculates result of expression(which is being represented by Tree)
     * @return calculated result
     */
    public int calculate() {
        return root.calculate();
    }

    /** prints tree into console */
    public void print() {
        root.print();
    }
}
