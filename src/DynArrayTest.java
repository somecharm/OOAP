import org.junit.Test;

import static org.junit.Assert.*;

public class DynArrayTest {
    @Test
    public void makeArray() {
        DynArray ar = new DynArray<Integer>(Integer.class);
    }

    @Test
    public void get_item() {
        DynArray ar = new DynArray<Integer>(Integer.class);
        for (int i = 0; i < 20; i++) {
            ar.append(new Integer(i));
            assertEquals(i, ar.get_item(i));
            assertEquals(i + 1, ar.size());
        }
        ar.get_item(-1);
        assertEquals(2, ar.get_get_status());
        ar.get_item(32);
        assertEquals(2, ar.get_get_status());
    }

    @Test
    public void append() {
        DynArray ar = new DynArray<Integer>(Integer.class);
        for (int i = 0; i < 16; i++) {
            ar.append(new Integer(i));
            assertEquals(i, ar.get_item(i));
            assertEquals(i + 1, ar.size());
        }
        assertEquals(16, ar.size());
    }

    @Test
    public void appendMore() {
        DynArray ar = new DynArray<Integer>(Integer.class);
        for (int i = 0; i < 65; i++) {
            ar.append(new Integer(i));
            assertEquals(i, ar.get_item(i));
            assertEquals(i + 1, ar.size());
        }
        assertEquals(65, ar.size());
        assertEquals(128, ar.capacity);
    }

    @Test
    public void insert() {
        DynArray ar = new DynArray<Integer>(Integer.class);
        for (int i = 0; i < 3; i++) {
            ar.append(new Integer(i));
        }
        ar.insert(new Integer(222), 2);
        assertEquals(0, ar.get_item(0));
        assertEquals(1, ar.get_item(1));
        assertEquals(222, ar.get_item(2));
        assertEquals(2, ar.get_item(3));

        assertEquals(4, ar.size());
        assertEquals(16, ar.capacity);
    }

    @Test
    public void insertLast() {
        DynArray ar = new DynArray<Integer>(Integer.class);
        for (int i = 0; i < 3; i++) {
            ar.append(new Integer(i));
        }
        ar.insert(new Integer(222), 3);

        assertEquals(0, ar.get_item(0));
        assertEquals(1, ar.get_item(1));
        assertEquals(2, ar.get_item(2));
        assertEquals(222, ar.get_item(3));

        assertEquals(4, ar.size());
        assertEquals(16, ar.capacity);
    }

    @Test
    public void insertMore() {
        DynArray ar = new DynArray<Integer>(Integer.class);
        for (int i = 0; i < 16; i++) {
            ar.append(new Integer(i));
            assertEquals(i, ar.get_item(i));
            assertEquals(i + 1, ar.size());
        }
        ar.insert(new Integer(222), 3);
        assertEquals(17, ar.size());
        assertEquals(32, ar.capacity);
    }

    @Test
    public void insertWrongIndex() {
        DynArray ar = new DynArray<Integer>(Integer.class);
        for (int i = 0; i < 3; i++) {
            ar.append(new Integer(i));
        }
        ar.insert(new Integer(222), 4);
        assertEquals(2, ar.get_insert_status());
        ar.insert(new Integer(222), -1);
        assertEquals(2, ar.get_insert_status());

    }

    @Test
    public void remove() {
        DynArray ar = new DynArray<Integer>(Integer.class);
        for (int i = 0; i < 3; i++) {
            ar.append(new Integer(i));
        }
        ar.remove(0);
        assertEquals(1, ar.get_item(0));
        assertEquals(2, ar.get_item(1));
        assertEquals(2, ar.size());
        assertEquals(16, ar.capacity);
    }

    @Test
    public void removeLast() {
        DynArray ar = new DynArray<Integer>(Integer.class);
        ar.append(new Integer(1));
        ar.remove(0);
        assertNull(ar.get_item(0));
        assertEquals(0, ar.size());
        assertEquals(16, ar.capacity);
    }

    @Test
    public void removeHalf() {
        DynArray ar = new DynArray<Integer>(Integer.class);
        for (int i = 0; i < 60; i++) {
            ar.append(new Integer(i));
        }
        for (int i = 0; i < 29; i++) {
            ar.remove(ar.size() - 1);
        }
        assertEquals(31, ar.size());
        assertEquals(42, ar.capacity);
    }

    @Test
    public void removeWrongIndex() {
        DynArray ar = new DynArray<Integer>(Integer.class);
        for (int i = 0; i < 10; i++) {
            ar.append(new Integer(i));
        }
        ar.remove(11);
        assertEquals(2, ar.get_remove_status());
        assertEquals(10, ar.size());
        assertEquals(16, ar.capacity);
    }

    @Test
    public void clear() {
        DynArray ar = new DynArray<Integer>(Integer.class);
        for (int i = 0; i < 20; i++) {
            ar.append(new Integer(i));
        }
        ar.clear();
        assertEquals(0, ar.size());
        assertEquals(16, ar.capacity);
    }
}