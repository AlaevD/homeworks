public class StackCalculator {
    public static int calculateExpression(String expression) {
        int i = 0;
        int n = expression.length();
        Stack<Integer> stack = new LinkedStack<>();

        while (i < n) {
            while (i < n && expression.charAt(i) == ' ') {
                i++;
            }

            if (i == n) {
                break;
            }

            if (isDigit(expression.charAt(i))) {
                int nextNumber = 0;
                while (expression.charAt(i) != ' ') {
                    nextNumber *= 10;
                    nextNumber += toDigit(expression.charAt(i));
                    i++;
                }
                stack.push(nextNumber);
            }
            else {
                int a = stack.pop();
                int b = stack.pop();

                stack.push(evaluateOperation(a, b, expression.charAt(i)));
                i++;
            }
        }

        return stack.pop();
    }

    private static boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }

    private static int toDigit(char c) {
        return c - '0';
    }

    private static int evaluateOperation(int a, int b, char operation) {
        switch (operation) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
            default: throw new UnsupportedOperationException();
        }
    }
}
