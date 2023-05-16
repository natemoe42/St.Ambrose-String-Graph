/**
 * A graph whose vertices are strings.
 *
 * @author Dr. Lillis
 */
public interface Graph {

    /**
     * The default capacity used when the no-argument constructor is called.
     */
    static final int DEFAULT_CAPACITY = 1;
    
    /**
     * Returns the number of vertices in this graph (the "order" of this graph).
     *
     * @return the number of vertices in this graph
     */
    int numberOfVertices();

    /**
     * Returns the number of edges in this graph (the "size" of this graph).
     *
     * @return the number of edges in this graph
     */
    int numberOfEdges();

    /**
     * Creates and returns a new array of Strings containing the vertices of
     * this graph. The length of the returned array equals the number of
     * vertices. The order of the vertices is unspecified.
     *
     * @return a String array containing the vertex labels
     */
    String[] getVertices();

    /**
     * Creates and returns a new 2D array of strings containing the edges of
     * this graph. The size of the returned array is m x 2. In other words,
     * there is one row for each edge. The order of the edges is unspecified 
     * and the order of the vertices in each edge is also unspecified.
     * For example, a graph with the three edges
     * {"A", "B"}, {"C", "A"}, and {"F", "D"} may return:
     *
     * {
     * {"A", "B"}, {"C", "A"}, {"F", "D"} }
     *
     * @return an m x 2 array in which each row represents and edge in this
     * graph.
     */
    String[][] getEdges();

    /**
     * Returns the number of vertices that can be added to this graph before
     * the array of vertices needs to be increased.
     * 
     * @return the number of vertices that can be added to this graph before the
     * array of vertices needs to be increased.
     */
    int capacity();

    /**
     * Resizes the array of labels and the adjacency matrix to a new capacity.
     * If the new capacity is larger than the current capacity, the capacity is
     * increased. Note: In future assignment the capacity may also be made
     * smaller.
     * newCapacity.
     *
     * @param newCapacity The new capacity
     */
    void resize(int newCapacity);

    /**
     * Determines if a vertex with the given label exists.
     *
     * @param vertex the label of a vertex.
     * @return true of the vertex exist, false if it does not exist.
     */
    boolean vertexExists(String vertex);

    /**
     * Adds a new vertex to this graph.
     *
     * @param vertex the new vertex to be added
     * @throws GraphException if the specified vertex already exists.
     */
    void addVertex(String vertex) throws GraphException;

    /**
     * Adds one or more vertices to this graph.
     *
     * @param vertices the vertices to be added
     */
    void addVertices(String[] vertices);

    /**
     * Determines if an edge with the given end vertices exists.
     *
     * @param vertex1 the label of one end of the edge
     * @param vertex2 the label of the other end of the edge
     * @return true edge {vertex1, vertex2} is in this graph, returns false if
     * the edge is not in this graph
     * @throws GraphException if either vertex1 or vertex2 is not in this graph.
     */
    boolean edgeExists(String vertex1, String vertex2) throws GraphException;

    /**
     * Adds a new edge.
     *
     * @param vertex1 the label of one end of the new edge
     * @param vertex2 the label of the other end of the new edge
     * @throws GraphException if the edge already exists or if either end vertex 
     * does not exist.
     */
    void addEdge(String vertex1, String vertex2) throws GraphException;

    /**
     * Adds multiple edges. Each row i in the edges parameter represents the
     * pair of vertices edges[i][0] and edges[i][1] for a new edge.
     *
     * @param edges a 2 x w array of vertices, w &ge; 0
     */
    void addEdges(String[][] edges);

    /**
     * Returns a string representation of this Graph.
     *
     * @return a string representation of this Graph.
     */
    @Override
    String toString();
    
    // =========================================================================
    // The following methods are added in assignment part 2
    /**
     * Deletes a vertex and all of its incident edges.
     *
     * @param vertex the vertex to be deleted
     * @throws GraphException if vertex does not exist.
     */
    void deleteVertex(String vertex) throws GraphException;

    /**
     * Deletes an edge.
     *
     * @param vertex1 one end vertex of the edge
     * @param vertex2 the other end vertex of the edge
     * @throws GraphException if the edge does not exist or if either end vertex
     *                        does not exist.
     */
    void deleteEdge(String vertex1, String vertex2) throws GraphException;

