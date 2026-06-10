public class BSTLowestCommonAncestor {

    // Ağacın yapıtaşı olan Düğüm sınıfı
    static class Node {
        int val;
        Node left, right;

        public Node(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    // ==========================================
    // YÖNTEM 1: DÖNGÜSEL (ITERATIVE) ÇÖZÜM
    // Bellek Karmaşıklığı: O(1) - Çok Verimli
    // ==========================================
    public static Node findLCAIterative(Node root, Node p, Node q) {
        Node current = root; // Kökten aramaya başla

        while (current != null) {
            // Eğer her iki değer de mevcut düğümden büyükse, SAĞA git
            if (p.val > current.val && q.val > current.val) {
                current = current.right;
            }
            // Eğer her iki değer de mevcut düğümden küçükse, SOLA git
            else if (p.val < current.val && q.val < current.val) {
                current = current.left;
            }
            // Yol ayrımı! Biri sağa biri sola düşüyor (veya birine eşit)
            // Bu durumda mevcut düğüm En Düşük Ortak Atadır (LCA).
            else {
                return current;
            }
        }
        return null; // Ağaç boşsa null döner
    }

    // ==========================================
    // YÖNTEM 2: ÖZYİNELEMELİ (RECURSIVE) ÇÖZÜM
    // Yazması daha kısa ama Call Stack kullandığı için bellekte O(H) yer kaplar
    // ==========================================
    public static Node findLCARecursive(Node root, Node p, Node q) {
        if (root == null) return null;

        // Her ikisi de büyükse sağ tarafa dal
        if (p.val > root.val && q.val > root.val) {
            return findLCARecursive(root.right, p, q);
        }
        // Her ikisi de küçükse sol tarafa dal
        else if (p.val < root.val && q.val < root.val) {
            return findLCARecursive(root.left, p, q);
        }
        // Yol ayrımındayız, cevabı bulduk
        else {
            return root;
        }
    }

    // ==========================================
    // TEST METODU
    // ==========================================
    public static void main(String[] args) {
        /*
                  Ağacımız:
                     20
                   /    \
                  8      22
                /   \
               4    12
                   /  \
                  10  14
        */
        Node root = new Node(20);
        root.left = new Node(8);
        root.right = new Node(22);
        root.left.left = new Node(4);
        root.left.right = new Node(12);
        root.left.right.left = new Node(10);
        root.left.right.right = new Node(14);

        Node p = root.left.left;             // Düğüm 4
        Node q = root.left.right.right;      // Düğüm 14

        Node lca = findLCAIterative(root, p, q);
        
        System.out.println("Düğüm " + p.val + " ve Düğüm " + q.val + " için...");
        if (lca != null) {
            System.out.println("En Düşük Ortak Ata (LCA): " + lca.val); // Beklenen Çıktı: 8
        }
    }
}