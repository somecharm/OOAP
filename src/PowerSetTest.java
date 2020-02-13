import org.junit.Test;

import static org.junit.Assert.*;

public class PowerSetTest {

    @Test
    public void intersection() {
        PowerSet<Integer> ps = new PowerSet<>(Integer.class, 5);
        PowerSet<Integer> set = new PowerSet<>(Integer.class, 3);
        ps.put(1);
        ps.put(3);
        ps.put(5);
        ps.put(7);
        ps.put(9);
        set.put(1);
        set.put(3);
        set.put(5);
        set.put(7);
        set.put(9);
        PowerSet<Integer> result = ps.intersection(set);
        assertEquals(3, result.size());
        assertTrue(result.get(1));
        assertTrue(result.get(3));
        assertTrue(result.get(5));
        assertFalse(result.get(7));
        assertFalse(result.get(9));

    }

    @Test
    public void union() {
        PowerSet<Integer> ps = new PowerSet<>(Integer.class, 5);
        PowerSet<Integer> set = new PowerSet<>(Integer.class, 5);
        ps.put(1);
        ps.put(3);
        ps.put(5);
        ps.put(3);
        ps.put(5);
        set.put(2);
        set.put(4);
        set.put(6);
        set.put(1);
        set.put(3);
        PowerSet<Integer> result = ps.union(set);
        assertEquals(6, result.size());
        assertTrue(result.get(1));
        assertTrue(result.get(3));
        assertTrue(result.get(5));
        assertTrue(result.get(2));
        assertTrue(result.get(4));
        assertTrue(result.get(6));
    }

    @Test
    public void difference() {
        PowerSet<Integer> ps = new PowerSet<>(Integer.class, 5);
        PowerSet<Integer> set = new PowerSet<>(Integer.class, 5);
        ps.put(1);
        ps.put(3);
        ps.put(5);
        ps.put(9);
        ps.put(7);
        set.put(2);
        set.put(4);
        set.put(6);
        set.put(1);
        set.put(3);
        PowerSet<Integer> result = ps.difference(set);
        assertEquals(3, result.size());
        assertTrue(result.get(5));
        assertTrue(result.get(7));
        assertTrue(result.get(9));
    }

    @Test
    public void is_subset() {
        PowerSet<Integer> ps = new PowerSet<>(Integer.class, 5);
        PowerSet<Integer> set = new PowerSet<>(Integer.class, 5);
        ps.put(1);
        ps.put(3);
        ps.put(5);
        ps.put(9);
        ps.put(7);
        set.put(9);
        set.put(1);
        set.put(3);
        assertTrue(ps.is_subset(set));
        assertFalse(set.is_subset(ps));
        set.put(7);
        assertTrue(ps.is_subset(set));
        set.put(2);
        assertFalse(ps.is_subset(set));
        set.remove(2);
        assertTrue(ps.is_subset(set));

    }
}