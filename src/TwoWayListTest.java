import org.junit.Test;

import static org.junit.Assert.*;

public class TwoWayListTest {

    @Test
    public void TwoWayList() {
        TwoWayList<Integer> list = new TwoWayList<>();
        assertEquals(0, list.size());
    }

    @Test
    public void head() {
        TwoWayList<Integer> list = new TwoWayList<>();
        assertEquals(0, list.size());
        list.head();
        assertNull(list.get_pointer());
        list.add_tail(1);
        assertEquals(1, list.size());
        list.head();
        assertEquals((Integer) 1, list.get_pointer().value);
        list.add_tail(2);
        assertEquals(2, list.size());
        list.head();
        assertEquals((Integer) 1, list.get_pointer().value);
    }

    @Test
    public void tail() {
        TwoWayList<Integer> list = new TwoWayList<>();
        assertEquals(0, list.size());
        list.tail();
        assertNull(list.get_pointer());
        list.add_tail(1);
        assertEquals(1, list.size());
        list.tail();
        assertEquals((Integer) 1, list.get_pointer().value);
        list.add_tail(2);
        assertEquals(2, list.size());
        list.tail();
        assertEquals((Integer) 2, list.get_pointer().value);
    }

    @Test
    public void right() {
        TwoWayList<Integer> list = new TwoWayList<>();
        assertEquals(0, list.size());
        assertNull(list.get_pointer());
        list.right();
        assertNull(list.get_pointer());
        list.add_tail(1);
        assertEquals(1, list.size());
        assertEquals((Integer) 1, list.get_pointer().value);
        list.right();
        assertEquals(2, list.get_right_status());
        assertEquals((Integer) 1, list.get_pointer().value);
        list.add_tail(2);
        assertEquals(2, list.size());
        assertEquals((Integer) 1, list.get_pointer().value);
        list.right();
        assertEquals(1, list.get_right_status());
        assertEquals((Integer) 2, list.get_pointer().value);
        list.add_tail(3);
        assertEquals(3, list.size());
        assertEquals((Integer) 2, list.get_pointer().value);
        list.right();
        assertEquals(1, list.get_right_status());
        assertEquals((Integer) 3, list.get_pointer().value);
    }

    @Test
    public void put_right() {
        TwoWayList<Integer> list = new TwoWayList<>();
        list.put_right(1);
        assertEquals(2, list.get_put_right_status());
        assertEquals(0, list.size());
        assertNull(list.get_pointer());
        list.add_tail(1);
        assertEquals(1, list.size());
        assertEquals((Integer) 1, list.get_pointer().value);
        list.put_right(2);
        assertEquals(2, list.size());
        assertEquals((Integer) 1, list.get_pointer().value);
        assertEquals(1, list.get_put_right_status());
        list.put_right(3);
        assertEquals(3, list.size());
        assertEquals((Integer) 1, list.get_pointer().value);
        assertEquals(1, list.get_put_right_status());
        list.right();
        assertEquals((Integer) 3, list.get_pointer().value);
        list.right();
        assertEquals((Integer) 2, list.get_pointer().value);
    }

    @Test
    public void put_left() {
        TwoWayList<Integer> list = new TwoWayList<>();
        list.put_left(1);
        assertEquals(0, list.size());
        list.add_tail(1);
        list.put_left(2);
        assertEquals(2, list.size());
        assertEquals((Integer) 1, list.get_pointer().value);
        list.head();
        assertEquals((Integer) 2, list.get_pointer().value);
        list.put_left(3);
        assertEquals(3, list.size());
        assertEquals((Integer) 2, list.get_pointer().value);
        list.head();
        assertEquals((Integer) 3, list.get_pointer().value);
    }

    @Test
    public void remove1() {
        TwoWayList<Integer> list = new TwoWayList<>();
        list.remove();
        assertEquals(2, list.get_remove_status());
        list.add_tail(1);
        list.remove();
        assertEquals(1, list.get_remove_status());
        assertEquals(0, list.size());
        assertNull(list.get_pointer());
    }

    @Test
    public void remove_head() {
        TwoWayList<Integer> list = new TwoWayList<>();
        list.add_tail(1);
        list.add_tail(2);
        list.add_tail(3);
        list.head();
        list.remove();
        assertEquals(1, list.get_remove_status());
        assertEquals(2, list.size());
        assertEquals((Integer) 2, list.get_pointer().value);
        list.remove();
        assertEquals(1, list.get_remove_status());
        assertEquals(1, list.size());
        assertEquals((Integer) 3, list.get_pointer().value);
        list.remove();
        assertEquals(1, list.get_remove_status());
        assertEquals(0, list.size());
        assertNull(list.get_pointer());
    }

    @Test
    public void remove_tail() {
        TwoWayList<Integer> list = new TwoWayList<>();
        list.add_tail(1);
        list.add_tail(2);
        list.add_tail(3);
        list.tail();
        list.remove();
        assertEquals(1, list.get_remove_status());
        assertEquals(2, list.size());
        assertEquals((Integer) 2, list.get_pointer().value);
        list.remove();
        assertEquals(1, list.get_remove_status());
        assertEquals(1, list.size());
        assertEquals((Integer) 1, list.get_pointer().value);
        list.remove();
        assertEquals(1, list.get_remove_status());
        assertEquals(0, list.size());
        assertNull(list.get_pointer());
    }

