package Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;


import DSA.Graphs.MatrixGraph.*;


/**
 * Unit test class for AdjacencyVect.
 * Tests all functionality of the AdjacencyVect class, including edge cases and exception handling.
 */
public class AdjacencyVectTest {

    private static int passedTests = 0;
    private static int totalTests = 0;

    /**
     * Main method to run all test cases
     */
    public static void main(String[] args) {
        System.out.println("Starting AdjacencyVect tests...");
        
        runTest("Constructor with negative capacity", AdjacencyVectTest::testConstructorNegative);
        runTest("Add and Contains", AdjacencyVectTest::testAddAndContains);
        runTest("Remove", AdjacencyVectTest::testRemove);
        runTest("Size and IsEmpty", AdjacencyVectTest::testSizeAndIsEmpty);
        runTest("Iterator", AdjacencyVectTest::testIterator);
        runTest("ToArray", AdjacencyVectTest::testToArray);
        runTest("Unsupported ToArray", AdjacencyVectTest::testUnsupportedToArray);
        runTest("ContainsAll", AdjacencyVectTest::testContainsAll);
        runTest("AddAll", AdjacencyVectTest::testAddAll);
        runTest("RemoveAll", AdjacencyVectTest::testRemoveAll);
        runTest("RetainAll", AdjacencyVectTest::testRetainAll);
        runTest("Clear", AdjacencyVectTest::testClear);
        
        System.out.println("\nTest Summary: " + passedTests + " of " + totalTests + " tests passed.");
        if (passedTests == totalTests) {
            System.out.println("✅ All tests passed successfully.");
        } else {
            System.out.println("❌ Some tests failed. Check the output above for details.");
        }
    }
    
