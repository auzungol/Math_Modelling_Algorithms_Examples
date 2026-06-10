import java.util.LinkedList;

public class DepthFirstSearch {
    private int V; // Düğüm sayısı
    private LinkedList<Integer>[] adj; // Komşuluk listesi

    // Grafı başlatan yapıcı metot
    @SuppressWarnings("unchecked")
    public DepthFirstSearch(int v) {
        this.V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    // Yönsüz kenar ekleme
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    // DFS'i başlatan ana metot
    public void dfs(int startVertex) {
        boolean[] visited = new boolean[V];
        System.out.print("DFS Çıktısı (Özyineleme): ");
        
        // Gerçek arama işlemini yapacak yardımcı metodu çağır
        dfsRecursive(startVertex, visited);
        
        System.out.println();
    }

    // DFS'in kalbi: Özyinelemeli (Recursive) yardımcı metot
    private void dfsRecursive(int vertex, boolean[] visited) {
        // Mevcut düğümü işaretle ve yazdır
        visited[vertex] = true;
        System.out.print(vertex + " ");

        // Mevcut düğümün tüm komşularını kontrol et
        for (int neighbor : adj[vertex]) {
            // Ziyaret edilmemiş bir komşu bulduğunda hemen onun içine dal
            if (!visited[neighbor]) {
                dfsRecursive(neighbor, visited);
            }
        }
    }

    public static void main(String[] args) {
        DepthFirstSearch graph = new DepthFirstSearch(6);

        // Örnek kenarlar (BFS ile aynı harita)
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);

        // 0 numaralı düğümden başlat
        graph.dfs(0);
    }
}