public class AdjacencyMatrixGraph {
    int numVertices;
    int[][] matrix;

    public AdjacencyMatrixGraph(int numVertices) {
        this.numVertices = numVertices;
        matrix = new int[numVertices][numVertices]; // Varsayılan olarak 0 ile dolar
    }

    // Kenar ekleme (Yönsüz graf olduğu için iki tarafa da ekliyoruz)
    public void addEdge(int i, int j) {
        matrix[i][j] = 1;
        matrix[j][i] = 1;
    }

    // Kenar silme
    public void removeEdge(int i, int j) {
        matrix[i][j] = 0;
        matrix[j][i] = 0;
    }

    public void printGraph() {
        System.out.println("Komşuluk Matrisi (Adjacency Matrix):");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(3);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.printGraph();
    }
}