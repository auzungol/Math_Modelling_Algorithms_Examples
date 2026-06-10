import java.util.*;

// 1. Ağırlıklı yönsüz Graf için bir sınıf oluşturun //10puan
class Graph {
    int nov; // Graftaki düğüm (vertex) sayısını tutan değişken
    int[][] adjMatrix; // Ağırlıklar için komşuluk matrisi görevi görecek 2 boyutlu dizi
    static final int INF = Integer.MAX_VALUE; // Sonsuzluğu temsil etmesi için bir sabit tanımla

    // 2. Graph sınıfı için bir yapıcı metot (constructor) oluşturun //10puan
    public Graph(int nov) {
        this.nov = nov; // nov parametresini sınıf değişkenine ata
        adjMatrix = new int[nov][nov]; // Matrisi nov x nov boyutlarında başlat
        for (int i = 0; i < nov; i++) { // Matrisin her bir satırında döngüye gir
            for (int j = 0; j < nov; j++) { // Matrisin her bir sütununda döngüye gir
                if (i == j) { // Kaynak ve hedefin aynı olup olmadığını kontrol et
                    adjMatrix[i][j] = 0; // Düğümün kendisine uzaklığı her zaman 0'dır
                } else { // Farklı düğümler için
                    adjMatrix[i][j] = INF; // Mesafeyi sonsuz olarak başlat (henüz kenar yok)
                }
            }
        }
    }

    /*
       AÇIKLAMA (Bölüm 1 & 2):
       Graph sınıfı, bağlantıları temsil etmek için bir komşuluk matrisi (adjacency matrix) kullanır.
       Yapıcı metot, her düğümün 0 olan kendisine bağlantısı hariç, 
       diğerlerinden tamamen kopuk (INF - Sonsuz) olarak başlamasını sağlar.
    */

    // 3. Bir kenar eklemek için addEdge metodunu yazın //5puan
    public void addEdge(int source, int dest, int w) {
        adjMatrix[source][dest] = w; // Kaynaktan hedefe ağırlığı ayarla
        adjMatrix[dest][source] = w; // Hedeften kaynağa ağırlığı ayarla (yönsüz graf)
    }

    /*
       AÇIKLAMA (Bölüm 3):
       Bu yönsüz bir graf olduğu için, 'a'dan 'b'ye giden bir kenar, 'b'den 'a'ya gidenle aynıdır.
       Bu metot, matristeki her iki simetrik konumu verilen ağırlıkla günceller.
    */

    // 4. Prim algoritmasını uygulayan bir metot yazın //prim algoritması 20 + yazdırma 10puan
    public void primMST(int startVertex) {
        int[] parent = new int[nov]; // Oluşturulan MST'nin ebeveyn (parent) işaretçilerini tutacak dizi
        int[] key = new int[nov]; // Kesimde (cut) minimum ağırlıklı kenarı seçmek için kullanılan değerler
        boolean[] mstSet = new boolean[nov]; // MST'ye dahil edilen düğümler kümesini temsil etmek için

        for (int i = 0; i < nov; i++) { // Tüm anahtarları (key) SONSUZ olarak başlat
            key[i] = INF; // Anahtarı sonsuza ayarla
            mstSet[i] = false; // MST'ye dahil edilme durumunu false (yanlış) yap
        }

        key[startVertex] = 0; // Bu düğümün ilk olarak seçilmesi için anahtarını 0 yap
        parent[startVertex] = -1; // İlk düğüm her zaman MST'nin köküdür (ebeveyni yoktur)

        for (int count = 0; count < nov - 1; count++) { // nov-1 adet kenar bulmak için döngüye gir
            int u = minKey(key, mstSet); // Henüz dahil edilmemiş düğümler kümesinden minimum anahtara sahip düğümü seç
            mstSet[u] = true; // Seçilen düğümü MST kümesine ekle

            for (int v = 0; v < nov; v++) { // Komşu düğümlerin anahtar değerini ve ebeveyn indeksini güncelle
                if (adjMatrix[u][v] != INF && !mstSet[v] && adjMatrix[u][v] < key[v]) { // Bağlantıyı ve ağırlığı kontrol et
                    parent[v] = u; // v'nin ebeveynini güncelle
                    key[v] = adjMatrix[u][v]; // v'nin anahtar (maliyet) değerini güncelle
                }
            }
        }
        printMST(parent, key); // Sonuç tablosunu yazdır
    }

