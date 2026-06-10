import java.util.*;

public class DijkstraAlgorithm {

    // Grafı oluşturmak için Kenar sınıfı
    static class Edge {
        int target; // Gidilecek düğüm
        int weight; // Yolun ağırlığı (maliyeti)

        public Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    // Kuyrukta tutulacak Düğüm sınıfı (Mesafeye göre sıralanabilmesi için Comparable implemente edilir)
    static class Node implements Comparable<Node> {
        int vertex;   // Düğüm numarası
        int distance; // Başlangıca olan anlık uzaklık

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance); // En kısa mesafeyi öne al
        }
    }

    // Dijkstra Algoritması Metodu
    public static void dijkstra(List<List<Edge>> graph, int source) {
        int numVertices = graph.size();
        
        // Başlangıç düğümünün diğer tüm düğümlere olan en kısa mesafesini tutacak dizi
        int[] distances = new int[numVertices];
        Arrays.fill(distances, Integer.MAX_VALUE); // Başlangıçta tüm mesafeler sonsuz
        distances[source] = 0; // Kaynağın kendisine uzaklığı 0'dır

        // Min-Heap yapısı ile her zaman en yakın düğümü önce çekeceğiz
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(source, 0));

        // Ziyaret edilen (kesinleşen) düğümleri takip etmek için
        boolean[] visited = new boolean[numVertices];

        while (!pq.isEmpty()) {
            // 1. Kuyruktaki en kısa mesafeli düğümü al
            Node currentNode = pq.poll();
            int u = currentNode.vertex;

            // Eğer bu düğümü daha önce ziyaret edip kesinleştirdiysek atla
            if (visited[u]) continue;
            visited[u] = true; // Ziyaret edildi olarak işaretle

            // 2. Mevcut düğümün tüm komşularını dolaş
            for (Edge edge : graph.get(u)) {
                int v = edge.target;
                int weight = edge.weight;

                // 3. RELAXATION (Gevşetme) Kuralı:
                // Mevcut düğüm üzerinden komşuya gitmek, komşunun bilinen mesafesinden daha kısaysa:
                if (!visited[v] && distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight; // Yeni kısa mesafeyi kaydet
                    pq.add(new Node(v, distances[v]));    // Komşuyu güncel mesafe ile kuyruğa ekle
                }
            }
        }

        // Sonuçları yazdır
        System.out.println("Dijkstra (Kaynak: " + source + ")");
        for (int i = 0; i < numVertices; i++) {
            System.out.println("Düğüm " + i + " için en kısa mesafe: " + distances[i]);
        }
    }

    public static void main(String[] args) {
        int vertices = 5;
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }

        // Kenarları ekleme: graph.get(kaynak).add(new Edge(hedef, ağırlık))
        graph.get(0).add(new Edge(1, 4));
        graph.get(0).add(new Edge(2, 1));
        graph.get(2).add(new Edge(1, 2));
        graph.get(1).add(new Edge(3, 1));
        graph.get(2).add(new Edge(3, 5));
        graph.get(3).add(new Edge(4, 3));

        // 0 numaralı düğümden başlat
        dijkstra(graph, 0); 
    }
}