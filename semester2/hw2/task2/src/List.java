public interface List<Type> {
    void add(Type value);
    boolean remove(Type value);
    Type getValue(int index) throws IndexOutOfRangeException;
    boolean isEmpty();
    int size();

    class IndexOutOfRangeException extends Exception {
        public IndexOutOfRangeException() {
            super("Index is out of range.");
        }

    }
}
