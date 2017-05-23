import java.util.ArrayList;
public class ArrayStack<Type> implements Stack<Type> {
    private ArrayList<Type> data = new ArrayList<Type>();
    private int head = -1;

    @Override
    public void push(Type value) {
        data.add(value);
        head++;
    }

    @Override
    public Type pop() {
        if (isEmpty()) {
            return null;
        }

        Type result =  data.get(head);
        data.remove(head);
        head--;
        return result;
    }

    @Override
    public boolean isEmpty() {
        return head == -1;
    }
}