    @Test
    public void remove() {
        TwoWayList<Integer> list = new TwoWayList<>();
        list.add_tail(1);
        list.add_tail(2);
        list.add_tail(3);
        list.right();
        assertEquals((Integer) 2, list.get_pointer().value);
        list.remove();
        assertEquals(1, list.get_remove_status());
        assertEquals(2, list.size());
        assertEquals((Integer) 3, list.get_pointer().value);
        list.remove();
        assertEquals(1, list.get_remove_status());
        assertEquals(1, list.size());
        assertEquals((Integer) 1, list.get_pointer().value);
        list.remove();
        assertEquals(1, list.get_remove_status());
        assertEquals(0, list.size());
        assertNull(list.get_pointer());
    }

    @Test
    public void clear() {
        TwoWayList<Integer> list = new TwoWayList<>();
        list.clear();
        assertEquals(0, list.size());
        list.add_tail(1);
        assertEquals(1, list.size());
        list.add_tail(2);
        assertEquals(2, list.size());
        list.clear();
        assertEquals(0, list.size());
        assertNull(list.get_pointer());
    }

    @Test
    public void add_tail() {
        TwoWayList<Integer> list = new TwoWayList<>();
        assertEquals(0, list.size());
        list.add_tail(1);
        assertEquals(1, list.size());
        list.add_tail(2);
        assertEquals(2, list.size());
    }

    @Test
    public void remove_all() {
        TwoWayList<Integer> list = new TwoWayList<>();
        list.add_tail(1);
        list.add_tail(2);
        list.add_tail(2);
        list.add_tail(1);
        list.add_tail(2);
        list.add_tail(2);
        list.add_tail(1);
        list.remove_all(1);
        assertEquals(1, list.get_remove_status());
        assertEquals(4, list.size());
    }

    @Test
    public void remove_all_1() {
        TwoWayList<Integer> list = new TwoWayList<>();
        list.add_tail(1);
        list.remove_all(1);
        assertEquals(0, list.size());
        assertEquals(1, list.get_remove_status());
    }

    @Test
    public void remove_all_2() {
        TwoWayList<Integer> list = new TwoWayList<>();
        list.add_tail(1);
        list.remove_all(2);
        assertEquals(1, list.size());
    }

    @Test
    public void remove_all_3() {
        TwoWayList<Integer> list = new TwoWayList<>();
        list.add_tail(1);
        list.add_tail(1);
        list.add_tail(1);
        list.add_tail(1);
        list.add_tail(1);
        list.add_tail(1);
        list.add_tail(1);
        list.add_tail(1);
        list.remove_all(1);
        assertEquals(0, list.size());
    }

    @Test
    public void replace() {
        TwoWayList<Integer> list = new TwoWayList<>();
        list.add_tail(1);
        list.replace(2);
        assertEquals(1, list.get_replace_status());
        assertEquals((Integer) 2, list.get_pointer().value);
    }

    @Test
    public void find_first() {
        TwoWayList<Integer> list = new TwoWayList<>();
        list.add_tail(1);
        list.add_tail(2);
        list.add_tail(3);
        list.add_tail(4);
        list.find(1);
        assertEquals(1, list.get_find_status());
        assertEquals((Integer) 1, list.get_pointer().value);
    }

    @Test
    public void find_last() {
        TwoWayList<Integer> list = new TwoWayList<>();
        list.add_tail(1);
        list.add_tail(2);
        list.add_tail(3);
        list.add_tail(4);
        list.find(4);
        assertEquals(1, list.get_find_status());
        assertEquals((Integer) 4, list.get_pointer().value);
    }

    @Test
    public void find() {
        TwoWayList<Integer> list = new TwoWayList<>();
        list.add_tail(1);
        list.add_tail(2);
        list.add_tail(3);
        list.add_tail(4);
        list.find(3);
        assertEquals(1, list.get_find_status());
        assertEquals((Integer) 3, list.get_pointer().value);
    }

    @Test
    public void find_not() {
        TwoWayList<Integer> list = new TwoWayList<>();
        list.add_tail(1);
        list.add_tail(2);
        list.add_tail(3);
        list.add_tail(4);
        list.find(5);
        assertEquals(2, list.get_find_status());
        assertEquals((Integer) 4, list.get_pointer().value);
    }

    @Test
    public void find_empty() {
        TwoWayList<Integer> list = new TwoWayList<>();
        list.find(5);
        assertEquals(2, list.get_find_status());
        assertNull(list.get_pointer());
    }

    @Test
    public void get() {
        TwoWayList<Integer> list = new TwoWayList<>();
        list.add_tail(1);
        list.add_tail(2);
        list.add_tail(3);
        list.add_tail(4);
        assertEquals(list.get(), list.get_pointer().value);
        assertEquals(1, list.get_get_status());
    }

    @Test
    public void get_empty() {
        TwoWayList<Integer> list = new TwoWayList<>();
        assertNull(list.get());
        assertNull(list.get_pointer());
        assertEquals(2, list.get_get_status());
    }


    @Test
    public void left() {
        TwoWayList<Integer> list = new TwoWayList<>();
        list.add_tail(1);
        list.add_tail(2);
        list.add_tail(3);
        list.add_tail(4);
        list.head();
        assertEquals((Integer) 1, list.get_pointer().value);
        list.tail();
        assertEquals((Integer) 4, list.get_pointer().value);
        list.left();
        assertEquals((Integer) 3, list.get_pointer().value);
        assertEquals(1, list.get_left_status());
        list.left();
        assertEquals((Integer) 2, list.get_pointer().value);
        assertEquals(1, list.get_left_status());
        list.left();
        assertEquals((Integer) 1, list.get_pointer().value);
        assertEquals(1, list.get_left_status());
        list.left();
        assertEquals((Integer) 1, list.get_pointer().value);
        assertEquals(2, list.get_left_status());
    }

    @Test
    public void left_empty() {
        TwoWayList<Integer> list = new TwoWayList<>();
        list.left();
        assertNull(list.get_pointer());
        assertEquals(2, list.get_left_status());
    }
}