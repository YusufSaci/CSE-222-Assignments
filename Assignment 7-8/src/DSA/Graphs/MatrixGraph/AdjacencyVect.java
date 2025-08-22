package DSA.Graphs.MatrixGraph;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class represents an adjacency vector for a graph vertex,
 * implemented as a boolean array indicating the presence of edges
 * to other vertices. It implements the Collection interface,
 * where each integer represents a connected vertex index.
 */
public class AdjacencyVect implements Collection<Integer> {
    private boolean[] edges; // boolean array to track edges (true means edge exists)
    private int numV;        // total number of vertices in the graph
    private int size;        // current number of edges (true entries) in the vector

    /**
     * Constructs an AdjacencyVect with capacity for numV vertices.
     *
     * @param numV the number of vertices this adjacency vector can represent
     * @throws IllegalArgumentException if numV is negative
     */
    public AdjacencyVect(int numV) {
        if (numV < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        this.numV = numV;
        edges = new boolean[numV]; // initialize all edges as false (no edges)
        size = 0; // no edges initially
    }

    /**
     * Returns the number of edges (connected vertices) currently in this adjacency vector.
     * Time Complexity: O(1)
     * @return the number of edges present
     */
    @Override
    public int size() {
        return size; // return current count of true entries in edges array
    }

    /**
     * Checks whether this adjacency vector contains no edges.
     * Time Complexity: O(1)
     * @return true if no edges are present, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0; // empty if no edges present
    }

    /**
     * Checks if the adjacency vector contains an edge to the given vertex.
     * Time Complexity: O(1)
     * @param o the vertex index to check for
     * @return true if an edge to the vertex exists, false otherwise
     */
    @Override
    public boolean contains(Object o) {
        if (!(o instanceof Integer)) return false; // only Integer vertices are valid
        int index = (Integer) o;
        if (index < 0 || index >= numV) {
            return false; // invalid vertex index
        }
        return edges[index]; // return whether edge exists at that index
    }

    /**
     * Returns an iterator over the vertices connected by edges.
     * Only vertices with edges (true in the boolean array) are iterated.
     * Time Complexity: O(n), where n = total number of vertices
     *
     * @return an Iterator of Integer vertex indices
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int index = 0; // current position in edges array

            @Override
            public boolean hasNext() {
                // find the next index with an edge
                while (index < numV) {
                    if (edges[index]) {
                        return true; // next edge found
                    }
                    index++; // skip false entries
                }
                return false; // no more edges
            }

            @Override
            public Integer next() {
                if (!hasNext()) throw new NoSuchElementException();
                return index++; // return current index then increment
            }
        };
    }

    /**
     * Returns an array containing all vertices that have edges.
     * Time Complexity: O(n), where n = total number of vertices
     *
     * @return an Object array of Integer vertices connected by edges
     */
    @Override
    public Object[] toArray() {
        int counter = 0;
        // count how many edges exist
        for (int i = 0; i < numV; i++) {
            if (edges[i]) {
                counter++;
            }
        }
        Integer[] neighbors = new Integer[counter];
        int c = 0;
        // fill array with vertex indices that have edges
        for (int i = 0; i < numV; i++) {
            if (edges[i]) {
                neighbors[c++] = i;
            }
        }
        return neighbors;
    }

    /**
     * Not supported operation for this implementation.
     *
     * @param a the array into which the elements are to be stored
     * @throws UnsupportedOperationException always
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException(); // operation not supported
    }

    /**
     * Adds an edge to the specified vertex if valid and not already present.
     * Time Complexity: O(1)
     * @param vertex the vertex index to connect an edge to
     * @return true if the edge was added, false if invalid or already present
     */
    @Override
    public boolean add(Integer vertex) {
        if (vertex < 0 || vertex >= numV) {
            return false; // invalid vertex index
        }

        if (!edges[vertex]) {
            edges[vertex] = true; // add edge
            size++; // increment edge count
            return true; // added successfully
        }

        return false; // edge already exists
    }

    /**
     * Removes the edge to the specified vertex if it exists.
     * Time Complexity: O(1)
     * @param o the vertex index to remove the edge from
     * @return true if the edge was removed, false otherwise
     */
    @Override
    public boolean remove(Object o) {
        if (!(o instanceof Integer)) return false; // only Integer valid
        int index = (Integer) o;
        if (index < 0 || index >= numV) {
            return false; // invalid index
        }

        if (edges[index]) {
            edges[index] = false; // remove edge
            size--; // decrement edge count
            return true; // removed successfully
        }

        return false; // edge was not present
    }

    /**
     * Checks if all vertices in the given collection have edges in this vector.
     * Time Complexity: O(n), where n = total number of vertices
     * @param other collection of vertices to check for containment
     * @return true if all vertices have edges, false otherwise
     */
    @Override
    public boolean containsAll(Collection<?> other) {
        for (Object o : other) {
            if (!contains(o)) {
                return false; // found a vertex without edge
            }
        }
        return true; // all vertices have edges
    }

    /**
     * Adds edges to all vertices in the specified collection.
     * Time Complexity: O(n), where n = total number of vertices
     * @param c collection of vertices to add edges to
     * @return true if this adjacency vector was modified as a result
     */
    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        boolean modified = false;
        for (Integer vertex : c) {
            if (add(vertex)) { // add each vertex edge
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Removes edges to all vertices in the specified collection.
     * Time Complexity: O(n), where n = total number of vertices
     * @param other collection of vertices to remove edges from
     * @return true if this adjacency vector was modified as a result
     */
    @Override
    public boolean removeAll(Collection<?> other) {
        boolean modified = false;
        for (Object o : other) {
            if (remove(o)) { // remove each vertex edge
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Retains only the edges to vertices contained in the specified collection,
     * removing edges to vertices not in the collection.
     * Time Complexity: O(n), where n = total number of vertices
     * @param other collection of vertices to retain edges for
     * @return true if this adjacency vector was modified as a result
     */
    @Override
    public boolean retainAll(Collection<?> other) {
        boolean modified = false;
        for (int i = 0; i < numV; i++) {
            // if edge exists but vertex not in 'other', remove edge
            if (edges[i] && !other.contains(i)) {
                edges[i] = false;
                size--;
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Removes all edges from this adjacency vector.
     * Time Complexity: O(n), where n = total number of vertices
     */
    @Override
    public void clear() {
        for (int i = 0; i < numV; i++) {
            edges[i] = false; // remove all edges
        }
        size = 0; // reset edge count
    }
}
