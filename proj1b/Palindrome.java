public class Palindrome {
    /**
     * Put the characters of the given string into a deque in the same order as in the string.
     *
     * @param word a given string.
     * @return an instance of Deque containing the characters.
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new ArrayDeque<>();
        if (word != null && !word.isEmpty()) {
            for (int i = 0; i < word.length(); i++) {
                d.addLast(word.charAt(i));
            }
        }
        return d;
    }

    /**
     * Return true if the given word is a palindrome, and false otherwise.
     *
     * @param word a given string.
     * @return true if the given word is a palindrome, and false otherwise.
     */
    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }
        if (word.isEmpty() || word.length() == 1) {
            return true;
        }

        return isPalindromeHelper(wordToDeque(word));
    }

    /**
     * Recursive solution to check whether the given deque contains palindrome characters.
     *
     * @param deque a given Deque instance.
     * @return true if the given deque contains palindrome characters, and false otherwise.
     */
    private boolean isPalindromeHelper(Deque<Character> deque) {
        if (deque.size() == 1 || deque.isEmpty()) {
            return true;
        }
        if (deque.removeFirst() == deque.removeLast()) {
            return isPalindromeHelper(deque);
        }
        return false;
    }

    /**
     * Return true if the string is a palindrome according to the character comparison test
     * provided by the CharacterComparator passed in as argument cc.
     *
     * @param word the given string
     * @param cc   the given CharacterComparator instance
     * @return Return true if the string is a palindrome
     * according to the character comparison test
     * provided by the CharacterComparator passed in as argument cc.
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null) {
            return false;
        }
        if (word.isEmpty() || word.length() == 1) {
            return true;
        }
        return overloadedIsPalindromeHelper(wordToDeque(word), cc);
    }

    private boolean overloadedIsPalindromeHelper(Deque<Character> deque, CharacterComparator cc) {
        if (deque.size() == 1 || deque.isEmpty()) {
            return true;
        }
        if (cc.equalChars(deque.removeFirst(), deque.removeLast())) {
            return overloadedIsPalindromeHelper(deque, cc);
        }
        return false;
    }
}
