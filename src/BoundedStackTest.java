import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class BoundedStackTest {

    @Test
    public void size() {
        BoundedStack<Integer> boundedStack = new BoundedStack<>();
        boundedStack.push(0);
        boundedStack.push(1);
        boundedStack.push(2);
        assertEquals(3, boundedStack.size());
    }

    @Test
    public void sizeEmpty() {
        BoundedStack<Object> boundedStack = new BoundedStack<>();
        assertEquals(0, boundedStack.size());
    }

    @Test
    public void peek() {
        BoundedStack<Object> boundedStack = new BoundedStack<>();
        boundedStack.push(222);
        boundedStack.push(1);
        assertEquals(1, boundedStack.peek());
        assertEquals(1, boundedStack.get_peek_status());
    }

    @Test
    public void peekEmpty() {
        BoundedStack<Object> boundedStack = new BoundedStack<>();
        assertNull(boundedStack.peek());
        assertEquals(2, boundedStack.get_peek_status());
    }

    @Test
    public void popEmpty() {
        BoundedStack<Object> boundedStack = new BoundedStack<>();
        boundedStack.pop();
        assertEquals(0, boundedStack.size());
        assertEquals(2, boundedStack.get_pop_status());
    }

    @Test
    public void pop() {
        BoundedStack<Integer> boundedStack = new BoundedStack<>();
        boundedStack.push(0);
        boundedStack.push(1);
        boundedStack.push(2);
        boundedStack.push(3);
        boundedStack.pop();
        assertEquals(1, boundedStack.get_pop_status());
        assertEquals(3, boundedStack.size());
    }

    @Test
    public void popAll() {
        BoundedStack<Object> boundedStack = new BoundedStack<>();
        boundedStack.push(0);
        boundedStack.push(1);
        boundedStack.push(2);
        boundedStack.push(3);
        boundedStack.pop();
        boundedStack.pop();
        boundedStack.pop();
        assertEquals(1, boundedStack.get_pop_status());
        assertEquals(1, boundedStack.size());
        boundedStack.pop();
        assertEquals(1, boundedStack.get_pop_status());
        assertEquals(0, boundedStack.size());
    }

    @Test
    public void push() {
        BoundedStack<Object> boundedStack = new BoundedStack<>();
        boundedStack.push(0);
        assertEquals(1, boundedStack.get_push_status());
        boundedStack.push(1);
        assertEquals(1, boundedStack.get_push_status());
        boundedStack.push(2);
        assertEquals(1, boundedStack.get_push_status());
        boundedStack.push(3);
        assertEquals(1, boundedStack.get_push_status());
        assertEquals(3, boundedStack.peek());
        assertEquals(4, boundedStack.size());
    }

    @Test
    public void push32() {
        BoundedStack<Object> boundedStack = new BoundedStack<>();
        for (int i = 1; i <33;i++){
            boundedStack.push(i);
            assertEquals(1, boundedStack.get_push_status());
            assertEquals(i, boundedStack.peek());
        }
        assertEquals(32, boundedStack.size());
        boundedStack.push(33);
        assertEquals(2, boundedStack.get_push_status());
        assertEquals(32, boundedStack.peek());
        assertEquals(32, boundedStack.size());
    }

    @Test
    public void clear() {
        BoundedStack<Object> boundedStack = new BoundedStack<>();
        boundedStack.push(0);
        boundedStack.push(1);
        boundedStack.push(2);
        boundedStack.push(3);
        assertEquals(4, boundedStack.size());
        boundedStack.clear();
        assertEquals(0, boundedStack.size());
        assertEquals(0, boundedStack.get_push_status());
        assertEquals(0, boundedStack.get_peek_status());
        assertEquals(0, boundedStack.get_pop_status());
    }

    @Test
    public void constructor() {
        BoundedStack<String> boundedStack = new BoundedStack<>(3);
        assertEquals(0, boundedStack.get_push_status());
        assertEquals(0, boundedStack.get_peek_status());
        assertEquals(0, boundedStack.get_pop_status());
        boundedStack.push("All ");
        assertEquals(1, boundedStack.get_push_status());
        assertEquals(0, boundedStack.get_peek_status());
        assertEquals(0, boundedStack.get_pop_status());
        boundedStack.push("is ");
        assertEquals(1, boundedStack.get_push_status());
        assertEquals(0, boundedStack.get_peek_status());
        assertEquals(0, boundedStack.get_pop_status());
        boundedStack.push("correct");
        assertEquals(1, boundedStack.get_push_status());
        assertEquals(0, boundedStack.get_peek_status());
        assertEquals(0, boundedStack.get_pop_status());
        assertEquals(3, boundedStack.size());
        boundedStack.push("out of bounds");
        assertEquals(2, boundedStack.get_push_status());
        assertEquals(0, boundedStack.get_peek_status());
        assertEquals(0, boundedStack.get_pop_status());
        assertEquals(3, boundedStack.size());
    }
}