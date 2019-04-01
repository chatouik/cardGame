package cardgame.utils;

import java.util.Iterator;

public interface CircularIterator<E> extends Iterator<E> {
    /**
     * Returns true if the list is not empty
     */
    boolean hasPrevious();

    /**
     * Returns to the previous position and returns
     * the element in that position.
     */
    E previous();

    /**
     * Returns the index of the element that
     * would be returned by a subsequent call to next.
     * If at the end of the list, circles around and
     * returns the first position.
     */
    int nextIndex();

    /**
     * Returns the index of the element that would be
     * returned by a subsequent call to previous.
     * If at the beginning of the list, circles
     * around and returns the last position.
     */
    int previousIndex();
}
