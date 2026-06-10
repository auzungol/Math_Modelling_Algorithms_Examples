class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    // Bir TreeNode'u verilen bir değerle başlatan yapıcı metot [cite: 752, 753, 754, 755]
    TreeNode(int val) {
        this.val = val;
    }
}

// 1. BSTChecker sınıfının ve isBST metodunun uygulanması [cite: 758]
class BSTChecker {

    public boolean isBST(TreeNode root) {
        // Her düğümün değerinin bulunması gerektiği geçerli minimum ve maksimum aralıklarını takip ederiz.
        return isBSTUtil(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isBSTUtil(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }

        // Düğümün değeri belirlenen geçerli aralıkta değilse, bu bir BST değildir.
        if (node.val <= min || node.val >= max) {
            return false;
        }

        // Sol alt ağaç için maksimum sınırı, sağ alt ağaç için minimum sınırı güncelleriz[cite: 745].
        return isBSTUtil(node.left, min, node.val) && isBSTUtil(node.right, node.val, max);
    }

    // 3. Test durumunun oluşturulması ve kontrolü [cite: 761, 762]
    public static void main(String[] args) {
        BSTChecker checker = new BSTChecker();

        // Soruda verilen hatalı yapıdaki ağacın oluşturulması 
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(6);
        root.right = new TreeNode(9);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(12); // HATA BURADA: 12, 6'nın sağında olabilir ama 7'den küçük değildir.
        root.right.left = new TreeNode(8);
        root.right.right = new TreeNode(11);

        System.out.println("Ağaç geçerli bir BST mi? " + checker.isBST(root)); // Çıktı: false
    }
}