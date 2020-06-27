import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestOffByN {
    static CharacterComparator offBy1 = new OffByN(1);
    static CharacterComparator offBy2 = new OffByN(2);
    static CharacterComparator offByMinus2 = new OffByN(-2);

    @Test
    public void testEqualChars() {
        assertTrue(offBy1.equalChars('a', 'b'));
        assertTrue(offBy1.equalChars('c', 'b'));
        assertTrue(offBy1.equalChars('&', '%'));
        assertFalse(offBy1.equalChars('a', 'z'));

        assertTrue(offBy2.equalChars('a', 'c'));
        assertTrue(offBy2.equalChars('c', 'a'));
        assertTrue(offBy2.equalChars('$', '&'));
        assertFalse(offBy2.equalChars('a', 'z'));

        assertTrue(offByMinus2.equalChars('a', 'c'));
        assertTrue(offByMinus2.equalChars('c', 'a'));
        assertTrue(offByMinus2.equalChars('$', '&'));
        assertFalse(offByMinus2.equalChars('a', 'z'));
    }
}
