package DSA.Sorting;

import java.util.Comparator;

/**
 * GTUSelectSort class implements the Selection Sort algorithm
 * by extending the abstract GTUSorter class.
 */
public class GTUSelectSort extends GTUSorter {

    /**
     * Sorts the array in the range [start, end) using the Selection Sort algorithm.
     * Time Complexity: O(n²)
     * Where n = end - start, the number of elements in the subarray to be sorted.
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
            return; // cannot sort null array or without comparator
        }
        if (start < 0 || end > arr.length || start >= end) {
            return; // invalid indices
        }

        // Perform selection sort
        for (int i = start; i < end; i++) {
            int minIndex = minIndex(arr, i, end, comparator); // find min element index in [i, end)
            swap(arr, i, minIndex); // swap current element with min element found
        }
    }

    /**
     * Finds the index of the minimum element in the subarray arr[start:end).
     * Time Complexity: O(n)
     * Where n = end - start, the number of elements in the subarray being scanned.
     *
     * @param <T>        the element type
     * @param arr        the array to search in
     * @param start      the start index (inclusive)
     * @param end        the end index (exclusive)
     * @param comparator the comparator used to compare elements
     * @return the index of the minimum element found
     */
    private <T> int minIndex(T[] arr, int start, int end, Comparator<T> comparator) {
        int minIndex = start;
        // Iterate over the subarray to find the minimum element
        for (int i = start + 1; i < end; i++) {
            if (comparator.compare(arr[i], arr[minIndex]) < 0) {
                minIndex = i; // update minIndex if current element is smaller
            }
        }
        return minIndex;
    }

    /**
     * Swaps two elements in the array at indices i and j.
     * Time Complexity: O(1)
     * Constant time regardless of array size.
     *
     * @param <T> the element type
     * @param arr the array containing elements to swap
     * @param i   index of the first element
     * @param j   index of the second element
     */
    private <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
