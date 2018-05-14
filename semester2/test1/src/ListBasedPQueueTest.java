import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/** 2 priority queue tests */
public class ListBasedPQueueTest {

    /** checking exception while dequeing empty queue */
    @Test(expected = PriorityQueue.QueueIsEmptyException.class)
    public void dequeueEmptyQueueTest() throws PriorityQueue.QueueIsEmptyException {
        PriorityQueue<Integer> q = new ListBasedPQueue<>();
        q.dequeue();
    }

    /** simple priority queue test */
    @Test
    public void queueTest() throws PriorityQueue.QueueIsEmptyException {
        PriorityQueue<Integer> q = new ListBasedPQueue<>();
        q.enqueue(1, 1);
        q.enqueue(2, 2);
        Assert.assertEquals(2, (int)q.dequeue());
        Assert.assertEquals(1, (int)q.dequeue());
    }
}