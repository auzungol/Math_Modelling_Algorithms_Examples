public class FloydWarshallAlgorithm {

    // Sonsuzluğu temsil etmek için büyük bir değer kullanıyoruz.
    // Integer.MAX_VALUE doğrudan KULLANILMAZ, çünkü üzerine toplama yapıldığında taşma (overflow) yapar.
    final static int INF = 99999; 

    public static void floydWarshall(int[][] graph) {
        int V = graph.length;
        
        // Orijinal graf matrisini bozmamak için mesafe matrisini kopyalıyoruz
        int[][] dist = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        // FLOYD-WARSHALL ALGORİTMASI KODUNUN KALBİ
        // 1. Döngü (k): Aracı (intermediate) düğüm
        for (int k = 0; k < V; k++) {
            // 2. Döngü (i): Başlangıç (source) düğümü
            for (int i = 0; i < V; i++) {
                // 3. Döngü (j): Hedef (destination) düğümü
                for (int j = 0; j < V; j++) {
                    
                    // Eğer i'den k'ya ve k'dan j'ye gidilebiliyorsa VE
                    // k üzerinden geçen yol, i'den j'ye olan mevcut yoldan daha kısaysa:
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j]; // Mesafeyi güncelle
                    }
                }
            }
        }

        printSolution(dist, V);
    }

    // Sonucu ekrana düzgün bir matris formatında yazdıran yardımcı metot
    public static void printSolution(int[][] dist, int V) {
        System.out.println("Floyd-Warshall: Her Düğüm Çifti Arasındaki En Kısa Mesafeler Matrisi");
        for (int i = 0; i < V; ++i) {
            for (int j = 0; j < V; ++j) {
                if (dist[i][j] == INF)
                    System.out.print(String.format("%7s", "INF"));
                else
                    System.out.print(String.format("%7d", dist[i][j]));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        /*
           Başlangıç Komşuluk Matrisi Örneği
           Düğümün kendisine uzaklığı 0'dır.
           Doğrudan kenar yoksa INF yazılır.
        */
        int graph[][] = { 
            {   0,   5, INF,  10 },
            { INF,   0,   3, INF },
            { INF, INF,   0,   1 },
            { INF, INF, INF,   0 } 
        };

        // Algoritmayı çalıştır
        floydWarshall(graph);
    }
}