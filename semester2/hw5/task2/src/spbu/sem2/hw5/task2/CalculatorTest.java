package spbu.sem2.hw5.task2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Random;

import static org.junit.Assert.*;

/** represents Calculator class test */
public class CalculatorTest {
    private BigDecimal testingValue = new BigDecimal(1);
    private Random rng = new Random();
    private static final int numberOfTests = 10;
    private Calculator calc = new Calculator();
    private BigDecimal[] numbers = new BigDecimal[numberOfTests];

    /** generates testing values */
    @Before
    public void generateTestingValue() {
        testingValue = BigDecimal.valueOf(rng.nextInt());
        for (int i = 0; i < numberOfTests; i++) {
            numbers[i] = new BigDecimal(rng.nextInt());
        }
    }

    /** addition operation test */
    @Test
    public void additionTest() {
        for (int i = 0; i < numberOfTests; i++) {
            Assert.assertTrue(testingValue.add(numbers[i]).equals(calc.calculate(testingValue, numbers[i], '+')));
        }
    }

    /** subtraction operation test */
    @Test
    public void subtractionTest() {
        for (int i = 0; i < numberOfTests; i++) {
            Assert.assertTrue(testingValue.subtract(numbers[i]).equals(calc.calculate(testingValue, numbers[i], '-')));
        }
    }

    /** multiplication operation test */
    @Test
    public void multiplicationTest() {
        for (int i = 0; i < numberOfTests; i++) {
            Assert.assertTrue(testingValue.multiply(numbers[i]).equals(calc.calculate(testingValue, numbers[i], '*')));
        }
    }

    /* division operation test */
    @Test
    public void divisionTest() {
        for (int i = 0; i < numberOfTests; i++) {
            Assert.assertTrue(testingValue.divide(numbers[i], 5, BigDecimal.ROUND_HALF_UP).equals(calc.calculate(testingValue, numbers[i], '/')));
        }
    }
}