/*
 * Nathan Moeller
 * CSCI-310-A Data Structures
 * Homework 07 Graph Part III
 */
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 *
 * @author natemoe
 */
public class StringGraph implements Graph{
     
    
    protected int capacity;
    protected boolean[][] edgeMatrix;
    protected String[] labels;
    protected int numEdges;
    protected int numVertices;
    static final int DEFAULT_CAPACITY = Graph.DEFAULT_CAPACITY;
    
    public StringGraph(){
        this(Graph.DEFAULT_CAPACITY);
    }
    
    public StringGraph(int intCapacity){
        if (intCapacity<1){
            throw new GraphException();
        }
        capacity = intCapacity;
        labels = new String[capacity];
        edgeMatrix =new boolean[capacity][capacity];
        numVertices = 0;
        numEdges = 0;
        for(int i = 0; i < capacity; i++)
        {
            for(int j = 0; j < capacity; j++)
            {
                edgeMatrix[i][j] = false;
            }
        }
    }
    
    public StringGraph(String[] initVertices){
       labels = initVertices;
       numVertices = labels.length;
       numEdges = 0;
       capacity = labels.length;
       edgeMatrix =new boolean[capacity][capacity];
       for(int i = 0; i < capacity; i++)
        {
            for(int j = 0; j < capacity; j++)
            {
                edgeMatrix[i][j] = false;
            }
        }
    }
    public StringGraph(String[] initVertices, String[][] initEdges){
       labels = initVertices;
       numVertices = labels.length;
       capacity = labels.length;
       numEdges = initEdges.length;
       edgeMatrix =new boolean[capacity][capacity];
       for(int i = 0; i < capacity; i++)
        {
            for(int j = 0; j < capacity; j++)
            {
                edgeMatrix[i][j] = false;
            }
        }
       
       for (int i=0;i<initEdges.length;i++){
               int index1 = getIndex(initEdges[i][0]);
               int index2 = getIndex(initEdges[i][1]);
               edgeMatrix[index1][index2] = true;
               edgeMatrix[index2][index1] = true;
           }
       }
    
    
    private int getIndex(String vertex){
        for(int i=0;i<numVertices;i++){
            if(labels[i].equals(vertex))
                return i;
        }
        return -1;
    }
    
    @Override
    public int numberOfVertices() {
            return numVertices;
    }

    @Override
    public int numberOfEdges() {
            return numEdges;
    }

    @Override
    public String[] getVertices() {
        String VertexList[] = new String[numVertices];
        for(int i = 0; i < numVertices; i++)
        {
            VertexList[i] = labels[i]; 
        }
        return VertexList;
    }

    @Override
    public String[][] getEdges() {
        String EdgeList[][] = new String[numEdges][2];

        int edgeCount = 0;
        
        for (int i = 0; i < numVertices; i++)
        {
            for (int j = 0; j < i; j++)
            {
                if (edgeMatrix[i][j])
                {
                    EdgeList[edgeCount][0] = labels[i];
                    EdgeList[edgeCount][1] = labels[j];
                    edgeCount++;
                }
            }
        }
        return EdgeList;
        
    }

    @Override
    public int capacity() {
        return capacity;   
    }

    @Override
    public void resize(int newCapacity) {
         if(newCapacity*2 > capacity)
        {
            increaseCapacity(capacity);
            
            String[] newLabels = new String[capacity];
            System.arraycopy(labels, 0, newLabels, 0, labels.length);

            // TODO: increase the matrix size also
            boolean[][] newMatrix = new boolean[capacity][capacity];
            for (int i = 0; i < numVertices; i++)
            {
                System.arraycopy(edgeMatrix[i], 0, newMatrix[i], 0, numVertices);
            }

            labels = newLabels;
            edgeMatrix = newMatrix;
        }
    }
private void increaseCapacity(int biggerCapacity)
    {
        capacity = biggerCapacity * 2;
    }
    
    @Override
    public boolean vertexExists(String vertex) {
        return getIndex(vertex)>=0;
    }
    
    @Override
    public void addVertex(String vertex) throws GraphException {
        if(vertexExists(vertex))
        {
            throw new GraphException(); 
        }
        if(numVertices == capacity)
        {
            resize(2*capacity);
        }
        
        labels[numVertices] = vertex;
        numVertices++;
    }

