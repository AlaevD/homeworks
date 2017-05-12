package spbu.sem2.hw5.task1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class CalculatorTest {
    private final int n = 20;
    private int[] a = new int[n];
    private int expected;
    private int actual;

    @Before
    public void initialize() {
        Random rng = new Random();
        for (int i = 0; i < n; i++) {
            a[i] = rng.nextInt() % 201 - 100;
        }
    }
    @Test
    public void additionOperationTest() {
        for (int i = 0; i < n; i += 2) {
            expected = a[i] + a[i + 1];
            actual = Calculator.calculate(a[i], a[i + 1], '+');
            String errorMessage = ((Integer)a[i]).toString() + "+" + ((Integer)a[i + 1]).toString() + "!=" + ((Integer)expected).toString();
            Assert.assertTrue(errorMessage, expected == actual);
        }

    }

    @Test
    public void subtractionOperationTest() {
        for (int i = 0; i < n; i += 2) {
            expected = a[i] - a[i + 1];
            actual = Calculator.calculate(a[i], a[i + 1], '-');
            String errorMessage = ((Integer)a[i]).toString() + "-" + ((Integer)a[i + 1]).toString() + "!=" + ((Integer)expected).toString();
            Assert.assertTrue(errorMessage, expected == actual);
        }

    }

    @Test
    public void multiplicationOperationTest() {
        for (int i = 0; i < n; i += 2) {
            expected = a[i] * a[i + 1];
            actual = Calculator.calculate(a[i], a[i + 1], '*');
            String errorMessage = ((Integer)a[i]).toString() + "*" + ((Integer)a[i + 1]).toString() + "!=" + ((Integer)expected).toString();
            Assert.assertTrue(errorMessage, expected == actual);
        }

    }

    @Test
    public void divisionOperationTest() {
        for (int i = 0; i < n; i += 2) {
            if (a[i + 1] == 0) {
                continue;
            }
            expected = a[i] / a[i + 1];
            actual = Calculator.calculate(a[i], a[i + 1], '/');
            String errorMessage = ((Integer)a[i]).toString() + "/(integer division)" + ((Integer)a[i + 1]).toString() + "!=" + ((Integer)expected).toString();
            Assert.assertTrue(errorMessage, expected == actual);
        }

    }
}