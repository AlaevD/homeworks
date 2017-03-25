public interface Stack<Type> {
    void push(Type value);
    void pop();
    Type top();
    boolean isEmpty();
}
