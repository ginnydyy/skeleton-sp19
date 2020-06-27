interface Deque<T> {
    public void addFirst(T item);//: Adds an item of type T to the front of the deque.

    public void addLast(T item);//: Adds an item of type T to the back of the deque.

    // Returns true if deque is empty, false otherwise.
    default public boolean isEmpty() {
        return size() == 0;
    }

    public int size();//: Returns the number of items in the deque.

    public void printDeque();//: Prints the items in the deque from first to last, separated by a space. Once all the items have been printed, print out a new line.

    public T removeFirst();//: Removes and returns the item at the front of the deque. If no such item exists, returns null.

    public T removeLast();//: Removes and returns the item at the back of the deque. If no such item exists, returns null.

    public T get(int index);//: Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!
}
