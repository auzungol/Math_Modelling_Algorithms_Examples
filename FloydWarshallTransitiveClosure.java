// Floyd-Warshall Algoritması kullanarak Geçişli Kapanış programı
import java.util.*;

class Question1 {
    final static int V = 7; // Graftaki düğüm (vertex) sayısı

    // Floyd-Warshall algoritmasını kullanarak graph[][] matrisinin geçişli kapanışını yazdırır
    int[][] transitiveClosure(int graph[][]) {
        /* reach[][] matrisi, her düğüm çifti arasındaki en kısa mesafelerin son halini
           tutacak olan çıktı matrisidir. */
        int reach[][] = new int[V][V];
        int i, j, k;

        /* Çözüm matrisini girdi graf matrisi ile aynı şekilde başlatıyoruz. 
           (Hiçbir ara düğüm (intermediate vertex) kullanılmadan önceki başlangıç durumları) */
        for (i = 0; i < V; i++) {
            for (j = 0; j < V; j++) {
                reach[i][j] = graph[i][j];
            }
        }

        /* Tüm düğümleri tek tek ara düğüm setine ekle.
           Her iterasyonun sonunda k düğümü ara düğümler kümesine katılır. */
        for (k = 0; k < V; k++) {
            // Tüm düğümleri sırayla kaynak (source) olarak seç
            for (i = 0; i < V; i++) {
                // Yukarıda seçilen kaynak için tüm düğümleri hedef (destination) olarak seç
                for (j = 0; j < V; j++) {
                    // Eğer k düğümü, i'den j'ye giden bir yol üzerindeyse, reach[i][j] değerini 1 yap
                    reach[i][j] = (reach[i][j] != 0) ||
                            ((reach[i][k] != 0) && (reach[k][j] != 0)) ? 1 : 0;
                }
            }
        }

        // Çıktı matrisini yazdır
        printSolution(reach);
        return reach;
    }

    /* Çözümü ekrana yazdırmak için yardımcı metot */
    void printSolution(int reach[][]) {
        System.out.println("Aşağıdaki matris verilen grafın geçişli kapanışıdır:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i == j)
                    System.out.print("1 "); // Kendisine olan yollar 1 kabul edilir
                else
                    System.out.print(reach[i][j] + " ");
            }
            System.out.println();
        }
    }

    // B. Test Senaryosu (Driver Code)
    public static void main(String[] args) {
        // Sorudaki görsele karşılık gelen komşuluk matrisi (Adjacency Matrix)
        int graph[][] = new int[][]{
                {0, 1, 0, 0, 0, 0, 0}, // 0'dan 1'e
                {0, 0, 0, 0, 1, 0, 1}, // 1'den 4'e ve 6'ya
                {0, 0, 0, 0, 0, 0, 0}, // 2'den çıkış yok
                {0, 0, 0, 0, 0, 0, 0}, // 3'ten çıkış yok
                {0, 0, 1, 1, 0, 1, 0}, // 4'ten 2, 3 ve 5'e
                {0, 0, 1, 0, 0, 0, 0}, // 5'ten 2'ye
                {0, 0, 0, 0, 0, 0, 0}  // 6'dan çıkış yok
        };

        // Çözümü çalıştır
        Question1 g = new Question1();
        int[][] result = g.transitiveClosure(graph);
    }
}