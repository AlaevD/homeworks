package spbu.sem2.hw5.task1;

/** Calculates binary expressions */
public class Calculator {
    /** The lowest operand value */
    private static final int MIN = -100;

    /** The highest operand value */
    private static final int MAX = 100;

    /**
     * Calculates binary expression result
     * @param a first operand
     * @param b second operand
     * @param operation one of the four applicable operations(+, -, *, /)
     * @return expression result
     */
    public static int calculate(int a, int b, char operation) {
        int result = 0;
        switch (operation) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                result = a / b;
                break;
        }
        return result;
    }

    /** returns the lowest operand value */
    public static int getMin() {
        return MIN;
    }

    /** returns the highest operand value */
    public static int getMax() {
        return MAX;
    }
}