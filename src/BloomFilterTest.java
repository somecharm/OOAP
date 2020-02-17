import org.junit.Test;

import static org.junit.Assert.*;

public class BloomFilterTest {

    @Test
    public void is_value() {
        BloomFilter<String> bf = new BloomFilter<>(32);
        bf.add("0123456789");
        bf.add("5678901234");
        bf.add("9012345678");
        assertTrue(bf.is_value("5678901234"));
        assertTrue(bf.is_value("0123456789"));
        assertTrue(bf.is_value("9012345678"));
        assertFalse(bf.is_value("qwerty"));
        assertFalse(bf.is_value("901234567"));
        assertFalse(bf.is_value("bloom"));
    }

    @Test
    public void is_value2() {
        BloomFilter<Integer> bf = new BloomFilter<>(32);
        bf.add(1236);
        bf.add(1345);
        bf.add(47486);
        assertTrue(bf.is_value(1236));
        assertTrue(bf.is_value(1345));
        assertTrue(bf.is_value(47486));
        assertFalse(bf.is_value(6321));
        assertFalse(bf.is_value(5413));
        assertFalse(bf.is_value(67448));
        assertFalse(bf.is_value(76484));
    }
}