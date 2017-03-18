package semester2.hw1.task1;

public class Main {

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<Integer>();

        System.out.println(s.isEmpty() ? "s is empty" : "error");

        Integer test = 1;

        s.push(test);

        System.out.print("s now contains ");

        System.out.println(s.top());

        s.pop();

        System.out.println(s.isEmpty() ? "s is empty" : "error");
    }
}
