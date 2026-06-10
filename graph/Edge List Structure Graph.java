import java.util.ArrayList;
import java.util.List;

// Bir kenarı temsil eden sınıf
class Edge {
    int source;
    int destination;

    public Edge(int source, int destination) {
        this.source = source;
        this.destination = destination;
    }
}

public class EdgeListGraph {
    List<Edge> edgeList;

    public EdgeListGraph() {
        edgeList = new ArrayList<>();
    }

    // Kenar ekleme
    public void addEdge(int source, int destination) {
        edgeList.add(new Edge(source, destination));
    }

    public void printGraph() {
        System.out.println("Kenar Listesi (Edge List):");
        for (Edge edge : edgeList) {
            System.out.println(edge.source + " - " + edge.destination);
        }
    }

    public static void main(String[] args) {
        EdgeListGraph graph = new EdgeListGraph();
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.printGraph();
    }
}