import org.junit.Test;
import org.junit.Assert;

public class StackCalculatorTest {
    @Test
    public void calculateExpressionTest() {
        Assert.assertTrue(StackCalculator.calculateExpression("3 3 +") == 6);
        Assert.assertTrue(StackCalculator.calculateExpression("3 3 -") == 0);
        Assert.assertTrue(StackCalculator.calculateExpression("3 3 *") == 9);
        Assert.assertTrue(StackCalculator.calculateExpression("3 3 /") == 1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void wrongOperationTest() {
        StackCalculator.calculateExpression("3 3 &");
    }

}