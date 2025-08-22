package Main;

import java.util.Collection;

import DSA.Graphs.MatrixGraph.*;

public class MatrixGraphUnitTest {
    private static int totalTests = 0;
    private static int passedTests = 0;
    private static int errorTests = 0;

    public static void main(String[] args) {
        System.out.println("Testing MatrixGraph implemented functions...\n");
        
        try {
            boolean setEdgeResult = testSetEdgeFunction();
            boolean getEdgeResult = testGetEdgeFunction();
            boolean getNeighborsResult = testGetNeighborsFunction();
            boolean sizeResult = testSizeFunction();
            boolean resetResult = testResetFunction();
            
            System.out.println("\n=== Final Results ===");
            System.out.println("setEdge(): " + (setEdgeResult ? "✅ PASSED ALL TESTS" : "❌ FAILED SOME TESTS"));
            System.out.println("getEdge(): " + (getEdgeResult ? "✅ PASSED ALL TESTS" : "❌ FAILED SOME TESTS"));
            System.out.println("getNeighbors(): " + (getNeighborsResult ? "✅ PASSED ALL TESTS" : "❌ FAILED SOME TESTS"));
            System.out.println("size(): " + (sizeResult ? "✅ PASSED ALL TESTS" : "❌ FAILED SOME TESTS"));
            System.out.println("reset(): " + (resetResult ? "✅ PASSED ALL TESTS" : "❌ FAILED SOME TESTS"));
            
            System.out.println("\nTotal tests run: " + totalTests);
            System.out.println("Tests passed: " + passedTests);
            System.out.println("Tests failed: " + (totalTests - passedTests - errorTests));
            System.out.println("Tests with errors: " + errorTests);
            
        } catch (Exception e) {
            System.err.println("\n❌❌❌ CRITICAL ERROR in test suite: " + e.getMessage());
            e.printStackTrace();
            System.err.println("\nTests run before error: " + totalTests);
            System.err.println("Tests passed: " + passedTests);
            System.err.println("Tests failed: " + (totalTests - passedTests - errorTests));
            System.err.println("Tests with errors: " + errorTests);
        }
    }
    
    private static boolean testSetEdgeFunction() {
        MatrixGraph graph = new MatrixGraph();
        graph.reset(5);
        int functionTests = 0;
        int functionPassed = 0;
        
        // Test 1: Add valid edge
        boolean result1 = graph.setEdge(0, 1);
        if (result1) functionPassed++;
        functionTests++;
        
        // Test 2: Add same edge again (should be idempotent)
        boolean result2 = graph.setEdge(0, 1);
        if (result2) functionPassed++;
        functionTests++;
        
        // Test 3: Add edge with invalid vertices
        boolean result3 = graph.setEdge(-1, 2);
        if (!result3) functionPassed++;
        functionTests++;
        
        // Test 4: Add edge beyond size
        boolean result4 = graph.setEdge(1, 5);
        if (!result4) functionPassed++;
        functionTests++;
        
        // Test 5: Verify edge was actually added
        boolean edgeExists = graph.getEdge(0,1);
        if (edgeExists) functionPassed++;
        functionTests++;
        
        totalTests += functionTests;
        passedTests += functionPassed;
        
        return functionPassed == functionTests;
    }
    
    private static boolean testGetEdgeFunction() {
        MatrixGraph graph = new MatrixGraph();
        graph.reset(4);
        graph.setEdge(0, 1);
        graph.setEdge(1, 2);
        graph.setEdge(2, 3);
        int functionTests = 0;
        int functionPassed = 0;
        
        // Test 1: Check existing edge
        boolean result1 = graph.getEdge(0, 1);
        if (result1) functionPassed++;
        functionTests++;
        
        // Test 2: Check reverse direction (undirected graph)
        boolean result2 = graph.getEdge(1, 0);
        if (result2) functionPassed++;
        functionTests++;
        
        // Test 3: Check non-existent edge
        boolean result3 = graph.getEdge(0, 3);
        if (!result3) functionPassed++;
        functionTests++;
        
        // Test 4: Check with invalid vertices
        boolean result4 = graph.getEdge(-1, 2);
        if (!result4) functionPassed++;
        functionTests++;
        
        totalTests += functionTests;
        passedTests += functionPassed;
        
        return functionPassed == functionTests;
    }
    
