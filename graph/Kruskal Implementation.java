import java.util.*;

public class KruskalAlgorithm {

    // Kruskal doğrudan kenarlarla (Edge List) çalışır
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        // Kenarları ağırlıklarına göre sıralamak için
        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    // Döngü (cycle) tespiti için Disjoint Set (Union-Find) yapısı
    static class DisjointSet {
        int[] parent, rank;

        public DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            // Başlangıçta herkes kendi kendinin ebeveynidir (hepsi ayrı birer ağaçtır)
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        // Bir düğümün hangi kümeye ait olduğunu (kümenin kökünü) bulur (Path Compression)
        public int find(int i) {
            if (parent[i] == i) {
                return i;
            }
            return parent[i] = find(parent[i]); 
        }

        // İki farklı kümeyi birleştirir (Union by Rank)
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
        // 1. ADIM: Tüm kenarları ağırlıklarına göre küçükten büyüğe sırala
        Collections.sort(edges);

        DisjointSet ds = new DisjointSet(V);
        List<Edge> mst = new ArrayList<>(); // Seçilen kenarları tutacak
        int totalMinWeight = 0;

        // 2. ADIM: Sıralanmış kenarları tek tek incele
        for (Edge edge : edges) {
            // Eğer V - 1 kadar kenar bulduysak ağaç tamamlanmıştır
            if (mst.size() == V - 1) break;

            int rootSrc = ds.find(edge.src);
            int rootDest = ds.find(edge.dest);

            // 3. ADIM: Eğer bu iki düğüm farklı kümelerdeyse (döngü oluşturmuyorsa)
            if (rootSrc != rootDest) {
                mst.add(edge); // Kenarı MST'ye ekle
                totalMinWeight += edge.weight;
                ds.union(rootSrc, rootDest); // Kümeleri birleştir (düğümleri bağla)
            }
            // Aynı kümedelerse döngü olur, kenarı görmezden gel.
        }

        // Sonuçları yazdır
        System.out.println("Kruskal Algoritması MST Kenarları:");
        for (Edge edge : mst) {
            System.out.println("Düğüm " + edge.src + " - Düğüm " + edge.dest + " (Ağırlık: " + edge.weight + ")");
        }
        System.out.println("Toplam Minimum Maliyet: " + totalMinWeight);
    }

    public static void main(String[] args) {
        int V = 5;
        List<Edge> edges = new ArrayList<>();

        // Kenar Listesi yapısı
        edges.add(new Edge(0, 1, 2));
        edges.add(new Edge(0, 3, 6));
        edges.add(new Edge(1, 2, 3));
        edges.add(new Edge(1, 3, 8));
        edges.add(new Edge(1, 4, 5));
        edges.add(new Edge(2, 4, 7));
        edges.add(new Edge(3, 4, 9));

        kruskalMST(V, edges);
    }
}