package MyPriorityQueue;

/**
 * A generic interface for a priority queue data structure.
 *
 * @param <T> the type of elements held in this priority queue, which must be comparable
 */
public interface MyPriorityQueue <T extends Comparable<T>> {
    
    /**
     * Adds an item to the priority queue.
     * Time complexity: O(log n)
     * @param item the item to be added to the queue
     */
    void add(T item);

    /**
     * Retrieves and removes the highest-priority item from the queue.
     * Time complexity:O(log n)
     * @return the item with the highest priority, or {@code null} if the queue is empty
     */
    T poll();

    /**
     * Checks if the priority queue is empty.
     * Time complexity: O(1)
     * @return {@code true} if the queue is empty, {@code false} otherwise
     */
    Boolean isEmpty();
}