    private static boolean testGetNeighborsFunction() {
        MatrixGraph graph = new MatrixGraph();
        graph.reset(5);
        graph.setEdge(0, 1);
        graph.setEdge(0, 2);
        graph.setEdge(0, 3);
        graph.setEdge(1, 4);
        int functionTests = 0;
        int functionPassed = 0;
        
        // Test 1: Get neighbors of vertex with multiple edges
        Collection<Integer> neighbors0 = graph.getNeighbors(0);
        boolean test1 = neighbors0 != null && neighbors0.size() == 3 && 
                       neighbors0.contains(1) && neighbors0.contains(2) && neighbors0.contains(3);
        if (test1) functionPassed++;
        functionTests++;
        
        // Test 2: Get neighbors of vertex with one edge
        Collection<Integer> neighbors4 = graph.getNeighbors(4);
        boolean test2 = neighbors4 != null && neighbors4.size() == 1 && neighbors4.contains(1);
        if (test2) functionPassed++;
        functionTests++;
        
        // Test 3: Get neighbors of isolated vertex
        Collection<Integer> neighbors3 = graph.getNeighbors(3);
        boolean test3 = neighbors3 != null && neighbors3.size() == 1 && neighbors3.contains(0);
        if (test3) functionPassed++;
        functionTests++;
        
        // Test 4: Get neighbors with invalid vertex
        Collection<Integer> invalid = graph.getNeighbors(5);
        boolean test4 = invalid == null;
        if (test4) functionPassed++;
        functionTests++;
        
        totalTests += functionTests;
        passedTests += functionPassed;
        
        return functionPassed == functionTests;
    }
    
    private static boolean testSizeFunction() {
        MatrixGraph graph = new MatrixGraph();
        int functionTests = 0;
        int functionPassed = 0;
        
        // Test 1: Initial size after reset(0)
        graph.reset(0);
        boolean test1 = graph.size() == 0;
        if (test1) functionPassed++;
        functionTests++;
        
        // Test 2: Size after reset(5)
        graph.reset(5);
        boolean test2 = graph.size() == 5;
        if (test2) functionPassed++;
        functionTests++;
        
        // Test 3: Size remains constant after adding edges
        graph.setEdge(0, 1);
        graph.setEdge(1, 2);
        boolean test3 = graph.size() == 5;
        if (test3) functionPassed++;
        functionTests++;
        
        totalTests += functionTests;
        passedTests += functionPassed;
        
        return functionPassed == functionTests;
    }
    
    private static boolean testResetFunction() {
        MatrixGraph graph = new MatrixGraph();
        int functionTests = 0;
        int functionPassed = 0;
        
        // Test 1: Reset to size 3
        graph.reset(3);
        boolean test1 = graph.size() == 3 && 
                       graph.getNeighbors(0) != null && 
                       graph.getNeighbors(1) != null && 
                       graph.getNeighbors(2) != null;
        if (test1) functionPassed++;
        functionTests++;
        
        // Test 2: Verify no edges exist after reset
        boolean test2 = !graph.getEdge(0, 1) && !graph.getEdge(1, 2);
        if (test2) functionPassed++;
        functionTests++;
        
        // Test 3: Reset after adding edges
        graph.setEdge(0, 1);
        graph.setEdge(1, 2);
        graph.reset(2);
        boolean test3 = graph.size() == 2 && !graph.getEdge(0, 1);
        if (test3) functionPassed++;
        functionTests++;
        
        totalTests += functionTests;
        passedTests += functionPassed;
        
        return functionPassed == functionTests;
    }
}