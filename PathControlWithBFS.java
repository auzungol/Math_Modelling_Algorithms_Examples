// İki düğüm arasında yol olup olmadığını kontrol eden Java programı
import java.util.*;
import java.util.LinkedList;

// Bu sınıf, komşuluk listesi (adjacency list) kullanarak yönlü bir grafı temsil eder.
class Graph {
    private int V; // Düğüm (Vertex) sayısı
    private LinkedList<Integer> adj[]; // Komşuluk Listesi

    // Yapıcı (Constructor)
    Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    // Grafa bir kenar (edge) ekleyen metot
    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    // s kaynağından başlayarak BFS taraması ile hedefe (d) ulaşıp ulaşılamayacağını kontrol eder
    Boolean isReachable(int s, int d) {
        // Tüm düğümleri ziyaret edilmedi (false) olarak işaretle
        boolean visited[] = new boolean[V];

        // BFS için bir kuyruk (queue) oluştur
        LinkedList<Integer> queue = new LinkedList<Integer>();

        // Mevcut düğümü ziyaret edildi olarak işaretle ve kuyruğa ekle
        visited[s] = true;
        queue.add(s);

        Iterator<Integer> i;

        while (queue.size() != 0) {
            // Kuyruktan bir düğüm çıkar (dequeue)
            s = queue.poll();
            int n;
            i = adj[s].listIterator();

            // Çıkarılan düğümün tüm komşularını al
            // Eğer komşu ziyaret edilmemişse, ziyaret edildi işaretle ve kuyruğa ekle
            while (i.hasNext()) {
                n = i.next();

                // Eğer bu komşu düğüm hedef düğüm (d) ise, true döndür
                if (n == d)
                    return true;

                // Değilse, BFS'ye devam et
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
        // BFS hedef düğüme ulaşmadan tamamlanırsa false döndür
        return false;
    }

    // B. Test Senaryosu (Driver method)
    public static void main(String args[]) {
        // Yukarıdaki soruda verilen grafı oluştur
        Graph g = new Graph(7);
        g.addEdge(0, 1);
        g.addEdge(1, 6);
        g.addEdge(1, 4);
        g.addEdge(4, 5);
        g.addEdge(5, 2);
        g.addEdge(4, 3);
        g.addEdge(4, 2);

        // Test 1: 1'den 3'e
        int u = 1;
        int v = 3;
        if (g.isReachable(u, v))
            System.out.println("There is a path from " + u + " to " + v); // Yol bulundu
        else
            System.out.println("There is no path from " + u + " to " + v);

        // Test 2: 3'ten 1'e
        u = 3;
        v = 1;
        if (g.isReachable(u, v))
            System.out.println("There is a path from " + u + " to " + v);
        else
            System.out.println("There is no path from " + u + " to " + v); // Yol bulunamadı
    }
}