    @Override
    public void addVertices(String[] vertices) {
        for(int i = 0; i < vertices.length; i++)
        {
            addVertex(vertices[i]);
        }
    }

    @Override
    public boolean edgeExists(String vertex1, String vertex2) throws GraphException {
        if(!vertexExists(vertex1))
        {
            throw new GraphException();
        }
        
        if(!vertexExists(vertex2))
        {
            throw new GraphException();
        }
        
        int indexv1 = getIndex(vertex1);
        int indexv2 = getIndex(vertex2);
        
        
        return edgeMatrix[indexv1][indexv2];    
    }

    @Override
    public void addEdge(String vertex1, String vertex2) throws GraphException {
        if(!vertexExists(vertex1))
        {
            throw new GraphException();
        }
        if(!vertexExists(vertex2))
        {
            throw new GraphException();
        }
        if(edgeExists(vertex1, vertex2))
        {
            throw new GraphException("");
        }
        
        int v1index = getIndex(vertex1);
        int v2index = getIndex(vertex2);
        
        
        edgeMatrix[v1index][v2index] = true;
        edgeMatrix[v2index][v1index] = true;
        numEdges++;        
    }

    @Override
    public void addEdges(String[][] edges) {
         for(int r = 0; r < edges.length; r++)
        {
            addEdge(edges[r][0], edges[r][1]);
        }    
    }
    
       
    @Override
    public String toString()
    {
        String[] vertices = this.getVertices();
        Arrays.sort(vertices);

        String[][] edges = this.getEdges();
        for (String[] edge : edges) {
            Arrays.sort(edge);
        }

        // Make a comparator using a lambda expression
        java.util.Comparator<String[]> comp = (String[] a, String[] b) -> {
            if (!a[0].equals(b[0])) {
                return a[0].compareTo(b[0]);
            } else {
                return a[1].compareTo(b[1]);
            }
        };

        Arrays.sort(edges, comp);

        String s = "Vertices: " + Arrays.toString(vertices);
        s += "\nEdges:";
        for (String[] edge : edges) {
            s += "\n" + Arrays.toString(edge);
        }

        return s;
    }

    @Override
    public void deleteVertex(String vertex) throws GraphException {
        if(!vertexExists(vertex))
        {
            throw new GraphException();
        }
        int delIndex = getIndex(vertex);
        int lastIndex = numVertices - 1;
        labels[delIndex] = labels[lastIndex];
        labels[lastIndex] = null;
        for(int i =0;i<=lastIndex;i++){
            if(edgeMatrix[delIndex][i]){
                numEdges--;
            }
            if(i!=delIndex&&i!=lastIndex){
                edgeMatrix[i][delIndex]=edgeMatrix[i][lastIndex];
                edgeMatrix[delIndex][i]=edgeMatrix[lastIndex][i];
            }
        
        }
        for(int v = 0;v<numVertices;v++){
            edgeMatrix[lastIndex][v] = false;
            edgeMatrix[v][lastIndex] = false;
        }
        numVertices--;
    }

    @Override
    public void deleteEdge(String vertex1, String vertex2) throws GraphException {
        if(!vertexExists(vertex1))
        {
            throw new GraphException();
        }
        if(!vertexExists(vertex2))
        {
            throw new GraphException();
        }
        if(!edgeExists(vertex1, vertex2))
        {
            throw new GraphException("");
        }
        int index1 = getIndex(vertex1);
        int index2 = getIndex(vertex2);
        edgeMatrix[index1][index2] = false;
        edgeMatrix[index2][index1] = false;
        numEdges--;
    }

    private int degree(int index){
        int degree = 0;
        for(int i = 0;i<numVertices;i++){
            degree+= edgeMatrix[index][i]?1:0;
        }
        return degree;
    }
    
    @Override
    public int degree(String vertex) throws GraphException {
        int index = getIndex(vertex);
        if(index < 0){
            throw new GraphException();
        }
        else{
            return degree(index);
        }
    }

    @Override
    public int maxDegree() {
        int[] deqSeq = degreeSequence();
        int maxDegree = deqSeq[0];
        return maxDegree;
    }

    @Override
    public int minDegree() {
        int[] deqSeq = degreeSequence();
        int minDegree = deqSeq[deqSeq.length-1];
        return minDegree;
    }

    @Override
    public double averageDegree() {
        int[] deqSeq = degreeSequence();
        int sum = 0;
        for(int i = 0;i<deqSeq.length;i++){
            sum+=deqSeq[i];
        }
        double avgDegree = sum/deqSeq.length;
        return avgDegree;
    }

