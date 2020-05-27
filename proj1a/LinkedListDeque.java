public class LinkedListDeque<T> {

    /**
     * add and remove operations must not involve any looping or recursion.
     * A single such operation must take “constant time”,
     * i.e. execution time should not depend on the size of the deque.
     * <p>
     * get must use iteration, not recursion.
     * <p>
     * size must take constant time.
     */

    private int size;
    private Node<T> sentinel; // circular sentinel topology

    /**
     * Creates an empty linked list deque.
     */
    public LinkedListDeque() {
        super();
        sentinel = new Node<T>(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /**
     * Creates a deep copy of other.
     *
     * @param other
     */
    public LinkedListDeque(LinkedListDeque other) {
        this();
        for (int i = 0; i < other.size(); i++) {
            addFirst((T) other.get(i));
        }
    }

    /**
     * Adds an item of type T to the front of the deque.
     *
     * @param item
     */
    public void addFirst(T item) {
        Node<T> newNode = new Node(item);
        newNode.next = sentinel.next;
        newNode.prev = sentinel;
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     *
     * @param item
     */
    public void addLast(T item) {
        Node<T> newNode = new Node(item);
        newNode.prev = sentinel.prev;
        newNode.next = sentinel;
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     *
     * @return
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Returns the number of items in the deque.
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        Node<T> p = sentinel;
        for (int i = 0; i < size; i++) {
            System.out.print(p.next.item);
            if (i < size - 1) {
                System.out.print(" ");
            }
            p = p.next;
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     *
     * @return
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node<T> node = sentinel.next;
        T item = node.item;
        sentinel.next = node.next;
        node.next.prev = sentinel;
        node = null;
        size--;
        return item;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     *
     * @return
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node<T> node = sentinel.prev;
        T item = node.item;
        sentinel.prev = node.prev;
        node.prev.next = sentinel;
        node = null;
        size--;
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
        Node<T> p = sentinel;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.next.item;
    }

    /**
     * Same as get, but uses recursion
     *
     * @param index
     * @return
     */
    public T getRecursive(int index) {
        if (size == 0 || index < 0 || index > size - 1) {
            return null;
        }
        Node<T> p = recursiveHelper(index, sentinel);
        return p.item;
    }

    private Node<T> recursiveHelper(int count, Node<T> p) {
        if (count == 0) {
            return p;
        } else {
            return recursiveHelper(--count, p.next);
        }
    }

    private static class Node<T> {
        Node<T> prev;
        Node<T> next;
        T item;

        Node(T item) {
            super();
            this.item = item;
        }
    }

}
