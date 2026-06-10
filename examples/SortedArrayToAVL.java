// Node class for AVL tree with integer data type
class Node { //5pt
    int data; // Data stored in the node
    Node left, right; // Pointers to left and right child nodes
    int height; // Height of the node in the AVL tree

    // Constructor to initialize the node with given data
    public Node(int data) {//5pt
        this.data = data;
        this.height = 1; // Height of a new node is initialized as 1
        this.left = null;
        this.right = null;
    }
}

// AVLTree class to convert an integer array to AVL tree
public class AVLTree {
    // Method to convert a sorted integer array to an AVL tree
    public Node arrayToAVL(int[] arr, int start, int end) {//Total=50pt
        if (start > end) // Base case: if start index is greater than end index //5pt
            return null;

        // Calculate the middle index // The rest of the code if it is correct 40pt
        int mid = (start + end) / 2;

        // Create a new node with the middle element as data
        Node root = new Node(arr[mid]);

        // Recursively construct left subtree and assign it to the left child of the root
        root.left = arrayToAVL(arr, start, mid - 1);

        // Recursively construct right subtree and assign it to the right child of the root
        root.right = arrayToAVL(arr, mid + 1, end);

        // Update the height of the root
        root.height = 1 + Math.max(height(root.left), height(root.right));

        return root; // Return the root of the constructed AVL tree //5pt
    }

    // Utility method to get the height of a node
    private int height(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    // Utility method to create and print the tree in inorder traversal
    public void printInOrder(Node node) { //10pt
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.data + " ");
            printInOrder(node.right);
        }
    }


    // Main method to create the tree and test the code
    public static void main(String[] args) {//Total =10pt
        // Given example array
        int[] array = {3, 6, 7, 8, 9, 11};

        // Create an instance of AVLTree class
        AVLTree tree = new AVLTree();

        // Convert the array to AVL tree and get the root node
        Node root = tree.arrayToAVL(array, 0, array.length - 1); //5pt

        // Print the inorder traversal of the AVL tree
        System.out.println("Inorder traversal of the AVL tree:");
        tree.printInOrder(root); //5pt
    }
}

