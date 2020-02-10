import org.junit.Test;

import static org.junit.Assert.*;

public class NativeDictionaryTest {

    @Test
    public void put() {
        NativeDictionary<String, Integer> nd = new NativeDictionary(Integer.class, 9);
        assertEquals(0, nd.size());
        nd.put("first", 1);
        assertEquals(1, nd.size());
        assertEquals((Integer) 1, nd.get("first"));
        assertEquals(1, nd.get_put_status());
        nd.put("first", 1);
        assertEquals(1, nd.size());
        assertEquals((Integer) 1, nd.get("first"));
        assertEquals(2, nd.get_put_status());
        nd.put("second", 2);
        assertEquals(2, nd.size());
        assertEquals((Integer) 1, nd.get("first"));
        assertEquals((Integer) 2, nd.get("second"));
        assertEquals(1, nd.get_put_status());
    }

    @Test
    public void remove() {
        NativeDictionary<String, Integer> nd = new NativeDictionary(Integer.class, 9);
        assertEquals(0, nd.size());
        nd.remove("first");
        assertEquals(2, nd.get_remove_status());
        nd.put("first", 1);
        assertEquals(1, nd.size());
        assertEquals((Integer) 1, nd.get("first"));
        assertEquals(1, nd.get_put_status());
        nd.remove("first");
        assertEquals(1, nd.get_remove_status());
        assertEquals(0, nd.size());
        nd.put("first", 1);
        assertEquals(1, nd.size());
        assertEquals((Integer) 1, nd.get("first"));
        assertEquals(1, nd.get_put_status());
        nd.put("second", 2);
        assertEquals(2, nd.size());
        assertEquals((Integer) 1, nd.get("first"));
        assertEquals((Integer) 2, nd.get("second"));
        assertEquals(1, nd.get_put_status());
        nd.remove("first");
        assertEquals(1, nd.get_remove_status());
        assertEquals(1, nd.size());
        nd.remove("first");
        assertEquals(2, nd.get_remove_status());
        nd.remove("second");
        assertEquals(1, nd.get_remove_status());
        assertEquals(0, nd.size());
    }

    @Test
    public void is_key() {
        NativeDictionary<String, Integer> nd = new NativeDictionary(Integer.class, 9);
        nd.put("first", 1);
        nd.put("second", 2);
        nd.put("third", 3);
        assertTrue(nd.is_key("first"));
        assertTrue(nd.is_key("second"));
        assertTrue(nd.is_key("third"));
        assertFalse(nd.is_key("sedcaed"));
        nd.remove("first");
        nd.remove("second");
        nd.remove("third");
        assertFalse(nd.is_key("first"));
        assertFalse(nd.is_key("second"));
        assertFalse(nd.is_key("third"));
    }

    @Test
    public void get() {
        NativeDictionary<String, Integer> nd = new NativeDictionary(Integer.class, 3);
        assertNull(nd.get("sfcsfc"));
        assertEquals(2, nd.get_get_status());
        nd.put("first", 1);
        nd.put("second", 2);
        nd.put("third", 3);
        assertEquals((Integer) 1, nd.get("first"));
        assertEquals(1, nd.get_get_status());
        assertEquals((Integer) 2, nd.get("second"));
        assertEquals(1, nd.get_get_status());
        assertEquals((Integer) 3, nd.get("third"));
        assertEquals(1, nd.get_get_status());
        assertNull(nd.get("forth"));
        assertEquals(2, nd.get_get_status());
        assertNull(nd.get("qqq"));
        assertEquals(2, nd.get_get_status());
        assertNull(nd.get("q"));
        assertEquals(2, nd.get_get_status());
        assertNull(nd.get("aa"));
        assertEquals(2, nd.get_get_status());
    }
}