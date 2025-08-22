package MyPriorityQueue;

import java.util.ArrayList;

/**
 * A min-heap implementation of the {@link MyPriorityQueue} interface using an {@link ArrayList}.
 * 
 * @param <T> the type of elements stored in the heap, must implement {@link Comparable}
 */
public class MinHeap <T extends Comparable<T>> implements MyPriorityQueue<T>  {

    /**
     * Internal list to store heap elements.
     */
    private ArrayList<T> data;

    /**
     * Constructs an empty MinHeap.
     * Time complexity: O(1)
     */
    public MinHeap(){
        data = new ArrayList<>();
    }

    /**
     * Adds an element to the min-heap and maintains the heap property.
     * Time complexity: O(log n)
     * @param element the element to add
     */
    @Override
    public void add(T element){
        data.add(element); // Add the element to the end of the list
        shiftUp(data.size() - 1); // Restore heap property by shifting it up
    }

    /**
     * Moves the element at the specified index up to its correct position to maintain heap order.
     * Time complexity: O(log n)
     * @param index the index of the element to shift up
     */
    private void shiftUp(int index){
        T element = data.get(index);

        // While the element is not at the root and smaller than its parent
        while(index > 0){
            int parentIndex = (index - 1) / 2;
            T parent = data.get(parentIndex);

            // If the heap property is satisfied, stop
            if(element.compareTo(parent) >= 0){
                break;
            }

            // Swap the element with its parent
            data.set(index, parent);
            data.set(parentIndex, element);
            index = parentIndex;
        }
    }

    /**
     * Checks if the heap is empty.
     * Time complexity: O(1)
     * @return {@code true} if the heap is empty, {@code false} otherwise
     */
    @Override
    public Boolean isEmpty(){
        return data.isEmpty();
    }

    /**
     * Removes and returns the minimum element (root) from the heap.
     * Time complexity: O(log n)
     * @return the minimum element, or {@code null} if the heap is empty
     */
    @Override
    public T poll(){
        if(isEmpty()){
            return null; // Nothing to poll
        }

        T element = data.get(0); // Root element (min)

        if(data.size() == 1){
            data.remove(0); // Just remove and return if only one element
            return element;
        }

        T lastElement = data.remove(data.size() - 1); // Remove last element
        data.set(0, lastElement); // Replace root with last element

        shiftDown(0); // Restore heap property by shifting down

        return element;
    }

    /**
     * Moves the element at the specified index down to its correct position to maintain heap order.
     * 
     * @param index the index of the element to shift down
     * Time complexity: O(log n)
     */
    private void shiftDown(int index){
        int size = data.size();
        T element = data.get(index);

        while(true){
            int leftChildIndex = index * 2 + 1;
            int rightChildIndex = leftChildIndex + 1;

            // If no children, stop
            if(leftChildIndex >= size){
                break;
            }

            // Find the smaller of the two children
            int minIndex = leftChildIndex;
            if(rightChildIndex < size && data.get(rightChildIndex).compareTo(data.get(leftChildIndex)) < 0){
                minIndex = rightChildIndex;
            }

            // If the heap property is satisfied, stop
            if(element.compareTo(data.get(minIndex)) <= 0){
                break;
            }

            // Swap with the smaller child
            data.set(index, data.get(minIndex));
            data.set(minIndex, element);

            index = minIndex; // Continue from the child's position
        }
    }

}
