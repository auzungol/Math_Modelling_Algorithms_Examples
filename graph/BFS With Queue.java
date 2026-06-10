import java.util.LinkedList;

/**
 * Adjacency List (Komşuluk Listesi) kullanarak BFS algoritmasının implementasyonu.
 */
public class Graph {
    private int V; // Graftaki toplam düğüm sayısı
    private LinkedList<Integer>[] adj; // Her düğümün komşularını tutan dizi (Komşuluk Listesi)

    // Grafı başlatan yapıcı metot (Constructor)
    @SuppressWarnings("unchecked")
    public Graph(int v) {
        this.V = v;
        adj = new LinkedList[v];
        
        // Her bir düğüm için boş bir bağlantılı liste oluştur
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    // Grafa yönlü kenar (edge) ekleme metodu
    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    /**
     * Verilen başlangıç düğümünden (s) itibaren Sığ Öncelikli Arama (BFS) yapar.
     */
    public void BFS(int s) {
        // Ziyaret edilen düğümleri takip etmek için boolean dizisi 
        // (Varsayılan olarak hepsi 'false' başlar)
        boolean[] visited = new boolean[V];

        // BFS'nin kalbi olan Kuyruk (Queue - FIFO) yapısı
        LinkedList<Integer> queue = new LinkedList<>();

        // Başlangıç düğümünü 'ziyaret edildi' olarak işaretle ve kuyruğa ekle
        visited[s] = true;
        queue.add(s);

        System.out.println("Breadth First Traversal (starting from vertex " + s + "):");

        // Kuyruk boşalana kadar katman katman aramaya devam et
        while (queue.size() != 0) {
            
            // Kuyruğun başındaki düğümü al (dequeue) ve ekrana yazdır
            s = queue.poll();
            System.out.print(s + " ");

            // Kuyruktan çıkarılan düğümün tüm komşularını dolaş
            for (int neighbor : adj[s]) {
                
                // Eğer komşu daha önce ziyaret edilmemişse (UNEXPLORED)
                if (!visited[neighbor]) {
                    visited[neighbor] = true; // Ziyaret edildi olarak işaretle
                    queue.add(neighbor);      // Bir sonraki katmanda işlenmek üzere kuyruğa ekle
                }
            }
        }
        System.out.println(); // İşlem bitince alt satıra geç
    }

    public static void main(String args[]) {
        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        // 2 numaralı düğümden (s=2) başlayarak BFS'yi çalıştır
        g.BFS(2);
    }
}