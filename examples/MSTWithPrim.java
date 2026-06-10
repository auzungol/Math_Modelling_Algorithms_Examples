import java.util.*;

// 1. Create a class for weighed undirected Graph //10pts
class Graph {
    int nov; // Variable to store the number of vertices in the graph
    int[][] adjMatrix; // 2D array to act as the adjacency matrix for weights
    static final int INF = Integer.MAX_VALUE; // Define a constant to represent infinity

    // 2. Create a constructor for the Graph class //10pts
    public Graph(int nov) {
        this.nov = nov; // Assign the parameter nov to the class variable
        adjMatrix = new int[nov][nov]; // Initialize the matrix with size nov x nov
        for (int i = 0; i < nov; i++) { // Loop through each row of the matrix
            for (int j = 0; j < nov; j++) { // Loop through each column of the matrix
                if (i == j) { // Check if the source and destination are the same
                    adjMatrix[i][j] = 0; // Distance to self is always 0
                } else { // For different vertices
                    adjMatrix[i][j] = INF; // Initialize distance as infinity (no edge yet)
                }
            }
        }
    }

    /*
       EXPLANATION (Parts 1 & 2):
       The Graph class uses an adjacency matrix to represent connections.
       The constructor ensures that every vertex starts disconnected from others (INF),
       except for the connection to itself, which is 0.
    */

    // 3. Write addEdge method to insert an edge //5pts
    public void addEdge(int source, int dest, int w) {
        adjMatrix[source][dest] = w; // Set the weight from source to destination
        adjMatrix[dest][source] = w; // Set the weight from destination to source (undirected)
    }

    /*
       EXPLANATION (Part 3):
       Since this is an undirected graph, an edge from 'a' to 'b' is the same as 'b' to 'a'.
       The method updates both symmetric positions in the matrix with the given weight.
    */

    // 4. Write a method to implement Prim's algorithm //prim’s algorithm is 20+ printing is 10pts
    public void primMST(int startVertex) {
        int[] parent = new int[nov]; // Array to store the constructed MST parent pointers
        int[] key = new int[nov]; // Values used to pick minimum weight edge in cut
        boolean[] mstSet = new boolean[nov]; // To represent set of vertices included in MST

        for (int i = 0; i < nov; i++) { // Initialize all keys as INFINITY
            key[i] = INF; // Set key to infinity
            mstSet[i] = false; // Set MST inclusion status to false
        }

        key[startVertex] = 0; // Make key 0 so that this vertex is picked as first vertex
        parent[startVertex] = -1; // First node is always the root of MST

        for (int count = 0; count < nov - 1; count++) { // Iterate to find nov-1 edges
            int u = minKey(key, mstSet); // Pick the minimum key vertex from the set of vertices not yet included
            mstSet[u] = true; // Add the picked vertex to the MST Set

            for (int v = 0; v < nov; v++) { // Update key value and parent index of the adjacent vertices
                if (adjMatrix[u][v] != INF && !mstSet[v] && adjMatrix[u][v] < key[v]) { // Check connectivity and weight
                    parent[v] = u; // Update parent of v
                    key[v] = adjMatrix[u][v]; // Update key value of v
                }
            }
        }
        printMST(parent, key); // Print the result table
    }

    // Helper to find the vertex with minimum key value
    private int minKey(int[] key, boolean[] mstSet) {
        int min = INF, min_index = -1; // Initialize min values
        for (int v = 0; v < nov; v++) { // Search through all vertices
            if (!mstSet[v] && key[v] < min) { // If not in MST and key is smaller than current min
                min = key[v]; // Update min
                min_index = v; // Update index
            }
        }
        return min_index; // Return the index of the minimum key vertex
    }

    // Method to print the resulting table //10pts
    private void printMST(int[] parent, int[] key) {
        System.out.println("Vertex\tDistance\tParent"); // Header for the table
        for (int i = 0; i < nov; i++) { // Loop through all vertices
            char vertexChar = (char) ('a' + i); // Convert index back to character ('a', 'b', etc.)
            String p = (parent[i] == -1) ? "None" : String.valueOf((char) ('a' + parent[i])); // Handle root parent
            System.out.println(vertexChar + "\t" + key[i] + "\t\t" + p); // Print vertex data row
        }
    }

    /*
       EXPLANATION (Part 4):
       Prim's algorithm builds the Minimum Spanning Tree by starting from one node
       and greedily adding the cheapest edge that connects a node in the tree to a node outside it.
       We use the 'key' array to track the minimum weight to reach each node and 'parent' to store the structure.
    */
}




public class Main {
    public static void main(String[] args) {
        // 5. Test the method for the example in the given graph
        // Mapping: a=0, b=1, c=2, d=3, e=4, f=5, g=6, h=7, i=8, j=9, k=10, m=11
        Graph g = new Graph(12); // Create a graph with 12 vertices (a through m, skipping l) //10pts

        // Adding edges based on the graph
        g.addEdge(0, 1, 3);  // a-b
        g.addEdge(0, 4, 2);  // a-e
        g.addEdge(1, 2, 5);  // b-c
        g.addEdge(1, 4, 4);  // b-e
        g.addEdge(2, 3, 7);  // c-d
        g.addEdge(2, 4, 7);  // c-e
        g.addEdge(2, 6, 6);  // c-g
        g.addEdge(3, 6, 8);  // d-g
        g.addEdge(3, 7, 3);  // d-h
        g.addEdge(4, 5, 2);  // e-f
        g.addEdge(4, 8, 6);  // e-i
        g.addEdge(5, 6, 3);  // f-g
        g.addEdge(5, 9, 4);  // f-j
        g.addEdge(5, 10, 6); // f-k
        g.addEdge(6, 7, 1);  // g-h
        g.addEdge(6, 10, 9); // g-k
        g.addEdge(6, 11, 10);// g-m
        g.addEdge(7, 11, 5); // h-m
        g.addEdge(8, 9, 7);  // i-j
        g.addEdge(9, 10, 1); // j-k
        g.addEdge(10, 11, 4);// k-m

        g.primMST(0); // Test Prim's algorithm starting from vertex 'a' (index 0) //5pts
    }}
