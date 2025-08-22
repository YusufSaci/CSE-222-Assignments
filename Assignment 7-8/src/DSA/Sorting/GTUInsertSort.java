package DSA.Sorting;

import java.util.Comparator;

/**
 * GTUInsertSort class implements the Insertion Sort algorithm
 * by extending the abstract GTUSorter class.
 */
public class GTUInsertSort extends GTUSorter {

    /**
     * Sorts the array in the range [start, end) using the Insertion Sort algorithm.
     * * Time Complexity:
     * - Best Case:    O(n)     when array is already sorted
     * - Average Case: O(n²)
     * - Worst Case:   O(n²)    when array is reverse sorted
     * Where n = end - start
     *
     * @param <T>        the type of elements to be sorted
     * @param arr        the array to be sorted
     * @param start      the starting index (inclusive)
     * @param end        the ending index (exclusive)
     * @param comparator the Comparator to determine the order of the elements
     */
    @Override
    protected <T> void sort(T[] arr, int start, int end, Comparator<T> comparator) {
        // Validate inputs
        if (arr == null || comparator == null) {
            return; // cannot sort null or without comparator
        }
        if (start < 0 || end > arr.length || start >= end) {
            return; // invalid index range
        }

        // Perform insertion sort
        for (int i = start; i < end - 1; i++) {
            int j = i;
            T key = arr[i + 1]; // element to be inserted in sorted subarray

            // Shift elements greater than key to one position ahead
            while (j >= start && comparator.compare(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            // Place key in correct position
            arr[j + 1] = key;
        }
    }
}
