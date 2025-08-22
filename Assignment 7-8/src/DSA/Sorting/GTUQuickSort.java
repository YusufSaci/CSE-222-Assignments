package DSA.Sorting;

import java.util.Comparator;
import java.util.Random;

/**
 * GTUQuickSort class implements the Quick Sort algorithm with an optional
 * hybrid approach using another sorter for small partitions.
 */
public class GTUQuickSort extends GTUSorter {

    private GTUSorter anotherSorter;  // Optional sorter for small partitions
    private int partitionLimit;       // Threshold to switch to anotherSorter
    private Random random;            // Random object for pivot selection

    /**
     * Constructor for hybrid QuickSort with another sorter and partition size limit.
     *
     * @param anotherSorter  the alternative sorter to use on small partitions
     * @param partitionLimit the size limit below which to switch to anotherSorter
     */
    public GTUQuickSort(GTUSorter anotherSorter, int partitionLimit) {
        this.anotherSorter = anotherSorter;
        this.partitionLimit = partitionLimit;
        this.random = new Random();
    }

    /**
     * Default constructor without hybrid optimization.
     */
    public GTUQuickSort() {
        this.anotherSorter = null;
        this.partitionLimit = 0;
        this.random = new Random();
    }

    /**
     * Sorts the array in the range [start, end) using Quick Sort.
     * Time Complexity:
     * - Best/Average Case: O(n log n)
     * - Worst Case: O(n²)
     * Where n = end - start
     *
     * @param <T>        the element type
     * @param arr        the array to sort
     * @param start      start index (inclusive)
     * @param end        end index (exclusive)
     * @param comparator comparator to define element order
     */
    @Override
    protected <T> void sort(T[] arr, int start, int end, Comparator<T> comparator) {
        // Validate inputs
        if (arr == null || comparator == null) {
            return; // cannot sort null or without comparator
        }
        if (start < 0 || end > arr.length || start >= end) {
            return; // invalid range
        }

        quickSort(arr, start, end, comparator); // perform quicksort
    }

    /**
     * Recursive quicksort implementation.
     * Switches to anotherSorter if partition size is below partitionLimit.
     * Time Complexity:
     * - Best/Average Case: O(n log n)
     * - Worst Case: O(n²)
     * Where n = end - start
     *
     * @param <T>        element type
     * @param arr        array to sort
     * @param start      start index (inclusive)
     * @param end        end index (exclusive)
     * @param comparator comparator to compare elements
     */
    private <T> void quickSort(T[] arr, int start, int end, Comparator<T> comparator) {
        // Use alternative sorter for small partitions if available
        if (anotherSorter != null && end - start < partitionLimit && end - start > 1) {
            anotherSorter.sort(arr, start, end, comparator);
            return;
        }

        // Base case: single element or empty range
        if (end - start <= 1) {
            return;
        }

        int pivot = partition(arr, start, end, comparator); // partition the array
        quickSort(arr, start, pivot, comparator);           // sort left partition
        quickSort(arr, pivot + 1, end, comparator);         // sort right partition
    }

    /**
     * Swaps two elements in the array at indices i and j.
     * Time Complexity: O(1)
     *
     * @param <T> element type
     * @param arr array
     * @param i   first index
     * @param j   second index
     */
    private <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Partitions the array around a pivot chosen randomly.
     * Time Complexity: O(n)
     * Where n = end - start
     *
     * @param <T>        element type
     * @param arr        array to partition
     * @param start      start index (inclusive)
     * @param end        end index (exclusive)
     * @param comparator comparator for element ordering
     * @return the final pivot index
     */
    private <T> int partition(T[] arr, int start, int end, Comparator<T> comparator) {
        // Randomly choose a pivot and move it to the end
        int pivotIndex = start + random.nextInt(end - start);
        swap(arr, pivotIndex, end - 1);

        T pivot = arr[end - 1];
        int i = start - 1;

        // Rearrange elements based on pivot comparison
        for (int j = start; j < end - 1; j++) {
            if (comparator.compare(arr[j], pivot) <= 0) {
                i++;
                swap(arr, i, j);
            }
        }

        // Place pivot in its correct sorted position
        swap(arr, i + 1, end - 1);
        return i + 1;
    }
}