    @Override
    public int[] degreeSequence() {
        int[] degrees = new int[numVertices];
        for(int v=0;v<numVertices;v++){
            degrees[v]=0;
        
        for(int i =0;i<numVertices;i++){
            degrees[v]+=edgeMatrix[v][i]?1:0;
        }
        }
        Arrays.sort(degrees);
        int[] newList = new int[numVertices];
        int listIndex = 0;
        for(int i = degrees.length-1; i>=0;i--){
            newList[listIndex]=degrees[i];
            listIndex++;
        }
        return newList;
    }


    @Override
    public String[] getNeighbors(String vertex) throws GraphException {
    int index = getIndex(vertex);
    if(!vertexExists(vertex))
        {
            throw new GraphException();
        }
    int numNeighbors = 0;
    for (int i = 0; i < capacity; i++) {
        if (edgeMatrix[index][i]) {
            numNeighbors++;
        }
    }
    String[] neighbors = new String[numNeighbors];
    int currentIndex = 0;
    for (int i = 0; i < capacity; i++) {
        if (edgeMatrix[index][i]) {
            neighbors[currentIndex] = labels[i];
            currentIndex++;
        }
    }
    return neighbors;
}

    @Override
    public String[] bfsOrder(String vertex) throws GraphException {
    int index = getIndex(vertex);
    if(!vertexExists(vertex))
        {
            throw new GraphException();
        }
    boolean[] visited = new boolean[capacity];
    Queue<Integer> queue = new LinkedList<Integer>();
    List<String> order = new ArrayList<String>();
    visited[index] = true;
    queue.add(index);
    while (!queue.isEmpty()) {
        int current = queue.poll();
        order.add(labels[current]);
        for (int i = 0; i < capacity; i++) {
            if (edgeMatrix[current][i] && !visited[i]) {
                visited[i] = true;
                queue.add(i);
            }
        }
    }
    return order.toArray(new String[0]);
}

    @Override
    public Graph bfsTree(String vertex) throws GraphException {
    int index = getIndex(vertex);
    if (!vertexExists(vertex)) {
        throw new GraphException();
    }
    boolean[] visited = new boolean[capacity];
    Queue<Integer> queue = new LinkedList<Integer>();
    StringGraph tree = new StringGraph(1);
    tree.addVertex(vertex);
    visited[index] = true;
    queue.add(index);
    while (!queue.isEmpty()) {
        int current = queue.poll();
        for (int i = 0; i < capacity; i++) {
            if (edgeMatrix[current][i] && !visited[i]) {
                visited[i] = true;
                queue.add(i);
                if (labels[current] != null && labels[i] != null) {
                    if (!tree.vertexExists(labels[current])) {
                        tree.addVertex(labels[current]);
                    }
                    if (!tree.vertexExists(labels[i])) {
                        tree.addVertex(labels[i]);
                    }
                    tree.addEdge(labels[current], labels[i]);
                }
            }
        }
    }
    return tree;
}
    @Override
    public String[] dfsOrder(String vertex) throws GraphException {
    int index = getIndex(vertex);
    if(!vertexExists(vertex))
        {
            throw new GraphException();
        }
    boolean[] visited = new boolean[capacity];
    List<String> order = new ArrayList<String>();
    dfsHelper(index, visited, order);
    return order.toArray(new String[0]);
}
private void dfsHelper(int vertexIndex, boolean[] visited, List<String> order) {
    visited[vertexIndex] = true;
    order.add(labels[vertexIndex]);
    String[] neighbors = getNeighbors(labels[vertexIndex]);
    for (String neighbor : neighbors) {
        int neighborIndex = getIndex(neighbor);
        if (!visited[neighborIndex]) {
            dfsHelper(neighborIndex, visited, order);
        }
    }
}

private void dfsTreeHelper(int v, boolean[] visited, String[] treeLabels, boolean[][] treeEdges, int parentIndex) {
    visited[v] = true;
    treeLabels[v] = labels[v];
    if (parentIndex != -1) {
        treeEdges[v][parentIndex] = true;
        treeEdges[parentIndex][v] = true;
    }
    for (int i = 0; i < capacity; i++) {
        if (edgeMatrix[v][i] && !visited[i]) {
            if (labels[i] != null) { // check for null value in labels array
                dfsTreeHelper(i, visited, treeLabels, treeEdges, v);
            }
        }
    }
}



