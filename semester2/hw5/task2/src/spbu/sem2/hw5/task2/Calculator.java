package spbu.sem2.hw5.task2;

import java.math.BigDecimal;

/** represents calculator which processes binary expressions with 4 basic arithmetic operations */
public class Calculator {
    /**
     * calculates binary expression result
     * @param a left operand
     * @param b right operand
     * @param operation operation
     * @return expression result
     */
    public BigDecimal calculate(BigDecimal a, BigDecimal b, char operation) {
        BigDecimal result = BigDecimal.valueOf(0);
        switch (operation) {
            case '+':
                result = a.add(b);
                break;
            case '-':
                result = a.subtract(b);
                break;
            case '*':
                result = a.multiply(b);
                break;
            case '/':
                result = a.divide(b, 5, BigDecimal.ROUND_HALF_UP);
                break;
        }
        return result;
    }
}