    /**
     * Returns the degree of a vertex
     *
     * @param vertex the vertex of interest
     * @return the degree of the given vertex
     * @throws GraphException if the vertex does not exist.
     */
    int degree(String vertex) throws GraphException;

    /**
     * Returns the maximum degree of the graph
     *
     * @return the maximum degree
     */
    int maxDegree();

    /**
     * Returns the minimum degree of the graph.
     *
     * @return the minimum degree
     */
    int minDegree();

    /**
     * Returns the average degree of the graph
     *
     * @return the average degree
     */
    double averageDegree();

    /**
     * Return the degree sequence for this graph. The degree sequence is a
     * sequence consisting of the degree of each vertex, sorted from largest to
     * smallest.
     *
     * @return an int array containing the degrees of all vertices, sorted from
     *         largest degree to smallest degree.
     */
    int[] degreeSequence();

    // =========================================================================
    // The following methods are added in assignment part 3
    
    /**
     * Returns the labels of all neighbors of a vertex. The order of the
     * neighbors is unspecified.
     *
     * @param vertex the vertex of interest
     * @return a String array holding the labels the neighbors.
     * @throws GraphException if the vertex does not exist.
     */
    String[] getNeighbors(String vertex) throws GraphException;

    /**
     * Performs a Breadth First Search traversal starting at a given vertex. The
     * label of each vertex visited is added to an array of Strings in the order
     * visited.
     *
     * @param vertex the vertex at which the BFS traversal is started
     * @return an array of Strings containing the labels of the vertices visited
     *         in the order that they were visited.
     * @throws GraphException if the given vertex is not in this graph.
     */
    String[] bfsOrder(String vertex) throws GraphException;

    /**
     * Performs a Breadth First Search traversal starting at a given vertex.
     * Constructs and returns a BFS spanning tree of the connected component
     * containing the specified vertex. Adjacent vertices are visited in the
     * lexicographical order of their labels.
     *
     * @param vertex The vertex at which the BFS traversal is started
     * @return A BFS spanning tree of the connected component containing the
     *         given vertex.
     * @throws GraphException if the given vertex is not in this graph.
     */
    Graph bfsTree(String vertex) throws GraphException;

    /**
     * Performs a Depth First Search traversal starting at a given vertex. The
     * label of each vertex visited is added to an array of Strings in the order
     * visited.
     *
     * @param vertex the vertex at which the DFS traversal is started
     * @return an array of Strings containing the labels of the vertices visited
     *         in the order that they were visited.
     * @throws GraphException if the given vertex is not in this graph.
     */
    String[] dfsOrder(String vertex) throws GraphException;

    /**
     * Performs a Depth First Search traversal starting at a given vertex.
     * Constructs and returns a DFS spanning tree of the connected component
     * containing the specified vertex. Adjacent vertices are visited in the
     * lexicographical order of their labels.
     *
     * @param vertex the vertex at which the DFS traversal is started
     * @return a DFS spanning tree of the connected component containing
     * @throws GraphException if the given vertex is not in this graph.
     */
    Graph dfsTree(String vertex) throws GraphException;

    /**
     * Determines if the graph is connected.
     *
     * @return true if the graph is connected, false otherwise.
     */
    boolean isConnected();
    
    // =========================================================================
    // The following methods are added in assignment part 4

    /**
     * Perform BFS Single Source Shortest Path from specified vertex.
     *
     * @param vertex Source for shortest paths
     * @return String array containing the "Previous" vertices for the shortest
     *         path.
     * @see Graph.shortestPath(String s, String t)
     * @throws GraphException if the specified vertex does not exist.
     */
    String[] bfsSSSP(String vertex) throws GraphException;

    /**
     * Returns the shortest path in this graph from vertex s to vertex t.
     *
     * @param s the source vertex
     * @param t the destination vertex
     * @return a list of strings containing the labels of the vertices along the
     *         shortest path from s to t, starting with s and ending with t.
     * @throws GraphException of either specified vertex does not exist.
     */
    String[] shortestPath(String s, String t) throws GraphException;


}