    // Minimum anahtar değerine sahip düğümü bulan yardımcı metot
    private int minKey(int[] key, boolean[] mstSet) {
        int min = INF, min_index = -1; // Minimum değerleri başlat
        for (int v = 0; v < nov; v++) { // Tüm düğümleri tara
            if (!mstSet[v] && key[v] < min) { // Eğer MST'de değilse ve anahtar mevcut minimumdan küçükse
                min = key[v]; // Minimumu güncelle
                min_index = v; // İndeksi güncelle
            }
        }
        return min_index; // Minimum anahtarlı düğümün indeksini döndür
    }

    // Sonuç tablosunu yazdırmak için metot //10puan
    private void printMST(int[] parent, int[] key) {
        System.out.println("Düğüm\tMesafe\tEbeveyn"); // Tablo başlığı
        for (int i = 0; i < nov; i++) { // Tüm düğümlerde döngüye gir
            char vertexChar = (char) ('a' + i); // İndeksi karaktere geri dönüştür ('a', 'b', vb.)
            String p = (parent[i] == -1) ? "Yok" : String.valueOf((char) ('a' + parent[i])); // Kök ebeveyn durumunu yönet
            System.out.println(vertexChar + "\t" + key[i] + "\t\t" + p); // Düğüm veri satırını yazdır
        }
    }

    /*
       AÇIKLAMA (Bölüm 4):
       Prim algoritması, Minimum Kapsayan Ağacı (MST) bir düğümden başlayarak ve ağaçtaki
       bir düğümü ağaç dışındaki bir düğüme bağlayan en ucuz kenarı açgözlü (greedy) bir 
       şekilde ekleyerek inşa eder. Her bir düğüme ulaşmak için gereken minimum ağırlığı 
       takip etmek için 'key' dizisini, yapıyı saklamak için ise 'parent' dizisini kullanırız.
    */
}

public class Main {
    public static void main(String[] args) {
        // 5. Verilen grafikteki örnek için metodu test edin
        // Eşleştirme: a=0, b=1, c=2, d=3, e=4, f=5, g=6, h=7, i=8, j=9, k=10, m=11
        Graph g = new Graph(12); // 12 düğümlü (l'yi atlayarak a'dan m'ye) bir graf oluştur //10puan

        // Grafiğe göre kenarları ekleme
        g.addEdge(0, 1, 3);  // a-b
        g.addEdge(0, 4, 2);  // a-e
        g.addEdge(1, 2, 5);  // b-c
        g.addEdge(1, 4, 4);  // b-e
        g.addEdge(2, 3, 7);  // c-d
        g.addEdge(2, 4, 7);  // c-e
        g.addEdge(2, 6, 6);  // c-g
        g.addEdge(3, 6, 8);  // d-g
        g.addEdge(3, 7, 3);  // d-h
        g.addEdge(4, 5, 2);  // e-f
        g.addEdge(4, 8, 6);  // e-i
        g.addEdge(5, 6, 3);  // f-g
        g.addEdge(5, 9, 4);  // f-j
        g.addEdge(5, 10, 6); // f-k
        g.addEdge(6, 7, 1);  // g-h
        g.addEdge(6, 10, 9); // g-k
        g.addEdge(6, 11, 10);// g-m
        g.addEdge(7, 11, 5); // h-m
        g.addEdge(8, 9, 7);  // i-j
        g.addEdge(9, 10, 1); // j-k
        g.addEdge(10, 11, 4);// k-m

        g.primMST(0); // Düğüm 'a'dan (indeks 0) başlayarak Prim algoritmasını test et //5puan
    }
}