/** priority queue class with 2 basic operations */
public interface PriorityQueue<Type> {
    /**
     * Adds value with its priority to queue
     * @param value value to be added
     * @param priority value's priority
     */
    void enqueue(Type value, int priority);

    /**
     * Removes element with highest priority and returns its value
     * @return queue element with highest priority
     * @throws QueueIsEmptyException if calling with empty queue
     */
    Type dequeue() throws QueueIsEmptyException;

    /** Queue is empty exception */
    class QueueIsEmptyException extends Exception {
        public QueueIsEmptyException() {
            super("Queue is empty");
        }
    }
}
