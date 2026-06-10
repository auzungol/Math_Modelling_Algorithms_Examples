import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

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

    // Öncelikli kuyrukta (Priority Queue) tutulacak Düğüm sınıfı 
    static class Node {
        int vertex;   // Düğüm numarası
        int distance; // Başlangıca olan anlık uzaklık

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    // Dijkstra Algoritması Metodu
    public static void dijkstra(List<List<Edge>> graph, int source) {
        int numVertices = graph.size();
        
        // Başlangıç düğümünün diğer tüm düğümlere olan en kısa mesafesini tutacak dizi
        int[] distances = new int[numVertices];
        
        // Ziyaret edilen (kesinleşen) düğümleri takip etmek için kullanılacak dizi
        boolean[] visited = new boolean[numVertices];

        // Başlangıç değerlerini atıyoruz
        for (int i = 0; i < numVertices; i++) {
            distances[i] = Integer.MAX_VALUE; // Başlangıçta tüm mesafeler sonsuz kabul edilir
            visited[i] = false;               // Başlangıçta hiçbir düğüm ziyaret edilmedi
        }
        
        // Kaynak düğümün kendisine olan uzaklığı her zaman 0'dır
        distances[source] = 0; 

        // Kuyruktaki elemanları 'distance' (mesafe) değerine göre küçükten büyüğe sıralayacak kuralı belirliyoruz
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.distance - n2.distance);
        
        // Başlangıç düğümünü kuyruğa ekleyerek süreci başlatıyoruz
        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            // 1. Kuyruktaki en kısa mesafeli (en tepedeki) düğümü al
            Node currentNode = pq.poll();
            int u = currentNode.vertex;

            // Eğer bu düğümü daha önce ziyaret edip kesinleştirdiysek işlemi atla
            if (visited[u]) continue;
            
            // Düğümü ziyaret edildi olarak işaretle
            visited[u] = true; 

            // 2. Mevcut düğümün tüm komşularını dolaş
            List<Edge> neighbors = graph.get(u);
            for (int i = 0; i < neighbors.size(); i++) {
                Edge edge = neighbors.get(i);
                int v = edge.target;
                int weight = edge.weight;

                // 3. Gevşetme (Relaxation) Kuralı:
                // Mevcut düğüm üzerinden komşuya gitmek, komşunun o anki bilinen mesafesinden daha kısaysa:
                if (!visited[v] && distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight; // Yeni kısa mesafeyi kaydet
                    pq.add(new Node(v, distances[v]));    // Komşuyu bu yeni ve daha kısa mesafe ile kuyruğa ekle
                }
            }
        }

        // Sonuçları ekrana yazdır
        System.out.println("Dijkstra (Kaynak Düğüm: " + source + ")");
        for (int i = 0; i < numVertices; i++) {
            System.out.println("Düğüm " + i + " için en kısa mesafe: " + distances[i]);
        }
    }

    public static void main(String[] args) {
        int vertices = 5;
        List<List<Edge>> graph = new ArrayList<>();
        
        // Graf yapısını (komşuluk listesini) boş olarak başlatıyoruz
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }

        // Kenarları (Edge) grafa ekliyoruz: graph.get(kaynak).add(new Edge(hedef, ağırlık))
        graph.get(0).add(new Edge(1, 4));
        graph.get(0).add(new Edge(2, 1));
        graph.get(2).add(new Edge(1, 2));
        graph.get(1).add(new Edge(3, 1));
        graph.get(2).add(new Edge(3, 5));
        graph.get(3).add(new Edge(4, 3));

        // Algoritmayı 0 numaralı düğümden başlat
        dijkstra(graph, 0); 
    }
}
