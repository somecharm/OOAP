import org.junit.Test;

import static org.junit.Assert.*;

public class DequeTest {

    @Test
    public void addFront() {
        Deque<Integer> deq = new Deque<>();
        assertEquals(0, deq.size());
        deq.addFront(0);
        assertEquals(1, deq.size());
        assertEquals((Integer) 0, deq.getFront());
        assertEquals((Integer) 0, deq.getLast());
        deq.addFront(1);
        assertEquals(2, deq.size());
        assertEquals((Integer) 1, deq.getFront());
        assertEquals((Integer) 0, deq.getLast());
        deq.addFront(2);
        assertEquals(3, deq.size());
        assertEquals((Integer) 2, deq.getFront());
        assertEquals((Integer) 0, deq.getLast());
    }

    @Test
    public void remove() {
        Deque<Integer> deq = new Deque<>();
        assertEquals(0, deq.size());
        deq.removeLast();
        assertEquals(2, deq.get_removeLast_status());
        deq.addFront(0);
        assertEquals(1, deq.size());
        assertEquals((Integer) 0, deq.getFront());
        assertEquals((Integer) 0, deq.getLast());
        deq.addLast(1);
        assertEquals(2, deq.size());
        assertEquals((Integer) 0, deq.getFront());
        assertEquals((Integer) 1, deq.getLast());
        deq.addLast(2);
        assertEquals(3, deq.size());
        assertEquals((Integer) 0, deq.getFront());
        assertEquals((Integer) 2, deq.getLast());
        deq.removeFront();
        assertEquals(2, deq.size());
        assertEquals((Integer) 1, deq.getFront());
        assertEquals((Integer) 2, deq.getLast());
        deq.removeLast();
        assertEquals(1, deq.size());
        assertEquals((Integer) 1, deq.getFront());
        assertEquals((Integer) 1, deq.getLast());
        assertEquals(1, deq.get_removeLast_status());
        deq.removeLast();
        assertEquals(0, deq.size());
        assertNull(deq.getHead());
        assertNull(deq.getTail());
        assertEquals(1, deq.get_removeLast_status());
        deq.removeLast();
        assertEquals(0, deq.size());
        assertNull(deq.getHead());
        assertNull(deq.getTail());
        assertEquals(2, deq.get_removeLast_status());
    }

    @Test
    public void getLast() {
        Deque<Integer> deq = new Deque<>();
        assertEquals(0, deq.size());
        assertNull(deq.getLast());
        assertEquals(2, deq.get_getLast_status());
        deq.addFront(0);
        assertEquals(1, deq.size());
        assertEquals((Integer) 0, deq.getFront());
        assertEquals((Integer) 0, deq.getLast());
        assertEquals(1, deq.get_getLast_status());
    }
}