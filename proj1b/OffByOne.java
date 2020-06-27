public class OffByOne implements CharacterComparator {
    /**
     * Returns true for characters that are different by exactly one.
     *
     * @param x a given character
     * @param y a given character
     * @return Returns true for characters that are different by exactly one.
     */
    @Override
    public boolean equalChars(char x, char y) {
        return ((x - y) == 1 || (x - y) == -1);
    }

}
