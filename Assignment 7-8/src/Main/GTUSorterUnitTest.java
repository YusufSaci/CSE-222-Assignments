package Main;

import java.util.Arrays;
import java.util.Comparator;

import DSA.Sorting.GTUInsertSort;
import DSA.Sorting.GTUQuickSort;
import DSA.Sorting.GTUSelectSort;
import DSA.Sorting.GTUSorter;

/**
 * Unit test class for testing GTUSorter implementations
 */
public class GTUSorterUnitTest {
    
    /**
     * Runs a single test case on a sorter
     * @param sorter The sorter to test
     * @param input Input array
     * @param expected Expected sorted array
     * @param comparator Comparator to use
     * @return True if test passed, false otherwise
     */
    private static <T> boolean runTest(GTUSorter sorter, T[] input, T[] expected, Comparator<T> comparator) {
        T[] actual = Arrays.copyOf(input, input.length);
        sorter.sort(actual, comparator);
        return Arrays.equals(actual, expected);
    }
    
    /**
     * Runs a test case on all sorters
     * @param sorters Array of sorters to test
     * @param sorterNames Array of sorter names
     * @param input Input array
     * @param expected Expected sorted array
     * @param comparator Comparator to use
     * @param testName Name of the test case
     */
    private static <T> void testAllSorters(GTUSorter[] sorters, String[] sorterNames, 
                                      T[] input, T[] expected, Comparator<T> comparator, String testName) {
        System.out.println("\n=== Testing: " + testName + " ===");
        
        for (int i = 0; i < sorters.length; i++) {
            boolean passed = runTest(sorters[i], input, expected, comparator);
            if (passed) {
                System.out.println(sorterNames[i] + " ✅  Passed");
            } else {
                System.out.println(sorterNames[i] + " ❌ Failed");
                System.out.println("  Expected: " + Arrays.toString(expected));
                T[] actual = Arrays.copyOf(input, input.length);
                sorters[i].sort(actual, comparator);
                System.out.println("  Actual: " + Arrays.toString(actual));
            }
        }
    }
    
