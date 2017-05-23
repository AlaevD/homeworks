import org.junit.Test;
import org.junit.Assert;

public class StackCalculatorTest {
    @Test
    public void simpleExpressionTest() {
        Assert.assertTrue(StackCalculator.calculateExpression("3 3 +") == 6);
        Assert.assertTrue(StackCalculator.calculateExpression("3 3 -") == 0);
        Assert.assertTrue(StackCalculator.calculateExpression("3 3 *") == 9);
        Assert.assertTrue(StackCalculator.calculateExpression("3 3 /") == 1);
    }

    @Test
    public void aBitMoreComplexExpressionTest() {
        Assert.assertTrue(StackCalculator.calculateExpression("2 3 5 * + 7 3 / - 2 +") == 17);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void wrongOperationTest() {
        StackCalculator.calculateExpression("3 3 &");
    }

}