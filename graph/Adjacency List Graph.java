import java.util.ArrayList;
import java.util.List;

public class AdjacencyListGraph {
    int numVertices;
    List<List<Integer>> adjList;

    public AdjacencyListGraph(int numVertices) {
        this.numVertices = numVertices;
        adjList = new ArrayList<>(numVertices);
        
        // Her düğüm için boş bir liste oluşturuyoruz
        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    // Kenar ekleme (Yönsüz graf)
    public void addEdge(int source, int destination) {
        adjList.get(source).add(destination);
        adjList.get(destination).add(source);
    }

    public void printGraph() {
        System.out.println("Komşuluk Listesi (Adjacency List):");
        for (int i = 0; i < numVertices; i++) {
            System.out.print("Düğüm " + i + " komşuları: ");
            for (Integer node : adjList.get(i)) {
                System.out.print(node + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        AdjacencyListGraph graph = new AdjacencyListGraph(3);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.printGraph();
    }
}