    @Override
    public Graph dfsTree(String vertex) throws GraphException {
    int index = getIndex(vertex);
    if (!vertexExists(vertex)) {
        throw new GraphException();
    }
    boolean[] visited = new boolean[capacity];
    String[] treeLabels = new String[capacity];
    boolean[][] treeEdges = new boolean[capacity][capacity];
    dfsTreeHelper(index, visited, treeLabels, treeEdges, -1);
    // count number of vertices and edges in tree
    int numVertices = 0;
    int numEdges = 0;
    for (String label : treeLabels) {
        if (label != null) {
            numVertices++;
        }
    }
    for (int i = 0; i < capacity; i++) {
        for (int j = i + 1; j < capacity; j++) {
            if (treeEdges[i][j]) {
                numEdges++;
            }
        }
    }
    // create new StringGraph object with the correct number of vertices and edges
    Graph tree = new StringGraph(numVertices);
    for (int i = 0; i < capacity; i++) {
        if (treeLabels[i] != null) {
            tree.addVertex(treeLabels[i]);
        }
    }
    for (int i = 0; i < capacity; i++) {
        for (int j = i + 1; j < capacity; j++) {
            if (treeEdges[i][j]) {
                tree.addEdge(treeLabels[i], treeLabels[j]);
            }
        }
    }
    return tree;
}




    @Override
    public boolean isConnected() {
    // Perform a breadth-first search starting from the first vertex
    String startVertex = labels[0];
    List<String> visited = new ArrayList<>();
    Queue<String> queue = new LinkedList<>();
    queue.add(startVertex);
    visited.add(startVertex);

    while (!queue.isEmpty()) {
        String currentVertex = queue.remove();
        int currentIndex = getIndex(currentVertex);

        for (int i = 0; i < numVertices; i++) {
            if (edgeMatrix[currentIndex][i] && !visited.contains(labels[i])) {
                queue.add(labels[i]);
                visited.add(labels[i]);
            }
        }
    }

    // If all vertices were visited, the graph is connected
    return visited.size() == numVertices;
}
    @Override
    public String[] bfsSSSP(String vertex) throws GraphException 
    {
        if (!vertexExists(vertex))
        {
            throw new GraphException();
        }
        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) 
        {
            visited[i] = false;
        }
        
        int[] dist = new int[numVertices];
        for (int i = 0; i < numVertices; i++) 
        {
            dist[i] = -1;
        }
        
        String[] prev = new String[numVertices];
        for (int i = 0; i < numVertices; i++) 
        {
            prev[i] = null;
        }
        
        Queue<String> queue = new LinkedList<>();
        visited[getIndex(vertex)] = true;
        dist[getIndex(vertex)] = 0;        
        queue.add(vertex);
        
        String w;
        String[] neighbors;
        while (!queue.isEmpty())
        {
            w = queue.remove();
            neighbors = this.getNeighbors(w);
            Arrays.sort(neighbors);
            for (String u : neighbors) 
            {
                if (!visited[this.getIndex(u)]) 
                {
                    queue.add(u);
                    visited[this.getIndex(u)] = true;
                    dist[getIndex(u)] = dist[getIndex(vertex)] + 1;
                    prev[getIndex(u)] = w;
                }
            }
        }
        
        String[] prevVertices = new String[numVertices];
        for (int i = 0; i < numVertices; i++) 
        {
            if (prev[i] == null) 
            {
                prevVertices[i] = null; // for vertices that are not part of the shortest path
            } 
            else 
            {
                prevVertices[i] = prev[i];
            }
        }
        return prevVertices;
    }

    @Override
    public String[] shortestPath(String s, String t) throws GraphException
    {
        if (!vertexExists(s))
        {
            throw new GraphException();
        }
        if (!vertexExists(t))
        {
            throw new GraphException();
        }
        
        String[] prev = bfsSSSP(s);
        List<String> sp = new LinkedList();
        String current = t;
        
        while(prev[getIndex(current)] != null)
        {
            sp.add(0, current);
            current = prev[getIndex(current)];
        }
        
        if(sp.isEmpty())
        {
            String[] arr = sp.toArray(new String[0]);
            return arr;
        }
        else
        {
            sp.add(0, s);
            String[] arr = sp.toArray(new String[0]);
            return arr;
        }
    }
}