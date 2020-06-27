import org.junit.Test;

import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome("no1on"));
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("Aa"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("")); // empty string
        assertTrue(palindrome.isPalindrome(" ")); // one space
        assertTrue(palindrome.isPalindrome("  ")); // two space
        assertFalse(palindrome.isPalindrome(null));
    }

    @Test
    public void testOverloadedIsPalindrome() {
        OffByOne obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("anmb", obo));
        assertTrue(palindrome.isPalindrome("an1mb", obo));
        assertTrue(palindrome.isPalindrome("an12mb", obo));
        assertTrue(palindrome.isPalindrome("an%&mb", obo));
        assertFalse(palindrome.isPalindrome("anna", obo));
        assertFalse(palindrome.isPalindrome("ana", obo));
        assertFalse(palindrome.isPalindrome("anmB", obo));
        assertFalse(palindrome.isPalindrome("anMb", obo));
        assertTrue(palindrome.isPalindrome("a", obo));
        assertTrue(palindrome.isPalindrome("", obo)); // empty string
        assertTrue(palindrome.isPalindrome(" ", obo)); // one space
        assertFalse(palindrome.isPalindrome("  ", obo)); // two space
        assertFalse(palindrome.isPalindrome(null, obo));
    }

    @Test
    public void testOverloadedIsPalindromeWithOffByTwo() {
        CharacterComparator obo = new OffByN(2);
        assertTrue(palindrome.isPalindrome("coma", obo));
        assertTrue(palindrome.isPalindrome("co1ma", obo));
        assertTrue(palindrome.isPalindrome("co13ma", obo));
        assertTrue(palindrome.isPalindrome("co1$&3ma", obo));
        assertFalse(palindrome.isPalindrome("anna", obo));
        assertFalse(palindrome.isPalindrome("ana", obo));
        assertFalse(palindrome.isPalindrome("co1mA", obo));
        assertFalse(palindrome.isPalindrome("co13Ma", obo));
        assertTrue(palindrome.isPalindrome("a", obo));
        assertTrue(palindrome.isPalindrome("", obo)); // empty string
        assertTrue(palindrome.isPalindrome(" ", obo)); // one space
        assertFalse(palindrome.isPalindrome("  ", obo)); // two space
        assertFalse(palindrome.isPalindrome(null, obo));
    }
}
