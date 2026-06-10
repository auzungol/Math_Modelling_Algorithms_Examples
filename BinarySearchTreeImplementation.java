public class BinarySearchTree {

    // 1. Ağacın yapıtaşı olan Düğüm (Node) Sınıfı
    static class Node {
        int key;
        Node left, right;

        public Node(int item) {
            key = item;
            left = right = null;
        }
    }

    // Ağacın kök (root) düğümü
    Node root;

    public BinarySearchTree() {
        root = null;
    }

    // ==========================================
    // 1. ARAMA (SEARCH) İŞLEMİ
    // ==========================================
    public boolean search(int key) {
        return searchRec(root, key) != null;
    }

    // Arama için özyinelemeli (recursive) yardımcı metot
    private Node searchRec(Node root, int key) {
        // Kök null ise veya aranan değer kökteyse geri dön
        if (root == null || root.key == key)
            return root;

        // Aranan değer kökten küçükse, sol alt ağaca git
        if (key < root.key)
            return searchRec(root.left, key);

        // Aranan değer kökten büyükse, sağ alt ağaca git
        return searchRec(root.right, key);
    }

    // ==========================================
    // 2. EKLEME (INSERT) İŞLEMİ
    // ==========================================
    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node root, int key) {
        // Eğer ağaç/alt ağaç boşsa, yeni düğümü buraya yerleştir
        if (root == null) {
            root = new Node(key);
            return root;
        }

        // Ağaçta gezinerek doğru yeri bul
        if (key < root.key) {
            root.left = insertRec(root.left, key); // Sola git
        } else if (key > root.key) {
            root.right = insertRec(root.right, key); // Sağa git
        }
        
        // Eşitse hiçbir şey yapma (BST'de genelde aynı değerden iki tane olmaz)
        return root;
    }

    // ==========================================
    // 3. SİLME (DELETE) İŞLEMİ (En Karmaşık Olanı)
    // ==========================================
    public void deleteKey(int key) {
        root = deleteRec(root, key);
    }

    private Node deleteRec(Node root, int key) {
        // Ağaç boşsa geri dön
        if (root == null) return root;

        // Silinecek düğümü bulmak için ağaçta gezin
        if (key < root.key) {
            root.left = deleteRec(root.left, key);
        } else if (key > root.key) {
            root.right = deleteRec(root.right, key);
        } 
        // Silinecek düğümü bulduk!
        else {
            // DURUM 1 ve DURUM 2: Düğümün hiç çocuğu yok veya tek çocuğu var
            if (root.left == null) {
                return root.right; // Sol boşsa sağı yukarı çek
            } else if (root.right == null) {
                return root.left;  // Sağ boşsa solu yukarı çek
            }

            // DURUM 3: Düğümün 2 çocuğu var.
            // Çözüm: Sağ alt ağaçtaki en küçük değeri (In-order Successor) bul.
            root.key = minValue(root.right);

            // Bulduğumuz o en küçük değeri, sağ alt ağaçtan sil
            root.right = deleteRec(root.right, root.key);
        }
        return root;
    }

    // Verilen kökün altındaki en küçük değeri bulur (Hep sola giderek)
    private int minValue(Node root) {
        int minv = root.key;
        while (root.left != null) {
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }

    // ==========================================
    // 4. SIRALI GEZİNME (IN-ORDER TRAVERSAL)
    // ==========================================
    // BST'nin en güzel özelliği: In-Order gezinildiğinde verileri KÜÇÜKTEN BÜYÜĞE verir!
    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);         // Önce sol
            System.out.print(root.key + " "); // Sonra kendisi
            inorderRec(root.right);        // En son sağ
        }
    }

    // ==========================================
    // TEST (MAIN) METODU
    // ==========================================
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        /* Şu ağacı oluşturuyoruz:
                  50
               /     \
              30      70
             /  \    /  \
           20   40  60   80
        */
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        System.out.println("Ağacın sıralı (In-order) hali (Küçükten Büyüğe):");
        tree.inorder();

        System.out.println("\n20 Siliniyor... (Yaprak düğüm - Durum 1)");
        tree.deleteKey(20);
        tree.inorder();

        System.out.println("\n30 Siliniyor... (Tek çocuklu düğüm veya 2 çocuklu düğüm - Durum 3)");
        tree.deleteKey(30);
        tree.inorder();

        System.out.println("\n50 Siliniyor... (Kök düğüm - Durum 3)");
        tree.deleteKey(50);
        tree.inorder();
        
        System.out.println("\n60 değeri ağaçta var mı? : " + tree.search(60));
    }
}