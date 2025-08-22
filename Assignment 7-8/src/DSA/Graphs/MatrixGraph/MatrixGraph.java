package DSA.Graphs.MatrixGraph;

import java.util.Collection;
import DSA.Graphs.*;

/**
 * MatrixGraph implements the GTUGraph interface using an adjacency matrix
 * representation where each vertex's edges are stored in an AdjacencyVect.
 * This graph is undirected, so edges are symmetric.
 */
public class MatrixGraph implements GTUGraph {
    private AdjacencyVect[] edges; // Array of adjacency vectors, one per vertex
    private int numV;              // Number of vertices in the graph

    /**
     * Adds an undirected edge between vertices v1 and v2.
     * Time Complexity: O(1)
     * @param v1 first vertex index
     * @param v2 second vertex index
     * @return true if edge is successfully added, false if vertices are invalid
     */
    @Override
    public Boolean setEdge(int v1, int v2) {
        if (v1 < 0 || v2 < 0 || v1 >= numV || v2 >= numV) return false; // invalid vertices
        edges[v1].add(v2); // add edge from v1 to v2
        edges[v2].add(v1); // add edge from v2 to v1 (undirected)
        return true;
    }

    /**
     * Checks if there is an edge between vertices v1 and v2.
     * Time Complexity: O(1)
     *
     * @param v1 first vertex index
     * @param v2 second vertex index
     * @return true if edge exists, false if not or vertices invalid
     */
    @Override
    public Boolean getEdge(int v1, int v2) {
        if (v1 < 0 || v2 < 0 || v1 >= numV || v2 >= numV) return false; // invalid vertices
        return edges[v1].contains(v2); // check adjacency vector for v1
    }

    /**
     * Returns a collection of all neighbors (connected vertices) of vertex v.
     * Time Complexity: O(1)
     * @param v vertex index
     * @return collection of neighbors if valid vertex, otherwise null
     */
    @Override
    public Collection<Integer> getNeighbors(int v) {
        if (v < 0 || v >= numV) return null; // invalid vertex
        return edges[v]; // adjacency vector for vertex v
    }

    /**
     * Returns the number of vertices in the graph.
     * Time Complexity: O(1)
     * @return the size of the graph (number of vertices)
     */
    @Override
    public int size() {
        return numV;
    }

    /**
     * Resets the graph to have the specified number of vertices,
     * initializing an adjacency vector for each vertex.
     * Time Complexity: O(n^2), where n = total number of vertices
     * @param size the new number of vertices
     */
    @Override
    public void reset(int size) {
        this.numV = size;
        edges = new AdjacencyVect[numV]; // create array for adjacency vectors
        for (int i = 0; i < numV; i++) {
            edges[i] = new AdjacencyVect(numV); // initialize each adjacency vector
        }
    }
}
