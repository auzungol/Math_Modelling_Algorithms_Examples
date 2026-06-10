import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Kruskal {

    // Kenar (Edge) yapısını tanımlar
    static class Edge {
        int src, dest, weight;
        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    // Alt küme (Subset) eleman yapısını tanımlar (Union-Find algoritması için)
    static class Subset {
        int parent, rank;
        public Subset(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }

    // Minimum Kapsayan Ağacı (MST) bulan fonksiyon
    private static void kruskals(int V, List<Edge> edges) {
        int j = 0;
        int noOfEdges = 0;

        // V adet alt küme oluşturmak için bellek tahsisi
        Subset subsets[] = new Subset[V];

        // Sonuçları tutmak için bellek tahsisi
        Edge results[] = new Edge[V];

        // Tek elemanlı V adet alt küme oluşturulur
        for (int i = 0; i < V; i++) {
            subsets[i] = new Subset(i, 0);
        }

        // Alınacak kenar sayısı V-1 olana kadar devam et
        while (noOfEdges < V - 1) {
            // En küçük ağırlıklı kenarı seç. Sonraki iterasyon için indeksi artır.
            Edge nextEdge = edges.get(j);
            int x = findRoot(subsets, nextEdge.src);
            int y = findRoot(subsets, nextEdge.dest);

            // Eğer bu kenarın dahil edilmesi bir döngü (cycle) oluşturmuyorsa,
            // sonuca dahil et ve sonraki kenar için sonuç indeksini artır.
            if (x != y) {
                results[noOfEdges] = nextEdge;
                union(subsets, x, y);
                noOfEdges++;
            }
            j++;
        }

        // Oluşturulan MST'yi sergilemek için result[] içeriğini yazdır
        System.out.println("Oluşturulan MST'nin kenarları aşağıdaki gibidir:");
        int minCost = 0;
        for (int i = 0; i < noOfEdges; i++) {
            System.out.println(results[i].src + " -- " + results[i].dest + " == " + results[i].weight);
            minCost += results[i].weight;
        }
        System.out.println("MST Toplam Maliyeti (Cost): " + minCost);
    }

    // Ayrık (disjoint) iki kümeyi birleştiren fonksiyon (Union by Rank)
    private static void union(Subset[] subsets, int x, int y) {
        int rootX = findRoot(subsets, x);
        int rootY = findRoot(subsets, y);

        // Rank'ı düşük olan ağaç, rank'ı yüksek olan ağacın altına eklenir
        if (subsets[rootY].rank < subsets[rootX].rank) {
            subsets[rootY].parent = rootX;
        } else if (subsets[rootX].rank < subsets[rootY].rank) {
            subsets[rootX].parent = rootY;
        } else {
            // Rank'lar eşitse birini kök yap ve rank'ını bir artır
            subsets[rootY].parent = rootX;
            subsets[rootX].rank++;
        }
    }

    // Bir kümenin kökünü (parent) bulan fonksiyon (Path Compression ile)
    private static int findRoot(Subset[] subsets, int i) {
        if (subsets[i].parent == i)
            return subsets[i].parent;

        subsets[i].parent = findRoot(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    // B. Test Senaryosu (Main metodu)
    public static void main(String[] args) {
        int V = 5; // Düğüm sayısı
        List<Edge> graphEdges = new ArrayList<Edge>(
                List.of(
                        new Edge(0, 1, 2),
                        new Edge(0, 3, 6),
                        new Edge(1, 2, 3),
                        new Edge(1, 3, 8),
                        new Edge(1, 4, 5),
                        new Edge(2, 4, 7),
                        new Edge(3, 4, 9)
                )
        );

        // Kenarları ağırlıklarına göre artan (non-decreasing) sırada sırala
        graphEdges.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });

        // MST'yi bulmak için metodu çağır
        kruskals(V, graphEdges);
    }
}