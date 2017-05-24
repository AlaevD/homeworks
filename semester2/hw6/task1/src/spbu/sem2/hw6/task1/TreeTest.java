package spbu.sem2.hw6.task1;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.Random;

import static org.junit.Assert.*;

/** represents tree test class */
public class TreeTest {
    /** random number generator */
    private Random rng = new Random();
    private static final int numberOfTests = 10;

    /** tests given operator */
    private void testOperation(Operator operator) {
        for (int i = 0; i < numberOfTests; i++) {
            operator.setLeftChild(new Number(i));
            int rightChild = rng.nextInt();
            operator.setRightChild(new Number(rightChild));
            Assert.assertTrue(expected(i, rightChild, operator.getOperation()) == operator.calculate());
        }
    }

    /**
     * calculates expected value for tests
     * @param a left operand
     * @param b right operand
     * @param operation operation to be evaluated
     * @return expected result
     */
    private int expected(int a, int b, String operation) {
        switch (operation) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            default:
                return a / b;
        }
    }

    /** addition operation test */
    @Test
    public void additionTest() {
        Operator test = new Addition();
        testOperation(test);
    }

    /** subtraction operation test */
    @Test
    public void subtractionTest() {
        Operator test = new Subtraction();
        testOperation(test);
    }

    /** multiplication operation test */
    @Test
    public void multiplicationTest() {
        Operator test = new Multiplication();
        testOperation(test);
    }

    /** division operation test */
    @Test
    public void divisionTest() {
        Operator test = new Division();
        testOperation(test);
    }

    /** all operation together test */
    @Test
    public void complexTest() throws UnsupportedEncodingException {
        String expression = "(+ (- (* 2 5) (/ 10 2)) (* 3 4))";
        try {
            File temp = File.createTempFile("test", ".txt", null);
            FileWriter writer = new FileWriter(temp);

            writer.write(expression);
            writer.flush();

            Tree root = new Tree();
            root.read("test.txt");

            Assert.assertTrue(root.calculate() == 17);
            temp.delete();
        } catch(IOException e) {
            System.out.print(e.getMessage());
        }
    }
}