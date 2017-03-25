import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("Enter an expression");

        Scanner sc = new Scanner(System.in);
        String expression = sc.nextLine();

        System.out.print("Expression value: ");
        System.out.println(StackCalculator.calculateExpression(expression));
    }
}
