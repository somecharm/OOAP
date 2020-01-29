import org.junit.Test;

import static org.junit.Assert.*;

public class QueueTest {

    @Test
    public void enqueue() {
        Queue<Integer> q = new Queue<>();
        assertEquals(0, q.size());
        q.enqueue(0);
        assertEquals(1, q.size());
        assertEquals((Integer) 0, q.peek());
        q.enqueue(0);
        assertEquals(2, q.size());
        assertEquals((Integer) 0, q.peek());
    }

    @Test
    public void dequeue() {
        Queue<Integer> q = new Queue<>();
        assertEquals(0, q.size());
        q.dequeue();
        assertEquals(2, q.get_dequeue_status());
        q.enqueue(0);
        assertEquals(1, q.size());
        assertEquals((Integer) 0, q.peek());
        q.enqueue(1);
        assertEquals(2, q.size());
        assertEquals((Integer) 0, q.peek());
        assertEquals((Integer) 0, q.dequeue());
        assertEquals(1, q.get_dequeue_status());
        assertEquals(1, q.size());
        assertEquals((Integer) 1, q.dequeue());
        assertEquals(0, q.size());
    }

    @Test
    public void clear() {
        Queue<Integer> q = new Queue<>();
        q.enqueue(0);
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        assertEquals(5, q.size());
        q.clear();
        assertEquals(0, q.size());
    }

    @Test
    public void peek() {
        Queue<Integer> q = new Queue<>();
        assertNull(q.peek());
        assertEquals(2, q.get_peek_status());
        q.enqueue(0);
        assertEquals((Integer) 0, q.peek());
        assertEquals(1, q.get_peek_status());
        q.enqueue(1);
        assertEquals((Integer) 0, q.peek());
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.dequeue();
        assertEquals((Integer) 1, q.peek());
        q.dequeue();
        q.dequeue();
        assertEquals((Integer) 3, q.peek());
    }
}