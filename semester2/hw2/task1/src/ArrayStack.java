import java.util.ArrayList;
public class ArrayStack<Type> implements Stack<Type> {
    private ArrayList<Type> data;
    private int head;

    public ArrayStack() {
        data = new ArrayList<Type>();
        head = -1;
    }

    @Override
    public void push(Type value) {
        data.add(value);
        head++;
    }

    @Override
    public void pop() {
        if (isEmpty()) {
            return;
        }

        data.remove(head);
        head--;
    }

    @Override
    public Type top() {
        if (isEmpty()) {
            return null;
        }

        return data.get(head);
    }

    @Override
    public boolean isEmpty() {
        return head == -1;
    }
}
