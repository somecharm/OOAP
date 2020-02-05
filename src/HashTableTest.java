import org.junit.Test;

import static org.junit.Assert.*;

public class HashTableTest {

    @Test
    public void put() {
        HashTable<Double> ht = new HashTable(Double.class, 11);
        ht.put(0.369);
        assertEquals(1, ht.get_put_status());
        ht.put(0.369);
        assertEquals(2, ht.get_put_status());
        ht.put(0.4458478);
        assertEquals(1, ht.get_put_status());
        ht.put(4.222);
        assertEquals(1, ht.get_put_status());
        ht.put(456.222);
        assertEquals(1, ht.get_put_status());
        ht.put(7899.1);
        assertEquals(1, ht.get_put_status());
        ht.put(564.00003);
        assertEquals(1, ht.get_put_status());
        ht.put(787.785875);
        assertEquals(1, ht.get_put_status());
        ht.put(-4.222);
        assertEquals(1, ht.get_put_status());
        ht.put(0.022);
        assertEquals(1, ht.get_put_status());
        ht.put(5555.5555);
        assertEquals(1, ht.get_put_status());
        ht.put(5555.5555);
        assertEquals(2, ht.get_put_status());
        ht.put(-999.222);
        assertEquals(1, ht.get_put_status());
        ht.put(4.222);
        assertEquals(2, ht.get_put_status());
    }

    @Test
    public void remove() {
        HashTable<Double> ht = new HashTable(Double.class, 11);
        ht.put(0.4458478);
        ht.put(4.222);
        ht.put(-4.222);
        assertEquals(1, ht.get_put_status());
        ht.remove(4.222);
        assertEquals(1, ht.get_remove_status());
        ht.remove(-4.222);
        assertEquals(1, ht.get_remove_status());
        ht.remove(-4.222);
        assertEquals(2, ht.get_remove_status());
        ht.remove(0.4458478);
        assertEquals(1, ht.get_remove_status());
        ht.remove(0.4458478);
        assertEquals(2, ht.get_remove_status());
    }

    @Test
    public void find() {
        HashTable<Double> ht = new HashTable(Double.class, 11);
        assertFalse(ht.is_value(4.222));
        assertEquals(2, ht.get_find_status());
        ht.put(0.4458478);
        ht.put(4.222);
        ht.put(-4.222);
        assertTrue(ht.is_value(4.222));
        assertTrue(ht.is_value(-4.222));
        assertTrue(ht.is_value(0.4458478));
        assertEquals(1, ht.get_find_status());
        assertFalse(ht.is_value(635.369));
        assertEquals(2, ht.get_find_status());
        assertFalse(ht.is_value(-0.555));
        assertEquals(2, ht.get_find_status());
    }
}