import java.util.ArrayList;
import java.util.List;

public class KruskalAlgorithm {

    // Grafı oluşturmak için temel Kenar sınıfı (Kruskal, kenar listesi üzerinden çalışır)
    static class Edge {
        int src, dest, weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    // Döngü (cycle) tespiti için Disjoint Set (Ayrık Kümeler) yapısı
    static class DisjointSet {
        int[] parent, rank;

        public DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            
            // Başlangıçta herkes kendi kendinin ebeveynidir (hepsi ayrı ve bağımsız birer ağaçtır)
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        // Bir düğümün hangi kümeye ait olduğunu (kümenin kökünü) bulur (Yol Sıkıştırma / Path Compression)
        public int find(int i) {
            if (parent[i] == i) {
                return i;
            }
            return parent[i] = find(parent[i]); 
        }

        // İki farklı kümeyi birleştirir (Rütbeye Göre Birleştirme / Union by Rank)
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }
    }

    public static void kruskalMST(int V, List<Edge> edges) {
        // 1. ADIM: Tüm kenarları ağırlıklarına (weight) göre küçükten büyüğe sırala
        edges.sort((e1, e2) -> e1.weight - e2.weight);

        DisjointSet ds = new DisjointSet(V);
        List<Edge> mst = new ArrayList<>(); // Seçilen kesin kenarları (MST'yi) tutacak liste
        int totalMinWeight = 0;

        // 2. ADIM: Sıralanmış kenarları en ucuzdan başlayarak tek tek incele
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            
            // Eğer (V - 1) kadar kenar bulduysak ağaç tamamlanmıştır, döngüden çık
            if (mst.size() == V - 1) break;

            // Kenarın bağlamak istediği iki ucun (düğümün) hangi kümelere ait olduğunu bul
            int rootSrc = ds.find(edge.src);
            int rootDest = ds.find(edge.dest);

            // 3. ADIM: Eğer bu iki düğüm farklı kümelerdeyse (yani aralarına kenar çizersek bir döngü oluşmayacaksa)
            if (rootSrc != rootDest) {
                mst.add(edge); // Kenarı güvenle ağaca (MST'ye) ekle
                totalMinWeight += edge.weight; // Toplam maliyeti hesaba kat
                ds.union(rootSrc, rootDest); // Bu iki kümeyi birleştir (düğümleri resmi olarak birbirine bağla)
            }
            // Not: Eğer aynı kümedelerse, bu kenarı eklemek bir döngü (cycle) yaratır. O yüzden hiçbir şey yapmadan atlıyoruz.
        }

        // Sonuçları ekrana yazdır
        System.out.println("Kruskal Algoritması MST Kenarları:");
        for (int i = 0; i < mst.size(); i++) {
            Edge edge = mst.get(i);
            System.out.println("Düğüm " + edge.src + " - Düğüm " + edge.dest + " (Ağırlık: " + edge.weight + ")");
        }
        System.out.println("Toplam Minimum Maliyet: " + totalMinWeight);
    }

    public static void main(String[] args) {
        int V = 5; // Toplam Düğüm Sayısı
        List<Edge> edges = new ArrayList<>();

        // Kenar Listesini (Edge List) dolduruyoruz
        edges.add(new Edge(0, 1, 2));
        edges.add(new Edge(0, 3, 6));
        edges.add(new Edge(1, 2, 3));
        edges.add(new Edge(1, 3, 8));
        edges.add(new Edge(1, 4, 5));
        edges.add(new Edge(2, 4, 7));
        edges.add(new Edge(3, 4, 9));

        // Algoritmayı çalıştır
        kruskalMST(V, edges);
    }
}
