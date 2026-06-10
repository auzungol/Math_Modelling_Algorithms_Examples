import java.util.LinkedList;
import java.util.Queue;

// Class to represent a graph using adjacency list
class Graph { //5pt
    int vertices;
    LinkedList<Integer>[] adjList;

    Graph(int vertices) //10pt
    {
        this.vertices = vertices;
        adjList = new LinkedList[vertices];
        for (int i = 0; i < vertices; ++i)
            adjList[i] = new LinkedList<>();
    }

    // Function to add an edge to the graph
    void addEdge(int u, int v) { adjList[u].add(v); } //15pt

    // Function to perform Breadth First Search on a graph
    // represented using adjacency list
    void bfs(int startNode) //30pt
    {
        // Create a queue for BFS
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[vertices];

        // Mark the current node as visited and enqueue it
        visited[startNode] = true;
        queue.add(startNode);

        // Iterate over the queue
        while (!queue.isEmpty()) {
            // Dequeue a vertex from queue and print it
            int currentNode = queue.poll();
            System.out.print(currentNode + " ");

            // Get all adjacent vertices of the dequeued
            // vertex currentNode If an adjacent has not
            // been visited, then mark it visited and
            // enqueue it
            for (int neighbor : adjList[currentNode]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }
}

        // Driver program to test methods of graph class //10pt
        public static void main(String[] args) {
            //create the graph with 7 vertices
            Graph g = new Graph(7);
            g.addEdge(0, 1);
            g.addEdge(1, 6);
            g.addEdge(1, 4);
            g.addEdge(4, 5);
            g.addEdge(5, 2);
            g.addEdge(4, 3);
            g.addEdge(4, 2);
            System.out.println("Following is the Breadth First Traversal");
            g.bfs(0);
}
 //5pt
