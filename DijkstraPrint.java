import java.util.*; // Importing utility tools for data handling

// 1. Create a class for weighed undirected Graph
public class WeightedGraph { // Defining the main graph class //10pts

    private int nov; // Variable to hold the total number of vertices
    private int[][] matrix; // 2D array to serve as the adjacency matrix
    private final int INF = Integer.MAX_VALUE; // Value to represent infinity/no direct path

    // 2. Create a constructor for the Graph class //10pts
    public WeightedGraph(int nov) { // Constructor accepting number of vertices
        this.nov = nov; // Assigning the vertex count to the class member
        this.matrix = new int[nov][nov]; // Initializing the matrix of size nov x nov
        for (int i = 0; i < nov; i++) { // Loop through each row of the matrix
            for (int j = 0; j < nov; j++) { // Loop through each column of the matrix
                if (i == j) { // Checking if the row index equals the column index
                    matrix[i][j] = 0; // The weight to travel from a node to itself is 0
                } else { // For all other combinations of nodes
                    matrix[i][j] = INF; // Set weight to infinity until an edge is added
                } // End of conditional block
            } // End of inner column loop
        } // End of outer row loop
    } // End of the constructor method

    // 3. Write addEdge method to insert an edge // 5pts
    public void addEdge(int source, int dest, int w) { // Method taking source, destination, and weight
        matrix[source][dest] = w; // Set the weight in the matrix from source to destination
        matrix[dest][source] = w; // Set the same weight from destination to source for undirected graph
    } // End of addEdge method

    // 4. Write a method to implement Dijkstra’s algorithm //total=20+10pts (20 pts for dijstra itself. 10 pts for display table )
    public void runDijkstra(int source) { // Method to find shortest paths from a starting node
        int[] dist = new int[nov]; // Array to keep track of the shortest distances found
        boolean[] visited = new boolean[nov]; // Array to mark nodes once they are fully processed

        for (int i = 0; i < nov; i++) { // Loop to set up the algorithm's starting state
            dist[i] = INF; // Initially assume all nodes are unreachable (infinity)
            visited[i] = false; // Mark all nodes as not yet visited
        } // End of setup loop

        dist[source] = 0; // Distance from the starting node to itself is always zero

        for (int count = 0; count < nov - 1; count++) { // Loop to visit all nodes except the last
            int u = findMinVertex(dist, visited); // Identify the unvisited node with the smallest distance
            visited[u] = true; // Mark this node as visited so it isn't processed again

            for (int v = 0; v < nov; v++) { // Loop to check all possible neighbor nodes
                // If v is unvisited, has a connection, and current path to u is valid
                if (!visited[v] && matrix[u][v] != INF && dist[u] != INF) {
                    if (dist[u] + matrix[u][v] < dist[v]) { // If path through u is shorter than current path
                        dist[v] = dist[u] + matrix[u][v]; // Update the shortest distance to node v
                    } // End of distance update check
                } // End of connection validation check
            } // End of neighbor loop
        } // End of main algorithm loop

        displayTable(source, dist); // Call helper method to show the results in a table
    } // End of runDijkstra method

    // Helper method to find the closest unvisited vertex
    private int findMinVertex(int[] dist, boolean[] visited) { // Method to pick the next node
        int minVal = INF; // Start with infinity as the baseline
        int minIdx = -1; // Placeholder for the index of the closest node
        for (int i = 0; i < nov; i++) { // Scan through all vertices
            if (!visited[i] && dist[i] <= minVal) { // If node is unvisited and has the lowest distance
                minVal = dist[i]; // Update the minimum value tracker
                minIdx = i; // Record the index of this node
            } // End of selection condition
        } // End of search loop
        return minIdx; // Return the index of the chosen node
    } // End of findMinVertex method

    // Helper method to print the final results //10pts display table
    private void displayTable(int src, int[] dist) { // Method to print distance results
        char[] labels = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm'}; // Map for labels
        System.out.println("Path Calculation Table (Source Node: " + labels[src] + ")"); // Print header
        System.out.println("Node Label\tMinimum Distance"); // Print column labels
        for (int i = 0; i < nov; i++) { // Loop through each calculated distance
            System.out.println(labels[i] + "\t\t" + dist[i]); // Output the label and its shortest path distance
        } // End of output loop
    } // End of displayTable method

    // 5. In main, test the method for vertex 'a' (index 0) //total: 15pts
    public static void main(String[] args) { // Entry point of the Java program
        WeightedGraph graph = new WeightedGraph(12); // Creating a graph for 12 nodes (a-m)  //10pts

        // Mapping: a=0, b=1, c=2, d=3, e=4, f=5, g=6, h=7, i=8, j=9, k=10, m=11
        graph.addEdge(0, 1, 3); // Connection a-b (3)
        graph.addEdge(0, 4, 2); // Connection a-e (2)
        graph.addEdge(1, 2, 5); // Connection b-c (5)
        graph.addEdge(1, 4, 4); // Connection b-e (4)
        graph.addEdge(2, 3, 7); // Connection c-d (7)
        graph.addEdge(2, 4, 7); // Connection c-e (7)
        graph.addEdge(2, 6, 6); // Connection c-g (6)
        graph.addEdge(3, 6, 8); // Connection d-g (8)
        graph.addEdge(3, 7, 3); // Connection d-h (3)
        graph.addEdge(4, 5, 2); // Connection e-f (2)
        graph.addEdge(4, 8, 6); // Connection e-i (6)
        graph.addEdge(5, 6, 3); // Connection f-g (3)
        graph.addEdge(5, 8, 8); // Connection f-i (8)
        graph.addEdge(5, 9, 4); // Connection f-j (4)
        graph.addEdge(5, 10, 6); // Connection f-k (6)
        graph.addEdge(6, 7, 1); // Connection g-h (1)
        graph.addEdge(6, 10, 9); // Connection g-k (9)
        graph.addEdge(6, 11, 10); // Connection g-m (10)
        graph.addEdge(7, 11, 5); // Connection h-m (5)
        graph.addEdge(8, 9, 7); // Connection i-j (7)
        graph.addEdge(9, 10, 1); // Connection j-k (1)
        graph.addEdge(10, 11, 4); // Connection k-m (4)

        graph.runDijkstra(0); // Executing the shortest path search starting from 'a' //5pts
    } // End of main method
} // End of WeightedGraph class

