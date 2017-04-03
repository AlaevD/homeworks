import java.util.ArrayList;
public class ArrayStack<Type> implements Stack<Type> {
    private ArrayList<Type> data;
    private int head = -1;

    public ArrayStack() {
        data = new ArrayList<Type>();
    }

    @Override
    public void push(Type value) {
        data.add(value);
        head++;
    }

    @Override
    public void pop() {
        if (isEmpty()) {
            return null;
        }

        Type result =  data.at(head);
        data.remove(head);
        head--;
        return result;
    }

    @Override
    public boolean isEmpty() {
        return head == -1;
    }
}