    /**
     * Run a test with try-catch to prevent program termination on test failure
     */
    private static void runTest(String testName, Runnable testMethod) {
        totalTests++;
        System.out.print("- Testing " + testName + "... ");
        try {
            testMethod.run();
            System.out.println("✅ PASSED");
            passedTests++;
        } catch (AssertionError e) {
            System.out.println("❌ FAILED");
            System.out.println("  " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ FAILED - Unexpected exception");
            System.out.println("  " + e.getClass().getSimpleName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Tests constructor behavior with negative capacity
     */
    static void testConstructorNegative() {
        try {
            new AdjacencyVect(-1);
            fail("IllegalArgumentException expected for negative capacity");
        } catch (IllegalArgumentException expected) {
            // expected - test passes
        }
    }
    
    /**
     * Tests add() and contains() methods
     */
    static void testAddAndContains() {
        AdjacencyVect vect = new AdjacencyVect(5);
        assertTrue(vect.add(1), "Add 1 should return true");
        assertTrue(vect.contains(1), "Should contain 1");
        assertFalse(vect.add(1), "Duplicate add should return false");
        assertFalse(vect.add(-1), "Invalid index add should return false");
        assertFalse(vect.add(5), "Out-of-bounds index should return false");
        assertFalse(vect.contains("string"), "Non-integer should return false");
        assertFalse(vect.contains(-1), "Negative index should return false");
        assertFalse(vect.contains(10), "Out-of-bounds index should return false");
    }
    
    /**
     * Tests the remove() method
     */
    static void testRemove() {
        AdjacencyVect vect = new AdjacencyVect(5);
        vect.add(3);
        assertTrue(vect.remove(3), "Should remove 3");
        assertFalse(vect.contains(3), "Should not contain 3 anymore");
        assertFalse(vect.remove(3), "Double remove should return false");
        assertFalse(vect.remove("not int"), "Invalid object remove should return false");
        assertFalse(vect.remove(-1), "Negative index remove should return false");
        assertFalse(vect.remove(5), "Out-of-bounds index remove should return false");
    }
    
    /**
     * Tests size() and isEmpty() methods
     */
    static void testSizeAndIsEmpty() {
        AdjacencyVect vect = new AdjacencyVect(5);
        assertEquals(0, vect.size(), "Initial size must be 0");
        assertTrue(vect.isEmpty(), "Initial isEmpty must be true");

        vect.add(0);
        vect.add(2);
        assertEquals(2, vect.size(), "Size should be 2");
        assertFalse(vect.isEmpty(), "Should not be empty");
        
        vect.remove(2);
        assertEquals(1, vect.size(), "Size should be 1 after removal");
        
        vect.remove(0);
        assertEquals(0, vect.size(), "Size should be 0 after removing all elements");
        assertTrue(vect.isEmpty(), "Should be empty after removing all elements");
    }
    
    /**
     * Tests the iterator() method
     */
    static void testIterator() {
        AdjacencyVect vect = new AdjacencyVect(5);
        
        // Test empty iterator
        Iterator<Integer> emptyIt = vect.iterator();
        assertFalse(emptyIt.hasNext(), "Empty iterator should not have next");
        try {
            emptyIt.next();
            fail("Expected NoSuchElementException not thrown for empty iterator");
        } catch (NoSuchElementException expected) {
            // expected - test passes
        }
        
        // Test non-empty iterator
        vect.add(0);
        vect.add(3);
        vect.add(4);

        Iterator<Integer> it = vect.iterator();
        List<Integer> result = new ArrayList<>();
        while (it.hasNext()) {
            result.add(it.next());
        }
        
        assertTrue(result.contains(0), "Iterator should return element 0");
        assertTrue(result.contains(3), "Iterator should return element 3");
        assertTrue(result.contains(4), "Iterator should return element 4");
        assertEquals(3, result.size(), "Iterator should return 3 elements");

        try {
            it.next(); // should throw when iterator is exhausted
            fail("Expected NoSuchElementException not thrown");
        } catch (NoSuchElementException expected) {
            // expected - test passes
        }
    }
    
    /**
     * Tests the toArray() method
     */
    static void testToArray() {
        AdjacencyVect vect = new AdjacencyVect(4);
        
        // Test empty toArray
        Object[] emptyArray = vect.toArray();
        assertEquals(0, emptyArray.length, "Empty toArray should return empty array");
        
        // Test non-empty toArray
        vect.add(1);
        vect.add(2);
        Object[] array = vect.toArray();
        assertEquals(2, array.length, "toArray should return array of size 2");
        
        List<Object> arrayAsList = Arrays.asList(array);
        assertTrue(arrayAsList.contains(1), "toArray result should contain 1");
        assertTrue(arrayAsList.contains(2), "toArray result should contain 2");
    }
    
    /**
     * Tests the unsupported toArray(Object[]) method
     */
    static void testUnsupportedToArray() {
        AdjacencyVect vect = new AdjacencyVect(3);
        try {
            vect.toArray(new Object[0]);
            fail("UnsupportedOperationException expected for toArray(Object[])");
        } catch (UnsupportedOperationException expected) {
            // expected - test passes
        }
    }
    
    /**
     * Tests the containsAll() method
     */
    static void testContainsAll() {
        AdjacencyVect vect = new AdjacencyVect(5);
        vect.addAll(Arrays.asList(1, 2, 3));
        
        assertTrue(vect.containsAll(Arrays.asList(1, 2)), "Should contain all elements in subset");
        assertTrue(vect.containsAll(new ArrayList<>()), "Should contain all elements in empty collection");
        assertFalse(vect.containsAll(Arrays.asList(1, 4)), "Should not contain all when one element is missing");
        assertFalse(vect.containsAll(Arrays.asList(5, 6)), "Should not contain elements outside range");
        
        List<Object> mixedTypes = new ArrayList<>();
        mixedTypes.add(1);
        mixedTypes.add("string");
        assertFalse(vect.containsAll(mixedTypes), "Should not contain non-Integer objects");
    }
    
    /**
     * Tests the addAll() method
     */
    static void testAddAll() {
        AdjacencyVect vect = new AdjacencyVect(6);
        
        // Add collection of elements
        List<Integer> toAdd = Arrays.asList(1, 2, 4);
        assertTrue(vect.addAll(toAdd), "addAll should return true when adding new elements");
        assertEquals(3, vect.size(), "Size should be 3 after adding three elements");
        assertTrue(vect.contains(1) && vect.contains(2) && vect.contains(4), "Should contain all added elements");
        
        // Add duplicates
        assertFalse(vect.addAll(toAdd), "Duplicate addAll should return false");
        assertEquals(3, vect.size(), "Size should remain 3 after adding duplicates");
        
        // Add empty collection
        assertFalse(vect.addAll(new ArrayList<>()), "Adding empty collection should return false");
        
        // Add mixed valid and invalid
        List<Integer> mixedValid = Arrays.asList(3, -1, 7);
        assertTrue(vect.addAll(mixedValid), "Should return true when at least one element is added");
        assertTrue(vect.contains(3), "Valid element 3 should be added");
        assertFalse(vect.contains(-1), "Invalid element -1 should not be added");
        assertFalse(vect.contains(7), "Out of bounds element 7 should not be added");
    }
    
    /**
     * Tests the removeAll() method
     */
    static void testRemoveAll() {
        AdjacencyVect vect = new AdjacencyVect(6);
        vect.addAll(Arrays.asList(1, 2, 3, 4));
        
        // Remove subset of elements
        assertTrue(vect.removeAll(Arrays.asList(1, 3)), "removeAll should return true when elements are removed");
        assertEquals(2, vect.size(), "Size should be 2 after removing two elements");
        assertFalse(vect.contains(1), "Element 1 should be removed");
        assertFalse(vect.contains(3), "Element 3 should be removed");
        assertTrue(vect.contains(2), "Element 2 should remain");
        
        // Remove non-existent elements
        assertFalse(vect.removeAll(Arrays.asList(5, 6)), "Non-existent removeAll should return false");
        assertEquals(2, vect.size(), "Size should remain unchanged");
        
        // Remove with mixed existing and non-existing
        assertTrue(vect.removeAll(Arrays.asList(2, 5)), "Should return true when at least one element is removed");
        assertEquals(1, vect.size(), "Size should be 1 after removing one more element");
        
        // Remove empty collection
        assertFalse(vect.removeAll(new ArrayList<>()), "Removing empty collection should return false");
        
        // Remove with non-Integer objects
        List<Object> mixedTypes = new ArrayList<>();
        mixedTypes.add(4);
        mixedTypes.add("string");
        assertTrue(vect.removeAll(mixedTypes), "Should remove integers and ignore non-integers");
        assertEquals(0, vect.size(), "Size should be 0 after removing last element");
    }
    
    /**
     * Tests the retainAll() method
     */
    static void testRetainAll() {
        AdjacencyVect vect = new AdjacencyVect(5);
        vect.addAll(Arrays.asList(0, 1, 2, 3));
        
        // Retain subset
        assertTrue(vect.retainAll(Arrays.asList(1, 2, 4)), "retainAll should return true when elements changed");
        assertEquals(2, vect.size(), "Size should be 2 after retaining");
        assertFalse(vect.contains(0), "Element 0 should be removed");
        assertFalse(vect.contains(3), "Element 3 should be removed");
        assertTrue(vect.contains(1), "Element 1 should be retained");
        assertTrue(vect.contains(2), "Element 2 should be retained");
        
        // Retain same elements (no change)
        assertFalse(vect.retainAll(Arrays.asList(1, 2)), "retainAll should return false when no change");
        
        // Retain empty collection (clear all)
        assertTrue(vect.retainAll(new ArrayList<>()), "retainAll with empty collection should return true");
        assertEquals(0, vect.size(), "Size should be 0 after retaining empty collection");
        
        // Test after clearing
        vect.add(1);
        vect.add(2);
        
        // Retain with non-Integer objects
        List<Object> mixedTypes = new ArrayList<>();
        mixedTypes.add(1);
        mixedTypes.add("string");
        assertTrue(vect.retainAll(mixedTypes), "Should retain integers and ignore non-integers");
        assertEquals(1, vect.size(), "Size should be 1 after retaining");
        assertTrue(vect.contains(1), "Element 1 should be retained");
    }
    
    /**
     * Tests the clear() method
     */
    static void testClear() {
        AdjacencyVect vect = new AdjacencyVect(3);
        vect.addAll(Arrays.asList(0, 1));
        
        vect.clear();
        assertEquals(0, vect.size(), "Size should be 0 after clear");
        assertTrue(vect.isEmpty(), "isEmpty should be true after clear");
        assertFalse(vect.contains(0), "Element 0 should not be contained after clear");
        assertFalse(vect.contains(1), "Element 1 should not be contained after clear");
        
        // Test clear on empty vector
        vect.clear();
        assertEquals(0, vect.size(), "Size should still be 0 after clearing empty vector");
        
        // Test add after clear
        vect.add(2);
        assertEquals(1, vect.size(), "Size should be 1 after adding element post-clear");
        assertTrue(vect.contains(2), "Element 2 should be contained after add post-clear");
    }

    // ========== Utility Assertion Methods ==========

    private static void assertTrue(boolean condition, String message) {
        if (!condition) throw new AssertionError("❌ " + message);
    }

    private static void assertFalse(boolean condition, String message) {
        if (condition) throw new AssertionError("❌ " + message);
    }

    private static void assertEquals(Object expected, Object actual, String message) {
        if (!Objects.equals(expected, actual)) {
            throw new AssertionError("❌ " + message + " — expected: " + expected + ", actual: " + actual);
        }
    }

    private static void fail(String message) {
        throw new AssertionError("❌ " + message);
    }
}