    /**
     * Main test method
     */
    public static void runAllTests() {
        // Initialize sorters
        GTUSorter[] sorters = new GTUSorter[5];
        String[] sorterNames = new String[5];
        
        sorters[0] = new GTUQuickSort(new GTUInsertSort(), 3);
        sorterNames[0] = "QuickSort with InsertSort (threshold=3)";
        
        sorters[1] = new GTUQuickSort(new GTUSelectSort(), 4);
        sorterNames[1] = "QuickSort with SelectSort (threshold=4)";
        
        sorters[2] = new GTUQuickSort();
        sorterNames[2] = "QuickSort";
        
        sorters[3] = new GTUInsertSort();
        sorterNames[3] = "InsertSort";
        
        sorters[4] = new GTUSelectSort();
        sorterNames[4] = "SelectSort";
        
        System.out.println("Starting GTUSorter unit tests...");
        
        // Test 1: Descending integer sort
        Integer[] input1 = {4, 1, 3, 2, 9, 7};
        Integer[] expected1 = {9, 7, 4, 3, 2, 1};
        testAllSorters(sorters, sorterNames, input1, expected1, 
                    (a, b) -> b - a, "Descending integer sort");
      
        // Test 2: Mixed positive/negative integers in ascending order
        Integer[] input2 = {-5, 3, 0, -2, 8, -1};
        Integer[] expected2 = {-5, -2, -1, 0, 3, 8};  
        testAllSorters(sorters, sorterNames, input2, expected2, 
                    Comparator.naturalOrder(), "Mixed positive/negative integers");
        
        // Test 3: Reversed integer array
        Integer[] input3 = {9, 8, 7, 6, 5, 4};
        Integer[] expected3 = {4, 5, 6, 7, 8, 9};
        testAllSorters(sorters, sorterNames, input3, expected3, 
                    Comparator.naturalOrder(), "Reversed integer array");

        // Test 4: Strings sorted by last character
        String[] input4 = {"dolphin", "cat", "apple", "dog"};
        String[] expected4 = {"apple", "dog", "dolphin", "cat"};
        testAllSorters(sorters, sorterNames, input4, expected4, 
                    Comparator.comparing(s -> s.charAt(s.length() - 1)), "Strings sorted by last character");

        // Test 5: Already sorted array
        Integer[] input5 = {1, 2, 3, 4, 5};
        Integer[] expected5 = {1, 2, 3, 4, 5};
        testAllSorters(sorters, sorterNames, input5, expected5, 
                    Comparator.naturalOrder(), "Already sorted array");

        // Test 6: Single element
        Integer[] input6 = {42};
        Integer[] expected6 = {42};
        testAllSorters(sorters, sorterNames, input6, expected6, 
                    Comparator.naturalOrder(), "Single element array");

        // Test 7: Empty array
        Integer[] input7 = {};
        Integer[] expected7 = {};
        testAllSorters(sorters, sorterNames, input7, expected7, 
                    Comparator.naturalOrder(), "Empty array");

        // Test 8: All elements equal
        Integer[] input8 = {7, 7, 7, 7};
        Integer[] expected8 = {7, 7, 7, 7};
        testAllSorters(sorters, sorterNames, input8, expected8, 
                    Comparator.naturalOrder(), "All elements equal");

        // Test 9: Strings sorted by length
        String[] input9 = {"zebra", "ca", "hippopotamus", "ant"};
        String[] expected9 = {"ca", "ant", "zebra", "hippopotamus"};
        testAllSorters(sorters, sorterNames, input9, expected9, 
                    Comparator.comparingInt(String::length), "Strings sorted by length");

        // Test 10: Double values
        Double[] input10 = {3.14, 2.71, 1.41, -0.5};
        Double[] expected10 = {-0.5, 1.41, 2.71, 3.14};
        testAllSorters(sorters, sorterNames, input10, expected10, 
                    Comparator.naturalOrder(), "Double values");

        // Test 11: Mixed number types
        Number[] input11 = {3, 2.5, 1, 4.5};
        Number[] expected11 = {1, 2.5, 3, 4.5};
        testAllSorters(sorters, sorterNames, input11, expected11, 
                    Comparator.comparingDouble(Number::doubleValue), "Mixed number types");

        // Test 12: Case-sensitive strings
        String[] input12 = {"apple", "Banana", "cherry", "Apricot"};
        String[] expected12 = {"Apricot", "Banana", "apple", "cherry"};
        testAllSorters(sorters, sorterNames, input12, expected12, 
                    Comparator.naturalOrder(), "Case-sensitive strings");
        
        // Test 13: Large array (performance test)
        Integer[] input13 = new Integer[1000];
        Integer[] expected13 = new Integer[1000];
        for (int i = 0; i < 1000; i++) {
            input13[i] = 1000 - i;
            expected13[i] = i + 1;
        }
        testAllSorters(sorters, sorterNames, input13, expected13, 
                    Comparator.naturalOrder(), "Large array (1000 elements)");
        
        // Test 14: Nearly sorted array
        Integer[] input14 = {1, 2, 4, 3, 5, 6};
        Integer[] expected14 = {1, 2, 3, 4, 5, 6};
        testAllSorters(sorters, sorterNames, input14, expected14, 
                    Comparator.naturalOrder(), "Nearly sorted array");
        
        // Test 15: Array with duplicate elements
        Integer[] input15 = {3, 1, 4, 1, 5, 9, 2, 6, 5};
        Integer[] expected15 = {1, 1, 2, 3, 4, 5, 5, 6, 9};
        testAllSorters(sorters, sorterNames, input15, expected15, 
                    Comparator.naturalOrder(), "Array with duplicate elements");
        
        System.out.println("\nAll tests completed.");
    }
    
    /**
     * Entry point for running tests
     */
    public static void main(String[] args) {
        runAllTests();
    }
}