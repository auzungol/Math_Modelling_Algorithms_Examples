// a. Node sınıfının ve yapıcısının uygulanması [cite: 722]
class Node {
    int key, height;
    Node left, right;

    Node(int d) {
        key = d;
        height = 1;
    }
}

// b. AVL sınıfının ve yapıcısının uygulanması [cite: 724]
class AVLTree {
    Node root;

    public AVLTree() {
        root = null;
    }

    // c. Node'un yüksekliğini döndüren height metodu [cite: 725]
    int height(Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    // Maksimum değeri bulmak için yardımcı metot
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // Sağa döndürme (Right Rotate) işlemi 
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Sola döndürme (Left Rotate) işlemi 
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Denge faktörünü (Balance Factor) getiren yardımcı metot
    int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    // d. Ağacın dengeli olup olmadığını kontrol eden Boolean isBalanced metodu [cite: 726]
    public Boolean isBalanced(Node root) {
        if (root == null) {
            return true;
        }

        int balance = getBalance(root);

        // Denge faktörü -1, 0 veya 1 olmalı ve alt ağaçlar da dengeli olmalıdır
        if (Math.abs(balance) <= 1 && isBalanced(root.left) && isBalanced(root.right)) {
            return true;
        }
        return false;
    }

    // e. Yeni bir eleman ekleyen ve gerekirse ağacı yeniden yapılandıran insert metodu [cite: 727, 728]
    Node insert(Node node, int element) {
        if (node == null)
            return (new Node(element));

        if (element < node.key)
            node.left = insert(node.left, element);
        else if (element > node.key)
            node.right = insert(node.right, element);
        else
            return node; // AVL ağaçlarında eşit değerlere izin verilmez

        node.height = 1 + max(height(node.left), height(node.right));
        int balance = getBalance(node);

        // Ağaç dengesizleşirse 4 farklı rotasyon durumu kontrol edilir
        if (balance > 1 && element < node.left.key)
            return rightRotate(node);

        if (balance < -1 && element > node.right.key)
            return leftRotate(node);

        if (balance > 1 && element > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && element < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Kullanım kolaylığı için aşırı yüklenmiş (overloaded) insert metodu
    public void insert(int element) {
        root = insert(root, element);
    }

    // Minimum değere sahip düğümü bulan yardımcı metot
    Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    // f. Bir elemanı silen ve gerekirse ağacı yeniden yapılandıran delete metodu [cite: 729, 730]
    Node deleteNode(Node root, int element) {
        if (root == null)
            return root;

        if (element < root.key)
            root.left = deleteNode(root.left, element);
        else if (element > root.key)
            root.right = deleteNode(root.right, element);
        else {
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                Node temp = minValueNode(root.right);
                root.key = temp.key;
                root.right = deleteNode(root.right, temp.key);
            }
        }

        if (root == null)
            return root;

        root.height = max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    // Kullanım kolaylığı için aşırı yüklenmiş delete metodu
    public void delete(int element) {
        root = deleteNode(root, element);
    }

    // Ağacı In-Order yazdırmak için yardımcı metot
    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    // g. Test Senaryosu (Main metodu) [cite: 731]
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // Verilen ağaçtaki değerleri ekliyoruz [cite: 733, 734, 735, 736, 737, 738, 739]
        int[] elements = {8, 3, 10, 1, 6, 14, 4, 7, 13};
        for (int el : elements) {
            tree.insert(el);
        }

        System.out.println("Ağacın Preorder geçişi:");
        tree.preOrder(tree.root);

        System.out.println("\nAğaç dengeli mi? " + tree.isBalanced(tree.root));

        System.out.println("\n13 değeri siliniyor...");
        tree.delete(13);
        System.out.println("13 silindikten sonra Preorder geçişi:");
        tree.preOrder(tree.root);
        System.out.println("\nAğaç dengeli mi? " + tree.isBalanced(tree.root));
    }
}