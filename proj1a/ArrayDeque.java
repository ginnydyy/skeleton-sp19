public class ArrayDeque<T> {

    /**
     * add and remove must take constant time, except during resizing operations.
     * <p>
     * get and size must take constant time.
     * <p>
     * The starting size of your array should be 8.
     * <p>
     * The amount of memory that your program uses at any given time
     * must be proportional to the number of items.
     * For example, if you add 10,000 items to the deque, and then remove 9,999 items,
     * you shouldn’t still be using an array of length 10,000ish.
     * For arrays of length 16 or more, your usage factor should always be at least 25%.
     * For smaller arrays, your usage factor can be arbitrarily low.
     */

    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    private static int INITIAL_LENGTH = 8;
    private static int LENGTH_THRESHOLD = 16;
    private static double MIN_USAGE_FACTOR = 0.25;

    public ArrayDeque() {
        super();
        items = (T[]) new Object[INITIAL_LENGTH];
        nextFirst = 0;
        nextLast = nextFirst + 1;
        size = 0;
    }

    public ArrayDeque(ArrayDeque other) {
        super();
        size = other.size();
        items = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            items[i] = (T) other.get(i);
        }
        nextFirst = getPreviousIndex(0);
        nextLast = getNextIndex(size - 1);
    }

    /**
     * @param item
     */
    public void addFirst(T item) {
        resize();
        items[nextFirst] = item;
        size++;
        nextFirst = getPreviousIndex(nextFirst);
    }

    /**
     * @param item
     */
    public void addLast(T item) {
        resize();
        items[nextLast] = item;
        size++;
        nextLast = getNextIndex(nextLast);
    }

    private int getPreviousIndex(int index) {
        return (index - 1 + items.length) % items.length;
    }

    private int getNextIndex(int index) {
        return (index + 1) % items.length;
    }

    /**
     * @return
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    //TODO rewrite after get() is done
    public void printDeque() {
        if (size != 0) {
            int index = getNextIndex(nextFirst);
            for (int i = 0; i < size; i++) {
                System.out.print(items[index]);
                index = getNextIndex(index);
                if (i != size - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    /**
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     *
     * @return
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item = get(0);
        int firstIndex = getNextIndex(nextFirst);
        items[firstIndex] = null;
        size--;
        nextFirst = firstIndex;
        resize();
        return item;
    }

    /**
     * @return
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T item = get(size - 1);
        int lastIndex = getPreviousIndex(nextLast);
        items[lastIndex] = null;
        size--;
        nextLast = lastIndex;
        resize();
        return item;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque!
     *
     * @param index
     * @return
     */
    public T get(int index) {
        if (size == 0 || index < 0 || index > size - 1) {
            return null;
        }
        int arrayIndex = getNextIndex(nextFirst + index);
        return items[arrayIndex];
    }

    private double getUsageFactor() {
        return (double) size / items.length;
    }

    /**
     * Check whether the current array needs to resize.
     * If so, then create a new array.
     * Copy the existing items to the new array, starting from index 0.
     * Update the nextFrist and nextLast.
     */
    private void resize() {
        int newLength = items.length;
        if (items.length > LENGTH_THRESHOLD && getUsageFactor() < MIN_USAGE_FACTOR) {
            newLength = items.length / 2;
        } else if (size == items.length) {
            newLength = 2 * items.length;
        }

        if (newLength != items.length) {
            T[] newItems = (T[]) new Object[newLength];
            int oldItemsIndex = getNextIndex(nextFirst);
            for (int i = 0; i < size; i++) {
                newItems[i] = items[oldItemsIndex];
                oldItemsIndex = getNextIndex(oldItemsIndex);
            }
            items = newItems;
            nextFirst = getPreviousIndex(0);
            nextLast = getNextIndex(size - 1);
        }
    }
}
